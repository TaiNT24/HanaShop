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

        <meta name="google-signin-client_id" 
              content="41311026166-1cfslhs449d062qv7l862ficnju8h1ed.apps.googleusercontent.com">

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

                <!--Login with gg-->
                <br>
                <div id="my-signin2"></div>
                <br>

                <!--end  login with gg-->

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
        <script>
            function onSuccess(googleUser) {

                var profile = googleUser.getBasicProfile();
                var email = profile.getEmail();
                var name = profile.getName();
                var id = profile.getId();

//                var id_token = googleUser.getAuthResponse().id_token;

                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'http://localhost:8085/HanaShop/loginAction');
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

                var params = 'txtUserID=' + email + '&name=' + name + '&txtPassword=' + id;
                xhr.send(params);

                xhr.onload = function () {
                    window.location.replace('StartupServlet');
                };

            }
            function onFailure(error) {
                console.log(error);
            }
            function renderButton() {
                gapi.signin2.render('my-signin2', {
                    'scope': 'profile email',
                    'width': 240,
                    'height': 50,
                    'longtitle': true,
                    'theme': 'dark',
                    'onsuccess': onSuccess,
                    'onfailure': onFailure
                });
            }
        </script>

        <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
    </body>
</html>
