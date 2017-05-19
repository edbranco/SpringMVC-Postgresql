<%-- 
    Document   : index
    Created on : 12/05/2017, 14:35:29
    Author     : ebranco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Register a Person</h3>
        <form action="crud/save" method="POST">
            <label>Name:</label>
            <input type="text" name="name"/>
            <label>Age:</label>
            <input type="number" name="age"/>
            <label>City:</label>
            <input type="text" name="city"/>
            <label>Country:</label>
            <input type="text" name="country"/>
            
            <input type="submit" value="Salvar"/>
        </form>
    </body>
</html>
