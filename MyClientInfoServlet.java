import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
public class MyClientInfoServlet extends HttpServlet
{
   protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
   {
        resp.setContentType("text/html");
        PrintWriter pw=resp.getWriter();
        pw.println("<html>");
        pw.println("<head><title>Client Details Servlet</title>");
        pw.println("<style>table,tr,td{background-color:black;color:white;}</style></head>");
        pw.println("<body>");

        String compName=req.getRemoteHost();
        String compIp=req.getRemoteAddr();

        pw.println("<h2>Client computer name:"+compName+"</h2>");
        pw.println("<h2>Client computer ip:"+compIp+"</h2>");
        pw.println("<table border='2'>");
        pw.println("<tr><th>Header Name</th><th>Header Value</th></tr>");

        Enumeration en=req.getHeaderNames();

        while(en.hasMoreElements()){
            String headerName=(String)en.nextElement();
            String headerValue=req.getHeader(headerName);
            pw.println("<tr><td>"+headerName+"</td><td>"+headerValue+"</td></tr>");
        }
        
        pw.println("</table>");
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
   }
}
