/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.paygateway.dto.DefValSettingsDelDto;
import com.eg.egsc.scp.paygateway.dto.DefValSettingsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.service.DefValSettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant.SUCCESS_CODE;

/**
 * 缴费网关默认设置表Api
 *
 * @author gucunyang
 * @since 2018-02-11
 */
@Api(description = "缴费网关默认设置表")
@RestController
@RequestMapping(value = "/api/defValSettings")
public class DefValSettingsApi extends BaseApiController {

    @Autowired
    DefValSettingsService defValSettingsServiceImpl;

    @ApiOperation(value = "新增默认设置表信息")
    @RequestMapping(value = "/insertDefValSetting", method = RequestMethod.POST)
    public ResponseDto insertDefValSettings(@RequestBody RequestDto<DefValSettingsDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        DefValSettingsDto defValSettingsDto = requestDto.getData();
        defValSettingsServiceImpl.insertDefValSettings(defValSettingsDto);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setMessage("新增默认设置表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "删除默认设置表信息")
    @RequestMapping(value = "/deleteDefValSetting", method = RequestMethod.POST)
    public ResponseDto deleteDefValSettings(@RequestBody RequestDto<DefValSettingsDelDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        DefValSettingsDelDto defValSettingsDelDto = requestDto.getData();
        List<String> defValSettingsUuids = defValSettingsDelDto.getDefValSettingsUuids();
        defValSettingsServiceImpl.deleteDefValSettings(defValSettingsUuids);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setMessage("删除默认设置表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "修改默认设置表信息")
    @RequestMapping(value = "/updateDefValSetting", method = RequestMethod.POST)
    public ResponseDto updateDefValSettings(@RequestBody RequestDto<DefValSettingsDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        DefValSettingsDto defValSettingsDto = requestDto.getData();
        defValSettingsServiceImpl.updateDefValSettings(defValSettingsDto);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setMessage("修改默认设置表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "查询默认设置表信息")
    @RequestMapping(value = "/getDefValSetting", method = RequestMethod.POST)
    public ResponseDto getDefValSettingsByUuid(@RequestBody RequestDto<DefValSettingsDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        DefValSettingsDto defValSettingsDto = requestDto.getData();
        String uuid = defValSettingsDto.getUuid();
        defValSettingsDto = defValSettingsServiceImpl.getDefValSettingsByUuid(uuid);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setData(defValSettingsDto);
        responseDto.setMessage("查询默认设置表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "分页查询默认设置表信息")
    @RequestMapping(value = "/listDefValSettings", method = RequestMethod.POST)
    public ResponseDto getDefValSettingsList(@RequestBody RequestDto<PageQueryDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        PageQueryDto pageQueryDto = requestDto.getData();
        List<DefValSettingsDto> defValSettingsDtoList = defValSettingsServiceImpl.getDefValSettingsList(pageQueryDto);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setData(defValSettingsDtoList);
        responseDto.setMessage("分页查询默认设置表信息成功");
        return responseDto;
    }

}
