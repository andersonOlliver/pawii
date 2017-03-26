<%-- 
    Document   : success
    Created on : 19/03/2017, 18:56:02
    Author     : ander
--%>

<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="model.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resource/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            List<User> users = Login.getUsers();
            request.setAttribute("userList", users);
            User user = new User();
            try {
                user = Login.getUser(session.getAttribute("user").toString());
            } catch (Exception e) {
            }
        %>
        <c:choose>
            <c:when test="${not empty user}" >
                <div class="box box2">
                    <h1>#<%= user.getId() + " " + user.getLogin()%> </h1> 
                    <h2>Seja bem vindo</h2>
                    <a href='logout.jsp'>Sair</a>
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Login</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${userList}">
                                <tr>
                                    <td><c:out value="${item.id}" /></td>
                                    <td><c:out value="${item.login}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="box box2">
                    Página com conteúdo restrito por favor <a href="index.jsp">efetue login</a>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
