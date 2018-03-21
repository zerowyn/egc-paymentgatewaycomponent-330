/**
 * @Probject Name: scp-pay-gateway-app-client
 * @Path: com.eg.egsc.scp.paygateway.RequestForGetOpenIdDto.java
 * @Create By lihui
 * @Create In 2018年3月19日 上午：10:10
 */
package com.eg.egsc.scp.paygateway.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;
import org.springframework.stereotype.Component;

/**
 * @Class Name CreateOrderRequestForBackendDto
 * @Author lihui
 * @Create In 2018年2月25日
 */
@Component
public class RequestForGetOpenIdDto extends BaseBusinessDto{

    /**
     * H5页面传来的code
     */
    private String code;

    /**
     * 公众号的唯一标识
     */
    private String appid;

    /**
     * 公众号公众号的appsecret
     */
    private String secret;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
