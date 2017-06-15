package sspri;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Ctrl {
  @Autowired private ApplicationContext ac;  
  
  @ResponseBody
  @RequestMapping("/body")
  public String body()
  {
    return "body";
  }
  
  @ResponseBody
  @RequestMapping("/")
  public String root()
  {
    return "root";
  }

  @ResponseBody
  @RequestMapping("/help")
  public String root(HttpServletRequest req)
  {
    return "<a href='" + req.getContextPath() + "/static_help.html'>"
      + "here you get help</a>";
  }

  @RequestMapping("/view")
  public String view()
  {
    return "hello";
  }
  
  @RequestMapping("/beans")
  @ResponseBody
  public String beans(HttpServletResponse resp)
  {
    resp.setContentType("text/plain");
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    for (String name: ac.getBeanDefinitionNames()) {
      pw.println("bean " + name
        + " of class " + ac.getBean(name).getClass());
    }
    return sw.toString();
  }
}
