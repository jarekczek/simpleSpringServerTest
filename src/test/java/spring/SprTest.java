package spring;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sspri.Ctrl;
import sspri.SpringConf;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SpringConf.class)
@Configuration
@ComponentScan(basePackages = "secu")
public class SprTest {
  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;
  
  @Autowired Ctrl control;
  
  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void test1() throws Exception
  {
    assertNotNull(wac);
    assertNotNull(control);
    System.out.println(mockMvc.perform(get("/open")).andReturn().getResponse().getContentAsString());
  }
}

