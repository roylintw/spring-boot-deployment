package com.example.springbootdeployment.controller;

import com.example.springbootdeployment.vo.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register() throws Exception {
        Member member = new Member();
        member.setAccount("Roy");
        member.setPassword("123");
        member.setMemberName("林阿憲");
        member.setGender("man");

        String json = objectMapper.writeValueAsString(member);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/register")
                .header("Content-Type", "application/json")
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));

        // 測試是否能夠使用這個帳號登入
        RequestBuilder loginRequestBuilder = MockMvcRequestBuilders
                .post("/memberLogin")
                .with(httpBasic("Roy", "123"));

        mockMvc.perform(loginRequestBuilder)
                .andExpect(status().is(200));

        // 新帳號可以請求 hello world
        RequestBuilder helloWorldRequestBuilder = MockMvcRequestBuilders
                .get("/helloWorld")
                .with(httpBasic("Roy", "123"));

        mockMvc.perform(helloWorldRequestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    void memberLogin() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/memberLogin")
                .with(httpBasic("Jack", "123"));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }
}