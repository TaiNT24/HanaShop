<%-- 
    Document   : HistoryShopping
    Created on : Feb 24, 2020, 11:03:55 PM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="SearchHistoryForm.jsp"/>

            <div class="main">
                <c:set var="listCart" value="${sessionScope.LIST_HISTORY_CART}"/>
                <c:set var="listItemsInCart" value="${sessionScope.LIST_ITEM_IN_CART}"/>
                <c:set var="isSearch" value="${requestScope.SEARCH}"/>

                <c:if test="${not empty requestScope.LIST_SEARCH_HISTORY_CART}">
                    <c:set var="listCart" 
                           value="${requestScope.LIST_SEARCH_HISTORY_CART}"/>
                </c:if>


                <!--search but not match-->
                <c:if test="${empty requestScope.LIST_SEARCH_HISTORY_CART}">
                    <c:if test="${not empty isSearch}">
                        <c:set var="search" value="true" />
                    </c:if>
                </c:if>

                <!--User don't shopping yet or search not match-->
                <c:if test="${empty listCart or not empty search}">
                    <c:set var="msg" value="History shopping of you is empty !!"/>
                    <c:if test="${not empty search}">
                        <c:set var="msg" value="There is no history match !!"/>
                    </c:if>
                    
                    <h2 class="text-secondary">${msg}</h2>
                </c:if>


                <!--list search match or all history-->
                <c:if test="${not empty listCart and empty msg}">
                    <c:forEach var="cart" items="${listCart}">
                        <c:forEach var="itemsInCart" items="${listItemsInCart}">
                            <c:if test="${cart.cartID==itemsInCart.key}">
                                <div class="item-shopping-cart">
                                    <div class="row">
                                        <div class="col-sm-6" style="margin:1em 2em;" >
                                            <table class="table table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>No</th>
                                                        <th>Name</th>
                                                        <th>Price</th>
                                                        <th>Quantity</th>
                                                        <th>Total</th>
                                                    </tr>
                                                </thead>
                                                <c:forEach var="item" items="${itemsInCart.value}"
                                                           varStatus="counter">
                                                    <tbody>
                                                        <tr>
                                                            <td>${counter.count}.</td>
                                                            <td>${item.foodName}</td>
                                                            <td> ${item.price}</td>
                                                            <td>${item.quantity}</td>
                                                            <td>${item.totalPrice}</td>
                                                        </tr>
                                                    </tbody>

                                                </c:forEach>
                                            </table>
                                        </div>

                                        <!--Total payment-->
                                        <div class="col-sm-4 " style="margin:1em 2em;" >
                                            <span class="text-primary text-lg-left">Total:</span>
                                            <h2 class="text-center text-danger ">
                                                ${cart.total}$
                                            </h2>
                                            <span><i>Payment method: </i></span>
                                            <p class="text-center">
                                                <b>${cart.payment}</b>
                                            </p>

                                            <p class="text-right text-secondary">
                                                ${cart.buyDate}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </c:if>


            </div>
        </div>

    </body>
</html>
