package com.alberto.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.alberto.beans.Mazo;
import com.alberto.utils.CookieUtils;

@WebServlet(urlPatterns = { "/estadisticas-servlet" })
public class Estadisticas extends HttpServlet { //Aquí es para carga de datos, carga de cookies e inicio de sesión.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("resultado.jsp").forward(req, resp); //Las comprobaciones, por ejemplo, lo de que el mazo tenga cartas o sea igual que 8 por si no lo cumple va de nuevo al mazo.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //Los doPost si no se modifica los modelos, no hace falta. Se utiliza solo para la modificación del modelo
        HttpSession session = req.getSession();
        Mazo mazo = (Mazo) session.getAttribute("mazo");
        mazo.vaciar();
        session.setAttribute("mazo", mazo);
        CookieUtils.deleteCookie(req, resp, "mazo");
        resp.sendRedirect("mazo-servlet");
    }
}
