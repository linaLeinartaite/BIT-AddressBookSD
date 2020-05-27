<%-- 
    Document   : edit
    Created on : 10-Mar-2020, 18:42:25
    Author     : Lina
--%>

<%@page import="lt.bit.data.Person"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="lt.bit.controller.ControllerP"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Person</title>
                <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="container-edit">
            <%

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    
                Person p = (Person) request.getAttribute("p");
            %>    
            <form class="form-edit" method="POST" action="save"> 
                <div class="input"><label>Vardas:</label> <input name="fn"  value="<%=(p != null) ? p.getFirstName() : ""%>"></div>
                <div class="input"><label> Pavarde:</label> <input name="ln" value="<%=(p != null) ? p.getLastName() : ""%>"></div>

                <div class="input"><label> Gimimo data:</label> <input name="bd" value="<%=(p != null && p.getBirthDate() != null) ? sdf.format(p.getBirthDate()) : ""%>"></div>
                <div class="input"><label> Alga:</label>  <input name="s" value="<%=(p != null && p.getSalary() != null) ? p.getSalary() : ""%>"> </div>
                <div> <input class="btn btn-green" type="submit" value="Išsaugoti">
                    <a class="btn btn-grey" href="./">Grįžti&nbsp;atgal</a></div>

                <%if (p != null) {
                %>
                <input type="hidden" name="idP" value="<%=p.getId()%>">  
                <%}%>
            </form>
            
        </div>
    </body>
</html>
