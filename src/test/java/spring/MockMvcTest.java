package spring;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import sspri.Ctrl;
import sspri.SpringConf;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SpringConf.class)
public class MockMvcTest {
  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;
  
  @Autowired Ctrl control;
  
  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void testBody() throws Exception
  {
    assertNotNull(wac);
    assertNotNull(control);
    mockMvc.perform(MockMvcRequestBuilders.get("/body"))
    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
    .andExpect(MockMvcResultMatchers.content().string("body"))
    //.andDo(MockMvcResultHandlers.print())
    ;
  }
  
  @Test
  public void testView() throws Exception
  {
    mockMvc.perform(MockMvcRequestBuilders.get("/view"))
    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
    .andExpect(MockMvcResultMatchers.view().name("hello"))
    .andDo(MockMvcResultHandlers.print());
  }
  
  @Test
  public void testJey() throws Exception
  {
    mockMvc.perform(MockMvcRequestBuilders.get("/jey"))
    .andDo(MockMvcResultHandlers.print());
  }
  
}

