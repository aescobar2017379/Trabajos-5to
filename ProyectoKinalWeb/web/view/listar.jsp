<%-- 
    Document   : listar
    Created on : 25/08/2022, 02:54:33 PM
    Author     : angel
--%>

<%@page import="java.util.Iterator"%>
<%@page import="model.Persona"%>
<%@page import="java.util.List"%>
<%@page import="modelDAO.PersonaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Listar Personas</title>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Registro de Personas</h1>
            <a class = "btn btn-success" href="Controlador?accion=add"> Agregar Nueva Persona</a>
            <table class = "table table-bordered">
                <thead>
                    <tr>
                        <th class = "text-center text-primary">CODIGO PERSONA</th>
                        <th class = "text-center text-primary">DPI</th>
                        <th class = "text-center text-primary">NOMBRES</th>
                        <th class = "text-center text-primary">ACCIONES</th>
                </tr>
            </thead>
            <%
                PersonaDAO dao = new PersonaDAO();
                List<Persona> listaPersona = dao.listar();
                Iterator<Persona> iterator = listaPersona.iterator();
                Persona per = null;
                while (iterator.hasNext()){
                    per = iterator.next();
             %>
            <tbody>
                <tr>
                    <td class = "text-center active"><%= per.getCodigoPersona()%></td>
                    <td class = "text-center"><%= per.getDPI()%></td>
                    <td class = "text-center active"><%= per.getNombrePersona()%></td>
                    <td class = "text-center">
                           <a class = "btn btn-warning"href="Controlador?accion=editar&codigoPersona=<%= per.getCodigoPersona()%>">Edit</a>
                           <a class = "btn btn-danger"href="Controlador?accion=eliminar&codigoPersona=<%=per.getCodigoPersona()%>">Eliminar</a>
                    </td>
                </tr>
                <%}%>
            </tbody>
            
        </table>
        </div>
    </body>
</html>
