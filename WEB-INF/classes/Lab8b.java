import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Lab8b extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        
        // Get or initialize total hits (application scope)
        Integer totalHits = (Integer) context.getAttribute("totalHits");
        if(totalHits == null) totalHits = 0;
        
        out.println("<html><body style='text-align:center; background-color:#f0f4f8; color:#2c3e50;'>");
        out.println("<h2>LoreMaps - Individual User Hit Counter</h2>");
        out.println("<div style='background:#fff; display:inline-block; padding:30px; border:2px solid #b8d4e8; border-radius:10px;'>");
        
        if("start".equals(action)) {
            session.setAttribute("explorer", username);
            session.setAttribute("userHits", 1);
            totalHits++;
            context.setAttribute("totalHits", totalHits);
            out.println("<h3 style='color:#4a7ba7;'>Session Started</h3>");
            out.println("<p>Explorer: <b>" + username + "</b></p>");
            out.println("<p style='font-size:36px; font-weight:bold; color:#2c3e50;'>Your Visit Count: 1</p>");
            out.println("<p style='font-size:24px; color:#666;'>Total Visits (All Users): " + totalHits + "</p>");
            out.println("<p style='color:#666;'>Your individual map exploration is now being tracked</p>");
        }
        else if("view".equals(action)) {
            String explorer = (String) session.getAttribute("explorer");
            Integer hits = (Integer) session.getAttribute("userHits");
            
            if(explorer == null || hits == null) {
                out.println("<h3 style='color:#e74c3c;'>No Active Session</h3>");
                out.println("<p>Please start a tracking session first</p>");
            } else {
                hits++;
                totalHits++;
                session.setAttribute("userHits", hits);
                context.setAttribute("totalHits", totalHits);
                out.println("<h3 style='color:#4a7ba7;'>Your Access Statistics</h3>");
                out.println("<p>Explorer: <b>" + explorer + "</b></p>");
                out.println("<p style='font-size:48px; font-weight:bold; color:#2c3e50; margin:20px;'>" + hits + "</p>");
                out.println("<p>Your personal map pages accessed this session</p>");
                out.println("<hr style='width:80%; border:1px solid #ddd;'>");
                out.println("<p style='font-size:32px; font-weight:bold; color:#4a7ba7; margin:20px;'>Total: " + totalHits + "</p>");
                out.println("<p style='color:#666;'>Combined visits by all explorers</p>");
                out.println("<p style='color:#666; font-size:14px;'>Individual: Session Scope | Total: Application Scope</p>");
            }
        }
        else if("reset".equals(action)) {
            String explorer = (String) session.getAttribute("explorer");
            session.setAttribute("userHits", 0);
            out.println("<h3 style='color:#4a7ba7;'>Individual Counter Reset</h3>");
            out.println("<p>Explorer: <b>" + (explorer != null ? explorer : username) + "</b></p>");
            out.println("<p>Your personal visit counter has been reset to 0</p>");
            out.println("<p style='color:#666;'>Total visits (all users): " + totalHits + " (unchanged)</p>");
        }
        
        out.println("</div><br><br>");
        out.println("<a href='Lab8b.html' style='color:#4a7ba7; text-decoration:none; padding:10px 20px; border:2px solid #7fb3d5; border-radius:6px;'>Back</a>");
        out.println("</body></html>");
    }
}
