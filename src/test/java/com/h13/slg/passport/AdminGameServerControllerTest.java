package com.h13.slg.passport;

import com.alibaba.fastjson.JSON;
import com.h13.slg.config.PropertiesConfiguration;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.passport.PassportResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by sunbo on 14-11-8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
@WebAppConfiguration
public class AdminGameServerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        try {
            PropertiesConfiguration.getInstance().addResource("/slg-passport.properties");
        } catch (RequestFatalException e) {
            e.printStackTrace();
        }
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void list() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/gameserver/list")
        ).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        PassportResponse r = JSON.parseObject(result.getResponse().getContentAsString(), PassportResponse.class);
    }

    @Test
    public void create() throws Exception {
        MvcResult result = mockMvc.perform(post("/admin/gameserver/create")
                        .param("name", "fdsafdsa")
                        .param("ip", "10.0.0.1")
                        .param("port", "432")
                        .param("opentime", "2014-01-01")
        ).andReturn();
        PassportResponse r = JSON.parseObject(result.getResponse().getContentAsString(), PassportResponse.class);
        System.out.println(r.end());
    }


    @Test
    public void index() throws Exception {
        MvcResult result = mockMvc.perform(post("/admin/gameserver/4")
        ).andReturn();
        PassportResponse r = JSON.parseObject(result.getResponse().getContentAsString(), PassportResponse.class);
        System.out.println(r.end());
    }


    @Test
    public void update() throws Exception {
        MvcResult result = mockMvc.perform(post("/admin/gameserver/update")
                        .param("name", "fdsafdsa")
                        .param("ip", "10.0.0.122")
                        .param("port", "222222")
                        .param("opentime", "2014-01-01")
                        .param("id", "4")
        ).andReturn();
        PassportResponse r = JSON.parseObject(result.getResponse().getContentAsString(), PassportResponse.class);
        System.out.println(r.end());
    }

    @Test
    public void delete() throws Exception {
        MvcResult result = mockMvc.perform(post("/admin/gameserver/delete")
                        .param("id", "4")
        ).andReturn();
        PassportResponse r = JSON.parseObject(result.getResponse().getContentAsString(), PassportResponse.class);
        System.out.println(r.end());
    }

}
