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
import com.nqkj.hfs.entity.DataInfo;
import com.nqkj.hfs.service.DataInfoService;

/**
 * 表单数据信息控制器
 * @author 
 *
 */
@RestController
public class DataInfoController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(DataInfoController.class);
	@Autowired
	private DataInfoService service;
	/**查询表单信息列表**/
	@PostMapping(value= {"/queryDataInfoList","/noencrypt/queryDataInfoList"})
	public String queryDataInfoList(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询表单信息列表  ******** start ********  ");
		List<DataInfo> formInfoLists=null;
		String dataInfo="";
		try {
			DataInfo reqParams = (DataInfo)getReqObjParams(request, DataInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfoLists);
			}
			formInfoLists=service.queryDataInfoList(reqParams);
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
	@PostMapping(value= {"/queryDataInfoCount","/noencrypt/queryDataInfoCount"})
	public String queryDataInfoCount(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询表单总数  ******** start ********  ");
		DataInfo formInfo=null;
		String dataInfo="";
		try {
			DataInfo reqParams = (DataInfo)getReqObjParams(request, DataInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			int res=service.queryDataInfoCount(reqParams);
			formInfo = new DataInfo();
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
	@PostMapping(value= {"/saveDataInfo","/noencrypt/saveDataInfo"})
	public String saveDataInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 保存表单信息  ******** start ********  ");
		DataInfo formInfo=null;
		String dataInfo="";
		try {
			Map reqParams = (Map)getReqObjParams(request, Map.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			String id=StringUtil.getUUID();
			reqParams.put("id",id);
			reqParams.put("createTime", DateUtils.getCurrTime());
			reqParams.put("lastupdatetime", DateUtils.getCurrTime());
			int res=service.saveDataInfo(reqParams);
			if (res>0) {
				formInfo = new DataInfo();
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
	@PostMapping(value= {"/queryDataInfoDeatil","/noencrypt/queryDataInfoDeatil"})
	public String queryDataInfoDeatil(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询表单信息详情  ******** start ********  ");
		DataInfo formInfo=null;
		String dataInfo="";
		try {
			DataInfo reqParams = (DataInfo)getReqObjParams(request, DataInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank(reqParams.getId())) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			formInfo=service.queryDataInfoDeatil(reqParams.getId());
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
	@PostMapping(value= {"/updateDataInfo","/noencrypt/updateDataInfo"})
	public String updateDataInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 更新修改表单信息  ******** start ********  ");
		DataInfo formInfo=null;
		String dataInfo="";
		try {
			Map reqParams = (Map)getReqObjParams(request, Map.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank((String)reqParams.get("id"))) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			reqParams.put("lastupdatetime", DateUtils.getCurrTime());
			int res=service.updateDataInfo(reqParams);
			if (res>0) {
				formInfo = new DataInfo();
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
	
	/**数据上传编辑**/
	@PostMapping(value= {"/uploadDataInfo","/noencrypt/uploadDataInfo"})
	public String uploadDataInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 数据上传编辑数据开始  ******** start ********  ");
		DataInfo formInfo=null;
		String dataInfo="";
		try {
			Map reqParams = (Map)getReqObjParams(request, Map.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank((String)reqParams.get("id"))) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			reqParams.put("lastupdatetime", DateUtils.getCurrTime());
			int res=service.uploadDataInfo(reqParams);
			if (res>0) {
				formInfo = new DataInfo();
				formInfo.setId((String)reqParams.get("id"));
			}
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfo);
			LOGGER.info("数据上传编辑数据返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("数据上传编辑数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	/**数据审核编辑**/
	@PostMapping(value= {"/shenHeDataInfo","/noencrypt/shenHeDataInfo"})
	public String shenHeDataInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 审核数据编辑开始  ******** start ********  ");
		DataInfo formInfo=null;
		String dataInfo="";
		try {
			Map reqParams = (Map)getReqObjParams(request, Map.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank((String)reqParams.get("id"))) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			reqParams.put("lastupdatetime", DateUtils.getCurrTime());
			int res=service.shenHeDataInfo(reqParams);
			if (res>0) {
				formInfo = new DataInfo();
				formInfo.setId((String)reqParams.get("id"));
			}
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfo);
			LOGGER.info("审核数据编辑数据返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("审核数据编辑数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	/**数据推荐编辑**/
	@PostMapping(value= {"/tuiJianDataInfo","/noencrypt/tuiJianDataInfo"})
	public String tuiJianDataInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 推荐数据编辑开始  ******** start ********  ");
		DataInfo formInfo=null;
		String dataInfo="";
		try {
			Map reqParams = (Map)getReqObjParams(request, Map.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank((String)reqParams.get("id"))) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			reqParams.put("lastupdatetime", DateUtils.getCurrTime());
			int res=service.tuiJianDataInfo(reqParams);
			if (res>0) {
				formInfo = new DataInfo();
				formInfo.setId((String)reqParams.get("id"));
			}
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfo);
			LOGGER.info("推荐数据编辑数据返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("推荐数据编辑数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	/**删除表单信息**/
	@PostMapping(value= {"/deleteDataInfo","/noencrypt/deleteDataInfo"})
	public String deleteDataInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 删除表单信息  ******** start ********  ");
		DataInfo formInfo=null;
		String dataInfo="";
		try {
			DataInfo reqParams = (DataInfo)getReqObjParams(request, DataInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank(reqParams.getId())) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			int res=service.deleteDataInfo(reqParams.getId());
			if (res>0) {
				formInfo = new DataInfo();
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

	/**查询数据上传列表**/
	@PostMapping(value= {"/queryDataInfouploadList","/noencrypt/queryDataInfouploadList"})
	public String queryDataInfouploadList(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询数据上传列表  ******** start ********  ");
		List<DataInfo> formInfoLists=null;
		String dataInfo="";
		try {
			DataInfo reqParams = (DataInfo)getReqObjParams(request, DataInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfoLists);
			}
			formInfoLists=service.queryDataInfouploadList(reqParams);
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfoLists);
			LOGGER.info("查询数据上传列表返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("查询数据上传列表数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	/**查询数据上传列表总数**/
	@PostMapping(value= {"/queryDataInfouploadCount","/noencrypt/queryDataInfouploadCount"})
	public String queryDataInfouploadCount(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询数据上传列表总数  ******** start ********  ");
		DataInfo formInfo=null;
		String dataInfo="";
		try {
			DataInfo reqParams = (DataInfo)getReqObjParams(request, DataInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			int res=service.queryDataInfouploadCount(reqParams);
			formInfo = new DataInfo();
			formInfo.setTotal(res);
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfo);
			LOGGER.info("查询数据上传列表返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("查询数据上传列表发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
	/**模糊搜索题目 **/
	@PostMapping(value= {"/queryDataNameList","/noencrypt/queryDataNameList"})
	public String queryDataNameList(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 模糊搜索题目列表  ******** start ********  ");
		List<DataInfo> formInfoLists=null;
		String dataInfo="";
		try {
			DataInfo reqParams = (DataInfo)getReqObjParams(request, DataInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfoLists);
			}
			formInfoLists=service.queryDataNameList(reqParams);
			String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, formInfoLists);
			LOGGER.info("模糊搜索题目列表返回数据： "+returndata);
			dataInfo=returnParams(request,returndata);		
		} catch (Exception e) {
			LOGGER.info("模糊搜索题目列表数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}
}
