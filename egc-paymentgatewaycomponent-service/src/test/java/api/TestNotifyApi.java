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
 * @Create In 2018年3月5日 下午3:08:41 TODO
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
//    @Transactional
//    @Rollback(false)
    public void test() throws Exception {
        String s = notifyApi.weixinNotifyResult(this.getString());
        logger.info(s);
    }

    @Test
//    @Transactional
//    @Rollback(false)
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

}
