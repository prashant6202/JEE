import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
public class MyUserDataServlet extends HttpServlet
{
   protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
   {
        resp.setContentType("text/html");
        PrintWriter pw=resp.getWriter();
        pw.println("<html>");
        pw.println("<head><title>User Details</title>");
        pw.println("<body>");
        pw.println("<h2>Form Data</h2>");
        String name=req.getParameter("userName");
        String gender=req.getParameter("gender");
        String []hobbies=req.getParameterValues("hobbies");
        String hb="";
        for (String h : hobbies) {
            hb=hb+h+",";
        };
        hb=hb.substring(0, hb.length()-1);
        pw.println("<b>Your name: </b>"+name);
        pw.println("</br>");
        pw.println("<b>Your gender: </b>"+gender);
        pw.println("</br>");
        pw.println("<b>Your hobbies: </b>"+hb);
        pw.println("</br>");
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
   }
}
