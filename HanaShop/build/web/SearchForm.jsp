<%-- 
    Document   : SearchForm
    Created on : Feb 20, 2020, 9:00:56 AM
    Author     : nguye
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <jsp:include page="NavBar.jsp"/>

        <div class="sidenav">
            <div style=" margin: 2em;">
                <form action="searchAction" method="POST">
                    <input class="form-control mr-sm-2" type="text" 
                           name="searchVal" value="${sessionScope.searchVal}"
                           placeholder="Product's name">

                    <button class="btn btn-success" type="submit"
                            style="float: right;margin-right: 4em" >
                        Search
                    </button>

                    <br>
                    <br>
                    <!--.-->
                    <button type="button" class="btn btn-primary"
                            onclick="DeleteFilterSearch()">
                        Delete Filter
                    </button>
                    <!--.-->

                    <div>
                        <c:set var="categories" value="${applicationScope.LIST_CATEGORY}" />
                        <!---->
                        <div class="form-check">
                            <input type="radio" id="Category" class="form-check-input"
                                   onclick="HideRangePrice()" 
                                   name="SearchByFilter" value="Category"
                                   <c:if test="${not (sessionScope.SearchByCategory eq 'Category'
                                                 or empty sessionScope.SearchByCategory )}">
                                         checked
                                   </c:if>
                                   />

                            <label for="Category" onclick="HideRangePrice()"
                                   class="form-check-label"
                                   >
                                <span  class="badge badge-pill badge-primary">
                                    Select Category (select one):
                                </span>
                            </label>

                        </div>
                        <br>
                        <select class="form-control" id="listCategory"
                                onclick="HideRangePrice()"
                                name="SearchByCategory"> 
                            <option id="CategoryOption">Category</option>
                            <c:forEach var="category" items="${categories}">
                                <option
                                    <c:if test="${sessionScope.SearchByCategory eq category}">
                                        selected                                    
                                    </c:if>
                                    >
                                    ${category}
                                </option>
                            </c:forEach>
                        </select>
                        <br>


                        <div class="form-check">
                            <input type="radio" id="Price" name="SearchByFilter" value="Price"
                                   onclick="ShowRangePrice()"
                                   class="form-check-input"
                                   <c:if test="${sessionScope.SearchByFilter eq 'Price'}">
                                       checked
                                   </c:if>
                                   />
                            <label for="Price" onclick="ShowRangePrice()"
                                   class="form-check-label"
                                   >
                                <span  class="badge badge-pill badge-primary">
                                    Price
                                </span>
                            </label>

                        </div>

                        <c:if test="${sessionScope.SearchByFilter eq 'Price'}">
                            <c:set var="display" value="display:block" />
                        </c:if>
                        <div id="RangeOfMoney" style="margin-left: 1em;${display}">
                            <input type="range" min="5" max="50" 
                                   class="custom-range" id="customRange" 
                                   name="priceVal" value="${sessionScope.priceVal}"
                                   />
                            <p id="priceVal">
                                <c:if test="${sessionScope.SearchByFilter eq 'Price'}">
                                    Price: ${sessionScope.priceVal-5}-${sessionScope.priceVal+5}$
                                </c:if>
                            </p>


                        </div>
                    </div>

                    <br>
                </form>

                <hr style="border: 1px solid darkcyan"/>
            </div>
        </div>



        <script>
            var slider = document.getElementById("customRange");
            var output = document.getElementById("priceVal");

            slider.oninput = function () {

                var fromPr = this.value - 5;
                var toPr = fromPr + 10;
                var outPr = fromPr + "-" + toPr;
                output.innerHTML = "Price: " + outPr + "$";
            }

            function DeleteFilterSearch() {
                var category = document.getElementById("Category");
                var categoryOption = document.getElementById("CategoryOption");
                var price = document.getElementById("Price");
                var rangePrice = document.getElementById("RangeOfMoney");

                category.checked = false;
                price.checked = false;
                categoryOption.selected = true;
                rangePrice.style.display = "none";
            }

            function ShowRangePrice() {
                var price = document.getElementById("Price");
                var rangePrice = document.getElementById("RangeOfMoney");
                var category = document.getElementById("CategoryOption");

                if (price.checked) {
                    rangePrice.style.display = "block";
                    category.selected = "true";
                }
                
            }

            function HideRangePrice() {
                var rangePrice = document.getElementById("RangeOfMoney");
                var category = document.getElementById("listCategory");
                var categoryRadio = document.getElementById("Category");

                rangePrice.style.display = "none";

                if (category.value !== "Category") {
                    categoryRadio.checked = true;
                }

            }

        </script>
    </body>
</html>
