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

        <c:set var="listItemsInCart" value="${sessionScope.LIST_ITEMS_IN_CART}"/>

        <div class="container">

            <h2>Your cart:</h2>

            <c:if test="${not empty listItemsInCart}">
                <div class="row">
                    <div class="col-sm-8" style="border: 1px solid lavender;">
                        <!-- loop -->
                        <c:set var="totalPayment" value="${0}"/>

                        <c:forEach var="item" items="${listItemsInCart}">
                            <div class="item-shopping-cart">
                                
                                <form action="ShoppingCart" method="POST">
                                    <div class="row">
                                        <div class="col-sm-4 " >
                                            <img src="${item.img}" class="image-custome"  />
                                        </div>

                                        <div class="col-sm-8" >
                                            <br>
                                            <div style="float: left;" class="text-primary">
                                                <h3>${item.foodName}</h3>

                                                <h5 >Price: ${item.price}$</h5>
                                            </div>


                                            <div style="float: right;margin-right: 3em">
                                                <h5>Quantity:</h5>
                                                <h5 >
                                                    <button class="btn btn-success" id="decreItem" 
                                                            name="btnAction" value="decreItem"
                                                            type="submit"
                                                            <c:if test="${item.quantity==1}">disabled</c:if>
                                                            >-</button>

                                                    <input type="tel" 
                                                           name="quantityItem"
                                                           value="${item.quantity}" 
                                                           style="width: 2em;"/>
                                                            
                                                    <button class="btn btn-success" id="increItem" 
                                                            name="btnAction" value="increItem"
                                                            type="submit">+</button>

                                                </h5>

                                                <h5 class="text-danger">Total: ${item.totalPrice}$</h5>
                                            </div>

                                        </div>
                                    </div>
                                    <input type="hidden" name="foodID" value="${item.foodID}" />
                                    <input type="hidden" name="userID" value="${sessionScope.USER_ID}" />
                                    <input type="hidden" name="oldQuantity" value="${item.quantity}" />
                                    
                                    
                                </form>
                            </div>
                            <c:set var="totalPayment" value="${totalPayment + item.totalPrice}"/>            
                        </c:forEach>
                    </div>


                    <div class="col-sm-3 checkout-custom">
                        <div class="content-checkout">
                            <span class="text-primary text-lg-left">Total:</span>
                            <h2 class="text-center text-danger ">${Math.round(totalPayment*10)/10.0} $</h2>
                            <div style="margin-top: 3em;">
                                <a href="" class="btn btn-success btn-lg btn-block">Payment by CASH</a>
                                <br>
                                <a href="" class="btn btn-success btn-lg btn-block">Payment by PayPal</a>
                            </div>
                        </div>
                    </div>

                </div>
            </c:if>

            <c:if test="${empty listItemsInCart}">

            </c:if>
        </div>

    </body>
</html>
