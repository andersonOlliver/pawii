<%-- 
    Document   : login
    Created on : 19/03/2017, 15:32:58
    Author     : ander
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="com.olliver.dao.UsuarioDAO"%>
<%@page import="com.olliver.model.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resource/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="box box2">
            <%
                Usuario usuario = new Usuario();
                List<Usuario> usuarios = UsuarioDAO.todos();
                usuario.setLogin(request.getParameter("txtLogin"));
                usuario.setPassword(request.getParameter("txPassword"));

                if (usuarios.contains(usuario)) {
                    Usuario aux = usuarios.get(usuarios.indexOf(usuario));

                    if (aux.getPassword().equals(usuario.getPassword())) {
                        session.setAttribute("usuario", usuario.getLogin());
                        response.sendRedirect("success.jsp");
                    } else {
                        out.println("senha incorreta <a href='index.jsp'>tentar novamente</a>");
                    }
                } else {
                    out.println("login inexistente <a href='index.jsp'>tentar novamente</a>");
                }
            %>
        </div>
    </body>
</html>