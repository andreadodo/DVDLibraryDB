package view;

import model.DVDCollection;
import model.DVDItem;
import model.UserDatabase;
import model.UserItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by andrea on 09/02/17.
 */
public class formDvdview extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List errorMsgs = new LinkedList();
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            UserDatabase userDb = UserDatabase.getUserDb();

            UserItem userItem = userDb.getUser((String) session.getAttribute("user"));

            if (userItem.getType() == UserItem.ADMIN) {
                //Retrieve from parameters.
                String idStr = request.getParameter("id");

                int id = -1;
                try {
                    id = Integer.parseInt(idStr);
                } catch (NumberFormatException nfe) {
                }

                DVDCollection dvdLibrary = DVDCollection.getDvdCollection();
                DVDItem dvd = dvdLibrary.getDvd(id);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Add DVD</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<form action='editDvd.do' method='POST'>");

                if (idStr != null) { //if id is defined edit else add
                    out.println("<input type='hidden' name='id' value='" + dvd.getDvdId() + "'>");
                    out.println("Title:<br>");
                    out.println("<input type='text' name='title' value='" + dvd.getDvdTitle() + "'>");
                    out.println("<br><br>Year:<br>");
                    out.println("<input type='text' name='year' value='" + dvd.getDvdYear() + "'>");
                    out.println("<br><br>Genre:<br>");
                    out.println("<select name='genre'>");
                    out.println("<option value ='Sci-Fi' ");
                    if (dvd.getDvdGenre().equals("Sci-Fi"))
                        out.println("selected");
                    out.println(">Sci-Fi</option>");
                    out.println("<option value ='Cartoon' ");
                    if (dvd.getDvdGenre().equals("Cartoon"))
                        out.println("selected");
                    out.println(">Cartoon</option>");
                    out.println("<option value ='Drammatics' ");
                    if (dvd.getDvdGenre().equals("Dramatics"))
                        out.println("selected");
                    out.println(">Dramatics</option>");
                    out.println("</select>");

                } else {
                    out.println("Title:<br>");
                    out.println("<input type='text' name='title' value='insert Title'>");
                    out.println("<br><br>Year:<br>");
                    out.println("<input type='text' name='year' value='insert Year'>");
                    out.println("<br><br>Genre:<br>");
                    out.println("<select name='genre'>");
                    out.println("<option value ='UNKNOW'>select...</option>");
                    out.println("<option value ='Sci-Fi'>Sci-Fi</option>");
                    out.println("<option value ='Cartoon'>Cartoon</option>");
                    out.println("<option value ='Dramatics'>Dramatics</option>");
                    out.println("</select>");
                }

                out.println("<br><br>");
                out.println("<input type='submit' value='Add to Library'>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            } else {
                errorMsgs.add("Permission denied.");
                request.setAttribute("errors", errorMsgs);
                RequestDispatcher view = request.getRequestDispatcher("Error.view");
                view.forward(request, response);
            }
        } catch (RuntimeException re) {
            errorMsgs.add(re.getMessage());
            request.setAttribute("errors", errorMsgs);
            RequestDispatcher view = request.getRequestDispatcher("Error.view");
            view.forward(request, response);
        }
    }
}
