package view;

import model.other.UserDatabase;
import model.other.UserItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by andrea on 14/02/17.
 */
public class homepageview extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        UserDatabase userDb = UserDatabase.getUserDb();
        UserItem userItem = userDb.getUser((String) session.getAttribute("user"));

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>HomePage DVD Library</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Hi " + userItem.getUser() + ", Welcome Back!</p>");
            out.println("<br>");
            if (userItem.getType() == UserItem.ADMIN)
                out.println("<form action=formDvd.view method='POST'>"
                                + "<input type='submit' value='Add a DVD to my collection'></form><br>");
            out.println("<form action=listLibrary.view method='POST'>"
                    + "<input type='submit' value='Display my DVD library'></form>");
            out.println("<br>");
            out.println("<form action=logout.do method='POST'>"
                    + "<input type='submit' value='Logout'></form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
