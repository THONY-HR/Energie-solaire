package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Fonction.Calculate;
import classTable.Batterie;
import classTable.Besoin;
import classTable.DetailleBesoin;
import classTable.Domicile;
import classView.InformationDomicile;
import utilitaire.BdConnect;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EnergieSolaire")
public class Index extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        try {
            if (session.getAttribute("domicile") != null) {
                request.setAttribute("domicile", (Domicile) session.getAttribute("domicile"));
            }
            if (session.getAttribute("allDetailleBesoins") != null) {
                request.setAttribute("allDetailleBesoins", (DetailleBesoin[]) session.getAttribute("allDetailleBesoins"));
            }
            if (session.getAttribute("besoin") != null) {
                request.setAttribute("besoin", (Besoin) session.getAttribute("besoin"));
            }
            if (session.getAttribute("domicile") == null) {
                BdConnect con = new BdConnect();
                Batterie[] batterie = con.getObjectFromTable("Batterie",Batterie.class);
                request.setAttribute("batterie", batterie);
                con.close();            
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        Calculate calculate = new Calculate();
        if (type.equals("domicile")) {
            String lot = request.getParameter("lot");
            String locationMap = request.getParameter("locationMap");
            String typeBatterie = request.getParameter("typeBatterie");
            String datyEnregistrement = request.getParameter("datyEnregistrement");
            try {
                Domicile domicile = new Domicile(lot, locationMap);
                Besoin besoin = new Besoin(typeBatterie, datyEnregistrement);
                HttpSession session = request.getSession(true);
                calculate.sessionDomicile(response, session, domicile);
                calculate.sessionBesoin(response, session, besoin);
                response.sendRedirect(request.getContextPath() + "/EnergieSolaire");
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        }else if(type.equals("detailleBesoin")){
            String nomBesoin = request.getParameter("nomBesoin");
            String puissanceWatt = request.getParameter("puissanceWatt");
            String debutDuree = request.getParameter("debutDuree");
            String finDuree = request.getParameter("finDuree");
            try {
                DetailleBesoin detailleBesoin = new DetailleBesoin("1", nomBesoin, puissanceWatt, debutDuree, finDuree);
                HttpSession session = request.getSession(true);
                calculate.sessionDetailleBesoin(response,session,detailleBesoin);
                response.sendRedirect(request.getContextPath() + "/EnergieSolaire");
            } catch (Exception e) {
                out.println("erreur" + e.getMessage());
            }
        }else if(type.equals("dernierConfirmation")){
            try {
                HttpSession session = request.getSession(true);
                InformationDomicile informationDomicile = new InformationDomicile((Domicile) session.getAttribute("domicile"), (Besoin) session.getAttribute("besoin"), (DetailleBesoin[]) session.getAttribute("allDetailleBesoins"));
                informationDomicile.inserer();
                calculate.destroyAllSessions(request,response);
                response.sendRedirect(request.getContextPath() + "/EnergieSolaire");
            } catch (Exception e) {
                out.println("erreur" + e.getMessage());
            }
        }
    }
}
