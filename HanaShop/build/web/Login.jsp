<%-- 
    Document   : login
    Created on : Jan 7, 2020, 2:20:36 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
        <title>Login</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="NavBar.jsp" />

            <!--Login form-->
            <div class="small-form">
                <h1>Login page </h1>
                <c:if test="${not empty requestScope.RegisterSuccess}">
                    <font color="green">Register Successful !!</font>
                </c:if>

                <form action="loginAction" method="POST">
                    User ID:
                    <input class="form-control" type="text" name="txtUserID" value="${param.txtUserID}" 
                           maxlength="50" />
                    <br><br>
                    Password:
                    <input class="form-control" type="password" name="txtPassword" value="" 
                           maxlength="100"/>
                    <br><br>
                    <button type="submit" class="btn btn-primary">Login</button>

                </form>

                <c:if test="${not empty sessionScope.ERROR_LOGIN}">
                    <font color="red">Email or password wrong ! Please check again</font>
                    <c:remove var="ERROR_LOGIN" scope="session"/>
                </c:if>

                <p>If you don't have account: </p>
                <a href="RegisterNewAccount">Click here to register</a>

                <br>
                <br>
            </div>
        </div>
    </body>
</html>
