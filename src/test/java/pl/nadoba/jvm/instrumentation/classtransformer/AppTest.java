package pl.nadoba.jvm.instrumentation.classtransformer;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.HttpURLConnection;
import java.net.URL;

public class AppTest extends TestCase {

    public void testHello() throws Exception {
        Logger.getRootLogger().info("Starting a standard test - HelloWebServlet usual behaviour");

        Server server = new Server(1223);
        ServletContextHandler context = new ServletContextHandler();
        ServletHolder defaultServ = new ServletHolder("default", HelloWebServlet.class);
        defaultServ.setInitParameter("resourceBase", System.getProperty("user.dir"));
        defaultServ.setInitParameter("dirAllowed", "true");
        context.addServlet(defaultServ, "/");
        server.setHandler(context);

        server.start();

        HttpURLConnection http = (HttpURLConnection) new URL("http://localhost:1223/hello?name=Adam").openConnection();
        http.connect();
        assertTrue(http.getResponseCode() == 200);

        server.stop();
    }
}
