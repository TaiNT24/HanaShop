<%-- 
    Document   : CheckoutCart
    Created on : Feb 22, 2020, 3:06:48 PM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Page</title>
    </head>
    <body>
        <jsp:include page="NavBar.jsp" />

        <c:set var="listItemsInCart" value="${requestScope.LIST_ITEMS_IN_CART}"/>

        <div class="container">

            <h2>Your cart:</h2><span class="small">(so sp)</span>

            <c:if test="${not empty listItemsInCart}">
                <div class="row">
                    <div class="col-sm-8" style="border: 1px solid lavender;">
                        <!-- loop -->
                        <c:forEach var="item" items="${listItemsInCart}">
                            <div class="item-shopping-cart">
                                <div class="row">
                                    <div class="col-sm-4 " >
                                        <img src="${item.img}" class="image-custome"  />
                                    </div>

                                    <div class="col-sm-8" >
                                        <br>
                                        <h3>${item.foodName}</h3>

                                        <h5 style="float: left;">${item.price}</h5>

                                        <div style="float: right;margin-right: 3em">
                                            <h5 >${item.quantity}</h5>
                                            <h5 >${item.totalPrice}</h5>
                                        </div>

                                    </div>
                                </div>
                            </div>
                                        
                        </c:forEach>
                    </div>


                    <div class="col-sm-3 checkout-custom">
                        <div class="content-checkout">
                            <span class="text-primary text-lg-left">Total:</span>
                            <h2 class="text-center text-danger ">Price</h2>
                            <div style="margin-top: 3em;">
                                <a href="" class="btn btn-success btn-lg btn-block">Payment by CASH</a>
                                <br>
                                <a href="" class="btn btn-success btn-lg btn-block">Payment by PayPal</a>
                            </div>
                        </div>
                    </div>

                </div>
            </c:if>
        </div>

    </body>
</html>
