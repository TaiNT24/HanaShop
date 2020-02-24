<%-- 
    Document   : ResultUserSearch
    Created on : Feb 20, 2020, 11:02:00 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result search</title>
    </head>
    <body>
        <c:set var="products" value="${requestScope.LIST_SEARCH}"/>

        <div class="container">

            <!--search form-->
            <jsp:include page="SearchForm.jsp"/>

            <!--//Display-->
            <div class="main">
                <c:if test="${empty products}" >
                    <h2>There is no product match</h2>
                </c:if>

                <c:if test="${not empty products}">
                    <c:forEach var="product" items="${products}" >
                        <div class="card ">
                            <img class="card-img-top " src="${product.img}" alt="Hamburger image" style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">${product.foodName}</h4>
                                <p class="card-text">${product.description}</p>

                                <h3 class="text-primary" style="float: left">Price: ${product.price}</h3>

                                <form action="AddToCart">
                                    <input type="hidden" name="foodID" 
                                           value="${product.id}" />
                                    
                                    <input type="hidden" name="searchVal" 
                                           value="${param.searchVal}" />
                                    <input type="hidden" name="SearchByFilter" 
                                           value="${param.SearchByFilter}" />
                                    <input type="hidden" name="SearchByCategory" 
                                           value="${param.SearchByCategory}" />
                                    <input type="hidden" name="priceVal" 
                                           value="${param.priceVal}" />
                                    
                                    <button class="btn btn-primary"
                                            type="submit" style="float: right">
                                        Add to cart
                                    </button>
                                </form>


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
