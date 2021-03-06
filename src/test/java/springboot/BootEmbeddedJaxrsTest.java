package springboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
  webEnvironment = WebEnvironment.RANDOM_PORT,
  classes = MySpringBootConfig.class
)
public class BootEmbeddedJaxrsTest {
  @LocalServerPort int port;
  
  @Test
  public void testRoot() {
    System.out.println("server port: " + port);
    Response resp = ClientBuilder.newClient()
      .target("http://localhost:" + port + "/")
      .request()
      .get();
    String respText = resp.readEntity(String.class);
    System.out.println("resp: " + resp);
    assertEquals(200, resp.getStatus());
    assertEquals("root", respText);
  }

  @Test
  public void testView() throws InterruptedException {
    System.out.println("server port: " + port);
    Response resp = ClientBuilder.newClient()
      .target("http://localhost:" + port + "/view")
      .request()
      .get();
    String respText = resp.readEntity(String.class);
    System.out.println("resp: " + resp);
    Thread.sleep(3);
    assertEquals(200, resp.getStatus());
    assertTrue(respText.contains("<title>Hello</title>"));
  }
}
