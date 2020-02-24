<%-- 
    Document   : Home
    Created on : Feb 14, 2020, 2:51:51 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="ImportBootstrap.html" %>--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body >
        <c:set var="products" value="${requestScope.LIST_PRODUCT}"/>

        <div class="container">

            <!--search form-->
            <jsp:include page="SearchForm.jsp"/>

            <!--//Display-->
            <div class="main">
                <c:if test="${not empty products}">
                    <c:forEach var="product" items="${products}" >
                        <div class="card ">
                            <img class="card-img-top " src="${product.img}" alt="Hamburger image" style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">${product.foodName}</h4>
                                <p class="card-text">${product.description}</p>

                                <h3 class="text-primary" style="float: left">Price: ${product.price}</h3>

                                <c:url var="addToCart" value="AddToCart">
                                    <c:param name="foodID" value="${product.id}"/>
                                </c:url>
                                
                                <a class="btn btn-primary" style="float: right" 
                                   href="${addToCart}" >
                                    Add to cart
                                </a>
                                <br>
                                <br>
                                <span class="badge badge-success">Available</span>
                            </div>
                        </div>
                    </c:forEach>
                    
                </c:if>
            </div>
            
            
            <!--Paging-->
            
        </div>

        
    </body>
</html>
