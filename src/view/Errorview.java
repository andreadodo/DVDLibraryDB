package view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by andrea on 09/02/17.
 */
public class Errorview extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List errorMsgs = (LinkedList)request.getAttribute("errors");


        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>DVD Library Application: Error</title>");
            out.println("</head>");
            out.println("<body text = 'red'>");
            out.println("<h3>Ops ...</h3>");
            out.println("<ul>");
            for (Object er: errorMsgs)
                out.println("<li>"+ er +"</li>");
            out.println("</ul><br><br>");
            out.println("<form><input type='button' value='Back' onClick='history.back();return true;'></form>");
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
