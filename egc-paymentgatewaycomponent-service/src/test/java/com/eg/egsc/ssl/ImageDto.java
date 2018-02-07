/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.ssl;

import java.util.HashMap;
import java.util.Map;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

/**
 * TODO
 * @author songjie
 * @since 2018年1月24日
 */
public class ImageDto extends BaseBusinessDto {
	// @Field long serialVersionUID 
	
	private static final long serialVersionUID = 1L;
	
	private String id = "123";
	
	private String businessId = "DM0000000119";
	private Map<String, Object> extAttributes = new HashMap<String, Object>();
	private String[] data = new String[]{};
    private String fileData="sadsa";
    private String sourceSysId="demo";
    private String targetSysId="api";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	public Map<String, Object> getExtAttributes() {
		return extAttributes;
	}
	public void setExtAttributes(Map<String, Object> extAttributes) {
		this.extAttributes = extAttributes;
	}
	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	public String getSourceSysId() {
		return sourceSysId;
	}
	public void setSourceSysId(String sourceSysId) {
		this.sourceSysId = sourceSysId;
	}
	public String getTargetSysId() {
		return targetSysId;
	}
	public void setTargetSysId(String targetSysId) {
		this.targetSysId = targetSysId;
	}  
    
    
}
