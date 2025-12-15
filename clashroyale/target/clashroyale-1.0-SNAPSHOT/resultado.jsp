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
    
    <div class="container">
        <div class="mazo">
            <c:forEach items="${sessionScope.mazo.mazo.entrySet()}" var="entry">
                <div class="carta">
                    <img src="${entry.key.imagen}" alt="${entry.key.nombre}">
                    <p><strong>${entry.key.nombre}</strong></p>
                    <p>Elixir: ${entry.key.elixir}</p>
                    <p>Rareza: ${entry.key.rareza}</p>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="stats">
        <h2>Estadísticas del Mazo</h2>
        <p><strong>Coste medio elixir:</strong> ${sessionScope.mazo.costeElixirMedio()}</p>
        <p><strong>Ataque Total:</strong> ${sessionScope.mazo.getAtaqueTotal()}</p>
        <p><strong>Vida Total:</strong> ${sessionScope.mazo.getVidaTotal()}</p>
        <p><strong>Comunes:</strong> ${sessionScope.mazo.contarComunes()}</p>
        <p><strong>Raras:</strong> ${sessionScope.mazo.contarRaras()}</p>
        <p><strong>Épicas:</strong> ${sessionScope.mazo.contarEpica()}</p>
        <p><strong>Legendarias:</strong> ${sessionScope.mazo.contarLegen()}</p>
    </div>
    
    <form action="estadisticas-servlet" method="post">
        <input type="submit" name="accion" value="volver">
    </form>

</body>
</html>