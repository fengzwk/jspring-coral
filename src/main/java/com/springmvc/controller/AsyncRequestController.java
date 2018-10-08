package com.springmvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.springmvc.common.User;

@Controller
public class AsyncRequestController {
	
	private ExecutorService executor = Executors.newFixedThreadPool(10);
	
	@GetMapping("/getAsyncReqeust.do")
	@ResponseBody
	public DeferredResult<User> getAsyncRequest(){
		System.out.println("执行的线程名： "+Thread.currentThread().getName());
		DeferredResult<User> deferredResult = new DeferredResult<>(2000L);
		 
		execute(deferredResult);
		requestCompletion(deferredResult);
		requestTimeOut(deferredResult);
		return deferredResult;
	}
	

	@GetMapping(value = "/getResponseBodyEmitter.do")
	@ResponseBody
	public ResponseBodyEmitter getResponseBodyEmitter(){	
		ResponseBodyEmitter responseBodyEmitter = new ResponseBodyEmitter();
		executor.submit(() ->{
			for(int i=0; i<5; i++){
				try {
					Thread.sleep(1000);
					User user = new User();
					user.setAddress("responseBodyEmitter");
					user.setUsername("admin");
					user.setAge(34+i);
					user.setBirthday(new Date());
					responseBodyEmitter.send(user);
				} catch (Exception e) {
					e.printStackTrace();
					responseBodyEmitter.completeWithError(new Exception("getResponseBodyEmitter Exception : "+e.getMessage()));
				}
			}
			responseBodyEmitter.complete();
		});
		return responseBodyEmitter;
	}
	
	/**
	 * 要留意响应的数据类型，也就是GetMapping的produces参数
	 * SSE传递的是文本，不是二进制数据，二进制数据需要编码再传输
	 * 二进制数据传输可以使用StreamResponseBody,
	 * @see AsyncRequestController#downFile()
	 */
	@GetMapping(value = "/getSseEmitter.do",produces={MediaType.TEXT_EVENT_STREAM_VALUE})
	public SseEmitter getSseEmitter(){
		SseEmitter sseEmitter = new SseEmitter();
		
		executor.submit(() ->{
			for(int i=0; i<5; i++){
				try {
					Thread.sleep(3000);
					User user = new User();
					user.setAddress("sseEmitter");
					user.setUsername("admin");
					user.setAge(34+i);
					user.setBirthday(new Date());
					sseEmitter.send(user);
				} catch (Exception e) {
					e.printStackTrace();
					sseEmitter.completeWithError(new Exception("getResponseBodyEmitter Exception : "+e.getMessage()));
				}
			}
			try {
				sseEmitter.send("end");
				sseEmitter.complete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		return sseEmitter;
	}
	
	/**
	 * eventSource的服务端，SSE传递的是文本，不是二进制数据，二进制数据需要编码再传输
	 */
	@GetMapping("/getSseEmitterSub.do")
	public void getSseEmitterSub(HttpServletResponse response){
		/**
		 * 响应头必须是如下格式,编码必须使用utf-8
		 */
		response.setContentType("text/event-stream");
		response.addHeader("Connection", "keep-alive");
		response.addHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("utf-8");
		
		/**
		 * 返回的数据必须是:
		 * event:事件名称
		 * data:返回消息内容
		 * retry:再次请求的时间，eventSource是一个事件轮询，会不断请求。
		 * id:事件标识符
		 *    如果服务器端返回的数据中包含了事件的标识符，浏览器会记录最近一次接收到的事件的标识符。
		 * 如果与服务器端的连接中断，当浏览器端再次进行连接时，会通过 HTTP 头“Last-Event-ID”来声明最后一次接收到的事件的标识符。
		 * 服务器端可以通过浏览器端发送的事件标识符来确定从哪个事件开始来继续连接。
		 * 注：data,event,retry,id必须紧接冒号，中间不能有空格。
		 */
		try {
			PrintWriter writer = response.getWriter();
			writer.write("event: open\n");
			
			for(int i=0; i<5; i++){
				Thread.sleep(2000);
				writer.write("event: message\n");
			    writer.write("data: " + (new Date()) + "\n\n");
			    writer.write("retry: 500000\n");
			    writer.flush(); //需要冲刷，不然客户端接收不到数据
			}
			
			Thread.sleep(500);
			writer.write("event: message\n");
			writer.write("data: end\n\n");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/getSSERequest.do")
	public ResponseEntity<StreamingResponseBody> getSSERequest(){
		StreamingResponseBody streamingResponseBody = new StreamingResponseBody() {
			@Override
			public void writeTo(OutputStream outputStream) throws IOException {
				OutputStreamWriter writer = new  OutputStreamWriter(outputStream);
				try {
					writer.write("event: open\n");
					
					for(int i=0; i<5; i++){
						Thread.sleep(2000);
						writer.write("event: message\n");
					    writer.write("data: " + (new Date()) + "\n\n");
					    writer.write("retry: 500000\n");
					    writer.flush(); //需要冲刷，不然客户端接收不到数据
					}
					
					Thread.sleep(500);
					writer.write("event: message\n");
					writer.write("data: end\n\n");
					writer.flush();
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		return ResponseEntity.ok()
				.header("Connection", "keep-alive")
				.header("Cache-Control", "no-cache")
				.contentType(MediaType.parseMediaType("text/event-stream;charset=utf-8"))
				.body(streamingResponseBody);
	}
	
	@GetMapping("/downFile.do")
	public ResponseEntity<StreamingResponseBody> downFile(){
		 StreamingResponseBody streamingResponseBody = new StreamingResponseBody() {
			@Override
			public void writeTo(OutputStream outputStream) throws IOException {
				 File file = new File("D:\\output.txt");
				 FileInputStream is = new FileInputStream(file);
				 
				 byte[] bs = new byte[1024];
				 while(true){
					 int read = is.read(bs);
					 if(read == -1){
						 break;
					 }
					 outputStream.write(bs,0,read);
				 }
				 outputStream.flush();
				 outputStream.close();
				 is.close();
			}
		};
		return ResponseEntity.ok()
					.header("Content-Disposition", "attachment;filename = output.txt")
					.body(streamingResponseBody);
	}
	
	/**
	 * 留意开启线程后的事务问题
	 * @param deferredResult                                           
	 */                                                                
	public void execute(DeferredResult<User> deferredResult){          
		executor.submit(() -> {                                        
			try {                                                      
				Thread.sleep(5000);                                    
				User user = new User();                                
				user.setAddress("tianjin");                            
				user.setUsername("admin");
				user.setAge(34);
				user.setBirthday(new Date());
				deferredResult.setResult(user);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void requestCompletion(DeferredResult<User> deferredResult){
		deferredResult.onCompletion(() ->{
			System.out.println("async request completion！！");
		});
	}
	
	public void requestTimeOut(DeferredResult<User> deferredResult){
		deferredResult.onTimeout(() ->{
			System.out.println("async request timeout！！");
			throw new RuntimeException("async request timeout！！");
		});
	}

}
