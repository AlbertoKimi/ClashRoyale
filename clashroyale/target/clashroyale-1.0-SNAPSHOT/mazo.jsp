<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ClashRoyale</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <h1>Bienvenido</h1>
    <div><c:out value="${sessionScope.mensaje}" default=""/></div>
    <c:if test="${not empty sessionScope.mensajeErr}">
        <div class="error"><c:out value="${sessionScope.mensajeErr}"/></div>
    </c:if>

    <c:remove var="mensaje" scope="session"/>
    <c:remove var="mensajeErr" scope="session"/>

    <c:set var="mazoVacia" value="${not empty sessionScope.mazo.mazo}" />

    <h2>Mi Mazo</h2>
    <div class="container">
        <div class="mazo">
            <c:forEach items="${sessionScope.mazo.mazo.entrySet()}" var="entry">
                <div class="carta">
                    <img src="${entry.key.imagen}" alt="${entry.key.nombre}">
                    <p><strong>${entry.key.nombre}</strong></p>
                    <p>Elixir: ${entry.key.elixir}</p>
                    <p>Rareza: ${entry.key.rareza}</p>
                    <form action="mazo-servlet" method="post">    
                        <input type="hidden" name="accion" value="quitar">
                        <input type="hidden" name="busca" value="${entry.key.id}">
                        <input type="submit" value="Quitar">
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>

    <h2>Cartas Disponibles</h2>
    <div class="container">
        <div class="cartas">
            <c:forEach items="${cartasDisponibles}" var="carta">
                <form action="mazo-servlet" method="post">
                    <div class="carta">
                        <img src="${carta.imagen}" alt="${carta.nombre}">
                        <p><strong>${carta.nombre}</strong></p>
                        <p>Coste Elixir: ${carta.elixir}</p>
                        <p>Rareza: ${carta.rareza}</p>
                        <input type="hidden" name="accion" value="agregar">
                        <input type="hidden" name="seleccion" value="${carta.id}">
                        <input type="submit" value="Añadir">
                    </div>
                </form>
            </c:forEach>
        </div>
    </div>

    <div>
        <form action="mazo-servlet" method="post">    
            <input type="hidden" name="accion" value="limpiar">
            <input type="submit" ${!mazoVacia ? 'disabled class="disabled"' : '' } value="Limpiar Mazo">
        </form>
    </div>

    <div>
        <form action="mazo-servlet" method="post">    
            <input type="hidden" name="accion" value="ver">
            <input type="submit" ${fn:length(sessionScope.mazo.mazo) == 8 ? '' : 'disabled class="disabled"' } value="Ver Mazo">
        </form>
    </div>
</body>
</html>