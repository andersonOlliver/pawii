<%-- 
    Document   : success
    Created on : 19/03/2017, 18:56:02
    Author     : ander
--%>

<%@page import="java.util.List"%>
<%@page import="com.olliver.dao.UsuarioDAO"%>
<%@page import="com.olliver.model.Usuario" %>
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
            List<Usuario> usuarios = UsuarioDAO.todos();
            request.setAttribute("usuarioList", usuarios);
            Usuario usuario = new Usuario();
            try {
                usuario = UsuarioDAO.entrar(session.getAttribute("usuario").toString());
            } catch (Exception e) {
            }
        %>
        <c:choose>
            <c:when test="${not empty usuario}" >
                <div class="box box2">
                    <h1>#<%= usuario.getId() + " " + usuario.getLogin()%> </h1> 
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
                            <c:forEach var="item" items="${usuarioList}">
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
