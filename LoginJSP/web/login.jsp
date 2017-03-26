<%-- 
    Document   : login
    Created on : 19/03/2017, 15:32:58
    Author     : ander
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Login"%>
<%@page import="model.User" %>
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
                User user = new User();
                List<User> users = Login.getUsers();
                user.setLogin(request.getParameter("txtLogin"));
                user.setPassword(request.getParameter("txPassword"));

                if (users.contains(user)) {
                    User aux = users.get(users.indexOf(user));

                    if (aux.getPassword().equals(user.getPassword())) {
                        session.setAttribute("user", user.getLogin());
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