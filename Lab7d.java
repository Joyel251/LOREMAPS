import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Lab7d extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        
        out.println("<html><body style='text-align:center; background-color:#f0f4f8; color:#2c3e50;'>");
        out.println("<h2>LoreMaps - HttpSession Demo</h2>");
        
        if("create".equals(action)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("explorer", username);
            session.setAttribute("loginTime", new java.util.Date().toString());
            out.println("<p>Account session created for Explorer: <b>" + username + "</b></p>");
            out.println("<p>Session ID: " + session.getId() + "</p>");
            out.println("<p>You can now save favorite locations and create custom maps!</p>");
        }
        else if("view".equals(action)) {
            HttpSession session = request.getSession(false);
            if(session != null) {
                String user = (String)session.getAttribute("explorer");
                String time = (String)session.getAttribute("loginTime");
                out.println("<p>Explorer: <b>" + user + "</b></p>");
                out.println("<p>Session started at: " + time + "</p>");
                out.println("<p>Session ID: " + session.getId() + "</p>");
                out.println("<p>Access: Saved locations, custom maps, HD visuals enabled</p>");
            } else {
                out.println("<p>No active exploration session found!</p>");
                out.println("<p>Please create an account session to access saved maps.</p>");
            }
        }
        else if("destroy".equals(action)) {
            HttpSession session = request.getSession(false);
            if(session != null) {
                session.invalidate();
                out.println("<p>Account session ended successfully!</p>");
                out.println("<p>All temporary data cleared. See you next time, Explorer!</p>");
            } else {
                out.println("<p>No active session to end!</p>");
            }
        }
        
        out.println("<br><a href='Lab7d.html' style='color:#4a7ba7;'>Back</a></body></html>");
    }
}
