package controller;

import model.UserDatabase;
import model.UserItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by andrea on 14/02/17.
 */
public class logindo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Keep a set of strings to record from processing errors.
        List errorMsgs = new LinkedList();
        request.setAttribute("errorMsgs", errorMsgs);

        try {
            //Retrieve from parameters.

            String user = request.getParameter("user").trim();
            String password = request.getParameter("password").trim();

            //Verify form parametrs
            if (user.equals(null))
                errorMsgs.add("Please enter Username.");
            if (password.equals(null))
                errorMsgs.add("Please enter Password.");

            //open error view
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errors", errorMsgs);
                RequestDispatcher view = request.getRequestDispatcher("Error.view");
                view.forward(request, response);
                return;
            }

            //Perform business logic
            UserDatabase userDb = UserDatabase.getUserDb();
            HttpSession session;

            if(userDb.loginUser(new UserItem(user,password))) { //false == error usr/psw
                //open homepage view

                session = request.getSession();
                session.setAttribute("user", user);

                //request.setAttribute("user", user);



                RequestDispatcher view = request.getRequestDispatcher("homepage.view");
                view.forward(request, response);
            } else {
                errorMsgs.add("Incorrect username or password ");
                request.setAttribute("errors", errorMsgs);
                RequestDispatcher view = request.getRequestDispatcher("Error.view");
                view.forward(request, response);
                return;
            }

            //Handle any unexpected exceptions
        } catch (RuntimeException re) {
            errorMsgs.add(re.getMessage());
            request.setAttribute("errors", errorMsgs);
            RequestDispatcher view = request.getRequestDispatcher("Error.view");
            view.forward(request, response);
        }
    }
}
