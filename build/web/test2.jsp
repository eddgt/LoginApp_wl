<%-- 
    Document   : test2
    Created on : Jul 14, 2017, 9:33:23 AM
    Author     : oulloa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>        
        <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.js"></script>
        <script>
            //Usually, you put script-tags into the head
            function myFunction() {
                //This performs a POST-Request.
                //Use "$.get();" in order to perform a GET-Request (you have to take a look in the rest-API-documentation, if you're unsure what you need)
                //The Browser downloads the webpage from the given url, and returns the data.
                $.post("http://localhost:8081/CMS_BUC/service/rest/post", function (data) {
                    //As soon as the browser finished downloading, this function is called.
                    $('#demo').html(data);
                });
            }
        </script>
    </head>
    <body>
        <form action="localhost:8081/CMS_BUC/rest/post" method="POST">
            Username:<br>
            <input type="text" id="user"  name="user" value="user"><br>
            Password:<br>
            <input type="password" id="pass" name="pass" value="contrasena"><br><br>
            <input type="text" id="time" name="time" value=""><br>
            <input type="text" id="hash" name="hash" value=""><br>
            <input type="submit" value="Submit">
        </form>

        <p>Click the button to trigger a function.</p>

        <button onclick="myFunction()">Click me</button>

        <p id="demo"></p>    
    </body>
</html>