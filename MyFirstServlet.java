import javax.servlet.*;
import java.io.*;
public class MyFirstServlet extends GenericServlet
{
  public void service(ServletRequest req, ServletResponse resp)throws ServletException,IOException
{
    resp.setContentType("text/html");
    PrintWriter pw=resp.getWriter();
    pw.println("<html>");
    pw.println("<head><title>First Servlet App</title></head>");
    pw.println("<body>");
    pw.println("<h2>Welcome To Java Backend </h2>");
    pw.println("</body>");
    pw.println("</html>");
    pw.close();
}
}