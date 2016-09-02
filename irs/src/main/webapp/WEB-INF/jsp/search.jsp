<%-- 
    Document   : search
    Created on : Apr 21, 2015, 4:32:23 AM
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
                                <form method="post" class="form-horizontal"  action="${pageContext.request.contextPath}/search.htm">

                                    <fieldset>

                                        <div class="control-group">
                                            <label class="control-label" for="query">Search String</label>
                                            <div class="controls">
                                                <input type="text" class="input-xlarge" name="query" id="query"/>
                                            </div>
                                        </div>
                                        
                                        <div class="form-actions">
                                            <input type="submit" class="btn btn-primary" value="Search"> 
                                        </div>


                                    </fieldset>           -->

                                </form>
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
