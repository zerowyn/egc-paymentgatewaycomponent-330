/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.paygateway.dto.CodeMapsDelDto;
import com.eg.egsc.scp.paygateway.dto.CodeMapsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.service.CodeMapsSerivce;
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
 * 缴费网关代码转换表Api
 *
 * @author gucunyang
 * @since 2018-02-24
 */
@Api(description = "缴费网关代码转换表")
@RestController
@RequestMapping(value = "/api/codeMaps")
public class CodeMapsApi extends BaseApiController {

    @Autowired
    CodeMapsSerivce codeMapsSerivceImpl;

    @ApiOperation(value = "新增代码转换表信息")
    @RequestMapping(value = "/insertCodeMap", method = RequestMethod.POST)
    public ResponseDto insertCodeMaps(@RequestBody RequestDto<CodeMapsDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        CodeMapsDto codeMapsDto = requestDto.getData();
        codeMapsSerivceImpl.insertCodeMaps(codeMapsDto);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setMessage("新增代码转换表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "删除代码转换表信息")
    @RequestMapping(value = "/deleteCodeMap", method = RequestMethod.POST)
    public ResponseDto deleteCodeMaps(@RequestBody RequestDto<CodeMapsDelDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        CodeMapsDelDto codeMapsDelDto = requestDto.getData();
        List<String> codeMapsUuids = codeMapsDelDto.getCodeMapsUuids();
        codeMapsSerivceImpl.deleteCodeMaps(codeMapsUuids);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setMessage("删除代码转换表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "修改代码转换表信息")
    @RequestMapping(value = "/updateCodeMap", method = RequestMethod.POST)
    public ResponseDto updateCodeMaps(@RequestBody RequestDto<CodeMapsDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        CodeMapsDto codeMapsDto = requestDto.getData();
        codeMapsSerivceImpl.updateCodeMaps(codeMapsDto);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setMessage("修改代码转换表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "查询代码转换表信息")
    @RequestMapping(value = "/getCodeMap", method = RequestMethod.POST)
    public ResponseDto getCodeMapsByUuid(@RequestBody RequestDto<CodeMapsDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        CodeMapsDto codeMapsDto = requestDto.getData();
        String uuid = codeMapsDto.getUuid();
        codeMapsDto = codeMapsSerivceImpl.getCodeMapsByUuid(uuid);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setData(codeMapsDto);
        responseDto.setMessage("查询代码转换表信息成功");
        return responseDto;
    }

    @ApiOperation(value = "分页查询代码转换表信息")
    @RequestMapping(value = "/listCodeMaps", method = RequestMethod.POST)
    public ResponseDto getCodeMapsList(@RequestBody RequestDto<PageQueryDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        PageQueryDto pageQueryDto = requestDto.getData();
        List<CodeMapsDto> codeMapsDtoList = codeMapsSerivceImpl.getCodeMapsList(pageQueryDto);
        responseDto.setCode(SUCCESS_CODE);
        responseDto.setData(codeMapsDtoList);
        responseDto.setMessage("分页查询代码转换表信息成功");
        return responseDto;
    }

}
