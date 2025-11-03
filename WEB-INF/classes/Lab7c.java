import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Lab7c extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String visits = request.getParameter("visits");
        
        int visitCount = (visits == null) ? 1 : Integer.parseInt(visits) + 1;
        
        out.println("<html><body style='text-align:center; background-color:#f0f4f8; color:#2c3e50;'>");
        out.println("<h2>LoreMaps - Hidden Fields Session Demo</h2>");
        out.println("<p>Explorer: <b>" + username + "</b></p>");
        out.println("<p>Map Layers Viewed: <b>" + visitCount + "</b></p>");
        out.println("<form action='Lab7c' method='post'>");
        out.println("<input type='hidden' name='username' value='" + username + "'>");
        out.println("<input type='hidden' name='visits' value='" + visitCount + "'>");
        out.println("<button type='submit' style='background-color:#c8dff0; color:#2c3e50; border:2px solid #7fb3d5; padding:10px 20px; border-radius:6px; cursor:pointer;'>View Next Map Layer</button>");
        out.println("</form><br>");
        out.println("<a href='Lab7c.html' style='color:#4a7ba7;'>New Exploration Session</a>");
        out.println("</body></html>");
    }
}
