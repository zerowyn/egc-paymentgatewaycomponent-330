/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.paygateway.dto.ConfigsDelDto;
import com.eg.egsc.scp.paygateway.dto.ConfigsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.service.ConfigsService;
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
 * 缴费网关配置表Api
 *
 * @author gucunyang
 * @since 2018-02-11
 */
@Api(value = "缴费网关配置表")
@RestController
@RequestMapping(value = "/api/configs")
public class ConfigsApi extends BaseApiController {

    @Autowired
    ConfigsService configsServiceImpl;

    @ApiOperation(value = "新增配置表信息")
    @RequestMapping(value = "/insertConfig", method = RequestMethod.POST)
    public ResponseDto insertConfigs(@RequestBody RequestDto<ConfigsDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        ConfigsDto configsDto = requestDto.getData();
        configsServiceImpl.insertConfigs(configsDto);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setMessage("新增配置表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "删除配置表信息")
    @RequestMapping(value = "/deleteConfig", method = RequestMethod.POST)
    public ResponseDto deleteConfigs(@RequestBody RequestDto<ConfigsDelDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        ConfigsDelDto configsDelDto = requestDto.getData();
        List<String> configsUuids = configsDelDto.getConfigsUuids();
        configsServiceImpl.deleteConfigs(configsUuids);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setMessage("删除配置表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "修改配置表信息")
    @RequestMapping(value = "/updateConfig", method = RequestMethod.POST)
    public ResponseDto updateConfigs(@RequestBody RequestDto<ConfigsDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        ConfigsDto configsDto = requestDto.getData();
        configsServiceImpl.updateConfigs(configsDto);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setMessage("修改配置表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "查询配置表信息")
    @RequestMapping(value = "/getConfig", method = RequestMethod.POST)
    public ResponseDto getConfigsByUuid(@RequestBody RequestDto<ConfigsDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        ConfigsDto configsDto = requestDto.getData();
        String uuid = configsDto.getUuid();
        configsDto = configsServiceImpl.getConfigsByUuid(uuid);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setData(configsDto);
        responseDto.setMessage("查询配置表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "分页查询配置表信息")
    @RequestMapping(value = "/listConfigs", method = RequestMethod.POST)
    public ResponseDto getConfigsList(@RequestBody RequestDto<PageQueryDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        PageQueryDto pageQueryDto = requestDto.getData();
        List<ConfigsDto> configsDtoList = configsServiceImpl.getConfigsList(pageQueryDto);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setData(configsDtoList);
        responseDto.setMessage("分页查询配置表信息成功");
        return responseDto;
    }

}
