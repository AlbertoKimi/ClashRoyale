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
public class Estadisticas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("resultado.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Mazo mazo = (Mazo) session.getAttribute("mazo");
        mazo.vaciar();
        session.setAttribute("mazo", mazo);
        CookieUtils.deleteCookie(req, resp, "mazo");
        resp.sendRedirect("mazo-servlet");
    }
}
