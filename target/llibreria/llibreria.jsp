<%
    // Solo redirigir a /consulta si no venimos del servlet (es decir, no hay libros en el request)
    if (request.getAttribute("llibres") == null) {
        response.sendRedirect("consulta");
        return; // cortar ejecución del JSP
    }
%>
<%@ page import="java.util.*, org.llibrerialliure.llibreriaweb.Llibre" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Llibres disponibles</title>
</head>
<body>
    <h1>Llista de llibres</h1>
    <table border="1">
        <tr>
            <th>Títol</th>
            <th>ISBN</th>
            <th>Editorial</th>
        </tr>
        <% //recogemos la lista de libros del objeto request "llibres"
            List<Llibre> llibres = (List<Llibre>) request.getAttribute("llibres");
            if (llibres != null) {
                for (Llibre llibre : llibres) {
        %>
        <tr>
            <td><%= llibre.getTitol() %></td>
            <td><%= llibre.getIsbn() %></td>
            <td><%= llibre.getEditorial() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="3">No s'han trobat llibres.</td></tr>
        <%
            }
        %>
    </table>
</body>
</html>
