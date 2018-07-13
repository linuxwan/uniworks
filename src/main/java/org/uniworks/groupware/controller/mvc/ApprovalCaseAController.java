/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.controller.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.HiddenField;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.common.util.ApplicationConfigReader;
import org.uniworks.groupware.common.util.AttachFileUtil;
import org.uniworks.groupware.common.util.CommUtil;
import org.uniworks.groupware.common.util.DateUtil;
import org.uniworks.groupware.common.util.WebUtil;
import org.uniworks.groupware.common.util.approval.ApprovalCommonUtil;
import org.uniworks.groupware.domain.CommonCode;
import org.uniworks.groupware.domain.Nw112m;
import org.uniworks.groupware.domain.Nw113m;
import org.uniworks.groupware.domain.Nw114m;
import org.uniworks.groupware.domain.Nw115m;
import org.uniworks.groupware.domain.approval.ApprovalCategory;
import org.uniworks.groupware.domain.approval.ApprovalDoc;
import org.uniworks.groupware.domain.approval.ApprovalItem;
import org.uniworks.groupware.domain.approval.ApprovalLevel;
import org.uniworks.groupware.domain.approval.ApprovalMaster;
import org.uniworks.groupware.domain.approval.LineApprover;
import org.uniworks.groupware.service.ApprovalService;
import org.uniworks.groupware.service.CommonService;
import org.uniworks.groupware.service.FileService;
import org.uniworks.groupware.service.HumanResourceService;
import org.uniworks.groupware.service.Nw112mService;
import org.uniworks.groupware.service.Nw113mService;
import org.uniworks.groupware.service.Nw114mService;
import org.uniworks.groupware.service.Nw115mService;
import org.uniworks.groupware.validator.ApprovalDocValidatorA;
import org.uniworks.groupware.validator.form.ApprovalDocForm01;

/**
 * @author Park Chungwan
 *
 */
@Controller
public class ApprovalCaseAController {
	private static final Logger logger = LoggerFactory.getLogger(ApprovalCaseAController.class);
	@Autowired private ApprovalService apprService;
	@Autowired private CommonService commService;
	@Autowired private Nw112mService nw112mService;
	@Autowired private Nw113mService nw113mService;
	@Autowired private Nw114mService nw114mService;
	@Autowired private Nw115mService nw115mService;	
	@Autowired private HumanResourceService hrService;
	@Autowired private FileService fileService;
	@Autowired @Qualifier("approvalDocValidatorA") private ApprovalDocValidatorA validator;
	
	@InitBinder("approvalDocForm01")
	public void init(WebDataBinder dataBinder) {
		dataBinder.setValidator(this.validator);
	}
	
