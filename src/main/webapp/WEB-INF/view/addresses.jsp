<%-- 
    Document   : addresses
    Created on : 10-Mar-2020, 18:45:47
    Author     : Lina
--%>

<%@page import="java.util.List"%>
<%--<%@page import="javax.persistence.EntityManager"%>--%>
<%@page import="lt.bit.controller.ControllerA"%>
<%--<%@page import="lt.bit.controller.ControllerP"%>--%>
<%--<%@page import="java.sql.Connection"%>--%>
<%@page import="lt.bit.data.Person"%>
<%@page import="lt.bit.data.Address"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Addresses</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
 
    </head>
    <body>
        <div class="container">
            <%

                        Person p = (Person) request.getAttribute("p");
                        Integer idP = p.getId();
            %>

            <div><h1><%=p.getFirstName() + " "%>
                    <%=p.getLastName() + " Adresai:"%></h1></div><br>
            <hr>
            <div id="person">
                <%

                    List<Address> listA = (List<Address>) request.getAttribute("listA");
                    for (Address a : listA) {

                %>  

                <div class="col-2">Id:<%= a.getId()%></div>
                <div class="col-4">  Adresas:  <%=a.getAddress()%> </div>             
                <div class="col-4">   Miestas:  <%=a.getCity()%> </div> 
                <div class="col-4">  Pašto kodas: <%=(a.getPostCode() != null && !a.getPostCode().equals("")) ? a.getPostCode() : " * * * * *"%> </div> 

                <div class="col-2">  <a class="btn-s" href="delete?idA=<%=a.getId()%>">Trinti </a>            
                    <a class="btn-s" href="edit?idA=<%=a.getId()%>"> Keisti</a> </div>            
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
