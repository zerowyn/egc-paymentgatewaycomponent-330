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
        return "<xml>\r\n" + "<appid><![CDATA[wx2421b1c4370ec43b]]></appid>\r\n"
                + "  <attach><![CDATA[支付测试]]></attach>\r\n" + "  <bank_type><![CDATA[CFT]]></bank_type>\r\n"
                + "  <fee_type><![CDATA[CNY]]></fee_type>\r\n"
                + "  <is_subscribe><![CDATA[Y]]></is_subscribe>\r\n"
                + "  <mch_id><![CDATA[10000100]]></mch_id>\r\n"
                + "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\r\n"
                + "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\r\n"
                + "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\r\n"
                + "  <result_code><![CDATA[SUCCESS]]></result_code>\r\n"
                + "  <return_code><![CDATA[SUCCESS]]></return_code>\r\n"
                + "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\r\n"
                + "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\r\n"
                + "  <time_end><![CDATA[20140903131540]]></time_end>\r\n"
                + "  <total_fee>1</total_fee><coupon_fee><![CDATA[10]]></coupon_fee>\r\n"
                + "<coupon_count><![CDATA[1]]></coupon_count>\r\n"
                + "<coupon_type><![CDATA[CASH]]></coupon_type>\r\n"
                + "<coupon_id><![CDATA[10000]]></coupon_id>\r\n"
                + "<coupon_fee><![CDATA[100]]></coupon_fee>\r\n"
                + "  <trade_type><![CDATA[JSAPI]]></trade_type>\r\n"
                + "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\r\n"
                + "</xml>\r\n" + "";
    }

}