	/**
	 * 결재양식함 - 카테고리별로 결재 양식폼을 제공한다.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("approval/approval_form")
	public ModelAndView approvalFrom(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("approval/approval_form_01");
		
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		
		//결재카테고리 정보를 가져온다.
		ArrayList<ApprovalCategory> arr = new ArrayList<ApprovalCategory>();
		List<ApprovalCategory> categoryList = apprService.getApprovalCategory(map);
		
		for (int i = 0; i < categoryList.size(); i++) {
			ApprovalCategory category = (ApprovalCategory) categoryList.get(i); 
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("coId", category.getCoId());
			itemMap.put("apprItemId", category.getApprItemId());
			itemMap.put("lang", userSession.getLanguage());
			
			//해당 카테고리의 결재양식 목록을 가져온다.
			List<ApprovalItem> itemList = apprService.getApprovalItemList(itemMap);
			category.setApprItemList(itemList);
			
			arr.add(category);
		}
		
		mav.addObject("categoryList", arr);
		return mav;
	}
	
	/**
	 * 결재 상태에 따른 결재 목록 가져오기 - 작성중인 문서 목록 가져오기
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "approval/approval_create_list_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approvalListA(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("approval/approval_list_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("authEmpNo", userSession.getUserId());
		map.put("apprStus", "0");	//apprStus가 0이면 작성 중.
		
		return mav;
	}
	
	/**
	 * 결재대기함 문서 목록을 가져온다.
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "approval/approval_waiting_list_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approvalWaitListA(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("approval/approval_waiting_list_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("authEmpNo", userSession.getUserId());
		map.put("apprStus", "1");	//apprStus가 1이면 대기 중.
		
		return mav;
	}
	
	/**
	 * 결재 반려함 문서 목록을 가져온다.
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "approval/approval_reject_list_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approvalRejectListA(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("approval/approval_reject_list_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("authEmpNo", userSession.getUserId());
		map.put("apprStus", "5");	//apprStus가 1이면 대기 중.
		
		return mav;
	}
	
	/**
	 * 결재 진행 중인 문서 목록을 가져온다.
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "approval/approval_progress_list_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approvalProgressListA(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("approval/approval_progress_list_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("authEmpNo", userSession.getUserId());
		map.put("apprStus", "3");	//apprStus가 3이면 진행 중.
		
		return mav;
	}
	
	/**
	 * 결재완료 문서 목록을 가져온다.
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "approval/approval_complete_list_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approvalCompleteListA(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("approval/approval_complete_list_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("authEmpNo", userSession.getUserId());
		map.put("apprStus", "7");	//apprStus가 7이면 완료.
		
		return mav;
	}
	
	/**
	 * 결재 완료된 수신 문서함 목록을 가져온다.
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approval/approval_receive_list_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approvalCompleteReceiveList(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("approval/approval_receive_list_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("authEmpNo", userSession.getUserId());
		map.put("apprStus", "7");	//apprStus가 7이면 완료.
		
		return mav;
	}
	
	/**
	 * 결재 양식함의 아이템을 클릭하면, 해당 결재 문서를 작성하는 화면으로 이동한다.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "approval/approval_write", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approvalWrite(@RequestParam String cntnId, @RequestParam String apprMstId, @RequestParam String apprDesc,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("approval/approval_write_01"); //Default 결재 양식 폼.
			
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		logger.debug("getLanguage : " + userSession.getLanguage() + " , getLocale : " + userSession.getLocale());
		
		DateUtil date = new DateUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("apprMstId", apprMstId);
		map.put("crntDate", date.getString());
		
		//결재 마스터 정보 가져오기.(결재양식함에서 선택한 결재의 마스터 정보임)
		
		ApprovalMaster apprMst = apprService.getApprovalMasterInfo(map);
		ApprovalLevel apprLevel = apprMst.getApprLevel();
		int napprLev = apprLevel.getApprLevel();
		ArrayList<LineApprover> arrApprLine = new ArrayList<LineApprover>();
		for (int i = 0; i < napprLev; i++) {
			arrApprLine.add(new LineApprover());
		}		
		logger.debug("chungwan apprLevel :" + apprLevel.getApprLevel());
		
		map.put("majCode", "CD008");
		map.put("orderBy", "rescKey");
		List<CommonCode> commonCodeList = commService.getCommonSubCodeList(map);
		
		mav.addObject("apprMstId", apprMstId);	//결재마스터 ID
		mav.addObject("apprMst", apprMst);	//결재 마스터 정보를 가져와서 설정
		mav.addObject("cntnId", cntnId);	//컨텐츠 ID 정보를 받아와서 설정
		mav.addObject("userSession", userSession);	//세션정보를 설정
		mav.addObject("apprLevel", apprLevel.getApprLevel());	//결재차수 설정
		mav.addObject("lineAppr", arrApprLine);
		mav.addObject("serviceLife", commonCodeList);	//보존연한
		mav.addObject("shortYear", date.getYearShort());	//현재 년도를 YY형태로 설정
		mav.addObject("year", date.getYear());	//현재 년도를 YYYY형태로 설정
		
		mav.addObject("apprDesc", apprDesc);
		return mav;
	}
	
	/**
	 * 결재문서를 저장/저장 후 닫기 시에 결재문서를 새롭게 생성.
	 * @param approvalDocForm01
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "approval/approval_registration_01", method = RequestMethod.POST)
	public ModelAndView approvalRegistrationA(@ModelAttribute ApprovalDocForm01 approvalDocForm01, BindingResult result, Map model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("approval/approval_write_01");
		
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String cntnId = request.getParameter("cntnId");
		String apprMstId = request.getParameter("apprMstId");
		String saveType= request.getParameter("saveType");	//문서 저장 타입을 의미 - S:저장만, C:저장 후 닫기
		String coId = userSession.getCoId();
		String lang = userSession.getLanguage();
		String userId = userSession.getUserId();
		String steadApprIndc = "N";
		String apprStus = request.getParameter("apprStus");
		String comment = "";
		
		//결재문서에 대한 Validation 체크를 한다.
		validator.validate(approvalDocForm01, result);
		//logger.debug("approvalDocForm01: " + approvalDocForm01);
		//logger.debug("result: " + result);
		if (result.hasErrors()) {
			//Validation 체크 도중 오류가 나면 작성화면으로 되돌린다.
			request.setAttribute("apprMstId", apprMstId);
			request.setAttribute("cntnId", cntnId);
			request.setAttribute("mode", "add");
			mav = approvalWritingForm1(request, response);
			return mav;
		}
		
		//결재 마스터 정보 가져오기.(결재양식함에서 선택한 결재의 마스터 정보임)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("cntnId", cntnId);
		map.put("lang", userSession.getLanguage());
		map.put("apprMstId", apprMstId);
		DateUtil date = new DateUtil();
		map.put("crntDate", date.getString());
		ApprovalMaster apprMst = apprService.getApprovalMasterInfo(map);
		
		ApprovalDoc apprDoc = new ApprovalDoc();
		apprDoc.setCoId(userSession.getCoId());
		//최대결재차수 정보 설정
		apprDoc.setMaxApprDgr(apprMst.getApprLevel().getApprLevel());
		
		WebUtil.bind(request, apprDoc);
		//문서등록 번호 생성
		apprDoc.setDcmtRgsrNo(CommUtil.createSequenceNo("A"));
		apprDoc.setOpenIndc("N");
		
		//라인결재자 설정
		List<LineApprover> list = ApprovalCommonUtil.setLineApproverList(request, hrService, apprDoc.getDcmtRgsrNo());
		//협조결재자 설정
		List<Nw112m> cprtnDestList = ApprovalCommonUtil.setCprtApproverList(request, apprDoc.getDcmtRgsrNo());
		//수신처 설정
		List<Nw113m> rcptDestList = ApprovalCommonUtil.setRcptDestList(request, apprDoc.getDcmtRgsrNo());
		//참조처 설정
		List<Nw114m> rfncDestList = ApprovalCommonUtil.setRfncDestList(request, apprDoc.getDcmtRgsrNo());
		//첨부문서 설정
		List<Nw115m> attachFileList = AttachFileUtil.setAttachFileList(request, apprDoc.getDcmtRgsrNo(), fileService);
		if(attachFileList == null || attachFileList.size() < 1)	apprDoc.setAtchIndc("N");
		apprService.addApprovalDocument(userSession.getLocale(), apprDoc, list, cprtnDestList, rcptDestList, rfncDestList, attachFileList, null);

		//저장 후 닫기일 경우, 결재양식함으로 이동.
		if (saveType.equalsIgnoreCase("C")) {
			//String redirectUrl = ApplicationConfigReader.get("webAppRoot") + "/approval/approval_create_list_01";
			String redirectUrl = ApplicationConfigReader.get("webAppRoot") + "/approval/approval_form";
			response.sendRedirect(redirectUrl);
		} else if (saveType.equalsIgnoreCase("S")) { //저장만 
			String redirectUrl = ApplicationConfigReader.get("webAppRoot") + "/approval/modify_approval_doc_01";
			String params = "?cntnId=" + cntnId + "&apprMstId=" + apprMstId + "&dcmtRgsrNo=" + apprDoc.getDcmtRgsrNo();
			response.sendRedirect(redirectUrl + params);
		} else if (saveType.equalsIgnoreCase("A")) {
			apprService.changeLineApproverStatus(coId, cntnId, apprDoc.getDcmtRgsrNo(), lang, userId, steadApprIndc, apprStus, comment);
			String redirectUrl = ApplicationConfigReader.get("webAppRoot") + "/approval/approval_form";
			response.sendRedirect(redirectUrl);
		}
		
		return mav;
	}
	
	/**
	 * 결재문서를 수정 후 저장/저장 후 닫기 시에 결재문서를 Update.
	 * @param approvalDocForm01
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "approval/approval_modify_01", method = RequestMethod.POST)
	public ModelAndView approvalModifyA(@ModelAttribute ApprovalDocForm01 approvalDocForm01, BindingResult result, 
			Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("approval/approval_modify_01");
		
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String cntnId = request.getParameter("cntnId");
		String apprMstId = request.getParameter("apprMstId");
		String saveType= request.getParameter("saveType");	//문서 저장 타입을 의미 - S:저장만, C:저장 후 닫기
		String crntApprDgr = request.getParameter("crntApprDgr");	//현재 진행 중인 라인 결재 차수
		String maxApprDgr = request.getParameter("maxApprDgr");	//최대 결재 차수
		String apprStus = request.getParameter("apprStus");	//문서의 결재 진행 상태
		String popupIndc = request.getParameter("popupIndc");	//Popup 창인지 체크

		//결재문서에 대한 Validation 체크를 한다.
		validator.validate(approvalDocForm01, result);
		if (result.hasErrors()) {
			//Validation 체크 도중 오류가 나면 작성화면으로 되돌린다.
			request.setAttribute("apprMstId", apprMstId);
			request.setAttribute("cntnId", cntnId);
			request.setAttribute("mode", "mod");
			mav = approvalWritingForm1(request, response);
			return mav;
		}

		//결재 마스터 정보 가져오기.(결재양식함에서 선택한 결재의 마스터 정보임)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("cntnId", cntnId);
		map.put("lang", userSession.getLanguage());
		map.put("apprMstId", apprMstId);
		DateUtil date = new DateUtil();
		map.put("crntDate", date.getString());
		ApprovalMaster apprMst = apprService.getApprovalMasterInfo(map);
				
		ApprovalDoc apprDoc = new ApprovalDoc();		
		
		//String Type의 날짜 정보를 Date Type으로 인식할 수 있도록 설정.
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPattern("yyyyMMdd");
		ConvertUtils.register(dateConverter, java.util.Date.class);
		BeanUtils.populate(apprDoc, request.getParameterMap());
		
		apprDoc.setCoId(userSession.getCoId());
		apprDoc.setCntnName(apprMst.getApprDesc());
				
		apprDoc.setOpenIndc("N");
		
		//현재 수정 중인 문서에 대한 결재 상태 정보를 DB에서 가져온다.
		map.put("dcmtRgsrNo", apprDoc.getDcmtRgsrNo());
		ApprovalDoc tempApprDoc = apprService.getByApprovalDocNw110m(map);		
		
		//작성 중 저장이 아닐 경우. 결재 진행 중 변경 사항을 저장할 경우.
		if (!crntApprDgr.equals("0") && !apprStus.equals("0")) {
			apprDoc.setCrntApprDgr(Integer.parseInt(crntApprDgr));
			apprDoc.setMaxApprDgr(Integer.parseInt(maxApprDgr));
			if (apprStus == null || apprStus.equals("")) {
				apprDoc.setApprStus(tempApprDoc.getApprStus());
			} else {
				apprDoc.setApprStus(apprStus);
			}
		}
		
		//라인결재자 설정
		List<LineApprover> list = ApprovalCommonUtil.setLineApproverList(request, hrService, apprDoc.getDcmtRgsrNo());		
		//협조결재자 설정
		List<Nw112m> cprtnDestList = ApprovalCommonUtil.setCprtApproverList(request, apprDoc.getDcmtRgsrNo());
		//결재 진행 중 수정일 경우에는 라인결재자와 협조결재자를 변경하지 못한다.
		if (!crntApprDgr.equals("0") && !apprStus.equals("0")) {
			list = null;
			cprtnDestList = null;
		}
		//수신처 설정
		List<Nw113m> rcptDestList = ApprovalCommonUtil.setRcptDestList(request, apprDoc.getDcmtRgsrNo());
		//참조처 설정
		List<Nw114m> rfncDestList = ApprovalCommonUtil.setRfncDestList(request, apprDoc.getDcmtRgsrNo());
		//첨부문서 설정
		List<Nw115m> attachFileList = AttachFileUtil.setAttachFileList(request, apprDoc.getDcmtRgsrNo(), fileService);

		apprService.updateApprovalDocument(userSession.getLanguage(), apprDoc, list, cprtnDestList, rcptDestList, rfncDestList, attachFileList, null);		
		
		//저장 후 닫기일 경우, 결재양식함으로 이동.
		if (saveType.equalsIgnoreCase("C")) {
			if (popupIndc.equalsIgnoreCase("Y")) {
				mav = new ModelAndView("common/close");
			} else {
				String redirectUrl = ApplicationConfigReader.get("webAppRoot") + "/approval/approval_form";
				response.sendRedirect(redirectUrl);
			}
		} else if (saveType.equalsIgnoreCase("S")) { //저장만 
			String redirectUrl = ApplicationConfigReader.get("webAppRoot") + "/approval/modify_approval_doc_01";
			String params = "?cntnId=" + cntnId + "&apprMstId=" + apprMstId + "&dcmtRgsrNo=" + apprDoc.getDcmtRgsrNo() + "&popupIndc=" + popupIndc;
			response.sendRedirect(redirectUrl + params);			
		}
				
		return mav;
	}
	
	/**
	 * 결재문서 상세 내용 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "approval/approval_view_form_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approvalViewForm(@ModelAttribute("param") HiddenField param, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("approval/approval_view_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession"); 		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dcmtRgsrNo", param.getDcmtRgsrNo());
		map.put("cntnId", param.getCntnId());
		map.put("lang", userSession.getLanguage());
		map.put("coId", userSession.getCoId());
				
		//결재문서 정보 가져오기
		ApprovalDoc doc = getApprovalDocumentInfo(map);
		
		map.put("apprMstId", doc.getApprMstId());	
		DateUtil date = new DateUtil();
		map.put("crntDate", date.getString());
		
		//결재마스터 정보 가져오기
		ApprovalMaster apprMst = apprService.getApprovalMasterInfo(map);
		ApprovalLevel apprLevel = apprMst.getApprLevel();
		//라인결재자 정보를 가져온다.
		List<LineApprover> lineAppr = getLineApproverList(map, apprLevel.getApprLevel());
		//협조결재자 정보를 가져온다.
		List<Nw112m> cprtnAppr = nw112mService.getNw112mList(map);
		Map<String, String> cprtnMap = getCprtnApproverList(map);
		//수진처정보를 가져온다.
		Map<String, String> rcptMap = getRcptInfo(map);
		//참조처정보를 가져온다.
		Map<String, String> rfncMap = getRfncInfo(map);
		//첨부파일 가져오기
		List<Nw115m> attachList = getAttachFileList(map);
		String strAttachList = getAttachFileListToString(map);
		
		//현재 로그인한 사용자가 해당 결재문서에 대해서 수정 및 결재가 가능한지를 체크
		boolean approved = apprService.checkApprovalDocModifyAuthrity(userSession.getCoId(), param.getCntnId(), param.getDcmtRgsrNo(), userSession);
		//현재 로그인한 사용자가 해당 결재문서에 대해서 협조결재자인지 체크
		boolean cprtnApproved = apprService.checkCprtnApprovalPersonInfo(userSession.getCoId(), param.getCntnId(), doc.getDcmtRgsrNo(), userSession);
		
		mav.addObject("apprMstId", apprMst.getApprMstId());
		mav.addObject("apprMst", apprMst);
		mav.addObject("doc", doc);
		mav.addObject("lineAppr", lineAppr);
		mav.addObject("apprLevel", apprLevel.getApprLevel());
		mav.addObject("rcptMap", rcptMap);
		mav.addObject("rfncMap", rfncMap);
		mav.addObject("attachList", attachList);
		mav.addObject("strAttachList", strAttachList);
		mav.addObject("approved", approved);
		mav.addObject("cprtnApproved", cprtnApproved);
		mav.addObject("cprtnAppr", cprtnAppr);
		mav.addObject("cprtnMap", cprtnMap);
		
		return mav;
	}				
	
	/**
	 * 결재문서 조회화면에서  승인/반려 처리를 한다.
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "approval/approved_request_01", method = RequestMethod.POST)
	public ModelAndView approvedConfirm01(@ModelAttribute("param") HiddenField param, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("approval/approval_view_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		String coId = userSession.getCoId();
		String cntnId = param.getCntnId();
		String dcmtRgsrNo = param.getDcmtRgsrNo();
		String lang = userSession.getLanguage();
		String userId = userSession.getUserId();
		String steadApprIndc = request.getParameter("steadApprIndc");
		if (steadApprIndc == null) steadApprIndc = "N";
		String apprStus = request.getParameter("apprStus");
		String comment = request.getParameter("comment");
		
		apprService.changeLineApproverStatus(coId, cntnId, dcmtRgsrNo, lang, userId, steadApprIndc, apprStus, comment);
		
		mav = approvalViewForm(param, request, response);
		return mav;
	}
	
	/**
	 * 결재문서 조회화면에서 협조결재 승인/반려 처리를 한다.
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "approval/cprtn_approved_request_01", method = RequestMethod.POST)
	public ModelAndView cprtnApprovedConfirm01(@ModelAttribute("param") HiddenField param, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("approval/approval_view_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		String coId = userSession.getCoId();
		String cntnId = param.getCntnId();
		String dcmtRgsrNo = param.getDcmtRgsrNo();
		String lang = userSession.getLanguage();
		String apprStus = request.getParameter("apprStus");
		String comment = request.getParameter("comment");
		
		//대리(위임) 결재자가 여부 
		String steadApprIndc = "N"; //현재는 Default로 설정. 대리(위임) 결재자 관리가 완성되면 변경해야 함.
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dcmtRgsrNo", param.getDcmtRgsrNo());
		map.put("cntnId", param.getCntnId());
		map.put("lang", userSession.getLanguage());
		map.put("coId", userSession.getCoId());		
				
		//결재문서 정보 가져오기
		ApprovalDoc apprDoc = getApprovalDocumentInfo(map);		

		apprService.changeCprtnApproverStatus(coId, cntnId, dcmtRgsrNo, lang, userSession, apprDoc, steadApprIndc, apprStus, comment);
		mav = approvalViewForm(param, request, response);
		return mav;
	}
	
	/**
	 * 결재문서 수정 중 승인요청을 했을 경우의 처리.
	 * @param approvalDocForm01
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "approval/modifying_approved_request_01", method = RequestMethod.POST)
	public ModelAndView modifyingApprovedConfirmA(@ModelAttribute ApprovalDocForm01 approvalDocForm01, BindingResult result, 
			Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("approval/approval_modify_01");
		
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String cntnId = request.getParameter("cntnId");
		String apprMstId = request.getParameter("apprMstId");
		String crntApprDgr = request.getParameter("crntApprDgr");	//현재 진행 중인 라인 결재 차수
		String maxApprDgr = request.getParameter("maxApprDgr");	//최대 결재 차수
		String apprStus = request.getParameter("apprStus");	//문서의 결재 진행 상태
		String comment = request.getParameter("comment");	//결재코멘트

		//결재문서에 대한 Validation 체크를 한다.
		validator.validate(approvalDocForm01, result);
		if (result.hasErrors()) {
			//Validation 체크 도중 오류가 나면 작성화면으로 되돌린다.
			request.setAttribute("apprMstId", apprMstId);
			request.setAttribute("cntnId", cntnId);
			request.setAttribute("mode", "mod");
			mav = approvalWritingForm1(request, response);
			return mav;
		}

		//결재 마스터 정보 가져오기.(결재양식함에서 선택한 결재의 마스터 정보임)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("cntnId", cntnId);
		map.put("lang", userSession.getLanguage());
		map.put("apprMstId", apprMstId);
		DateUtil date = new DateUtil();
		map.put("crntDate", date.getString());
		ApprovalMaster apprMst = apprService.getApprovalMasterInfo(map);
				
		ApprovalDoc apprDoc = new ApprovalDoc();				
		
		//String Type의 날짜 정보를 Date Type으로 인식할 수 있도록 설정.
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPattern("yyyyMMdd");
		ConvertUtils.register(dateConverter, java.util.Date.class);
		BeanUtils.populate(apprDoc, request.getParameterMap());
		
		apprDoc.setCoId(userSession.getCoId());
		apprDoc.setCntnName(apprMst.getApprDesc());
				
		apprDoc.setOpenIndc("N");
		
		//작성 중 저장이 아닐 경우. 결재 진행 중 변경 사항을 저장할 경우.
		if (!crntApprDgr.equals("0") && !apprStus.equals("0")) {
			apprDoc.setCrntApprDgr(Integer.parseInt(crntApprDgr));
			apprDoc.setMaxApprDgr(Integer.parseInt(maxApprDgr));
			apprDoc.setApprStus(apprStus);
		}
		
		//라인결재자 설정
		List<LineApprover> list = ApprovalCommonUtil.setLineApproverList(request, hrService, apprDoc.getDcmtRgsrNo());		
		//협조결재자 설정
		List<Nw112m> cprtnDestList = ApprovalCommonUtil.setCprtApproverList(request, apprDoc.getDcmtRgsrNo());
		//결재 진행 중 수정일 경우에는 라인결재자와 협조결재라를 변경하지 못한다.
		if (!crntApprDgr.equals("0") && !apprStus.equals("0")) {
			list = null;
			cprtnDestList = null;
		}
				
		//수신처 설정
		List<Nw113m> rcptDestList = ApprovalCommonUtil.setRcptDestList(request, apprDoc.getDcmtRgsrNo());
		//참조처 설정
		List<Nw114m> rfncDestList = ApprovalCommonUtil.setRfncDestList(request, apprDoc.getDcmtRgsrNo());
		//첨부문서 설정
		List<Nw115m> attachFileList = AttachFileUtil.setAttachFileList(request, apprDoc.getDcmtRgsrNo(), fileService);
		//결재문서 내용을 변경
		apprService.updateApprovalDocument(userSession.getLanguage(), apprDoc, list, cprtnDestList, rcptDestList, rfncDestList, attachFileList, null);
		
		String coId = userSession.getCoId();
		String dcmtRgsrNo = apprDoc.getDcmtRgsrNo();
		String lang = userSession.getLanguage();
		String userId = userSession.getUserId();
		String steadApprIndc = request.getParameter("steadApprIndc");
		if (steadApprIndc == null) steadApprIndc = "N";
		//결재상태를 변경.
		apprService.changeLineApproverStatus(coId, cntnId, dcmtRgsrNo, lang, userId, steadApprIndc, apprStus, comment);
		
		//Popup창을 닫도록 처리.
		mav = new ModelAndView("common/close");		
				
		return mav;
	}
	
	/**
	 * 결재문서 수정 중 승인/반려 처리를 했을 경우의 처리.
	 * @param approvalDocForm01
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "approval/modifying_approved_request_02", method = RequestMethod.POST)
	public ModelAndView modifyingApprovedConfirmB(@ModelAttribute ApprovalDocForm01 approvalDocForm01, BindingResult result, 
			Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("approval/approval_modify_01");
		
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String cntnId = request.getParameter("cntnId");
		String apprMstId = request.getParameter("apprMstId");
		String crntApprDgr = request.getParameter("crntApprDgr");	//현재 진행 중인 라인 결재 차수
		String maxApprDgr = request.getParameter("maxApprDgr");	//최대 결재 차수
		String apprStus = request.getParameter("apprStus");	//문서의 결재 진행 상태
		String comment = request.getParameter("comment");	//결재코멘트

		//결재문서에 대한 Validation 체크를 한다.
		validator.validate(approvalDocForm01, result);
		if (result.hasErrors()) {
			//Validation 체크 도중 오류가 나면 작성화면으로 되돌린다.
			request.setAttribute("apprMstId", apprMstId);
			request.setAttribute("cntnId", cntnId);
			request.setAttribute("mode", "mod");
			mav = approvalWritingForm1(request, response);
			return mav;
		}

		//결재 마스터 정보 가져오기.(결재양식함에서 선택한 결재의 마스터 정보임)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("cntnId", cntnId);
		map.put("lang", userSession.getLanguage());
		map.put("apprMstId", apprMstId);
		DateUtil date = new DateUtil();
		map.put("crntDate", date.getString());
		ApprovalMaster apprMst = apprService.getApprovalMasterInfo(map);
				
		ApprovalDoc apprDoc = new ApprovalDoc();				
		
		//String Type의 날짜 정보를 Date Type으로 인식할 수 있도록 설정.
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPattern("yyyyMMdd");
		ConvertUtils.register(dateConverter, java.util.Date.class);
		BeanUtils.populate(apprDoc, request.getParameterMap());
		
		apprDoc.setCoId(userSession.getCoId());
		apprDoc.setCntnName(apprMst.getApprDesc());
				
		apprDoc.setOpenIndc("N");
						
		//라인결재자 설정
		List<LineApprover> list = ApprovalCommonUtil.setLineApproverList(request, hrService, apprDoc.getDcmtRgsrNo());		
		//협조처 설정 부분을 추가해야 함.
		//협조결재자 설정
		List<Nw112m> cprtnDestList = ApprovalCommonUtil.setCprtApproverList(request, apprDoc.getDcmtRgsrNo());
		
		//결재 진행 중 수정일 경우에는 라인결재자와 협조결재자를 변경하지 못한다.
		if (!crntApprDgr.equals("0") && !apprStus.equals("0")) {
			list = null;
			cprtnDestList = null;
		}
				
		//수신처 설정
		List<Nw113m> rcptDestList = ApprovalCommonUtil.setRcptDestList(request, apprDoc.getDcmtRgsrNo());
		//참조처 설정
		List<Nw114m> rfncDestList = ApprovalCommonUtil.setRfncDestList(request, apprDoc.getDcmtRgsrNo());
		//첨부문서 설정
		List<Nw115m> attachFileList = AttachFileUtil.setAttachFileList(request, apprDoc.getDcmtRgsrNo(), fileService);
		//결재문서 내용을 변경
		apprService.updateApprovalDocument(userSession.getLanguage(), apprDoc, list, cprtnDestList, rcptDestList, rfncDestList, attachFileList, null);
		
		String coId = userSession.getCoId();
		String dcmtRgsrNo = apprDoc.getDcmtRgsrNo();
		String lang = userSession.getLanguage();
		String userId = userSession.getUserId();
		String steadApprIndc = request.getParameter("steadApprIndc");
		if (steadApprIndc == null) steadApprIndc = "N";
	
		apprService.changeLineApproverStatus(coId, cntnId, dcmtRgsrNo, lang, userId, steadApprIndc, apprStus, comment);
		
		//Popup창을 닫도록 처리.
		mav = new ModelAndView("common/close");		
				
		return mav;
	}
	
	/**
	 * 결재문서 수정 화면
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "approval/modify_approval_doc_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modifyApprovalDocumentForm01(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("approval/approval_modify_01");
		
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession"); 		
		String popupIndc = request.getParameter("popupIndc");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dcmtRgsrNo", param.getDcmtRgsrNo());
		map.put("cntnId", param.getCntnId());
		map.put("lang", userSession.getLanguage());
		map.put("coId", userSession.getCoId());
				
		//결재문서 정보 가져오기
		ApprovalDoc doc = getApprovalDocumentInfo(map);		
		map.put("apprMstId", doc.getApprMstId());	
		DateUtil date = new DateUtil();
		map.put("crntDate", date.getString());
		
		//보존연한 정보 가져오기.
		map.put("majCode", ApplicationConfigReader.get("cd.prsvTerm"));
		map.put("orderBy", "rescKey");
		List<CommonCode> commonCodeList = getCommonCodeInfo(map);

		//결재마스터 정보 가져오기
		ApprovalMaster apprMst = apprService.getApprovalMasterInfo(map);
		
		//라인결재자 정보를 가져온다.
		List<LineApprover> lineAppr = getLineApproverList(map, doc.getMaxApprDgr());
		//협조결재자 정보를 가져온다.
		List<Nw112m> cprtnAppr = nw112mService.getNw112mList(map);
		Map<String, String> cprtnMap = getCprtnApproverList(map);
		//수진처정보를 가져온다.
		Map<String, String> rcptMap = getRcptInfo(map);
		//참조처정보를 가져온다.
		Map<String, String> rfncMap = getRfncInfo(map);
		//첨부파일 가져오기
		List<Nw115m> attachList = getAttachFileList(map);
		String strAttachList = getAttachFileListToString(map);
		
		//현재 로그인한 사용자가 해당 결재문서에 대해서 수정 및 결재가 가능한지를 체크
		boolean approved = apprService.checkApprovalDocModifyAuthrity(userSession.getCoId(), param.getCntnId(), param.getDcmtRgsrNo(), userSession);
		//현재 로그인한 사용자가 해당 결재문서에 대해서 협조결재자인지 체크
		boolean cprtnApproved = apprService.checkCprtnApprovalPersonInfo(userSession.getCoId(), param.getCntnId(), doc.getDcmtRgsrNo(), userSession);
		
		mav.addObject("doc", doc);		
		mav.addObject("apprMst", apprMst);
		mav.addObject("apprMstId", doc.getApprMstId());
		mav.addObject("lineAppr", lineAppr);
		mav.addObject("rcptMap", rcptMap);
		mav.addObject("rfncMap", rfncMap);
		mav.addObject("apprLevel", doc.getMaxApprDgr());
		mav.addObject("attachList", attachList);
		mav.addObject("strAttachList", strAttachList);
		mav.addObject("shortYear", date.getYearShort());	//현재 년도를 YY형태로 설정
		mav.addObject("year", date.getYear());	//현재 년도를 YYYY형태로 설정
		mav.addObject("serviceLife", commonCodeList);	//보존연한
		mav.addObject("approved", approved);
		mav.addObject("cprtnApproved", cprtnApproved);
		mav.addObject("popupIndc", popupIndc);
		mav.addObject("cprtnAppr", cprtnAppr);
		mav.addObject("cprtnMap", cprtnMap);
		
		return mav;
	}
	
	/**
	 * 결재문서를 삭제한다.-현재 사용은 하고 있지 않음. rest에 있는 것으로 대체
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "approval/remove_approval_doc_01")
	public ModelAndView removeApprovalDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String cntnId = request.getParameter("cntnId");
		String dcmtRgsrNo = request.getParameter("dcmtRgsrNo");
		String headMenuId = request.getParameter("headMenuId");
		String menuId = request.getParameter("menuId");
		String menuLevel = request.getParameter("menuLevel");
		String crntPage = request.getParameter("crntPage");
		String apprMstId = request.getParameter("apprMstId");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("cntnId", cntnId);
		map.put("dcmtRgsrNo", dcmtRgsrNo);
		map.put("apprMstId", apprMstId);
		map.put("lang", userSession.getLanguage());
		
		apprService.removeApprovalDocument(map);
		
		String url = ApplicationConfigReader.get("webAppRoot") + "/approval/approval_create_list_01";
		url += "?dcmtRgsrNo=" + dcmtRgsrNo + "&headMenuId=" + headMenuId + "&menuId=" + menuId + "&menuLevel=" + menuLevel + "&cntnId=" + cntnId + "&crntPage=" + crntPage;
		
		try {
			response.sendRedirect(url);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * 일반코드 정보를 가져온다. Sub Code 목록을 반환.
	 * @param map
	 * @return
	 */
	private List<CommonCode> getCommonCodeInfo(Map<String, Object> map) {
		return commService.getCommonSubCodeList(map);
	}
	
