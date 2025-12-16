package com.alberto.filtro;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alberto.beans.Mazo;
import com.alberto.beans.FuenteDeDatos;
import com.alberto.beans.Carta;
import com.alberto.utils.CookieUtils;

@WebFilter(urlPatterns = {"/*"})
public class DataFilter implements Filter{


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // Casteamos la request a HttpServletRequest para poder acceder a la sesión. Si no, imposible
        HttpServletRequest request = (HttpServletRequest) req;
        // Pillamos el ámbito global de la aplicación
        ServletContext context = request.getServletContext(); //AplicationScope
        // Obtenemos la sesión
        HttpSession session = request.getSession();
        // Obtenemos nuestro mock que sustituye a una base de datos
        FuenteDeDatos fd = FuenteDeDatos.getInstancia();
        // Colocamos todos los libros de la aplicación en el ámbito global
        context.setAttribute("cartas", fd.getCartas());          // También podría almacenarse en la sesión, pero el contexto es un ámbito único, mejor para este caso puesto que evita guardar toda la colección de libros por cada usuario
        //Esto lo está pillando para los jsp
        // Vamos a preparar el carrito, lo hacemos en un filtro que se lanzará antes de cada uno de los servlets, así ahorramos trabajo
        // Si ya existe el carrito en la sesión no hay que hacer nada
        if (session.getAttribute("cartas")==null) {
            // Si el carrito no está en la sesión vamos a comprobar si tenemos una cookie guardada
            Cookie cartasCookie = CookieUtils.getCookie(request, "mazo");
            Mazo mazo = new Mazo();
            if (cartasCookie!=null) {
                // En caso de que tengamos la cookie, la decodificamos usando nuestra clase de utilidad y guardamos el HashMap en el objeto carrito
                mazo.setMazo((HashMap<Carta, Integer>)CookieUtils.decodeCookieIntValue(cartasCookie, fd.getCartas()));
            }
            // Con cookie o sin ella, ponemos el carrito en la sesión, que estará vacío si no tenemos la cookie
            session.setAttribute("mazo", mazo);
        }

        // Salimos del filtro y delegamos al siguiente elemento de la cadena, que va a ser el Servlet que toque
        chain.doFilter(request, res);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
    
    @Override
    public void destroy() {
    }
}
