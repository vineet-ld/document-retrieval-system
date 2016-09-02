<%-- 
    Document   : admin
    Created on : Apr 18, 2015, 10:39:45 PM
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
                                <form  class="form-horizontal"  method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/upload.htm">

                                    <fieldset>

                                        <div class="control-group">
                                            <label class="control-label" for="file">Upload a File</label>
                                            <div class="controls">
                                                <input type="file" class="input-xlarge" name="file" id="file"/>
                                            </div>
                                        </div>

                                        <div class="form-actions">
                                            <input type="submit" class="btn btn-primary" value="Upload"> 
                                        </div>


                                    </fieldset>           

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


<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "spring" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IRS - Admin</title>
    </head>
    <body>

        <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/upload.htm">

            <input type="file" name="file"/> <br/>
            <input type="submit" name="Upload">            

        </form>

    </body>
</html>--%>
