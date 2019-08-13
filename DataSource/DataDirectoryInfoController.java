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
import com.nqkj.hfs.entity.PermissionInfo;
import com.nqkj.hfs.service.DataDirectoryInfoService;

/**
 * 表单数据信息控制器
 * @author 
 *
 */
@RestController
public class DataDirectoryInfoController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(DataDirectoryInfoController.class);
	@Autowired
	private DataDirectoryInfoService service;
	/**查询表单信息列表**/
	@PostMapping(value= {"/queryDataDirectoryInfoList","/noencrypt/queryDataDirectoryInfoList"})
	public String queryDataDirectoryInfoList(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询表单信息列表  ******** start ********  ");
		List<DataDirectoryInfo> formInfoLists=null;
		String dataInfo="";
		try {
			DataDirectoryInfo reqParams = (DataDirectoryInfo)getReqObjParams(request, DataDirectoryInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfoLists);
			}
			formInfoLists=service.queryDataDirectoryInfoList(reqParams);
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
	@PostMapping(value= {"/queryDataDirectoryInfoCount","/noencrypt/queryDataDirectoryInfoCount"})
	public String queryDataDirectoryInfoCount(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询表单总数  ******** start ********  ");
		DataDirectoryInfo formInfo=null;
		String dataInfo="";
		try {
			DataDirectoryInfo reqParams = (DataDirectoryInfo)getReqObjParams(request, DataDirectoryInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			int res=service.queryDataDirectoryInfoCount(reqParams);
			formInfo = new DataDirectoryInfo();
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
	@PostMapping(value= {"/saveDataDirectoryInfo","/noencrypt/saveDataDirectoryInfo"})
	public String saveDataDirectoryInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 保存表单信息  ******** start ********  ");
		DataDirectoryInfo formInfo=null;
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
			int res=service.saveDataDirectoryInfo(reqParams);
			if (res>0) {
				formInfo = new DataDirectoryInfo();
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
	@PostMapping(value= {"/queryDataDirectoryInfoDeatil","/noencrypt/queryDataDirectoryInfoDeatil"})
	public String queryDataDirectoryInfoDeatil(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询表单信息详情  ******** start ********  ");
		DataDirectoryInfo formInfo=null;
		String dataInfo="";
		try {
			DataDirectoryInfo reqParams = (DataDirectoryInfo)getReqObjParams(request, DataDirectoryInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank(reqParams.getId())) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			formInfo=service.queryDataDirectoryInfoDeatil(reqParams.getId());
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
	@PostMapping(value= {"/updateDataDirectoryInfo","/noencrypt/updateDataDirectoryInfo"})
	public String updateDataDirectoryInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 更新修改表单信息  ******** start ********  ");
		DataDirectoryInfo formInfo=null;
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
			int res=service.updateDataDirectoryInfo(reqParams);
			if (res>0) {
				formInfo = new DataDirectoryInfo();
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
	@PostMapping(value= {"/deleteDataDirectoryInfo","/noencrypt/deleteDataDirectoryInfo"})
	public String deleteDataDirectoryInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 删除表单信息  ******** start ********  ");
		DataDirectoryInfo formInfo=null;
		String dataInfo="";
		try {
			DataDirectoryInfo reqParams = (DataDirectoryInfo)getReqObjParams(request, DataDirectoryInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			if (StringUtils.isBlank(reqParams.getId())) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,"id is null", formInfo);
			}
			int res=service.deleteDataDirectoryInfo(reqParams.getId());
			if (res>0) {
				formInfo = new DataDirectoryInfo();
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
	
	/**
	 * 	查询数据集结构树
	 * */
	@PostMapping(value= {"/queryDirectoryTreeInfo","/noencrypt/queryDirectoryTreeInfo"})
	public String queryDirectoryTreeInfo(HttpServletRequest request, HttpServletResponse response){
		LOGGER.info(" 查询表单信息详情  ******** start ********  ");
		List<Map<String,Object>> treeList = null;
		String dataInfo="";
		DataDirectoryInfo formInfo=null;
		try {
			PermissionInfo reqParams = (PermissionInfo)getReqObjParams(request, PermissionInfo.class);
			if (null == reqParams) {
				return RespJsonUtil.respObject(ErrorMsgUtils.ERROR_CODE_PARAM__ERROR,ErrorMsgUtils.ERROR_CODE_TIP_0203, formInfo);
			}
			treeList = service.getTreeList(reqParams.getId());
		    String returndata = RespJsonUtil.respObject(ErrorMsgUtils.QUERY_DATA_SUCCESS,ErrorMsgUtils.QUERY_DATA_SUCCESS_TIP, treeList);
		    LOGGER.info("删除表单信息返回数据： "+returndata);
		    dataInfo=returnParams(request,returndata);	
		}catch(Exception e) {
			LOGGER.info("删除表单信息数据发生异常");
			e.printStackTrace();
		}
		return dataInfo;
	}

}
