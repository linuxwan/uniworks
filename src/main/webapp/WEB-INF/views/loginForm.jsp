<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login Page</title>
	<link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/icon.css">
	<script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.min.js"></script>
	<script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.easyui.min.js"></script>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;		
}

body {
    font-family:verdana,helvetica,arial,sans-serif;
    padding:20px;
    font-size:12px;
    margin:0;
}
h2 {
    font-size:18px;
    font-weight:bold;
    margin:0;
    margin-bottom:15px;
}
.demo-info{
	padding:0 0 12px 0;
}
.demo-tip{
	display:none;
}
</style>

</head>
<body onload='document.loginForm.username.focus();'>
	
	<div class="easyui-panel" title="Login">
	<div id="login-box">
		<h3>Login with Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<div style="padding:10px 60px 20px 60px">
		<form name='loginForm' action="<c:out value='${contextPath}' />/loginForm" method='POST'>
			<table>
				<tr>
					<td>User ID:</td>
					<td><input class="easyui-textbox" type='text' name='username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input class="easyui-password" type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input class="easyui-linkbutton" name="submit" type="submit" value="submit" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		</div>
	</div>
	</div>
</body>
</html>