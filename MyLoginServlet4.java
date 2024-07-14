import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.sql.*;
public class MyLoginServlet4 extends HttpServlet
{   
    private PreparedStatement ps;
    public void init()throws ServletException
    {
        System.out.println("init called");
        ServletContext ctxt=super.getServletContext();
        ServletConfig cfg=super.getServletConfig();
        Connection conn=(Connection)ctxt.getAttribute("dbConn");
        System.out.println("Connection is:"+conn);
        try{
        if(conn==null){
            throw new SQLException();
        }
        String qry=cfg.getInitParameter("qry");
         ps=conn.prepareStatement(qry);
        System.out.println("query set successfully!");
    }
        catch(SQLException ex){
           System.out.println("Exception in Login:");
            ServletException  se=new ServletException(ex.getMessage());
           throw se;
        }
    }
        protected  void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
        {
                String userid=req.getParameter("userid");
                String userpwd=req.getParameter("userpwd");
                PrintWriter pw=resp.getWriter();
                resp.setContentType("text/html");
                pw.println("<html><head><title>Login Response</title></head>");
                pw.println("<body>");

                try{
                    ps.setString(1,userid);
                    ps.setString(2,userpwd);
                    ResultSet rs=ps.executeQuery();
                    if(rs.next())
                    {
                        String username=rs.getString(1);
                        pw.println("<p>Hello <strong>"+username+"</strong><br>Welcome to our site!</p>");
                    }
                    else{
                        pw.println("<p>Sorry! You cannot login<br>Invalid credential<br>");
                        pw.println("<a href='signin.html'>Try again</a><br>");
                        pw.println("<a href='signup.html'>New User ?</a><br></p>");

                    }
                }catch(SQLException ex){
                    pw.println("<p>Server is experiencing heavy traffic.Please try later</p>");
                    System.out.println("Exception in doPost:"+ex);
                 }
                 finally{
                    pw.println("</body>");
                    pw.println("</html>");
                 }
        }
        
   
}