	/**
	 * 결재문서 정보를 가져온다.
	 * @param map
	 * @return
	 */
	private ApprovalDoc getApprovalDocumentInfo(Map<String, Object> map) {
		return apprService.getByApprovalDocNw110m(map);
	}
	
	/**
	 * 라인결재자 정보를 가져온다.
	 * @param map
	 * @return
	 */
	private List<LineApprover> getLineApproverList(Map<String, Object> map, int apprLev) {
		List<LineApprover> list = apprService.getLineApproverNw111m(map);
		ArrayList<LineApprover> arrAppr = new ArrayList<LineApprover>();
		Map<Integer, Object> apprListMap = new HashMap<Integer, Object>();
		
		for (int j = 0; j < list.size(); j++) {
			LineApprover appr = list.get(j);
			apprListMap.put(new Integer(appr.getApprDgr()), appr);
		}
		
		for (int i = 1; i <= apprLev; i++) {
			if (!apprListMap.containsKey(new Integer(i))) {	//결재자 정보가 없을 경우 아무런 값이 없는 LineApprover 클래스 생성해서 추가
				apprListMap.put(new Integer(i), new LineApprover());
			}
		}
		
		//TreeMap을 이용해서 정렬
		TreeMap<Integer, Object> tm = new TreeMap<Integer, Object>(apprListMap);
		Iterator<Integer> iteratorKey = tm.keySet( ).iterator( );   //키값 오름차순 정렬(기본)
        //Iterator<Integer> iteratorKey = tm.descendingKeySet().iterator(); //키값 내림차순 정렬
		while(iteratorKey.hasNext()) {
			Integer key = iteratorKey.next();
			arrAppr.add((LineApprover)tm.get(key));
		}
		logger.debug("log arrAppr.size() :" + arrAppr.size());
		return arrAppr;
	}
	
