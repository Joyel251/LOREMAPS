import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Lab7b extends HttpServlet {
    private int visitCount = 0;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String count = request.getParameter("count");
        
        if(count == null) count = "0";
        visitCount = Integer.parseInt(count) + 1;
        
        out.println("<html><body style='text-align:center; background-color:#f0f4f8; color:#2c3e50;'>");
        out.println("<h2>LoreMaps - URL Rewriting Session Demo</h2>");
        out.println("<p>Explorer: <b>" + username + "</b></p>");
        out.println("<p>Regions Explored: <b>" + visitCount + "</b></p>");
        
        String url = "Lab7b?username=" + username + "&count=" + visitCount;
        out.println("<a href='" + response.encodeURL(url) + "' style='color:#4a7ba7;'>Explore Next Region</a><br><br>");
        out.println("<a href='Lab7b.html' style='color:#4a7ba7;'>New Exploration</a>");
        out.println("</body></html>");
    }
}
