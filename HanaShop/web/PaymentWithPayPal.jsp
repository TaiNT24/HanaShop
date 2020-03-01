<%-- 
    Document   : PaymentWithPayPal
    Created on : Mar 1, 2020, 7:57:32 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script src="https://www.paypal.com/sdk/js?client-id=AXLQI5MjzuitpwtYOEcXSxE1yKW2EktK_bogWJpQt8XYUomExNBjHJN153ZvlGCSzaUeRwq644KYF0dK"></script>

        <div class="container">
            <jsp:include page="NavBar.jsp"/>

            <div id="paypal-button-container"></div>
            <c:set var="cartID" value="${requestScope.cartID}" />
            <c:set var="totalPayment" value="${requestScope.totalPayment}" />
        </div>


        <script>
            paypal.Buttons(
                    {
                        createOrder: function (data, actions) {
                            // This function sets up the details of the transaction, including the amount and line item details.
                            return actions.order.create({
                                purchase_units: [{
                                        amount: {
                                            value: '${totalPayment}'
                                        }
                                    }]
                            });
                        }
                        ,
                        onApprove: function (data, actions) {
                            return actions.order.capture().then(function () {
                                actions.redirect('http://localhost:8085/HanaShop/PaymentWithPaypal?cartID=${cartID}');
                            }
                            )
                        }
                    }
            ).render('#paypal-button-container');
        </script>
    </body>
</html>
