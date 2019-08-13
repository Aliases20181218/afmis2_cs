package com.nqkj.hfs.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nqkj.base.DateUtils;
import com.nqkj.base.StringUtil;
import com.nqkj.base.common.BaseController;
import com.nqkj.base.common.ErrorMsgUtils;
import com.nqkj.base.common.RespJsonUtil;
import com.nqkj.hfs.entity.DataDirectoryInfo;
import com.nqkj.hfs.entity.DataInfo;
import com.nqkj.hfs.entity.DataLogInfo;
import com.nqkj.hfs.service.DataLogInfoService;

/**
 * 表单数据信息控制器
 * @author 
 *
 */
@RestController
public class DataLogInfoController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(DataLogInfoController.class);
	@Autowired
	private DataLogInfoService service;
	/**查询表单信息列表**/
	@PostMapping(value= {"/queryDataLogInfoList","/noencrypt/queryDataLogInfoList"})
	public String queryDataLogInfoList(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询表单信息列表  ******** start ********  ");
		List<DataLogInfo> formInfoLists=null;
		String dataInfo="";
		try {
			DataLogInfo reqParams = (DataLogInfo)getReqObjParams(request, DataLogInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfoLists);
			}
			formInfoLists=service.queryDataLogInfoList(reqParams);
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfoLists);
			LOGGER.info("查询表单信息列表返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("查询表单信息列表数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	
	/**查询表单总数**/
	@PostMapping(value= {"/queryDataLogInfoCount","/noencrypt/queryDataLogInfoCount"})
	public String queryDataLogInfoCount(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询表单总数  ******** start ********  ");
		DataLogInfo formInfo=null;
		String dataInfo="";
		try {
			DataLogInfo reqParams = (DataLogInfo)getReqObjParams(request, DataLogInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			int res=service.queryDataLogInfoCount(reqParams);
			formInfo = new DataLogInfo();
			formInfo.setTotal(res);
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfo);
			LOGGER.info("查询表单总数返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("查询表单总数发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	
	/**保存表单信息**/
	@PostMapping(value= {"/saveDataLogInfo","/noencrypt/saveDataLogInfo"})
	public String saveDataLogInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 保存表单信息  ******** start ********  ");
		DataLogInfo formInfo=null;
		String dataInfo="";
		try {
			Map reqParams = (Map)getReqObjParams(request, Map.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			String id=StringUtil.getUUID();
			reqParams.put("id",id);
			reqParams.put("createTime", DateUtils.getCurrTime());
			int res=service.saveDataLogInfo(reqParams);
			if (res>0) {
				formInfo = new DataLogInfo();
				formInfo.setId(id);
			}
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfo);
			LOGGER.info("保存表单信息返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("保存表单信息数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	/**查询表单信息详情**/
	@PostMapping(value= {"/queryDataLogInfoDeatil","/noencrypt/queryDataLogInfoDeatil"})
	public String queryDataLogInfoDeatil(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询表单信息详情  ******** start ********  ");
		DataLogInfo formInfo=null;
		String dataInfo="";
		try {
			DataLogInfo reqParams = (DataLogInfo)getReqObjParams(request, DataLogInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank(reqParams.getId())) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			formInfo=service.queryDataLogInfoDeatil(reqParams.getId());
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfo);
			LOGGER.info("查询表单信息详情返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("查询表单信息详情数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	/**更新修改表单信息**/
	@PostMapping(value= {"/updateDataLogInfo","/noencrypt/updateDataLogInfo"})
	public String updateDataLogInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 更新修改表单信息  ******** start ********  ");
		DataLogInfo formInfo=null;
		String dataInfo="";
		try {
			Map reqParams = (Map)getReqObjParams(request, Map.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank((String)reqParams.get("id"))) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			int res=service.updateDataLogInfo(reqParams);
			if (res>0) {
				formInfo = new DataLogInfo();
				formInfo.setId((String)reqParams.get("id"));
			}
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfo);
			LOGGER.info("更新修改表单信息返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("更新修改表单信息数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	
	/**删除表单信息**/
	@PostMapping(value= {"/deleteDataLogInfo","/noencrypt/deleteDataLogInfo"})
	public String deleteDataLogInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 删除表单信息  ******** start ********  ");
		DataLogInfo formInfo=null;
		String dataInfo="";
		try {
			DataLogInfo reqParams = (DataLogInfo)getReqObjParams(request, DataLogInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank(reqParams.getId())) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			int res=service.deleteDataLogInfo(reqParams.getId());
			if (res>0) {
				formInfo = new DataLogInfo();
				formInfo.setId(reqParams.getId());
			}
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfo);
			LOGGER.info("删除表单信息返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("删除表单信息数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}

}
