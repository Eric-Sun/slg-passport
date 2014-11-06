package com.h13.slg.passport;

import com.alibaba.fastjson.JSON;
import com.h13.slg.config.PropertiesConfiguration;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.passport.core.PassportResponse;
import org.junit.Assert;
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

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Petri Kainulainen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
@WebAppConfiguration
public class AccountControllerTest {

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
    public void login() throws Exception {
        MvcResult result = mockMvc.perform(get("/account/login")
                .param("name", "aaa")
                .param("password", "bbb")).andReturn();
        PassportResponse r = JSON.parseObject(result.getResponse().getContentAsString(), PassportResponse.class);
        System.out.println(r.end());
        Assert.assertEquals(0, r.getCode());
        Assert.assertEquals(1, ((Map) r.getData().get("account")).get("id"));
    }


    @Test
    public void registerForDuplicateName() throws Exception {
        MvcResult result = mockMvc.perform(get("/account/register")
                .param("name", "aaaa")
                .param("password", "bbb")).andReturn();
        PassportResponse r = JSON.parseObject(result.getResponse().getContentAsString(), PassportResponse.class);
        Assert.assertEquals(1004, r.getCode());
    }


    @Test
    public void checkToken() throws Exception {
        MvcResult result = mockMvc.perform(get("/account/checkToken")
                .param("token", "1415275936036")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

}