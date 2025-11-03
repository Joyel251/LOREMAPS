import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DBServlet9 extends HttpServlet {
    private String dbUrl;
    private String dbUser;
    private String dbPass;

    public void init() throws ServletException {
        ServletConfig cfg = getServletConfig();
        dbUrl = cfg.getInitParameter("dbUrl");
        dbUser = cfg.getInitParameter("dbUser");
        dbPass = cfg.getInitParameter("dbPass");
        if (dbUrl == null) dbUrl = "jdbc:mysql://localhost:3306/loremapdb?useSSL=false&serverTimezone=UTC";
        if (dbUser == null) dbUser = "root";
        if (dbPass == null) dbPass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL JDBC Driver not found", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body style='font-family:Arial, sans-serif;padding:20px;'>");
        out.println("<h2>DB Operation Result</h2>");

        if ("insert".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String desc = request.getParameter("description");
            String landmarks = request.getParameter("landmarks");
            String level = request.getParameter("level");

            String sql = "INSERT INTO regions (id,name,type,description,landmarks,explorationlevel) VALUES (?,?,?,?,?,?)";
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.setString(2, name);
                ps.setString(3, type);
                ps.setString(4, desc);
                ps.setInt(5, Integer.parseInt(landmarks == null || landmarks.isEmpty() ? "0" : landmarks));
                ps.setString(6, level);
                int c = ps.executeUpdate();
                out.println("<p>Inserted " + c + " row(s) for region <b>" + id + "</b>.</p>");
            } catch (SQLException e) {
                out.println("<p style='color:red;'>Error during insert: " + e.getMessage() + "</p>");
            }
        } else if ("update".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            String desc = request.getParameter("description");
            String sql = "UPDATE regions SET description=? WHERE id=?";
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, desc);
                ps.setString(2, id);
                int c = ps.executeUpdate();
                out.println("<p>Updated " + c + " row(s) for region <b>" + id + "</b>.</p>");
            } catch (SQLException e) {
                out.println("<p style='color:red;'>Error during update: " + e.getMessage() + "</p>");
            }
        }

        out.println("<p><a href='Lab9.html'>Back</a></p>");
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = request.getParameter("view");
        String id = request.getParameter("id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body style='font-family:Arial, sans-serif;padding:20px;'>");
        out.println("<h2>Region Data</h2>");

        String sql;
        if ("byid".equalsIgnoreCase(view) && id != null && !id.trim().isEmpty()) {
            sql = "SELECT * FROM regions WHERE id = ?";
        } else {
            sql = "SELECT * FROM regions";
        }

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (sql.contains("WHERE")) ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                out.println("<table border='1' cellpadding='6' style='border-collapse:collapse;'>");
                out.println("<tr style='background:#4a7ba7;color:#fff;'><th>ID</th><th>Name</th><th>Type</th><th>Description</th><th>Landmarks</th><th>Level</th></tr>");
                int rows = 0;
                while (rs.next()) {
                    rows++;
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("id") + "</td>");
                    out.println("<td>" + rs.getString("name") + "</td>");
                    out.println("<td>" + rs.getString("type") + "</td>");
                    out.println("<td>" + rs.getString("description") + "</td>");
                    out.println("<td align='center'>" + rs.getInt("landmarks") + "</td>");
                    out.println("<td>" + rs.getString("explorationlevel") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                if (rows == 0) out.println("<p>No records found.</p>");
            }
        } catch (SQLException e) {
            out.println("<p style='color:red;'>Error during select: " + e.getMessage() + "</p>");
        }

        out.println("<p><a href='Lab9.html'>Back</a></p>");
        out.println("</body></html>");
    }
}
