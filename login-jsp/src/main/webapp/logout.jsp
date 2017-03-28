<%-- 
    Document   : logout
    Created on : 19/03/2017, 19:36:56
    Author     : ander
--%>
<%
    session.setAttribute("user", null);
    session.invalidate();
    response.sendRedirect("index.jsp");
%>