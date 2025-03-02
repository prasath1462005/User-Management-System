<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
        body{
            margin: 0;
            padding: 0;
            background: rgb(205, 117, 10);
        }
        .container{
            height: 100vh;
            width: 100vw;
            position: relative;
            text-align: center;
        }
        .in{
            position: relative;
            top: 30%;
            font-size: 50px;
        }
</style>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setDateHeader("Expires", System.currentTimeMillis() + 6 * 1000);
	if(session.getAttribute("gmail")==null){
		response.sendRedirect("profile.jsp");
	}
	else{
		session.invalidate();
	}
	%>
	<div class="container">
        <div class="in">Registered Sucessfully</div><br>
        <div class="in">Thank you!!!</div>
    </div>
</body>
</html>