/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.HiddenField;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.domain.approval.ApprovalDoc;
import org.uniworks.groupware.domain.approval.LineApprover;
import org.uniworks.groupware.service.ApprovalService;
import org.uniworks.groupware.service.Nw112mService;
import org.uniworks.groupware.service.Nw120mService;

/**
 * @author Park Chungwan
 *
 */
@Controller
@RequestMapping(value = "/rest")
public class ApprovalController {
	private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);
	@Autowired private ApprovalService apprService;
	@Autowired private Nw112mService nw112mService;
	@Autowired private Nw120mService nw120mService;
	@Autowired private MessageSource messageSource;
	/**
	 * 작성중인 문서 목록 가져오기
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approval/approval_writing_list_01", method = RequestMethod.GET)
	@ResponseBody
	public List<ApprovalDoc> approvalWritingList(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("authEmpNo", userSession.getUserId());
		map.put("apprStus", "0");	//apprStus가 0이면 작성 중.
		
		List<ApprovalDoc> docList = apprService.getWritingApprovalDocList(map);
		
		return docList;
	}
	
	/**
	 * 대기중인 문서 목록 가져오기
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approval/approval_waiting_list_01", method = RequestMethod.GET)
	@ResponseBody
	public List<ApprovalDoc> approvalWaitingList(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("apprEmpNo", userSession.getUserId());
		map.put("apprStus", "1");	//apprStus가 1이면 대기중.
		
		List<ApprovalDoc> docList = apprService.getWaitingApprovalDocList(map);
		
		return docList;
	}
	
	/**
	 * 결재 진행 중인 문서 목록 가져오기
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approval/approval_progress_list_01", method = RequestMethod.GET)
	@ResponseBody
	public List<ApprovalDoc> approvalProcessList(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("apprEmpNo", userSession.getUserId());
		map.put("apprStus", "3");	//apprStus가 3이면 진행중.
		
		List<ApprovalDoc> docList = apprService.getProgressApprovalDocList(map);
		
		return docList;
	}
	
	/**
	 * 결재 완료된 결재 문서 목록 가져오기
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approval/approval_complete_list_01", method = RequestMethod.GET)
	@ResponseBody
	public List<ApprovalDoc> approvalCompleteList(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("apprEmpNo", userSession.getUserId());
		
		List<ApprovalDoc> docList = apprService.getCompleteApprovalDocList(map);
		
		return docList;
	}
	
	/**
	 * 결재 반려된 결재문서 목록 가져오기
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approval/approval_reject_list_01", method = RequestMethod.GET)
	@ResponseBody
	public List<ApprovalDoc> approvalRejectList(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("apprEmpNo", userSession.getUserId());
		map.put("apprStus", "5");	//apprStus가 5이면 반려.
		
		List<ApprovalDoc> docList = apprService.getRejectApprovalDocList(map);
		
		return docList;
	}
	
	/**
	 * 결재문서 삭제
	 * @param cntnId
	 * @param dcmtRgsrNo
	 * @param apprMstId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/approval/remove_approval_doc_01", method = RequestMethod.GET)
	@ResponseBody
	public String removeApprovalDocument(@RequestParam String cntnId, @RequestParam String dcmtRgsrNo, 
			@RequestParam String apprMstId, HttpServletRequest request, HttpServletResponse response) {
		String strDelResult = messageSource.getMessage("resc.msg.deleteOk", null, response.getLocale());
		logger.debug("removeApprovalDocument - response.getLocale() : " + response.getLocale());
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("cntnId", cntnId);
		map.put("dcmtRgsrNo", dcmtRgsrNo);
		map.put("apprMstId", apprMstId);
		map.put("lang", userSession.getLanguage());
		
		apprService.removeApprovalDocument(map);
		
		return strDelResult;
	}
	
	/**
	 * 결재문서에 대한 라인 결재자 목록을 가져온다. 결재차수별 결재자 승인 정보를 목록으로 제공.
	 * @param cntnId
	 * @param dcmtRgsrNo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approval/line_appr_comments_01", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<LineApprover> getAppriverList(@RequestParam String cntnId, @RequestParam String dcmtRgsrNo,
			HttpServletRequest request) {
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("cntnId", cntnId);
		map.put("dcmtRgsrNo", dcmtRgsrNo);
		map.put("lang", userSession.getLanguage());
		
		List<LineApprover> lineApprList = apprService.getDocApproverCommentsList(map);
		
		return lineApprList;
	}		
	
	/**
	 * 라인결재자 정보를 저장한다.
	 * @param lineApprovals
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approval/save_line_appr", method = {RequestMethod.POST})
	@ResponseBody
	public String saveLineApprovalEmpNo(@RequestParam String lineApprovals, HttpServletRequest request, HttpServletResponse response) {
		String msg = "";
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("userId", userSession.getUserId());
		
		int maxSeqNo = nw120mService.getMaxSeqNo(map);
		map.put("seqNo", maxSeqNo + 1);
		
		StringTokenizer lineApprArray = new StringTokenizer(lineApprovals, ",");
		while(lineApprArray.hasMoreTokens()) {
			StringTokenizer lineApprs = new StringTokenizer(lineApprArray.nextToken(), ":");
			String apprLineLev = lineApprs.nextToken();
			String apprEmpNo = lineApprs.nextToken();
			
			map.put(apprLineLev, apprEmpNo);
		}
		
		int result = nw120mService.addNw120mMap(map);
		if (result == 1) msg = messageSource.getMessage("resc.msg.addOk", null, response.getLocale());
		else msg = messageSource.getMessage("resc.msg.addFail", null, response.getLocale());;
		
		return msg;
	}
}
