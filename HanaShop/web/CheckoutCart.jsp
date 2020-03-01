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


        <c:set var="listItemsInCart" value="${sessionScope.LIST_ITEMS_IN_CART}"/>
        <c:set var="itemOutOfStock" value="${requestScope.ITEM_OUT_OF_STOCK}"/>

        <div class="container">
            <jsp:include page="NavBar.jsp" />
            <h2>Your cart:</h2>

            <c:if test="${not empty listItemsInCart}">
                <div class="row">
                    <div class="col-sm-8" style="border: 1px solid lavender;">
                        <!-- loop -->
                        <c:set var="totalPayment" value="${0}"/>

                        <c:forEach var="item" items="${listItemsInCart}">
                            <div class="item-shopping-cart">

                                <c:if test="${not empty itemOutOfStock}">
                                    <c:forEach var="id" items="${itemOutOfStock}">
                                        <!--id is a hash Table with key is FoodID, value is quantity in stock-->
                                        <c:if test="${id.key == item.foodID}">
                                            <p style="color: red">
                                                This item have ${id.value} in stock. 
                                                Please delete it or choose again !
                                            </p>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

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
                                                <c:set var="showConfirm" value="Confirm${item.itemID}"/>

                                                <a href="#${showConfirm}" 
                                                   data-toggle="collapse"
                                                   >
                                                    Delete item
                                                </a>
                                                <div id="${showConfirm}" class="collapse"
                                                     >
                                                    <p>Delete this item ?</p>
                                                    <div style="margin-bottom: 1em">
                                                        <c:url var="delete" value="DeleteItemFromCart">
                                                            <c:param name="itemID" 
                                                                     value="${item.itemID}"/>
                                                            <c:param name="userID" 
                                                                     value="${sessionScope.USER_ID}"/>
                                                        </c:url>
                                                        <a href="${delete}" class="btn btn-danger" >
                                                            Yes
                                                        </a>
                                                        <a href="#${showConfirm}" 
                                                           data-toggle="collapse"
                                                           class="btn btn-primary" 
                                                           >
                                                            No
                                                        </a>
                                                    </div>
                                                </div>
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

                    <!--form total payment-->
                    <c:set var="totalPayment" value="${Math.round(totalPayment*10)/10.0}"/>            

                    <div class="col-sm-3 checkout-custom">
                        <div class="content-checkout">
                            <span class="text-primary text-lg-left">Total:</span>
                            <h2 class="text-center text-danger ">${totalPayment} $</h2>

                            <div style="margin-top: 3em;">
                                <c:url var="paymentByCast" value="CheckQuantityInStock">
                                    <c:param name="cartID" 
                                             value="${sessionScope.CART_ID}"/>
                                    <c:param name="paymentMethod" 
                                             value="PaymentByCast"/>
                                </c:url>

                                <a href="${paymentByCast}" 
                                   class="btn btn-success btn-lg btn-block">
                                    Payment by CASH
                                </a>
                                <br>
                                <c:url var="paymentByPaypal" value="CheckQuantityInStock">
                                    <c:param name="userID" 
                                             value="${sessionScope.USER_ID}"/>
                                    <c:param name="cartID" 
                                             value="${sessionScope.CART_ID}"/>
                                    <c:param name="totalPayment" 
                                             value="${totalPayment}"/>
                                    <c:param name="paymentMethod" 
                                             value="PaymentByPaypal"/>
                                </c:url>
                                
                                <a href="${paymentByPaypal}"
                                   class="btn btn-success btn-lg btn-block">
                                    Payment by PayPal
                                </a>

                            </div>
                        </div>
                    </div>

                </div>
            </c:if>

            <c:if test="${empty listItemsInCart}">
                <c:set var="msg" value="is Empty !!"/>
                <c:set var="style" value="text-secondary"/>

                <!--If payment success-->
                <c:if test="${not empty requestScope.PAYMENT_SUCCESSFUL}">
                    <c:set var="msg" value="Payment successful !!"/>
                    <c:set var="style" value="text-success"/>
                </c:if>

                <h3 class="${style}" style="margin-left: 5em">
                    ${msg}
                </h3>
                <br>
                <a class="btn btn-primary"
                   href="StartupServlet"
                   >
                    Continue Shopping
                </a>
            </c:if>
        </div>

    </body>
</html>
