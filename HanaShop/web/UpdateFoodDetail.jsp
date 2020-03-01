<%-- 
    Document   : UpdateFoodDetail
    Created on : Feb 25, 2020, 9:43:10 PM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update details Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.ROLE or sessionScope.ROLE==0}">
            <c:redirect url="StartupServlet"></c:redirect>
        </c:if>

        <div class="container">
            <jsp:include page="NavBar.jsp"/>

            <div class="small-form">
                <h1>Update page </h1>

                <div style="margin-left: 20%;">

                    <c:set var="categories" value="${applicationScope.LIST_CATEGORY}" />
                    <c:set var="statuses" value="${applicationScope.LIST_STATUS}" />
                    <c:set var="food" value="${requestScope.food}"/>

                    <form action="UpdateFoodServlet" method="POST" 
                          class="form-group"
                          enctype="multipart/form-data" 
                          >
                        <br>
                        Choose Image:
                        <input type="file" class="form-control-file border" 
                               name="img"  />
                        <input type="hidden" name="oldImg" value="${food.img}" />
                        <br>
                        Name:
                        <input class="form-control" type="text" name="foodName" 
                               value="${food.foodName}" 
                               maxlength="50" />
                        <br>

                        Description:
                        <input class="form-control" type="text" name="description" 
                               value="${food.description}" 
                               maxlength="200"/>
                        <br>

                        Price:
                        <input class="form-control" type="number" name="price" 
                               value="${food.price}" />
                        <br>

                        Quantity:
                        <input class="form-control" type="number" name="quantity" 
                               value="${food.quantity}" />
                        <br><br>

                        <!--cate-->
                        <label for="cate">Category:</label>
                        <div class="form-inline">
                            <select class="form-control" id="cate" 
                                    name="category">
                                <c:forEach var="category" items="${categories}">
                                    <option
                                        <c:if test="${food.category eq category}">
                                            selected                                    
                                        </c:if>
                                        >
                                        ${category}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <!--status-->
                        <label for="status">Status:</label>
                        <div class="form-inline">
                            <select class="form-control" id="status" 
                                    name="status">
                                <c:forEach var="status" items="${statuses}">
                                    <option
                                        <c:if test="${food.status eq status}">
                                            selected                                    
                                        </c:if>
                                        >
                                        ${status}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <br><br>

                        <input type="hidden" name="updateDetail" value="ON"/>
                        <input type="hidden" name="foodID" value="${food.id}"/>

                        <button type="submit" class="btn btn-primary">Update</button>

                    </form>
                    <br>
                    <br>
                </div>
            </div>
        </div>

    </body>
</html>