	/**
	 * 협조 결재자 목록을 가져온다.
	 * @param map
	 * @return
	 */
	private Map<String, String> getCprtnApproverList(Map<String, Object> map) {
		Map<String, String> cprtn = new HashMap<String, String>();
		List<Nw112m> list = nw112mService.getNw112mList(map);
		
		String selCprtn = "";
		String selCprtnDesc = "";
		
		//협조처 정보
		Nw112m cprtnDest = null;
		
		for (int i = 0; i < list.size(); i++) {
			cprtnDest = list.get(i);
			if (i == 0) {
				selCprtn += cprtnDest.getApprDgr() + ":" + cprtnDest.getDeptCode();
				selCprtnDesc += cprtnDest.getApprDgr() + ":" + cprtnDest.getDeptDesc();
			} else {
				selCprtn += ", " + cprtnDest.getApprDgr() + ":" + cprtnDest.getDeptCode();
				selCprtnDesc += ", " + cprtnDest.getApprDgr() + ":" + cprtnDest.getDeptDesc();
			}
		}
		
		cprtn.put("selCprtn", selCprtn);
		cprtn.put("selCprtnDesc", selCprtnDesc);
		return cprtn;
	}
	
	/**
	 * 수신처 정보를 코드와 명칭으로 가져온다.
	 * @param map
	 * @return
	 */
	private Map<String, String> getRcptInfo(Map<String, Object> map) {
		Map<String, String> rcpt = new HashMap<String, String>();
		List<Nw113m> rcptList = nw113mService.getNw113mList(map);
		
		String selRcpt = "";
		String selRcptDesc = "";
		
		//수신처 정보
		Nw113m rcptDest = null;
		
		for (int i = 0; i < rcptList.size(); i++) {
			rcptDest = rcptList.get(i);
			if (i == 0) {
				selRcpt += rcptDest.getRcptOganGrpCode();
				selRcptDesc += rcptDest.getRcptOganGrpName();
			} else {
				selRcpt += ", " + rcptDest.getRcptOganGrpCode();
				selRcptDesc += ", " + rcptDest.getRcptOganGrpName();
			}
		}
		
		rcpt.put("selRcpt", selRcpt);
		rcpt.put("selRcptDesc", selRcptDesc);
		
		return rcpt;
	}
	
