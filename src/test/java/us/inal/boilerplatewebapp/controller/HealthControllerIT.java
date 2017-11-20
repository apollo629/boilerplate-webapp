package us.inal.boilerplatewebapp.controller;

/**
 * Created by alpereninal on 20/11/17.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import us.inal.boilerplatewebapp.BoilerplateWebappApplication;

import static org.junit.Assert.assertEquals;


// http://www.springboottutorial.com/integration-testing-for-spring-boot-rest-services
@RunWith(SpringRunner.class)
//Launch the entire Spring Boot Application on a Random Port
@SpringBootTest(classes = BoilerplateWebappApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthControllerIT {

    // Autowire the random port into the variable so that we can use it create the url.
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    //Utility method to create the url given an uri. It appends the port.
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void heartbeatListenerIT(){
        // We use entity so that we have the flexibility of adding in request headers in future.
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        // Fire a GET request to the specify uri and get the response as a String.
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/servers/server1/heartbeat"), HttpMethod.GET, entity, String.class);

        String expected = "{\"server1\":\"OK\"}";

        assertEquals(expected, response.getBody());
    }


}
