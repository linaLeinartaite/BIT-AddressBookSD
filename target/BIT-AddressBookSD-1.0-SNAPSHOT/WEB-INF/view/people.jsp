<%-- 
    Document   : index
    Created on : 10-Mar-2020, 18:42:08
    Author     : Lina
--%>

<%@page import="java.util.List"%>
<%--<%@page import="lt.bit.controller.ListingController"%>--%>
<%@page import="lt.bit.controller.ControllerP"%>
<%--<%@page import="java.sql.Connection"%>--%>
<%@page import="lt.bit.data.Person"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Darbuotoju Sarasas</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">       
    </head>
    <body>
        <div class="container">
            <div id="title"><h1>DARBUOTOJŲ SĄRAŠAS</h1></div> 
            <div class="in-line">
                <form method="POST">
                    <input class="btn" name="filter" placeholder="Raktinis žodis">
                    <input class="btn btn-grey mr-2" type="submit" value="Ieškoti"> 
                </form>
                <a class="btn btn-green" href="edit">Pridėti&nbsp;Naują</a>
            </div>
            <br>
            <hr>   
            <div id="person">
                <%
                    List<Person> listP = (List<Person>) request.getAttribute("listP");
                    for (Person p : listP) {
                %>           


                <div class="col-1">id: <%=p.getId()%> </div>  
                <div class="col-3"> Vardas: <%=p.getFirstName()%> </div> 
                <div class="col-3"> Pavarde: <%=p.getLastName()%> </div> 
                <div class="col-4"> Gimimo data: <%=(p.getBirthDate() != null) ? (p.getBirthDate()) : " * * * * *"%> </div>   
                <div class="col-3"> Alga: <%=(p.getSalary() != null) ? p.getSalary() : " * * * * *"%>  </div> 
                <div class="col-3"> <a class="link"  href="<%=p.getId()%>/addresses/">Adresai</a> 
                    <a class="link" href="<%=p.getId()%>/contacts/">Kontaktai</a> </div> 
                <div class="col-3"> <a class="btn-s" href="delete?idP=<%=p.getId()%>">Trinti</a>            
                    <a class="btn-s" href="edit?idP=<%=p.getId()%>">Keisti</a>  </div>              
                <hr>
                <%
                    }
                %>
                <br>              
            </div>
        </div>
    </body>
</html>
