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
    <div style="color:red"><c:out value="${sessionScope.mensajeErr}" default=""/></div>

    <c:remove var="mensaje" scope="session"/>
    <c:remove var="mensajeErr" scope="session"/>

    <c:set var="mazoVacia" value="${not empty sessionScope.mazo.mazo}" />

    <div class="contenedor" id="contenedor">
      <c:forEach items="${cartasDisponibles}" var="carta">
        <form action="mazo-servlet" method="post">
          <div class="foto">
            <img src="${carta.imagen}" alt="${carta.nombre}">
          </div>
            <div class="stats2">
                <p id="nombre"><strong>${carta.nombre}</strong></p>
                <p>Coste Elixir: ${carta.elixir}</p>
                <p>Rareza: ${carta.rareza}</p>
            </div>
                <div>
                  <input type="hidden" name="accion" value="agregar">
                  <input type="hidden" name="seleccion" value="${carta.id}">
                  <input type="submit" value="Añadir">
                </div>
        </form>
      </c:forEach>
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
            <%--Los elementos se encuentran en la sesión -> objeto mazo -> mapa mazo -> entrySet--%>
            <c:forEach items="${sessionScope.mazo.mazo.entrySet()}" var="entry">
            <tr>
                <td>${entry.key.nombre}</td>
                <td>${entry.key.elixir}</td>
                <td>${entry.key.rareza}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    
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