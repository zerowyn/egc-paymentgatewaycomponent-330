/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.Header;
import com.eg.egsc.framework.client.dto.RequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * super class of api test
 *
 * @author gucunyang
 * @since 2018-02-27
 */
public class BaseApiTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseApiTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    public void getHead(String url, RequestDto requestDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String dto = objectMapper.writeValueAsString(requestDto);
            RequestBuilder request = MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
                    .content(dto.getBytes());
            mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
