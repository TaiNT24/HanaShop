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

        <nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
            <ul class="navbar-nav">
                <li class="nav-item ">
                    <a class="navbar-brand" href="StartupServlet">Home</a>
                </li>

                <li class="nav-item ">
                    <a class="nav-link text-white" href="#">Food</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link text-white" href="#">Drink</a>
                </li>
            </ul>

            <ul class="navbar-nav" style="margin-left: 45%">

                <!--Not Login yet-->
                <c:if test="${empty sessionScope.USER_ID}">
                    <li class="nav-item  " 
                        style="text-align: center;margin-left: 20em">
                        <a href="LoginPage" class="text-white">Login</a> 
                    </li>
                </c:if>

                <!--USer-->
                <c:if test="${not empty sessionScope.USER_ID}">
                    <li class="nav-item text-white " 
                        style="text-align: center; width: 15em;"
                        >
                        <div class="dropdown">
                            <button type="button" class="btn btn-primary dropdown-toggle"  
                                    data-toggle="dropdown">
                                Welcome: Tai
                            </button>

                            <div class="dropdown-menu" >
                                <p class="dropdown-item" data-toggle="collapse" 
                                   style="padding: 0.5em;text-align: center"
                                   >
                                    <a href="#History">History shopping</a>
                                </p>
                                <p class="dropdown-item" data-toggle="collapse" 
                                   style="padding: 0.5em;text-align: center"
                                   >
                                    <a href="Logout">Logout</a>
                                </p>
                            </div>
                        </div>                   
                    </li>

                    <li class="nav-item" style="margin-left: 1em">
                        <a href="#Cart" class="btn btn-info">
                            Shopping Cart
                        </a>
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
