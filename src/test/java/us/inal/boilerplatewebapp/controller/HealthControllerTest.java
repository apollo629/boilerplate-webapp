package us.inal.boilerplatewebapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.Assert.assertEquals;

/**
 * Created by alpereninal on 20/11/17.
 */

//http://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services

//SpringRunner is short hand for SpringJUnit4ClassRunner which extends BlockJUnit4ClassRunner providing the functionality to launch a Spring TestContext Framework.
@RunWith(SpringRunner.class)
/*
WebMvcTest annotation is used for unit testing Spring MVC application.
This can be used when a test focuses only Spring MVC components.
In this test, we want to launch only HealthController. All other controllers and mappings will not be launched when this unit test is executed.
 */
@WebMvcTest(value = HealthController.class, secure = false)
public class HealthControllerTest {

    /*
    MockMvc is the main entry point for server-side Spring MVC test support.
    It allows us to execute requests against the test context.
     */
    @Autowired
    private MockMvc mockMvc;

    /*
     MockBean is used to add mocks to a Spring ApplicationContext.
     A mock of healthService is created and auto-wired into the HealthController.
     */
    //@MockBean
    //private HealtService healthService;

    @Test
    public void healthListenerTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/servers/server1/heartbeat");
        //accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "{\"server1\":\"OK\"}";
        assertEquals(expected, result.getResponse().getContentAsString());
    }



}
