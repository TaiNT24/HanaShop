<%-- 
    Document   : NavBar
    Created on : Feb 18, 2020, 8:25:51 PM
    Author     : nguye
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nav Bar</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="library/login.css">
    </head>

    <body >
        <div class="jumbotron">
            <h1 class="display-2">Drink and Fast Food Center</h1>
        </div>

        <c:set var="Home" value="StartupServlet" />
        <c:if test="${sessionScope.ROLE==1}">
            <c:set var="Home" value="AdminStartupServlet" />
            <c:set var="display" value="block"/> 
        </c:if>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
            <ul class="navbar-nav">
                <li class="nav-item ">
                    <a class="navbar-brand" href="${Home}">Home</a>
                </li>
                <c:if test="${sessionScope.ROLE==1}">
                    <li class="nav-item" style="width: 9em;">
                        <a class="nav-link text-white" href="createNewFood">
                            Create new food
                        </a>
                    </li>
                </c:if>


            </ul>

            <!--.-->
            <ul class="navbar-nav" style="margin-left: 45%">

                <!--Not Login yet-->
                <c:if test="${empty sessionScope.USER_ID}">
                    <li class="nav-item  " 
                        style="text-align: center;margin-left: 20em">
                        <a href="LoginPage" class="text-white">Login</a> 
                    </li>
                </c:if>

                <!--User-->
                <c:if test="${sessionScope.ROLE == 0}">
                    <li class="nav-item text-white " 
                        style="text-align: center; width: 15em;"
                        >
                        <div class="dropdown">
                            <button type="button" class="btn btn-primary dropdown-toggle"  
                                    data-toggle="dropdown">
                                Welcome: ${sessionScope.USERNAME}
                            </button>

                            <div class="dropdown-menu" >
                                <c:url var="history" value="HistoryShopping">
                                    <c:param name="userID" value="${sessionScope.USER_ID}"/>
                                </c:url>
                                <p class="dropdown-item" data-toggle="collapse" 
                                   style="padding: 0.5em;text-align: center"
                                   >
                                    <a href="${history}">History shopping</a>
                                </p>
                                <p class="dropdown-item" data-toggle="collapse" 
                                   style="padding: 0.5em;text-align: center"
                                   >
                                    <a href="Logout">Logout</a>
                                </p>
                            </div>
                        </div>                   
                    </li>

                    <c:url var="yourCart" value="ShoppingCart">
                        <c:param name="userID" value="${sessionScope.USER_ID}" />
                    </c:url>
                    <li class="nav-item" style="margin-left: 1em">
                        <a href="${yourCart}" class="btn btn-info">
                            Shopping Cart
                        </a>
                    </li>

                </c:if>

                <!--Admin-->
                <c:if test="${sessionScope.ROLE == 1}">
                    <li class="nav-item" style="margin-left: 1em;width: 15em">
                        <button type="button" class="btn btn-primary">
                            Welcome: ${sessionScope.USERNAME}
                        </button>
                    </li>
                    <li class="nav-item" style="margin-left: 1em">
                        <a href="Logout" class="btn btn-info">Logout</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <br>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    </body>
</html>
