<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		function windowClose() {
			//opener 페이지 reload
    		window.opener.location.reload();
			window.self.close();
		}
		setTimeout(windowClose, 1000);
	</script>
</head>
<body>

</body>
</html>