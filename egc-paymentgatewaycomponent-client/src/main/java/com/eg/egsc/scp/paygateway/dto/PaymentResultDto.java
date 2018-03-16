package com.eg.egsc.scp.paygateway.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

public class PaymentResultDto extends BaseBusinessDto {
  /**
   * @Field long serialVersionUID 
   */
  private static final long serialVersionUID = 1L;
  private Object informStr;// 封装请求参数
  private String platfrom;

  public Object getInformStr() {
    return informStr;
  }

  public void setInformStr(Object informStr) {
    this.informStr = informStr;
  }

  /**
   * @Return the String platfrom
   */
  public String getPlatfrom() {
    return platfrom;
  }

  /**
   * @Param String platfrom to set
   */
  public void setPlatfrom(String platfrom) {
    this.platfrom = platfrom;
  }


}
