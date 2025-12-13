<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mostrar Mazo</title>
</head>
<body>
    <h1>Confirmación de compra</h1>
    <table>
        <thead>
            <tr>
                <th>Imagen</th>
                <th>Nombre</th>
                <th>Coste Elixir</th>
                <th>Rareza</th>
            </tr>
        </thead>
        <tbody>
            <%--Los elementos se encuentran en la sesión -> objeto carrito -> mapa carrito -> entrySet--%>
            <c:forEach items="${sessionScope.mazo.mazo.entrySet()}" var="entry">
            <tr>
                <td>${entry.value}</td>
                <td>${entry.key.imagen}</td>
                <td>${entry.key.nombre}</td>
                <td>${entry.key.elixir}</td>
                <td>${entry.key.rareza}</td>
            </tr>
            </c:forEach>
            <tr>
                <td colspan="4">Coste medio elixir</td>
                <td>${sessionScope.mazo.elixirMedio}</td>
            </tr>
            <tr>
                <td colspan="4">Ataque Total</td>
                <td>${sessionScope.mazo.ataque}</td>
            </tr>
            <tr>
                <td colspan="4">Vida</td>
                
            </tr>
            <tr>
                <td colspan="4">Comunes:</td>
            <td>${sessionScope.mazo.comunes}</td>
            </tr>
                        <tr>
                <td colspan="4">Raras</td>
                <td>${sessionScope.mazo.raras}</td>
            </tr>
                        <tr>
                <td colspan="4">Épicas</td>
                <td>${sessionScope.mazo.epicas}</td>
            </tr>
                        <tr>
                <td colspan="4">Legendaria</td>
                <td>${sessionScope.mazo.legendaria}</td>
            </tr>
        </tbody>
    </table>
    
    <form action="factura-servlet" method="post">
        <input type="submit" name="accion" value="volver">
    </form>

</body>
</html>