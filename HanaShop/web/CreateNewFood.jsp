<%-- 
    Document   : CreateNewFood
    Created on : Feb 26, 2020, 9:41:46 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create food</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.ROLE or sessionScope.ROLE==0}">
            <c:redirect url="StartupServlet"></c:redirect>
        </c:if>

        <div class="container">
            <jsp:include page="NavBar.jsp"/>

            <c:set var="categories" value="${applicationScope.LIST_CATEGORY}" />

            <div class="small-form">
                <h1>Create new food </h1>

                <div style="margin-left: 20%;">
                    <form  method="POST" action="CreateFoodServlet" 
                           class="form-group"
                           enctype="multipart/form-data" 
                           >
                        <br>
                        Choose Image:
                        <input type="file" class="form-control-file border" 
                               name="file" required
                               />
                        <br>
                        <input class="form-control" type="text" name="foodName" 
                               value="${param.foodName}"
                               placeholder="Food Name" required
                               maxlength="50" />
                        <c:if test="${not empty requestScope.ERROR_NAME}">
                            <p class="text-danger">
                                ${requestScope.ERROR_NAME}
                            </p>
                        </c:if>
                        <br>


                        <input class="form-control" type="text" name="description" 
                               value="${param.description}"
                               placeholder="Description Food" required
                               maxlength="200"/>
                        <br>


                        <input class="form-control" type="text" name="price" 
                               value="${param.price}" 
                               placeholder="Price" required
                               />
                        <c:if test="${not empty requestScope.ERROR_PRICE}">
                            <p class="text-danger">
                                ${requestScope.ERROR_PRICE}
                            </p>
                        </c:if>
                        <br>


                        <input class="form-control" type="number" name="quantity" 
                               value="${param.quantity}" 
                               placeholder="Quantity" required
                               />
                        <c:if test="${not empty requestScope.ERROR_QUANTITY}">
                            <p class="text-danger">
                                ${requestScope.ERROR_QUANTITY}
                            </p>
                        </c:if>
                        <br><br>

                        <!--cate-->
                        <label for="cate">Category:</label>
                        <div class="form-inline">
                            <select class="form-control" id="cate" 
                                    name="category">
                                <c:forEach var="category" items="${categories}">
                                    <option>
                                        ${category}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <br><br>

                        <button type="submit" class="btn btn-primary">
                            Create
                        </button>

                    </form>
                    <br>
                    <br>
                </div>
            </div>
        </div>
    </body>
</html>
