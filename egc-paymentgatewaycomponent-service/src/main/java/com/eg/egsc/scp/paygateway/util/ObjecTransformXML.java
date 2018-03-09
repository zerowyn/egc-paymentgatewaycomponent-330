package com.eg.egsc.scp.paygateway.util;
import com.eg.egsc.scp.paygateway.exception.PaymentGatewayException;
import com.eg.egsc.scp.paygateway.service.model.WeiXinNotifyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class ObjecTransformXML {

    private static final Logger logger = LoggerFactory.getLogger(ObjecTransformXML.class);

    public static String jaxbRequestObjectToXMLForWeiXin(WeiXinNotifyResponse weiXinNotifyResponse) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(WeiXinNotifyResponse.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            m.marshal(weiXinNotifyResponse, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            logger.error(e.getMessage());
            throw new PaymentGatewayException(ErrorCodeConstant.ABNORMAL_CONVERSION);
        }

        return xmlString;

    }
}