	/**
	 * 참조처 정보를 코드와 명칭으로 가져온다. 
	 * @param map
	 * @return
	 */
	private Map<String, String> getRfncInfo(Map<String, Object> map) {
		Map<String, String> rfnc = new HashMap<String, String>();
		List<Nw114m> rfncList = nw114mService.getNw114mList(map);
		
		String selRfnc = "";
		String selRfncDesc = "";
		
		Nw114m rfncDest = null;
		for (int i = 0; i < rfncList.size(); i++) {
			rfncDest = rfncList.get(i);
			if (i == 0) {
				selRfnc += rfncDest.getRfncType() + ":" + rfncDest.getRfncCode();
				selRfncDesc += rfncDest.getRfncCodeName();
			} else {
				selRfnc += ", " + rfncDest.getRfncType() + ":" + rfncDest.getRfncCode();
				selRfncDesc += ", " + rfncDest.getRfncCodeName();
			}
		}
		
		rfnc.put("selRfnc", selRfnc);
		rfnc.put("selRfncDesc", selRfncDesc);
		
		return rfnc;
	}
	
	/**
	 * 첨부파일 목록을 가져와서 attachList에 할당할 문자열을 반환
	 * fileId^attchFileName^fileSize^tempIndc으로 |으로 구분.
	 * @param map
	 * @return
	 */
	private String getAttachFileListToString(Map<String, Object> map) {
		String attachList = "";
		List<Nw115m> fileList = getAttachFileList(map);
		
		Nw115m attachFile = null;
		for (int i = 0; i < fileList.size(); i++) {
			attachFile = fileList.get(i);
			if (i > 0) attachList += "|";
			attachList += attachFile.getFileId() + "^" + attachFile.getAttchFileName() + "^" + attachFile.getFileSize() + "^N";
		}
		
		return attachList;
	}
	
