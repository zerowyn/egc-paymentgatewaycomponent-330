/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

/**
 * 提供应用与组件调用组装的UserDto
 * @author wanghongben
 * @since 2018年1月11日
 */
public class UserDto extends BaseBusinessDto {
  /**
   * @Field long serialVersionUID 
   */
  private static final long serialVersionUID = 1L;
  @NotBlank(message = "demo.usermgnt.user.id.notblank")
  private String id;
  private String name;


  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }

}
