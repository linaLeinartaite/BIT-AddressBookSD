<%-- 
    Document   : editA
    Created on : 10-Mar-2020, 18:49:12
    Author     : Lina
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="lt.bit.data.Address"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.data.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Address</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    </head>

    <body>
        <%
            Address a = (Address) request.getAttribute("a");
        %>
        <div class="container-edit">
            <form class="form-edit" method="POST" action="save">      
                <%
    //                if (idPs != null && idAs != null) {
    //                    try {
    //                        idA = new Integer(idAs);
    //                        if ((idPs != null && p == null) || (idAs != null && p == null)) { //cia jei nori redaguoti zmogu kurio nera (ne tas id); cia jei paduodi id daug didesni
    //                            response.sendRedirect("addresses.jsp?id=" + idPs);
    //                            return;
    //                        }
    //                    } catch (Exception ex) {
    //                        response.sendRedirect("addresses.jsp?id=" + idPs);
    //                        return;
    //                    }

                %>              
                <div class="input"><label>Adresas:</label> <input name="a"  value="<%=(a != null) ? a.getAddress() : ""%>"></div>
                <div class="input"><label>Miestas:</label> <input name="c" value="<%=(a != null) ? a.getCity() : ""%>"></div>
                <div class="input"><label>Pašto kodas:</label> <input name="pc" value="<%=(a != null) ? a.getPostCode() : ""%>"></div>  
                    <%
                        if (a != null) {
                    %>
                <input type="hidden" name="idA" value="<%=a.getId()%>">  
                <%}%>           

                <div> <input class="btn btn-green" type="submit" value="Išsaugoti">  
                    <a class="btn btn-grey" href="./">Grįžti&nbsp;atgal</a> </div>
            </form>           
        </div>
    </body>
</html>




