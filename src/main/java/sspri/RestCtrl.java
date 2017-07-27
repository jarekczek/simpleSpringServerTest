package sspri;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCtrl {

  @RequestMapping("/jey")
  public Answer jey()
  {
    Answer ans = new Answer();
    ans.ans = "oooo";
    return ans;
  }

  public static class Answer
  {
    public String ans;
  }
  
}
