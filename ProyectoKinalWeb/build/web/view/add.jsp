<%-- 
    Document   : add
    Created on : 25/08/2022, 02:41:33 PM
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Agregar Persona</title>
    </head>
        <body>
             <div class = "container">
                <div class = "col-lg-5">
                    <h1>Agregar Persona</h1>
                    <form action="Controlador">
                        <strong>DPI:</strong> <br><br>
                        <input class ="form-control" type="text" name="txtDPI"><br><br>
                        <strong>Nombre:</strong> <br><br>
                        <input class = "form-control" type="text" name="txtNombre"><br><br>
                        <input class ="btn btn-primary" type="submit" name="accion" value="Agregar"><br>        
                    </form>
                </div>
            </div>
        </body>
</html>
