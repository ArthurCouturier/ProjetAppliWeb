package main.webapp.profilPage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class inscriptionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("inscription/html"); // pas certain ici, je me base sur lz frappe p3
        res.getWriter().print("<html><body><h1>Directory</h1>");
    }
}
