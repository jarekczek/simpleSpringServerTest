package server;

import java.util.Collections;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.SpringServletContainerInitializer;

/**
Listener is needed, because I wanted to use a thin jar, without
spring libraries etc. A thin jar reload at Glassfish for 4-5 sec,
while fat jar gives 15-25 sec.

Problems where discovered when trying to use thin jar on Glassfish.
SpringServletContainerInitializer which @HandleTypes WebApplicationInitalizer
was not receiving our web initializer. Probably due to a different classloader
involved. So a workaround is applied. If at servlet startup
there is no "dispatcher" servlet visible, the initialization is done manually.
These problems were not observable on Tomcat.
*/
@WebListener
public class EEServerSetup implements ServletContextListener
{

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    Logger.getLogger("bs").info("our class loader: "
      + this.getClass().getClassLoader());
    ServletContext sc = sce.getServletContext();
    Logger.getLogger("bs").info("servlet registrations: "
      + sc.getServletRegistrations().size());
    boolean springActive = sc.getServletRegistrations().keySet().stream()
      .anyMatch(k -> k == "dispatcher");
    if (springActive) {
      // On Tomcat it's always so, on Glassfish only with fat war.
      Logger.getLogger("bs").info("Spring dispatcher servlet already loaded.");
    }
    else {
      // Seems to be necessary only on Glassfish with thin war.
      Logger.getLogger("bs").info("Loading spring dispatcher servlet manually.");
      try {
        SpringServletContainerInitializer ssci =
          new SpringServletContainerInitializer();
        Logger.getLogger("bs").info("spring containter class loader: "
          + ssci.getClass().getClassLoader());
        ssci.onStartup(Collections.singleton(SpringWebSetup.class),
                       sce.getServletContext());
      } catch (Exception e) {
        e.printStackTrace();
        Logger.getAnonymousLogger().severe(
          "SpringServletContainerInitializer failed." + e.getMessage());
      }
    }
    Logger.getAnonymousLogger().info("context init done");
  }

}
