<%-- 
    Document   : SearchHistoryForm
    Created on : Feb 25, 2020, 8:13:07 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="NavBar.jsp"/>
        <h2 >
            <span class="badge badge-info">History Shopping</span>
        </h2>

        <div class="sidenav">
            <div style=" margin: 2em;">


                <form action="searchHistoryAction" method="POST">
                    <input class="form-control mr-sm-2" type="text" 
                           name="searchHistoryVal" 
                           value="${requestScope.searchHistoryVal}"
                           placeholder="Product's name">

                    <button class="btn btn-success" type="submit"
                            style="float: right;margin-right: 4em" >
                        Search
                    </button>

                    <input type="hidden" name="userID" value="${sessionScope.USER_ID}" />

                    <br><br>
                    
                    <jsp:include page="DateInput.html" />

                </form>
            </div>
        </div>
    </body>
</html>
