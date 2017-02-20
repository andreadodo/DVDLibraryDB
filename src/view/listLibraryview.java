package view;

import model.DVDCollection;
import model.DVDItem;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/**
 * Created by andrea on 16/02/17.
 */
public class listLibraryview extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        DVDCollection dvdLibrary = DVDCollection.getDvdCollection();
        List<DVDItem> dvdOrderedLibrary = (List<DVDItem>) dvdLibrary.getLibrary();

        //SAVE ORDER PREFERENCE WITH COOKIES
        Cookie[] cookiesBox = request.getCookies();
        String order = request.getParameter("order") + request.getParameter("direction");
        boolean save = Boolean.parseBoolean(request.getParameter("save"));

        if(save)
            response.addCookie(new Cookie("orderCookie", order));

        for (Cookie c: cookiesBox){
            if(c.getName().equals("orderCookie")&&order.equals("nullnull")) {
                order = c.getValue();
                save = true;
            }
        }

        switch (order) {

            case "TitleAsc":
                Collections.sort(dvdOrderedLibrary);
                break;
            case "TitleDesc":
                Collections.sort(dvdOrderedLibrary, Collections.reverseOrder());
                break;
            case "YearAsc":
                Collections.sort(dvdOrderedLibrary, new DVDItem.DVDYearComparator());
                break;
            case "YearDesc":
                Collections.sort(dvdOrderedLibrary, Collections.reverseOrder(new DVDItem.DVDYearComparator()));
                break;
            case "GenreAsc":
                Collections.sort(dvdOrderedLibrary, new DVDItem.DVDGenreComparator());
                break;
            case "GenreDesc":
                Collections.sort(dvdOrderedLibrary, Collections.reverseOrder(new DVDItem.DVDGenreComparator()));
                break;
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>LibraryServelet</title>");
            out.println("<style>");
            out.println("table {");
            out.println("border-collapse: collapse;");
            out.println("width: 100%;");
            out.println("}");
            out.println("td, th {");
            out.println("border: 1px solid #dddddd;");
            out.println("text-align: left;");
            out.println("padding: 8px;");
            out.println("}");
            out.println("tr:nth-child(even) {");
            out.println("background-color: #dddddd;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>You have " + dvdLibrary.getLibrary().size() + " DVD in your collection.</p>");

            if (!dvdLibrary.getLibrary().isEmpty()) {
                out.println("<form action=listLibrary.view method='POST'>");
                out.println("Order by: ");
                out.println("<select name='order'>");
                out.println("<option value ='Title'");
                if(order.startsWith("Title"))
                    out.println("selected");
                out.println(">Title</option>");
                out.println("<option value ='Year'");
                if(order.startsWith("Year"))
                    out.println("selected");
                out.println(">Year</option>");
                out.println("<option value ='Genre'");
                if(order.startsWith("Genre"))
                    out.println("selected");
                out.println(">Genre</option>");
                out.println("</select>");

                out.println("  <select name='direction'>");
                out.println("<option value ='Asc'");
                if(order.endsWith("Asc"))
                    out.println("selected");
                out.println(">Ascending</option>");
                out.println("<option value ='Desc'");
                if(order.endsWith("Desc"))
                    out.println("selected");
                out.println(">Descending</option>");
                out.println("</select>");
                out.println("<input type='submit' value='Order'> ");
                if(save)
                    out.println("<input type='checkbox' name='save' value='true' checked> Save Your Choice (uses cookies)");
                else
                    out.println("<input type='checkbox' name='save' value='true'> Save Your Choice (uses cookies)");

                out.println("</form>");
                out.println("<br>");
                out.println("<table>");

                out.println("<tr><td>ID<td>TITLE</td><td>YEAR</td><td>GENRE</td><td>EDIT</td><td>DELETE</td></tr>");
                for (DVDItem dvd : dvdOrderedLibrary) {
                    out.println("<tr>");
                    out.println("<td>" + dvd.getDvdId() + "</td>"); //test id
                    out.println("<td>" + dvd.getDvdTitle() + "</td>");
                    out.println("<td>" + dvd.getDvdYear() + "</td>");
                    out.println("<td>" + dvd.getDvdGenre() + "</td>");

                    out.println("<td><form action=formDvd.view method='POST'>"
                            + "<input name='id' value='" + dvd.getDvdId() + "' type='hidden'>"
                            + "<input type='submit' value='edit'> </form></td>");

                    out.println("<td><form action=delDvd.do method='POST'>"
                            + "<input name='id' value='" + dvd.getDvdId() + "' type='hidden'>"
                            + "<input type='submit' value='delete'> </form></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
            out.println("<br>");
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
