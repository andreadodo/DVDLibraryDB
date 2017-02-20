package view;


import model.DVDItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by andrea on 09/02/17.
 */
public class Successview extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Retrieve the dvd from request-scope
        DVDItem dvd = (DVDItem) request.getAttribute("dvd");

        //Specify the content type HTML
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>DVD Library Application: Insert Success</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Your request to add the ");
            out.println("<i>" + dvd.getDvdTitle() + "</i>");
            out.println(" dvd was succesful.</p>");
            out.println("<br><br>");
            out.println("<form action=homepage.view method='POST'>"
                    + "<input type='submit' value='back Home'></form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}