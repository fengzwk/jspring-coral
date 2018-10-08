<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%String path = request.getContextPath(); %>
<html ng-app="springmvcApp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="third-party/angular/angular.js"></script>
	<script type="text/javascript">
		var springmvcApp = angular.module("springmvcApp",[]);
		springmvcApp.controller("loginController",function($scope,$http){
			$scope.sendContentType = function(){
				$http.post("login/sendContentType.do",{username:"zhangsan",password:"123456"},{"Content-Type":"application/json"})
					 .then(function(response){
						 cosole.log("发送完成")
					 });
			};
			
			$scope.sendAccept = function(){
				$http.get("login/sendAccept.do",{Accept:"application/json"})
					 .then(function(response){
						 console.log("发送完成")
					 });
			}
			
			$scope.sendParam = function(){
				$http.get("login/sendParam.do?myParam=myValue",{})
					 .then(function(response){
						 console.log("发送完成")
					 });
			}
			
			$scope.sendHttpEntity = function(){ //错误示例
				$http.get("login/sendHttpEntity.do",{})
					 .then(function(response){
						 console.log("发送完成")
					 });
			}
			
			$scope.getResponseBody = function(){
				$http.get("getResponseBody.do",{})
					 .then(function(response){
						 debugger;
						 console.log("发送完成")
					 });
			}
			
			$scope.getJsonView = function(){
				$http.get("getJsonView.do",{})
					 .then(function(response){
						 debugger;
						 console.log("发送完成")
					 });
			}
			
			$scope.getAsyncReqeust = function(){
				$http.get("getAsyncReqeust.do",{})
					 .then(function(response){
						 debugger;
						 console.log("发送完成")
					 });
			}
			
			$scope.testLogin = function(){
				$http.get("testLogin.do",{})
					 .then(function(response){
						 debugger;
						 console.log("发送完成")
					 });
			}
			
			$scope.getResponseBodyEmitter = function(){
				$http.get("getResponseBodyEmitter.do",{})
					 .then(function(response){
						 //debugger;
						 console.log(response.data)
						 console.log("发送完成")
					 });
			}
		});
		
		/* var source = new EventSource("getSseEmitter.do");
		source.onopen = function(event){
			console.log("sse connection is open !!")
		}
		
		source.onmessage = function(event){
			console.log("invoke message getData : "+  event.data)
			if(event.data == "end"){
				source.close();
			}
		}
		
		source.onerror = function(event){
			console.log("invoke error : "+event.data)
		} */
		
		/* var sourcesub = new EventSource("getSSERequest.do");
		sourcesub.onopen = function(event){
			console.log("sse connection is open !!")
		}
		
		sourcesub.onmessage = function(event){
			console.log("invoke message getData : "+  event.data)
			if(event.data == "end"){
				sourcesub.close();
			}
		}
		
		sourcesub.onerror = function(event){
			console.log("invoke error : "+event.data)
		} */
	</script>	
</head>
<body ng-controller="loginController">
	 <form  action="user/add.do">
	 	<input type="text" name="username" value="admin">
	 	<input type="text" name="age" value="23">
	 	<input type="text" name="address" value="tianjin">
	 	<input type="text" name="birthday" value="2018-12-12">
	 	<button type="submit">提交</button>
	 </form>
	 
	  <form  action="upload/uploadFileParam.do" method="POST" enctype="multipart/form-data">
	 	<input type="file" name="uploadFile">
	 	<button type="submit">提交</button>
	 </form>
	 
	 <br>
	 Content-Type ： <button ng-click="sendContentType()">发送请求</button><br>
	 Accept ： <button ng-click="sendAccept()">发送请求</button><br>
	 Params ： <button ng-click="sendParam()">发送请求</button><br>
	 sendHttpEntity ： <button ng-click="sendHttpEntity()">发送请求</button><br>
	 getResponseBody ： <button ng-click="getResponseBody()">发送请求</button><br>
	 getJsonView ： <button ng-click="getJsonView()">发送请求</button><br>
	 getAsyncReqeust ： <button ng-click="getAsyncReqeust()">发送请求</button><br>
	 testLogin ： <button ng-click="testLogin()">发送请求</button><br>
	 getResponseBodyEmitter ： <button ng-click="getResponseBodyEmitter()">发送请求</button><br>
</body>
</html>
