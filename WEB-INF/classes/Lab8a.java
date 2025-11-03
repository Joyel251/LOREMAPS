import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Lab8a extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        ServletContext context = getServletContext();
        Integer count = (Integer) context.getAttribute("totalHits");
        
        if(count == null) {
            count = 1;
        } else {
            count++;
        }
        
        context.setAttribute("totalHits", count);
        
        out.println("<html><body style='text-align:center; background-color:#f0f4f8; color:#2c3e50;'>");
        out.println("<h2>LoreMaps - Total Page Access Statistics</h2>");
        out.println("<div style='background:#fff; display:inline-block; padding:30px; border:2px solid #b8d4e8; border-radius:10px;'>");
        out.println("<h3 style='color:#4a7ba7;'>Total Map Page Visits</h3>");
        out.println("<p style='font-size:48px; font-weight:bold; color:#2c3e50; margin:20px;'>" + count + "</p>");
        out.println("<p>This counter tracks all map explorations by every user</p>");
        out.println("<p style='color:#666; font-size:14px;'>Scope: Application Level (Shared across all users)</p>");
        out.println("</div><br><br>");
        out.println("<a href='Lab8a' style='color:#4a7ba7; text-decoration:none; padding:10px 20px; border:2px solid #7fb3d5; border-radius:6px;'>Refresh Count</a> ");
        out.println("<a href='Lab8a.html' style='color:#4a7ba7; text-decoration:none; padding:10px 20px; border:2px solid #7fb3d5; border-radius:6px;'>Back</a>");
        out.println("</body></html>");
    }
}
