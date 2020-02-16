<%-- 
    Document   : RegisterNewAccount
    Created on : Jan 7, 2020, 2:40:57 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register New Account</title>
    </head>
    <body>
        <h1>Register New Account</h1>

        <form action="registerAccountAction" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>User ID: </td>
                        <td>
                            <input type="text" name="txtUserID"  
                                   value="${param.txtUserID}"  maxlength="50"/>
                        </td>
                        <c:if test="${not empty requestScope.ErrorRegister.email}">
                            <td>
                                ${requestScope.ErrorRegister.email}
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Full Name:</td>
                        <td>
                            <input type="text" name="txtName"  
                                   value="${param.txtName}" maxlength="50"/>
                        </td>
                        <c:if test="${not empty requestScope.ErrorRegister.name}">
                            <td>
                                ${requestScope.ErrorRegister.name}
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td>
                            <input type="password" name="txtPassword" 
                                   value="" maxlength="100" />
                        </td>
                        <c:if test="${not empty requestScope.ErrorRegister.password}">
                            <td>
                                ${requestScope.ErrorRegister.password}
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Confirm Password: </td>
                        <td>
                            <input type="password" name="txtConfPassword" 
                                   value="" maxlength="100" />
                        </td>
                        <c:if test="${not empty requestScope.ErrorRegister.confPassword}">
                            <td>
                                ${requestScope.ErrorRegister.confPassword}
                            </td>
                        </c:if>
                    </tr>
                </tbody>
            </table>
            <br>

            <input type="submit" value="Register"/>

        </form>
        <br><br>

        <p>If you have account: </p>

        <a href="Login">Click here to login</a>
        <br>
        <p>Or</p>
        <br>
<!--        <a href="StartupServlet">Back home</a>-->
    </body>
</html>
