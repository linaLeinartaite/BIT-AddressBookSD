<%-- 
    Document   : editC
    Created on : 04-May-2020, 22:52:23
    Author     : Lina
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="lt.bit.data.Contact"%>
<%@page import="lt.bit.data.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Contact</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    </head>

    <body>
        <div class="container-edit">
            <%  
                Contact c = (Contact) request.getAttribute("c");
            %>
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
                <div class="input"><label>Tipas:</label> <input name="ct"  value="<%=(c != null) ? c.getType() : ""%>"></div>
                <div class="input"><label>Kontaktas:</label> <input name="co" value="<%=(c != null) ? c.getContact() : ""%>"></div>  
                    <%
                        if (c != null) {
                    %>
                <input type="hidden" name="idC" value="<%=c.getId()%>">  
                <%}%>  
                <div> <input class="btn btn-green" type="submit" value="Išsaugoti"> 
                    <a class="btn btn-grey" href="./">Grįžti&nbsp;atgal</a> 
                </div>
            </form>
        </div>
    </body>
</html>





