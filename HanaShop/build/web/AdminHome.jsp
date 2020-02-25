<%-- 
    Document   : AdminHome
    Created on : Feb 25, 2020, 3:07:34 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <c:set var="products" value="${requestScope.LIST_PRODUCT}"/>
        <c:set var="categories" value="${applicationScope.LIST_CATEGORY}" />
        <c:set var="statuses" value="${applicationScope.LIST_STATUS}" />

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
                                <h4 class="card-title">
                                    ${product.foodName}

                                    <span style="float: right"
                                          class="badge badge-success 
                                          <c:if test="${product.status=='Inactive'}">
                                              badge-danger
                                          </c:if>
                                          ">
                                        ${product.status}
                                    </span>
                                </h4>
                                <p class="card-text">${product.description}</p>

                                <h3 class="text-primary" style="float: left">Price: ${product.price}</h3>

                                <br>
                                <br>
                                <form action="quickUpdate">
                                    <input type="hidden" name="foodID" 
                                           value="${product.id}"/>
                                    <label for="cate">Category:</label>
                                    <div class="form-inline">
                                        <select class="form-control" id="cate" 
                                                name="category">
                                            <c:forEach var="category" items="${categories}">
                                                <option
                                                    <c:if test="${product.category eq category}">
                                                        selected                                    
                                                    </c:if>
                                                    >
                                                    ${category}
                                                </option>
                                            </c:forEach>
                                        </select>

                                        <button type="submit" class="btn btn-primary">
                                            Update
                                        </button>
                                    </div>
                                    <br>
                                    
                                    <!--status-->
                                    <c:set var="textMsg" value="text${product.id}"/>
                                    <c:set var="statusID" value="status${product.id}"/>
                                    <c:set var="showConfirm" value="Confirm${product.id}"/>

                                    <label for="${statusID}">Status:</label>
                                    <div class="form-inline">

                                        <select class="form-control" id="${statusID}" 
                                                onchange="ChangeStatus('${statusID}', '${textMsg}')"
                                                name="status">
                                            <c:forEach var="status" items="${statuses}">
                                                <option
                                                    <c:if test="${product.status eq status}">
                                                        selected                                    
                                                    </c:if>
                                                    >
                                                    ${status}
                                                </option>
                                            </c:forEach>
                                        </select>

                                        
                                        <a href="#${showConfirm}" class="btn btn-primary"
                                           data-toggle="collapse"
                                           onclick="ChangeStatus('${statusID}', '${textMsg}')"
                                           >
                                            Update
                                        </a>
                                        <div id="${showConfirm}" class="collapse">
                                            <p id="${textMsg}"></p>

                                            <div style="margin-bottom: 1em">
                                                <button type="submit" class="btn btn-danger" >
                                                    Yes
                                                </button>
                                                <a href="#${showConfirm}" 
                                                   data-toggle="collapse"
                                                   class="btn btn-primary" 
                                                   >
                                                    No
                                                </a>
                                            </div>
                                        </div>
                                    </div>  
                                    <!--s-->

                                    <!--3-->
                                </form>

                                <br>
                                <div class="form-group text-white">
                                    <c:url var="gotoDetail"  value="UpdateFoodDetail">
                                        <c:param name="foodID" value="${product.id}"/>
                                    </c:url>
                                    <a href="${gotoDetail}"
                                        class="btn btn-primary form-control"
                                        >
                                        Update details
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>


            <!--Paging-->

        </div>

        <script>
            function ChangeStatus(statusID, textMsg) {
                var text = document.getElementById(textMsg);
                var status = document.getElementById(statusID);

                if (status.value === "Active") {
                    text.innerHTML = "Update this item?";
                } else {
                    text.innerHTML = "Delete this item?";
                }
            }
        </script>
    </body>
</html>
