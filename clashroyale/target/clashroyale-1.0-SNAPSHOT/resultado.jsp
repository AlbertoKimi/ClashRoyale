<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mostrar Mazo</title>
    <link rel="stylesheet" href="css/estilos.css">
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
                <td><img src="${entry.key.imagen}" alt="${entry.key.nombre}"></td>
                <td>${entry.key.nombre}</td>
                <td>${entry.key.elixir}</td>
                <td>${entry.key.rareza}</td>
            </tr>
            </c:forEach>
            <tr>
                <td colspan="4">Coste medio elixir</td>
                <td>${sessionScope.mazo.costeElixirMedio()}</td>
            </tr>
            <tr>
                <td colspan="4">Ataque Total</td>
                <td>${sessionScope.mazo.getAtaqueTotal()}</td>
            </tr>
            <tr>
                <td colspan="4">Vida</td>
                <td>${sessionScope.mazo.getVidaTotal()}</td>
                
            </tr>
            <tr>
                <td colspan="4">Comunes:</td>
            <td>${sessionScope.mazo.contarComunes()}</td>
            </tr>
                        <tr>
                <td colspan="4">Raras</td>
                <td>${sessionScope.mazo.contarRaras()}</td>
            </tr>
                        <tr>
                <td colspan="4">Épicas</td>
                <td>${sessionScope.mazo.contarEpica()}</td>
            </tr>
                        <tr>
                <td colspan="4">Legendaria</td>
                <td>${sessionScope.mazo.contarLegen()}</td>
            </tr>
        </tbody>
    </table>
    
    <form action="estadisticas-servlet" method="post">
        <input type="submit" name="accion" value="volver">
    </form>

</body>
</html>