<%-- 
    Document   : index
    Created on : 19/03/2017, 18:33:40
    Author     : ander
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="resource/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1 id="login">Login</h1>

        <form class="box" method="post" action="login.jsp">
            <div class="imgcontainer">
                <img src="resource/img/user.png" alt="Avatar" class="avatar"/>

            </div>
            <div class="container">
                <label for="txt-login">Login: </label>
                <input id="txt-login" name="txtLogin" type="text" required/>

                <br/>
                <label for="txt-password">Password: </label>
                <input id="txt-password" name="txPassword" type="password" required/>

                <br/>
                <input type="submit" />
            </div>
        </form>
    </body>
</html>
