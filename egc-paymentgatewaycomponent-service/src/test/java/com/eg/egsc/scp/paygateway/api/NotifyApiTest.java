package com.eg.egsc.scp.paygateway.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.eg.egsc.config.AbstractUnitTestSupport;
import com.eg.egsc.framework.client.dto.Header;
import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.dto.PaymentResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class NotifyApiTest extends AbstractUnitTestSupport {

    @Test
    public void testWeiXin() throws Exception {
        PaymentResultDto paymentResultDto = new PaymentResultDto();
        paymentResultDto.setInformStr(this.getString());
        paymentResultDto.setPlatfrom("WEIXIN");
        RequestDto<PaymentResultDto> requestDto = new RequestDto<PaymentResultDto>();
        requestDto.setData(paymentResultDto);
        requestDto.setHeader(this.getHeader());

        ObjectMapper objectMapper = new ObjectMapper();
        String dto = objectMapper.writeValueAsString(requestDto);
        RequestBuilder request = MockMvcRequestBuilders.post("/pay/notifyResult")
                .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());
        MvcResult mvcResult = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String string = response.getContentAsString();
        logger.info(string + "&&&&&&&&&");
    }

    /**
     * @return Header
     * @Methods Name getHeader
     * @Create In 2018年3月13日 By fandong
     */
    private Header getHeader() {
        Header header = new Header();
        header.setBusinessId("string");
        header.setCharset("utf-8");
        header.setSourceSysId("fyrehh");
        header.setContentType("application/json");
        HashMap<String, Object> extMap = new HashMap<>();
        extMap.put("test", "test01");
        header.setExtAttributes(extMap);
        return header;
    }

    public String getString() {
        return "<xml>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<appid><![CDATA[wx5332d47f724492fa]]></appid>\n" +
                "<mch_id><![CDATA[1497973582]]></mch_id>\n" +
                "<nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><sign><![CDATA[F1F70604572199F6E4875D88738B89A7]]></sign> " +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<total_fee>1</total_fee>\n" +
                "<cash_fee>1</cash_fee>\n" +
                "<out_trade_no><![CDATA[alipayd794aa46e89cb775c3b3ba7f2c]]></out_trade_no>\n" +
                "<time_end><![CDATA[20140903131540]]></time_end>\n" +
                "</xml>";
    }

    @Test
    public void testAlipay() throws Exception {
        PaymentResultDto paymentResultDto = new PaymentResultDto();
        paymentResultDto.setInformStr(this.alipayString());
        paymentResultDto.setPlatfrom("alipay");
        RequestDto<PaymentResultDto> requestDto = new RequestDto<PaymentResultDto>();
        requestDto.setData(paymentResultDto);
        requestDto.setHeader(this.getHeader());

        ObjectMapper objectMapper = new ObjectMapper();
        String dto = objectMapper.writeValueAsString(requestDto);
        RequestBuilder request = MockMvcRequestBuilders.post("/pay/notifyResult")
                .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());
        MvcResult mvcResult = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String string = response.getContentAsString();
        logger.info(string + "&&&&&&&&&");
    }

    public Map alipayString() {
        Map<String,Object> map = new HashMap<>();
        map.put("app_id", "2018020102122242");
        map.put("charset", "utf-8");
        map.put("notify_id", "ac05099524730693a8b330c5ecf72da9786");
        map.put("notify_time", "2016-07-19 14:10:49");
        map.put("notify_type", "trade_status_sync");
        map.put("out_trade_no", "alipayd794aa46e89cb775c3b3ba7f2c");
        map.put("trade_no", "2013112011001004330000121536");
        map.put("version", "1.0");
        map.put("sign", "oKuh73ymSAEbNWZyJnE4dewJwKnVSO+ouQL+PFAfZmpNOqoMGqM7CKeBT5ihPi9oV/tgxy2oxcE4TVx811b4YqTEpQablsma0M9+PNjWMWpfBe+4Uf5NvUUUJwB1GZAzhXc9rKWTInF6VwMAERqTZLawdjjXis32flWwrgQLyLQvxFpAvEN5CDnv9YBwq8UKYSVGVuc8W+peLRiKR96m60zdzwOPzU4wSpoy3nxYQCezvlHfJPdtDVXhs7nAKhIzARNwDE0f4zXrPASJ6eFA9H17AfOeAqAYlYyCB4AOQfVbk8O1leOTFABBDpOTTfq03zrJ3ytP+Rg/gYyeJ9nutA==");

        return map;

    }

}
