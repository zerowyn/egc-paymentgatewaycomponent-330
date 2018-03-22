package com.eg.egsc.scp.paygateway.service.model;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

@Component
@XmlRootElement(name = "xml")
public class WeiXinNotifyResponse {

    private String returnCode;
    private String returnMsg;


    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }


    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
