<%-- 
    Document   : PagesDivision
    Created on : Jan 11, 2020, 11:34:35 PM
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
        <c:if test="${not empty param.PagesListForUser}">
            <c:set var="pageNumber" value="${applicationScope.PAGES_LIST}" />
            <c:url var="link" value="RedirectPage" >
                <c:param name="pageView" value="Home"/>
            </c:url>
        </c:if>

        <c:if test="${not empty param.PagesListForUserSearch}">
            <c:set var="pageNumber" value="${sessionScope.PAGES_LIST_SEARCH}" />
            <c:url var="link" value="RedirectPage" >
                <c:param name="pageView" value="Search"/>
            </c:url>
        </c:if>

        <c:if test="${param.adminPage eq 'PagesListForAdmin'}">
            <c:set var="pageNumber" value="${sessionScope.PAGES_LIST_ADMIN}" />
            <c:url var="link" value="RedirectPage" >
                <c:param name="pageView" value="AdminHome"/>
            </c:url>
        </c:if>

        <c:if test="${param.adminPage eq 'PagesListForAdminSearch'}">
            <c:set var="pageNumber" 
                   value="${sessionScope.PAGES_LIST_ADMIN_SEARCH}" />
            <c:url var="link" value="RedirectPage" >
                <c:param name="pageView" value="AdminSearch"/>
            </c:url>
        </c:if>

        <!-- -->
        <ul class="pagination justify-content-center" style="margin:20px 0">
            <c:forEach var="pageIndex" items="${pageNumber}">
                <c:url var="fullLink" value="${link}">
                    <c:param name="page" value="${pageIndex}"/>
                </c:url>

                <li class="page-item 
                    <c:if test="${pageIndex==param.page}">
                        active
                    </c:if>
                    <c:if test="${empty param.page and pageIndex==1}">
                        active
                    </c:if>    
                    ">
                    <a class="page-link" href="${fullLink}">
                        ${pageIndex}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
