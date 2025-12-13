<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ClashRoyale</title>
</head>
<body>
    <h1>Bienvenido</h1>
    <div><c:out value="${mensaje}" default=""/></div>
    <div style="color:red"><c:out value="${mensajeErr}" default=""/></div>

    <div class="contenedor" id="contenedor">
      <form action="mazo-servlet" method="post">
        <c:forEach items="${applicationScope.cartas}" var="carta">
          <div class="foto">
            <img src="${carta.imagen}" alt="${carta.nombre}">
          </div>
            <div class="stats2">
                <p id="nombre" name = "contenedor"><strong>${carta.id}</strong></p>
                <p>Coste Elixir: ${carta.elixir}</p>
                <p>Rareza: ${carta.rareza}</p>
            </div>
                <div>
                  <input type="submit" name="accion" value="${carta.id}">
                </div>
        </c:forEach>
      </form>
    </div> 

    <div class ="mazo">
        <table>
          <thead>
            <tr>
                <th>Nombre</th>
                <th>Elixir</th>
                <th>Rareza</th>
            </tr>
          </thead>
        <tbody>
            <%--Los elementos se encuentran en la sesión -> objeto carrito -> mapa carrito -> entrySet--%>
            <c:forEach items="${sessionScope.mazo.mazo.entrySet()}" var="entry">
            <tr>
                <td>${entry.value}</td>
                <td>${entry.key.nombre}</td>
                <td>${entry.key.elixir}</td>
                <td>${entry.key.rareza}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    
    </div>       

    <a href="estadisticas-servlet" <c:if test="${empty sessionScope.mazo.mazo}">style="pointer-events: none;"</c:if>>Ver Mazo</a>
    <a href="mazo-servlet" <c:if test="${empty sessionScope.mazo.mazo}">style="pointer-events: none;"</c:if>>Limpiar</a>
</body>
</html>