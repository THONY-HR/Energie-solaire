package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classTable.Domicile;
import classView.Affichage;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ListeDomicile")
public class ListeDomicile extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Affichage affichage = new Affichage();        
            request.setAttribute("affichages", affichage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ListeDomicile.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            out.println("Erreur ListeDomicile" + e.getMessage());
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Affichage affichage = new Affichage();        
            request.setAttribute("affichages", affichage);
            String idDomicile = request.getParameter("idDomicile");
            for(Domicile domicile: affichage.getDomicile()){
                if (domicile.getIdDomicile() == Integer.parseInt(idDomicile)) {
                    request.setAttribute("domicile", domicile);
                }
            }
            // out.println(idDomicile);
            RequestDispatcher dispatcher = request.getRequestDispatcher("DetailleDomicile.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            out.println("Erreur ListeDomicile" + e.getMessage());
        }
    }
}
