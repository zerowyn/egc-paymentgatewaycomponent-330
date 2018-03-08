package com.eg.egsc.scp.paygateway.util;
import com.eg.egsc.scp.paygateway.exception.PaymentGatewayException;
import com.eg.egsc.scp.paygateway.service.model.WeiXinNotifyResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class ObjecTransformXML {

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
            e.printStackTrace();
            throw new PaymentGatewayException(ErrorCodeConstant.XML_ABNORMAL_CONVERSION);
        }

        return xmlString;

    }
}
