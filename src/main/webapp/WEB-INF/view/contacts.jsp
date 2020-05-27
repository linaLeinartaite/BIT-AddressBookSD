<%-- 
    Document   : contacts
    Created on : 10-Mar-2020, 18:49:35
    Author     : Lina
--%>

<%@page import="java.util.List"%>
<%--<%@page import="javax.persistence.EntityManager"%>--%>
<%--<%@page import="java.sql.Connection"%>--%>
<%@page import="lt.bit.data.Contact"%>
<%@page import="lt.bit.controller.ControllerC"%>
<%@page import="lt.bit.data.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contacts</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <%
//                String ids = request.getParameter("idP");
//
//                Integer id = null;
//                Person p = null;
//                if (ids != null) {
//                    try {
//
//                        id = new Integer(ids);
//                        p = (Person) request.getAttribute("p");
//
//                        if (ids != null && p == null) {
//                            response.sendRedirect("/20200430_AddressBookMVC/");
//                            return;
//                        }
//                    } catch (Exception ex) {
//                        response.sendRedirect("/20200430_AddressBookMVC/");
//                        return;
//                    }
//                }
                Person p = (Person) request.getAttribute("p");
                Integer idP = p.getId();
            %>

            <div><h1><%=p.getFirstName() + " "%>
                    <%=p.getLastName() + " Kontaktai:"%></h1></div><br>
            <hr>
            <div id="person">
                <%
                    List<Contact> listC = (List<Contact>) request.getAttribute("listC");
                    for (Contact c : listC) {
                %> 
                <div class="col-1"> Id:<%= c.getId()%></div>
                <div class="col-3"> Tipas:  <%=c.getType()%> </div> 
                <div class="col-4">  Kontaktas:  <%=c.getContact()%> </div>
                <div class="col-2"> <a class="btn-s" href="delete?idC=<%=c.getId()%>">Trinti </a>               
                    <a class="btn-s" href="edit?idC=<%=c.getId()%>"> Keisti</a> </div>             
                <hr>
                <%
                    }
                %>
                <br>
                <div class="col-4"> <a class="btn btn-green" href="edit">Pridėti&nbsp;Naują</a></div>
                <div class="col-3"> <a class="btn btn-grey" href="../..">Grįžti&nbsp;atgal</a></div>
            </div>
        </div>
    </body>
</html>
