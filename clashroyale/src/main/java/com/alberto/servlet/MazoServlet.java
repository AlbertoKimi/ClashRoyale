package com.alberto.servlet;

import java.io.IOException;
import java.util.ArrayList;

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

        HttpSession session = req.getSession();
        Mazo mazo = (Mazo) session.getAttribute("mazo");
        FuenteDeDatos fd = FuenteDeDatos.getInstancia();
        ArrayList<Carta> cartasDisponibles = new ArrayList<>();

        for (Carta carta : fd.getCartas()) {
            if (!mazo.estaEnMazo(carta.getId())) {
                cartasDisponibles.add(carta);
            }
        }

        req.setAttribute("cartasDisponibles", cartasDisponibles);

        req.getRequestDispatcher("mazo.jsp").forward(req, resp);
        System.out.println("redirigiendo al mazo");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mensaje = "";
        String mensajeErr = "";
        HttpSession session = req.getSession();
        Mazo mazo = (Mazo) session.getAttribute("mazo");
        FuenteDeDatos fd = FuenteDeDatos.getInstancia();
        String seleccion = "";
        boolean error = false;
        String accion = req.getParameter("accion");

        switch (accion) {
            case "agregar":
                try {
                    seleccion = Validador.validarString(req.getParameter("seleccion"));

                } catch (Exception e) {
                    mensajeErr = e.getMessage();
                    error = true;
                }
                if (!error) {
                    Carta carta = fd.getCarta(seleccion);
                    if (carta != null) {
                        if (mazo.getMazo().size() < 8) {
                            mazo.addCarta(carta);
                            System.out.println("Se ha añadido la carta");
                            System.out.println(
                                    "Se ha eliminado la carta " + carta.getNombre() + " de la fuente de datos ");
                            mensaje = "Carta " + carta.getNombre() + " añadido al mazo";
                        } else {
                            mensajeErr = "El mazo no puede contener más de 8 cartas";
                        }

                    } else {
                        mensajeErr = "No se ha encontrado la carta seleccionada";
                    }
                    Cookie mazoCookie = CookieUtils.encodeMapForCookie("mazo", mazo.getMazo());
                    resp.addCookie(mazoCookie);
                }
                break;
            case "limpiar":
                if (mazo != null && !mazo.getMazo().isEmpty()) {
                    mazo.vaciar();
                    Cookie mazoCookie = new Cookie("mazo", "");
                    mazoCookie.setMaxAge(0);
                    resp.addCookie(mazoCookie);
                    mensaje = "Mazo y cartas reseteados";
                }
                break;
            case "ver":
                if (mazo != null && mazo.getMazo().size() == 8) {
                    resp.sendRedirect("estadisticas-servlet");
                    return;
                } else {
                    mensaje = "El mazo debe contener 8 cartas";
                }
                break;

            default:
                mensaje = "Acción no encontrada";
                break;
        }

        if (!mensajeErr.equals("")) {
            req.setAttribute("mensajeErr", mensajeErr);
        }
        req.setAttribute("mensaje", mensaje);
        resp.sendRedirect("mazo-servlet");
    }
}
