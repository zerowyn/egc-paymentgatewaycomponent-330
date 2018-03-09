package api;

import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.api.NotifyApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: TestNotifyApi.java
 * @Create By fandong
 * @Create In 2018年3月5日 下午4:25:46
 */

/**
 * @Class Name TestNotifyApi
 * @Author fandong
 * @Create In 2018年3月5日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
public class TestNotifyApi extends AbstractUnitTestSupport {

    @Autowired
    private NotifyApi notifyApi;

    @Test
    public void testWeiXin() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String dto = objectMapper.writeValueAsString(this.getString());
        RequestBuilder request = MockMvcRequestBuilders.post("/pay/weixinNotifyResult")
                .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());
        MvcResult mvcResult = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String string = response.getContentAsString();
        logger.info(string + "&&&&&&&&&");
    }

    public String getString() {
        return "<xml>\r\n" + "<return_code><![CDATA[SUCCESS]]></return_code>\r\n"
                + "  <appid><![CDATA[wx5332d47f724492fa]]></appid>\r\n" + "  <mch_id><![CDATA[1497973582]]></mch_id>\r\n"
                + "   <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\r\n"
                + "  <sign><![CDATA[C276B6A85A3FE93B52A36D1D433BA163]]></sign>\r\n"
                + "  <result_code><![CDATA[SUCCESS]]></result_code>\r\n"
                + "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\r\n"
                +"<trade_type><![CDATA[JSAPI]]></trade_type>\r\n"
                +"<bank_type><![CDATA[CFT]]></bank_type>\r\n"
                +"<total_fee>1</total_fee>\r\n"
                +"<cash_fee>1</cash_fee>\r\n"
                +"<out_trade_no><![CDATA[44444444444441444]]></out_trade_no>\r\n"
                +" <time_end><![CDATA[20140903131540]]></time_end>\r\n"
                +  "</xml>";
    }

    @Test
    public void testAlipay() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String dto = objectMapper.writeValueAsString(this.alipayString());
        RequestBuilder request = MockMvcRequestBuilders.get("/pay/alipayNotifyResult")
                .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());
        MvcResult mvcResult = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String string = response.getContentAsString();
        logger.info(string + "&&&&&&&&&");
    }

    public String alipayString(){
        return "?app_id=2018020102122242&charset=utf-8&notify_id=ac05099524730693a8b330c5ecf72da9786&notify_time=2016-07-19 14:10:49&notify_type=trade_status_sync&out_trade_no=2018022300007150&trade_no=2013112011001004330000121536&version=1.0&sign=pFDrP4R21/DSD2z1Dn3j4PTRg2ZhHW491ynPagH22wTpH658wZ2D9/izUyOrBMtbTXMhadzcwSIiWjbaG4lydSoGljhM9MeYczcD454CuVZi83P9DhrFhM1xn3XtRIlGq4UNkA4ckIBQnzoiuWZPzWFIWIvoPe6nyXutU139wajYTkx+IamzPJZZivaWoWysT4tZiny3eQbdjuVGr3RuIe9GBEVlDRhPJJLJOvrSSHd0BqCvAuBv1tGxhH/ZseguV5JOHGCBWgUJw+oWf9+kJQBNC2uzUzTkOXW1O8xUyANPOKXoBJfWt1PFmelBDl3yNaZK0KLRzNdgk2OXnAt0Bg==";
    }

}
