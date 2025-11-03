import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LProg1 extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        message = "Welcome to LoreMaps – The Realms Await You!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.setContentType("text/html");

        // Retrieve form inputs
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String realm = request.getParameter("realm");
        String charclass = request.getParameter("charclass");
        String journey = request.getParameter("journey");
        String guild = request.getParameter("guild");

        // Generate the fantasy-styled HTML response
        PrintWriter out = response.getWriter();
        out.println("<html><body style='text-align:center; background-color:#f0f4f8; color:#2c3e50; font-family:IM Fell English SC,serif;'>");
        out.println("<h1 style='color:#4a7ba7; font-family:Cinzel,serif; text-shadow:0 1px 2px rgba(74,123,167,0.2);'>" + message + "</h1>");
        out.println("<h3>Greetings, " + name + " of the " + guild + " Guild!</h3>");
        out.println("<p>Your chosen class: <b>" + charclass + "</b></p>");
        out.println("<p>Realm of Preference: <b>" + realm + "</b></p>");
        out.println("<p>Your Journey: <b>" + journey + "</b></p>");
        out.println("<p>We shall reach you via Raven Post at: <b>" + email + "</b></p>");
    // Use context path so the link always points to the correct app root
    String ctx = request.getContextPath();
    out.println("<br><a href='" + ctx + "/LProg1.html' style='color:#4a7ba7; text-decoration:none; font-weight:bold;'>Return to Realm Registration</a>");
        out.println("</body></html>");
    }

    public void destroy() {
        // Cleanup if necessary
    }
}
