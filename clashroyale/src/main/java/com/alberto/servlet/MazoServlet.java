package com.alberto.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alberto.beans.Mazo;
import com.alberto.beans.FuenteDeDatos;
import com.alberto.beans.Carta;
import com.alberto.utils.CookieUtils;
import com.alberto.utils.Validador;

@WebServlet(urlPatterns = { "/mazo-servlet" })
public class MazoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("mazo.jsp").forward(req, resp);
        System.out.println("redirigiendo a index");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Mensajes para la vista
        String mensaje = "";
        String mensajeErr = "";
        // Objetos de obtención de datos
        HttpSession session = req.getSession();
        Mazo mazo = (Mazo) session.getAttribute("mazo");
        FuenteDeDatos fd = FuenteDeDatos.getInstancia();
        // Variables para los parámetros
        String seleccion = "";
        boolean error = false;
        String accion = req.getParameter("accion");
        // Validamos los parámetros, el mensaje de error se crea en las excepciones
        // lanzadas por los métodos de la clase Validador
        try {
            seleccion = Validador.validarString(req.getParameter("seleccion"));

        } catch (Exception e) {
            mensajeErr = e.getMessage();
            error = true;
        }

        if (accion.equals("agregar")) {
            if (!error) {
                Carta carta = fd.getCarta(seleccion);
                if (carta != null) {
                    mazo.addCarta(carta);
                    System.out.println("Se ha añadido la carta");
                    fd.eliminarCarta(carta.getId());
                    mensaje = "Carta " + carta.getNombre() + " añadido al mazo";
                } else {
                    mensajeErr = "No se ha encontrado la carta seleccionada";
                }
                Cookie mazoCookie = CookieUtils.encodeMapForCookie("mazo", mazo.getMazo());
                resp.addCookie(mazoCookie);
            }
        } else if (accion.equals("limpiar")){
            if(mazo!=null && !mazo.getMazo().isEmpty()){
                for(Carta cartaDeVuelta : mazo.getMazo().keySet()){
                    fd.anadirCarta(cartaDeVuelta);
                }
                mazo.vaciar();
                Cookie mazoCookie = new Cookie("mazo", "");
                mazoCookie.setMaxAge(0);
                resp.addCookie(mazoCookie);
                mensaje= "Mazo y cartas reseteados";
            }

            else{
                mensaje = "Acción no encontrada";
            }

        }

        if (!mensajeErr.equals("")) {
            req.setAttribute("mensajeErr", mensajeErr);
        }
        req.setAttribute("mensaje", mensaje);
        req.getRequestDispatcher("mazo.jsp").forward(req, resp);
    }
}