	/**
	 * 첨부파일 목록 가져오기
	 * @param map
	 * @return
	 */
	private List<Nw115m> getAttachFileList(Map<String, Object> map) {
		List<Nw115m> fileList = nw115mService.getNw115mList(map);
		return fileList;
	}
	
	/**
	 * approvalRegistration1 메소드에서 Validate 체크 시에 문제가 있을 경우, 다시 결재문서 작성 화면으로 띄워준다.
	 * 사용자가 입력한 값들을 그대로 설정해서 이전 작성화면으로 보여준다.
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView approvalWritingForm1(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		
		String mode = (String) request.getAttribute("mode");
		if (mode.equalsIgnoreCase("add")) {
			mav = new ModelAndView("approval/approval_write_01");
		} else if (mode.equalsIgnoreCase("mod")) {
			mav = new ModelAndView("approval/approval_modify_01");
		}
		
		String apprMstId = (String) request.getAttribute("apprMstId");
		String cntnId = (String) request.getAttribute("cntnId");
		String prsvTermType = request.getParameter("prsvTermType");
		String attachList = request.getParameter("attachList");	//첨부파일 목록 정보 
		String atchIndc = request.getParameter("atchIndc");	//첨부파일 유무
		String dcmtRgsrNo = request.getParameter("dcmtRgsrNo");
		
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		DateUtil date = new DateUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("apprMstId", apprMstId);
		map.put("crntDate", date.getString());
						
		//결재문서 정보 가져오기
		ApprovalDoc doc = null;	
		
		//결재 마스터 정보 가져오기.(결재양식함에서 선택한 결재의 마스터 정보임)
		ApprovalMaster apprMst = apprService.getApprovalMasterInfo(map);
		ApprovalLevel apprLevel = apprMst.getApprLevel();
				
		map.put("majCode", "CD008");
		map.put("orderBy", "rescKey");
		List<CommonCode> commonCodeList = commService.getCommonSubCodeList(map);
		
		String selRcpt = request.getParameter("selRcpt");		
		String selRcptDesc = request.getParameter("selRcptDesc");
		Map<String, String> rcptMap = new HashMap<String, String>();
		rcptMap.put("selRcpt", selRcpt);
		rcptMap.put("selRcptDesc", selRcptDesc);
		
		String selRfnc = request.getParameter("selRfnc");
		String selRfncDesc = request.getParameter("selRfncDesc");
		Map<String, String> rfncMap = new HashMap<String, String>();
		rfncMap.put("selRfnc", selRfnc);
		rfncMap.put("selRfncDesc", selRfncDesc);
		
		String content = request.getParameter("content");
		String docTitle = request.getParameter("docTitle");
		List<LineApprover> list = ApprovalCommonUtil.getParameterLineApproverList1(request);
		List<Nw115m> attachFileList = AttachFileUtil.setAttachFileList(request, dcmtRgsrNo, fileService);
		
		mav.addObject("doc", doc);
		mav.addObject("apprMstId", apprMstId);	//결재마스터 ID
		mav.addObject("apprMst", apprMst);	//결재 마스터 정보를 가져와서 설정
		mav.addObject("cntnId", cntnId);	//컨텐츠 ID 정보를 받아와서 설정
		mav.addObject("userSession", userSession);	//세션정보를 설정
		mav.addObject("apprLevel", apprLevel.getApprLevel());	//결재차수 설정
		mav.addObject("lineAppr", list);
		mav.addObject("rcptMap", rcptMap);
		mav.addObject("rfncMap", rfncMap);
		mav.addObject("serviceLife", commonCodeList);	//보존연한
		mav.addObject("prsvTermType", prsvTermType);	//선택한 보존연한 값
		mav.addObject("shortYear", date.getYearShort());	//현재 년도를 YY형태로 설정
		mav.addObject("year", date.getYear());	//현재 년도를 YYYY형태로 설정
		mav.addObject("docTitle", docTitle);
		mav.addObject("content", content);
		mav.addObject("attachList", attachFileList);	//첨부파일 목록 정보
		mav.addObject("atchIndc", atchIndc);	//첨부파일 유무
		
		return mav;
	}
}
