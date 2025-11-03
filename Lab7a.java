import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Lab7a extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        
        out.println("<html><body style='text-align:center; background-color:#f0f4f8; color:#2c3e50;'>");
        out.println("<h2>LoreMaps - Cookies Session Demo</h2>");
        
        if("login".equals(action)) {
            Cookie userCookie = new Cookie("explorer", username);
            userCookie.setMaxAge(60*5); // 5 minutes
            response.addCookie(userCookie);
            out.println("<p>Exploration session started for: <b>" + username + "</b></p>");
            out.println("<p>Cookie stored - You can now explore interactive world maps!</p>");
        } 
        else if("visit".equals(action)) {
            Cookie[] cookies = request.getCookies();
            String user = null;
            if(cookies != null) {
                for(Cookie c : cookies) {
                    if("explorer".equals(c.getName())) user = c.getValue();
                }
            }
            out.println("<p>Welcome back, Explorer: <b>" + (user != null ? user : "Guest (No Active Session)") + "</b></p>");
            if(user != null) out.println("<p>Access granted to HD maps and saved locations!</p>");
        }
        else if("logout".equals(action)) {
            Cookie userCookie = new Cookie("explorer", "");
            userCookie.setMaxAge(0);
            response.addCookie(userCookie);
            out.println("<p>Exploration session ended. Cookie deleted!</p>");
        }
        
        out.println("<br><a href='Lab7a.html' style='color:#4a7ba7;'>Back</a></body></html>");
    }
}
