package pl.nadoba.jvm.instrumentation.classtransformer;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/hello")
public class HelloWebServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter writer = response.getWriter();

        String addressee = getAddressee(request);
        String message = "Hello " + addressee + "!";

        writer.write("<html><body><h2>" + message + "</h2></body></html>");
        writer.close();
        Logger.getRootLogger().info("HelloWebServlet says 'Hello " + addressee + "!'");
    }

    private String getAddressee(HttpServletRequest request) {
        String addressee = request.getParameter("name");
        return (addressee == null) ? "World" : addressee;
    }
}
