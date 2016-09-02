<%-- 
    Document   : result
    Created on : Apr 21, 2015, 4:36:48 AM
    Author     : Vineet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="../../jsp/include/commonTags.jsp" %>
    </head>
    <body>

        <%@include file="../../jsp/include/header.jsp" %>

        <div class="container-fluid">
            <div class="row-fluid">


                <div id="content" class="span12">

                    <div class="row-fluid sortable">
                        <div class="box span12">
                            <div class="box-content">
                                <c:forEach var="document" items="${documentList}">
                                    <p><a href="${pageContext.request.contextPath}/data/${document.name}">${document.name}</a></p>
                                </c:forEach>
                            </div>

                        </div>

                    </div>

                </div>

            </div>

            <hr/>

            <%@include file="../../jsp/include/footer.jsp" %>

        </div>

    </body>


</html>
