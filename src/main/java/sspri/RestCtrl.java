package sspri;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCtrl {

  @RequestMapping("/jey")
  public Answer jey(@RequestBody @Valid Question q)
  {
    Answer ans = new Answer();
    ans.ans = "no " + q.q;
    return ans;
  }

  public static class Answer
  {
    public String ans;
  }

  public static class Question
  {
    @Size(min = 4)
    public String q;
  }
  
}
