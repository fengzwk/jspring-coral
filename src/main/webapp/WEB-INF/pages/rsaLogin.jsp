<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">  
    	<meta http-equiv="cache-control" content="no-cache">  
		<title>Insert title here</title>
		<script type="text/javascript" src="<%=path %>/third-party/rsa/RSA.js"></script>
		<script type="text/javascript" src="<%=path %>/third-party/rsa/BigInt.js"></script>
		<script type="text/javascript" src="<%=path %>/third-party/rsa/Barrett.js"></script>
		<script type="text/javascript" src="<%=path %>/third-party/jquery/jquery-3.3.1.min.js"></script>
	</head>
<body>
	<input type="hidden" id="modulus" value="${modulus_16}">
	<input type="hidden" id="publicExponent" value="${publicExponent_16}">
	 需要加密的数据:<input type='text' name="password" id="password" style='width:400px' value="123456" />   <input id="submitcontent" type="button" value="提交" onclick="descptcontent()" />  
    <br/>  
    <br/>  
          解密后的内容:<input readonly="readonly" id="showcontent" type="text" value="">   
</body>
	<script type="text/javascript">
		function descptcontent(){
			var modulus = $("#modulus").val();
			var publicExponent =$("#publicExponent").val();
			var thisPwd =$("#password").val();
			
			setMaxDigits(130);
			var key = new RSAKeyPair(publicExponent,publicExponent,modulus);
			var enPassword = encryptedString(key,encodeURIComponent(thisPwd)); //这个方法会将原字符串翻转顺序之后，再加密，详情可以看源码
			
			$.post({
				url : "rsaRequest.do",
				data : {password : enPassword},
				success : function(response){
					$("#showcontent").val(decryptedString(key,response.content))
				},
				error : function(response){
					console.log(response)
				}
			})
		}
	</script>
</html>