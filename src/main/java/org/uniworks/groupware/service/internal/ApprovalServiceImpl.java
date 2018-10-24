/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service.internal;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.common.util.ApplicationConfigReader;
import org.uniworks.groupware.common.util.DateUtil;
import org.uniworks.groupware.common.util.StringUtil;
import org.uniworks.groupware.domain.Nw111m;
import org.uniworks.groupware.domain.Nw112m;
import org.uniworks.groupware.domain.Nw113m;
import org.uniworks.groupware.domain.Nw114m;
import org.uniworks.groupware.domain.Nw115m;
import org.uniworks.groupware.domain.Nw116m;
import org.uniworks.groupware.domain.Nw119m;
import org.uniworks.groupware.domain.approval.ApprovalCategory;
import org.uniworks.groupware.domain.approval.ApprovalDoc;
import org.uniworks.groupware.domain.approval.ApprovalItem;
import org.uniworks.groupware.domain.approval.ApprovalLevel;
import org.uniworks.groupware.domain.approval.ApprovalMaster;
import org.uniworks.groupware.domain.approval.LineApprover;
import org.uniworks.groupware.mapper.ApprovalMapper;
import org.uniworks.groupware.mapper.Nw110mMapper;
import org.uniworks.groupware.mapper.Nw111mMapper;
import org.uniworks.groupware.mapper.Nw112mMapper;
import org.uniworks.groupware.mapper.Nw113mMapper;
import org.uniworks.groupware.mapper.Nw114mMapper;
import org.uniworks.groupware.mapper.Nw115mMapper;
import org.uniworks.groupware.mapper.Nw116mMapper;
import org.uniworks.groupware.mapper.Nw119mMapper;
import org.uniworks.groupware.service.ApprovalService;

/**
 * @author Park Chungwan
 *
 */
@Service
@Transactional(readOnly = true) 
public class ApprovalServiceImpl implements ApprovalService {
	private static final Logger logger = LoggerFactory.getLogger(ApprovalServiceImpl.class);
	@Autowired ApprovalMapper apprMapper;
	@Autowired Nw110mMapper nw110mMapper;
	@Autowired Nw111mMapper nw111mMapper;
	@Autowired Nw112mMapper nw112mMapper;
	@Autowired Nw113mMapper nw113mMapper;
	@Autowired Nw114mMapper nw114mMapper;
	@Autowired Nw115mMapper nw115mMapper;
	@Autowired Nw116mMapper nw116mMapper;	
	@Autowired Nw119mMapper nw119mMapper;
	
	/**
	 * 결제 카테고리 목록을 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public List<ApprovalCategory> getApprovalCategory(Map<String, Object> map) {
		// TODO Auto-generated method stub		
		return apprMapper.selectByApprvalCategory(map);
	}

	/**
	 * 결재 카테고리별 결재 양식함 목록을 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public List<ApprovalItem> getApprovalItemList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return apprMapper.selectByApprovalItemList(map);
	}

	/**
	 * 결재 마스터 정보를 가져온다.
	 * 수신처 유무, 참조처 유무, 협조 결재 유무, 협조 결재 유형, 참석자 유무, 회람 유무, 전결 규정 유무, 결재차수 등의 정보를 가져옴.
	 * @param map
	 * @return
	 */
	@Override
	public ApprovalMaster getApprovalMasterInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ApprovalMaster apprMst = apprMapper.selectByApprovalMasterInfo(map);
		ApprovalLevel apprLevel = apprMapper.selectByApprovalLevelNw012m(map);
		apprMst.setApprLevel(apprLevel);
		return apprMst;
	}

	/**
	 * 작성 중인 결재문서 목록을 가져온다.
	 * 작성 중: apprStus - 0
	 * @param map
	 * @return
	 */
	@Override
	public List<ApprovalDoc> getWritingApprovalDocList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String apprStus = (String) map.get("apprStus");
		List<ApprovalDoc> apprWritingList = null;
		
		if (apprStus.equalsIgnoreCase("0")) {	//작성 중인 상태
			apprWritingList = apprMapper.selectWritingApprovalDocList(map);
		}
		return apprWritingList;
	}
	
	/**
	 * 결재대기 중인 결제문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public List<ApprovalDoc> getWaitingApprovalDocList(Map<String, Object> map) {
		String apprStus = (String) map.get("apprStus");
		List<ApprovalDoc> apprWaitingList = null;
		
		if (apprStus.equalsIgnoreCase("1")) {	//대기상태
			apprWaitingList = apprMapper.selectWaitingApprovalDocList(map);
		}
		return apprWaitingList;
	}
	
	/**
	 * 결재진행 중인 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public List<ApprovalDoc> getProgressApprovalDocList(Map<String, Object> map) {
		String apprStus = (String) map.get("apprStus");
		List<ApprovalDoc> apprProgressList = null;
		
		if (apprStus.equalsIgnoreCase("3")) {
			apprProgressList = apprMapper.selectProgressApprovalDocList(map);
		}
		
		return apprProgressList;
	}
	
	/**
	 * 결재 완료된 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public List<ApprovalDoc> getCompleteApprovalDocList(Map<String, Object> map) {
		List<ApprovalDoc> apprCompleteList = null;
		
		apprCompleteList = apprMapper.selectCompleteApprovalDocList(map);		
		
		return apprCompleteList;
	}
	
	/**
	 * 수신 문서함 목록을 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public List<ApprovalDoc> getCompleteReceiveApprovalDocList(Map<String, Object> map) {
		List<ApprovalDoc> apprCompleteList = null;
		
		apprCompleteList = apprMapper.selectCompleteReceiveApprovalDocList(map);		
		
		return apprCompleteList;
	}
	
	/**
	 * 결재 반려된 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public List<ApprovalDoc> getRejectApprovalDocList(Map<String, Object> map) {
		String apprStus = (String) map.get("apprStus");
		List<ApprovalDoc> rejectList = null;
		
		if (apprStus.equalsIgnoreCase("5")) {
			rejectList = apprMapper.selectRejectApprovalDocList(map);
		}
		
		return rejectList;
	}
	
	/**
	 * 결재문서 정보를 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public ApprovalDoc getByApprovalDocNw110m(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ApprovalDoc doc = apprMapper.selectByApprovalDocNw110m(map);
		return doc;
	}
	/**
	 * 라인 결재자 정보를 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public List<LineApprover> getLineApproverNw111m(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<LineApprover> lineApprList = apprMapper.selectLineApproverNw111m(map);
		return lineApprList;
	}
	
	/**
	 * 등록된 결재문건 삭제
	 * 라인결재정보(NW111M),협조결재정보(NW112M),수신처(NW113M),참조처(NW114M),첨부파일(NW115M),참석자(NW116M) 및 결재정보(NW110M) 삭제
	 * @param map
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void removeApprovalDocument(Map<String, Object> map) {
		//결재마스터 정보를 가져온다.
		ApprovalMaster apprMst = apprMapper.selectByApprovalMasterInfo(map);
		//라인결재자 정보 삭제
		nw111mMapper.deleteByPrimaryKey(map);
		
		//수신처유무 확인 후 수신처 정보 삭제
		if (apprMst.getRcptIndc().equalsIgnoreCase("Y")) nw113mMapper.deleteByPrimaryKey(map);
		//참조처유무 확인 후 참조처 정보 삭제
		if (apprMst.getRfncIndc().equalsIgnoreCase("Y")) nw114mMapper.deleteByPrimaryKey(map);
		//협조결재 정보 확인 후 삭제
		if (apprMst.getCprtnIndc().equalsIgnoreCase("Y")) nw112mMapper.deleteByPrimaryKey(map);
		//참석자 유무 확인 후 참석자 정보 삭제
		if (apprMst.getAtndIndc().equalsIgnoreCase("Y")) nw116mMapper.deleteByPrimaryKey(map);
		//첨부파일 삭제 (물리적인 파일)
		deleteAttachFiles(map);
		//첨부파일 삭제 (Database)
		nw115mMapper.deleteByPrimaryKey(map);
		//결재정보 삭제
		nw110mMapper.deleteByPrimaryKey(map);
	}
	
	/**
	 * 결재 문서를 최초 등록 시 사용. 저장만, 저장후닫기에서만 호출.
	 * @param lang - 언어 
	 * @param apprDoc - 결재문서 정보
	 * @param list - 라인결재자 정보 
	 * @param nw112m - 협조결재자 정보
	 * @param nw113m - 수신처 정보
	 * @param nw114m - 참조처 정보
	 * @param nw115m - 첨부파일 정보
	 * @param nw116m - 참석자 정보
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addApprovalDocument(String lang, ApprovalDoc apprDoc, List<LineApprover> list, List<Nw112m> nw112m, 
			List<Nw113m> nw113m, List<Nw114m> nw114m, List<Nw115m> nw115m, List<Nw116m> nw116m) {
		//apprDoc의 dcmtRgsrNo값이 Null이거나 "" 경우에는 return. 
		//dcmtRgsrNo의 값이 설정되어 있어야 한다.
		if (apprDoc.getDcmtRgsrNo() == null || apprDoc.getDcmtRgsrNo().equals("")) return;		
		//현재 시간을 설정
		apprDoc.setDcmtRgsrDatetime(DateUtil.getCurrentDate());
		
		//같은 문서번호(dcmtRgsrNo)로 등록된 문서가 있는지 확인
		Map<String, Object> apprDocStus = new HashMap<String, Object>();
		apprDocStus.put("coId", apprDoc.getCoId());
		apprDocStus.put("cntnId", apprDoc.getCntnId());
		apprDocStus.put("dcmtRgsrNo", apprDoc.getDcmtRgsrNo());
		apprDocStus.put("lang", lang);
		
		if(!checkApprovalDocRgsrNoExist(apprDocStus)) {	//같은 문서번호가 존재하지 않을 경우 신규 결재문서를 등록.
			//결재문서 등록 
			addApprovalMasterDocument(apprDoc);			
			//라인 결재자 등록
			addLineApprover(list);			
			//협조결재자 등록
			addCprtApprover(nw112m);			
			//수신처 등록
			addRcptDestination(nw113m);			
			//참조처 등록
			addRfncDestination(nw114m);			
			//첨부파일 등록
			addAttachFile(nw115m);			
			//참석자 등록
			addAttendant(nw116m);
		}
	}
	
	/**
	 * 결재 문서 수정 시 사용. 저장만, 저장후닫기에서 호출.
	 * @param lang - 언어 
	 * @param apprDoc - 결재문서 정보
	 * @param list - 라인결재자 정보 
	 * @param nw112m - 협조결재자 정보
	 * @param nw113m - 수신처 정보
	 * @param nw114m - 참조처 정보
	 * @param nw115m - 첨부파일 정보
	 * @param nw116m - 참석자 정보
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateApprovalDocument(String lang, ApprovalDoc apprDoc, List<LineApprover> list, List<Nw112m> nw112m, 
			List<Nw113m> nw113m, List<Nw114m> nw114m, List<Nw115m> nw115m, List<Nw116m> nw116m) {
		//apprDoc의 dcmtRgsrNo값이 Null이거나 "" 경우에는 return. 
		//dcmtRgsrNo의 값이 설정되어 있어야 한다.
		if (apprDoc.getDcmtRgsrNo() == null || apprDoc.getDcmtRgsrNo().equals("")) return;
		
		apprDoc.setDcmtModDatetime(DateUtil.getCurrentDate());
		
		//같은 문서번호(dcmtRgsrNo)로 등록된 문서가 있는지 확인
		Map<String, Object> apprDocStus = new HashMap<String, Object>();
		apprDocStus.put("coId", apprDoc.getCoId());
		apprDocStus.put("cntnId", apprDoc.getCntnId());
		apprDocStus.put("dcmtRgsrNo", apprDoc.getDcmtRgsrNo());
		apprDocStus.put("lang", lang);
		
		if(checkApprovalDocRgsrNoExist(apprDocStus)) {	//등록된 결재문서가 있을 경우 Update.
			//결재문서 Update
			updateApprovalMasterDocument(apprDoc);
			//라인결재자 Update
			if (list != null) updateLineApprover(apprDoc, list);
			//협조결재자 Update
			if (nw112m != null) updateCprtApprover(apprDoc, nw112m);
			//수신처 Update
			updateRcptDestination(apprDoc, nw113m);			
			//참조처 Update
			updateRfncDestination(apprDoc, nw114m);			
			//첨부파일 Update
			updateAttachFile(apprDoc, nw115m);			
			//참석자 Update
			updateAttendant(apprDoc, nw116m);
		}
	}
	
	/**
	 * 결재문서의 라인결재 승인/반려 시의 문서 결재상태 및 라인결재 정보를 변경한다.
	 * @param coId - 회사코드
	 * @param cntnId - Content ID
	 * @param dcmtRgsrNo - 문서등록번호
	 * @param lang - 사용언어
	 * @param userId - 로그인한 사용자의 사번(세션정보에서 가져와야 함)
	 * @param steadApprIndc - 대결유무 
	 * @param apprStus 결재자의 결재승인 정보 (5:반려, 7:승인, 9:대결) 
	 * @param comment 결재 코멘트
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void changeLineApproverStatus(String coId, String cntnId, String dcmtRgsrNo, String lang, String userId, String steadApprIndc, String apprStus, String comment) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", coId);
		map.put("cntnId", cntnId);
		map.put("dcmtRgsrNo", dcmtRgsrNo);
		map.put("lang", lang);
		ApprovalDoc apprDoc = apprMapper.selectByApprovalDocNw110m(map);
		
		boolean apprCheck = checkAndChangeApprovalDocOfLineApprover(coId, cntnId, dcmtRgsrNo, lang, userId, apprDoc, apprStus, comment);
		logger.debug("apprCheck: " + apprCheck);
	}
	
	/**
	 * 결재문서의 협조결재 승인/반려 시의 문서 결재상태 및 협조결재 정보를 변경한다.
	 * @param coId
	 * @param cntnId
	 * @param dcmtRgsrNo
	 * @param lang
	 * @param cprtEmpNo
	 * @param steadApprIndc
	 * @param apprStus
	 * @param comment
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void changeCprtnApproverStatus(String coId, String cntnId, String dcmtRgsrNo, String lang, UserSession userSession, ApprovalDoc apprDoc, String steadApprIndc, String apprStus, String comment) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", coId);
		map.put("cntnId", cntnId);
		map.put("dcmtRgsrNo", dcmtRgsrNo);
		map.put("lang", lang);
						
		//협조 결재는 결재 진행 상태일 경우에만 처리
		if (apprDoc != null && apprDoc.getApprStus().equalsIgnoreCase("3")) {
			checkAndChangeApprovalDocOfCprtnApprover(coId, cntnId, dcmtRgsrNo, lang, userSession, apprDoc, steadApprIndc, apprStus, comment);
		}
	}		
	
	/**
	 * userId로 현재 결재를 해야 할 경우에만 true를 리턴. 그렇지 않은 경우에는 모두 false를 리턴.(등록된 라인결재자가 승인할 경우)
	 * @param userId
	 * @param apprDoc
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private boolean checkAndChangeApprovalDocOfLineApprover(String coId, String cntnId, String dcmtRgsrNo, String lang, String userId, ApprovalDoc apprDoc, String apprStus, String comment) {
		boolean check = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", coId);
		map.put("cntnId", cntnId);
		map.put("dcmtRgsrNo", dcmtRgsrNo);
		map.put("lang", lang);		
		map.put("apprStus", "1");
		
		//라인결재자의 차기 결재차수를 가져와서 설정. nextApprDgr이 0일 경우에는 최종결재자임.
		int nextApprDgr = apprMapper.selectNextApproverDgrNw111m(map);		
		
		ApprovalDoc tempApprDoc = new ApprovalDoc();
		tempApprDoc.setCoId(coId);
		tempApprDoc.setCntnId(cntnId);
		tempApprDoc.setDcmtRgsrNo(dcmtRgsrNo);		
		tempApprDoc.setApprStus("3");
		tempApprDoc.setCrntApprDgr(apprDoc.getCrntApprDgr());
		
		//라인결재자의 상태 정보와 승인 정보를 변경하기 위해서 선언.
		Nw111m nw111m = new Nw111m();
		nw111m.setCoId(coId);
		nw111m.setCntnId(cntnId);
		nw111m.setDcmtRgsrNo(dcmtRgsrNo);
		nw111m.setApprDgr(apprDoc.getCrntApprDgr());
		nw111m.setComment(comment);
		
		//작성 중인 문서일 경우.
		if (apprDoc.getCrntApprDgr() == 0) {	//현재 문서가 작성 중인 결재문서이면서 
			if (userId.equalsIgnoreCase(apprDoc.getAuthEmpNo())) {	//userId가 작성자 사번과 같을 경우.
				tempApprDoc.setCrntApprDgr(nextApprDgr);
				approvalRequest(tempApprDoc);	//결재문서의 결재상태 정보를 변경.
				nw111m.setApprDgr(nextApprDgr);	//차기 라인결재 차수 정보를 설정.
				setlineApproverStatusWaiting(nw111m, nextApprDgr);
				check = true;
			} else {
				check = false;
			}			
			return check;
		}
		
		map.put("apprStus", "1");
		map.put("apprEmpNo", userId);
		map.put("apprDgr", apprDoc.getCrntApprDgr());
		List<LineApprover> list = apprMapper.selectLineApproverNw111m(map);
		if (list.size() > 0) {
			LineApprover lineAppr = (LineApprover) list.get(0);
		
			if (apprDoc.getCrntApprDgr() > 0 && apprDoc.getCrntApprDgr() < apprDoc.getMaxApprDgr()) {	//라인 결재 진행 중인 결재문서 - 승인나기 전.
				//라인결재자 중 결재를 아직 하지 않은 사람들 중에 결재 차수가 가장 낮은 사람의 사번과 userId를 비교해서 동일한 사람일 경우 true를 리턴.			
				if (lineAppr.getApprDgr() == apprDoc.getCrntApprDgr()) {	//로그인 사용자가 결재자인 경우
					tempApprDoc.setCrntApprDgr(nextApprDgr);
					if (apprStus.equals("5")) {	//반려일 경우
						tempApprDoc.setApprStus(apprStus);
					} else {	//승인일 경우
						tempApprDoc.setApprStus("3");
					}
					check = true;
				} else {
					check = false;
				}
			} else if (apprDoc.getCrntApprDgr() == apprDoc.getMaxApprDgr() && tempApprDoc.getApprStus().equalsIgnoreCase("3") && nextApprDgr == 0) {	//최종결재자가 결재해야 할 순서일 경우 nextApprDgr의 값이 0.
				if (lineAppr.getApprDgr() == apprDoc.getCrntApprDgr()) {	//로그인 사용자가 최종 결재자인 경우.
					tempApprDoc.setApprStus(apprStus);	//최종 승인자가 반려일 경우 5, 승인일 경우 7
					if (!apprStus.equalsIgnoreCase("5")) {
						DateUtil du = new DateUtil();
						String docNo = apprDoc.getDocNo() + getApprovalDocumentNo(coId, cntnId, apprDoc.getAuthDeptCode(), du.getYear(), 3);	//3자리까지 표기.
						tempApprDoc.setDocNo(docNo);
					}
					check = true;
				} else {
					check = false;
				}
			} else if (apprDoc.getCrntApprDgr() == apprDoc.getMaxApprDgr() && apprDoc.getApprStus().equalsIgnoreCase("7")) {	//결재승인이 완료
				check = false;
			} else if (apprDoc.getCrntApprDgr() <= apprDoc.getMaxApprDgr() && apprDoc.getApprStus().equalsIgnoreCase("9")) {	//대결이 완료된 경우
				check = false;
			} else if (apprDoc.getCrntApprDgr() < 0 && apprDoc.getApprStus().equalsIgnoreCase("5")) {	//결재가 반려된 경우
				check = false;
			}
		}
		
		if (check) {
			approvalRequest(tempApprDoc);	//결재문서의 결재상태 정보를 변경.
			setLineApproved(nw111m, apprStus);
			if (apprDoc.getCrntApprDgr() != apprDoc.getMaxApprDgr()) {
				if (apprStus.equals("7"))	setlineApproverStatusWaiting(nw111m, nextApprDgr);
			}
		}
		return check;
	}
	
	/**
	 * userId로 현재 협조결재를 해야 할 경우에만 true를 리턴. 그렇지 않은 경우에는 모두 false를 리턴.(등록된 협조결재자가 승인할 경우)
	 * @param coId
	 * @param cntnId
	 * @param dcmtRgsrNo
	 * @param lang
	 * @param userSession
	 * @param apprDoc
	 * @param steadApprIndc
	 * @param apprStus
	 * @param comment
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private boolean checkAndChangeApprovalDocOfCprtnApprover(String coId, String cntnId, String dcmtRgsrNo, String lang, UserSession userSession, ApprovalDoc apprDoc, String steadApprIndc, String apprStus, String comment) {
		boolean check = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", coId);
		map.put("cntnId", cntnId);
		map.put("dcmtRgsrNo", dcmtRgsrNo);
		map.put("lang", lang);		
		map.put("apprStus", "1");
		
		//협조결재자의 차기 결재차수를 가져와서 설정. nextApprDgr이 0일 경우에는 최종결재자임.
		int crntApprDgr = apprMapper.selectCrntApproverDgrNw112m(map);
		int nextApprDgr = apprMapper.selectNextApproverDgrNw112m(map);			
		
		//결재상태가 진행상태가 아닐 경우에는 협조결재를 할 수 없다.
		if (!apprDoc.getApprStus().equalsIgnoreCase("3")) return false;
		
		//협조결재자의 상태 정보와 승인 정보를 변경하기 위해서 선언.
		Nw112m nw112m = new Nw112m();
		nw112m.setCoId(coId);
		nw112m.setCntnId(cntnId);
		nw112m.setDcmtRgsrNo(dcmtRgsrNo);
		nw112m.setApprDgr(crntApprDgr);
		nw112m.setComment(comment);
		nw112m.setCprtEmpNo(userSession.getUserId());
		nw112m.setDutyDesc(userSession.getDutyDesc());
		nw112m.setPstnDesc(userSession.getPstnDesc());		
		nw112m.setApprStus(apprStus);
		nw112m.setApprDateTime(new Timestamp(DateUtil.getCurrentDate().getTime()));
		
		int cnt = nw112mMapper.updateByPrimaryKey(nw112m);
		
		//협조 반려를 했을 경우, 결재 마스터의 결재 진행상태 값을 5로 변경
		if (apprStus.equalsIgnoreCase("5")) {
			ApprovalDoc tempApprDoc = new ApprovalDoc();
			tempApprDoc.setCoId(coId);
			tempApprDoc.setCntnId(cntnId);
			tempApprDoc.setDcmtRgsrNo(dcmtRgsrNo);		
			tempApprDoc.setApprStus("5");
			
			approvalRequest(tempApprDoc);	//결재문서의 결재상태 정보를 변경.
		}
		
		
		//nextApprDgr이 협조결재 최종 승인자가 아니면 0이 아님. 따라서 자신의 결재정보 업데이트 이후 차기 결재자 상태를 1로 변경
		if (cnt > 0 && nextApprDgr > 0 && apprStus.equalsIgnoreCase("7")) {
			//차기 협조 결재자 결재상태 정보를 대기 상태로 변경 (0 -> 1)
			Nw112m nextCprnt = new Nw112m();
			nextCprnt.setCoId(coId);
			nextCprnt.setCntnId(cntnId);
			nextCprnt.setDcmtRgsrNo(dcmtRgsrNo);
			nextCprnt.setApprDgr(nextApprDgr);
			nextCprnt.setApprStus("1");
			cnt = nw112mMapper.updateByPrimaryKey(nextCprnt);
		}
		
		if (cnt > 0) check = true;
		
		return check;
	}
	
	/**
	 * 결재문서에 대해서 현재 결재를 할 수 있는지 여부를 체크.
	 * 결재차수에 해당하는 결재자가 본인이 맞는지 확인해서 맞으면 true, 틀리면 false를 리턴한다.
	 * @param coId
	 * @param cntnId
	 * @param dcmtRgsrNo
	 * @param lang
	 * @param userId
	 * @return
	 */
	@Override
	public boolean checkApprovalDocModifyAuthrity(String coId, String cntnId, String dcmtRgsrNo, UserSession userSession) {
		boolean check = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", coId);
		map.put("cntnId", cntnId);
		map.put("dcmtRgsrNo", dcmtRgsrNo);
		map.put("lang", userSession.getLanguage());		
		//결재문서 정보를 가져온다.
		ApprovalDoc apprDoc = apprMapper.selectByApprovalDocNw110m(map);
		
		if (apprDoc.getCrntApprDgr() == 0) { //작성 중인 문건
			if (apprDoc.getAuthEmpNo().equals(userSession.getUserId())) {
				check = true;
			}
		} else {
			map.put("apprStus", "1");
			map.put("apprEmpNo", userSession.getUserId());
			map.put("apprDgr", apprDoc.getCrntApprDgr());
			List<LineApprover> list = apprMapper.selectLineApproverNw111m(map);
			if (list.size() > 0) {	//결재차수에 해당하는 결재자가 있을 경우
				LineApprover lineAppr = (LineApprover)list.get(0);
				if (lineAppr.getApprEmpNo().equals(userSession.getUserId())) check = true;
			} else {	//결재차수에 해당하는 결재자가 없을 경우 false를 리턴.
				check = false;
			}	
			
			//협조결재자 결재 유무 확인
			if (check) {
				//협조결재자 결재 유무 체크
				boolean cprtnChk = checkLineApproveUseIndc(coId, cntnId, dcmtRgsrNo, userSession);
				//협조결재자 결재 유무 체크가 true를 반환하면 라인결재를 할 수 없도록 false를 설정
				if (!cprtnChk) {
					check = false;
				} 
			}
		}
				
		return check;
	}
	
	/**
	 * 문서의 결재 상태 정보를 변경한다.
	 * apprStus, crntApprDgr의 정보를 0이 아닌 값으로 변경한다.
	 * @param apprDoc
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private void approvalRequest(ApprovalDoc apprDoc) {
		apprMapper.updateApprovalDocNw110m(apprDoc);
	}
	
	private void setLineApproved(Nw111m nw111m, String apprStus) {
		nw111m.setApprStus(apprStus);
		DateUtil dt = new DateUtil();
		Timestamp apprDateTime = dt.getCurrentDateTimeConvertToTimestamp();
		nw111m.setApprDateTime(apprDateTime);
		nw111mMapper.updateByPrimaryKey(nw111m);
	}
	
	/**
	 * 차기 라인결재자 정보를 대기상태로 변경한다.
	 * @param nw111m
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private void setlineApproverStatusWaiting(Nw111m nw111m, int nextApprDgr) {
		nw111m.setApprStus("1");
		nw111m.setApprDateTime(null);
		nw111m.setApprDgr(nextApprDgr);
		nw111m.setComment("");	//현 결재자의 코멘트 정보를 삭제한다. 파라미터로 들어오는 nw111m에는 현재 결재자 정보가 저장되어 있음.
		nw111mMapper.updateByPrimaryKey(nw111m);
	}
	
	/**
	 * 기 등록된 결재 문서가 있는 확인.
	 * @param map
	 * @return
	 */
	private boolean checkApprovalDocRgsrNoExist(Map<String, Object> map) {
		boolean exist = false;
		//결재문서 정보를 가져온다.
		ApprovalDoc apprDoc = (ApprovalDoc) apprMapper.selectByApprovalDocNw110m(map);
		
		String dcmtRgsrNo = (String) map.get("dcmtRgsrNo");
		if (apprDoc != null && apprDoc.getDcmtRgsrNo().equalsIgnoreCase(dcmtRgsrNo)) exist = true;
		
		return exist;
	}
	
	/**
	 * 결재 문서 정보(NW110M)를 가져온다.
	 * @param map - coId, cntnId, dcmtRgsrNo, locale
	 * @return
	 */
	public ApprovalDoc getApprovalDocumentInfo(Map<String, Object> map) {
		//결재문서 정보를 가져온다.
		ApprovalDoc apprDoc = (ApprovalDoc) apprMapper.selectByApprovalDocNw110m(map);
		
		return apprDoc;
	}
	
	/**
	 * 결재문서 정보(NW110M)를 등록한다.
	 * @param apprDoc
	 */	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addApprovalMasterDocument(ApprovalDoc apprDoc) {
		apprMapper.insertApprovalDocNw110m(apprDoc);
	}
	
	/**
	 * 결재문서 정보(NW100M)를 수정한다.
	 * @param apprDoc
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateApprovalMasterDocument(ApprovalDoc apprDoc) {
		//결재문서 업데이트 시 현재 결재 차수가 0일 경우, 결재 상태를 0으로 설정.
		if (apprDoc.getCrntApprDgr() == 0) apprDoc.setApprStus("0");
		apprMapper.updateApprovalDocNw110m(apprDoc);
	}
	
	/**
	 * 라인 결재자의 코멘트 정보를 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<LineApprover> getDocApproverCommentsList(Map<String, Object> map) {
		List<LineApprover> apprCommentsList = apprMapper.selectLineApproverNw111m(map);
		
		for(int i = 0; i < apprCommentsList.size(); i++) {
			LineApprover lineAppr = apprCommentsList.get(i);
			if (lineAppr.getSteadApprIndc().equals("Y")) {
				lineAppr.setApprEmpName(lineAppr.getSteadApprEmpName());
			}
		}
		
		return apprCommentsList;
	}
	
	/**
	 * 라인 결재자 정보 등록.
	 * @param lineList
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addLineApprover(List<LineApprover> list) {
		if (list == null) return;
		
		//라인결재자 등록
		for (LineApprover lineAppr : list) {
			apprMapper.insertLineApproverNw111m(lineAppr);
		}
	}
	
	/**
	 * 라인 결재자 정보를 업데이트
	 * @param list
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateLineApprover(ApprovalDoc doc, List<LineApprover> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", doc.getCoId());
		map.put("cntnId", doc.getCntnId());
		map.put("dcmtRgsrNo", doc.getDcmtRgsrNo());
		
		//등록된 라인 결재자 정보를 삭제
		apprMapper.deleteLineApproverNw111m(map);
		
		//라인결재자 등록
		if (list == null) return;
		for (LineApprover lineAppr : list) {
			apprMapper.insertLineApproverNw111m(lineAppr);
		}
	}
	
	/**
	 * 협조 결재자 정보 등록
	 * @param cprtList
	 */	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addCprtApprover(List<Nw112m> cprtList) {
		if (cprtList == null) return;
		
		//협조결재자 등록
		for (Nw112m cprtAppr : cprtList) {			
			nw112mMapper.insert(cprtAppr);
		}
	}
	
	/**
	 * 협조 결재자 정보 수정(Nw112m)
	 * @param cprtList
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCprtApprover(ApprovalDoc doc, List<Nw112m> cprtList) {
		//협조결재자 삭제
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", doc.getCoId());
		map.put("cntnId", doc.getCntnId());
		map.put("dcmtRgsrNo", doc.getDcmtRgsrNo());
		
		apprMapper.deleteCprtApproverNw112m(map);
		
		if (cprtList == null) return;
		
		//협조결재자 등록
		for (Nw112m cprtAppr : cprtList) {			
			nw112mMapper.insert(cprtAppr);
		}
	}
	
	/**
	 * 수신처 정보 등록 
	 * @param rcptList
	 */	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addRcptDestination(List<Nw113m> rcptList) {
		if (rcptList == null) return;
		
		//수신처 정보 등록
		for (Nw113m rcptDest : rcptList) {
			nw113mMapper.insert(rcptDest);
		}
	}
	
	/**
	 * 수신처 정보 수정(Nw113m)
	 * @param rcptList
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateRcptDestination(ApprovalDoc doc, List<Nw113m> rcptList) {
		//수신처 삭제
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", doc.getCoId());
		map.put("cntnId", doc.getCntnId());
		map.put("dcmtRgsrNo", doc.getDcmtRgsrNo());
	
		apprMapper.deleteRcptDestinationNw113m(map);
		
		if (rcptList == null) return;
		
		//수신처 정보 등록
		for (Nw113m rcptDest : rcptList) {
			nw113mMapper.insert(rcptDest);
		}
	}
	
	/**
	 * 참조처 정보 등록
	 * @param rfncList
	 */	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addRfncDestination(List<Nw114m> rfncList) {
		if (rfncList == null) return;
		
		//참조처 정보 등록
		for (Nw114m rfncDest : rfncList) {
			nw114mMapper.insert(rfncDest);
		}
	}
	
	/**
	 * 참조처 정보 수정
	 * @param rfncList
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateRfncDestination(ApprovalDoc doc, List<Nw114m> rfncList) {
		//참조처 삭제
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", doc.getCoId());
		map.put("cntnId", doc.getCntnId());
		map.put("dcmtRgsrNo", doc.getDcmtRgsrNo());
		
		apprMapper.deleteRfncDestinationNw114m(map);
		
		if (rfncList == null) return;
		
		//참조처 정보 등록
		for (Nw114m rfncDest : rfncList) {
			nw114mMapper.insert(rfncDest);
		}
	}
	
	/**
	 * 첨부파일 등록
	 * @param attchFile
	 */	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addAttachFile(List<Nw115m> attchFileList) {
		if (attchFileList == null) return;
		
		//첨부파일 등록
		for (Nw115m attchFile : attchFileList) {
			moveTempFileToDestinationFolder(attchFile);
			File existFile = new File(attchFile.getFilePath() + File.separator + attchFile.getAttchFileSysName());
			//파일이 정상적으로 move되었을 경우에만 DB에 파일정보 추가.
			if (existFile.exists()) {				
				nw115mMapper.insert(attchFile);
			}
		}
	}
	
	/**
	 * 첨부파일 수정
	 * @param attchFileList
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateAttachFile(ApprovalDoc doc, List<Nw115m> attchFileList) {
		//첨부파일 삭제
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", doc.getCoId());
		map.put("cntnId", doc.getCntnId());
		map.put("dcmtRgsrNo", doc.getDcmtRgsrNo());
		
		apprMapper.deleteAttachFileListNw115m(map);
		
		if (attchFileList == null) return;
		
		//첨부파일 등록
		for (Nw115m attchFile : attchFileList) {
			moveTempFileToDestinationFolder(attchFile);
			File existFile = new File(attchFile.getFilePath() + File.separator + attchFile.getAttchFileSysName());
			//파일이 정상적으로 move되었을 경우에만 DB에 파일정보 추가.
			if (existFile.exists()) {				
				nw115mMapper.insert(attchFile);
			}
		}
	}
	
	/**
	 * 첨부파일 삭제. Database에 등록된 파일에 대해서 실제 물리적 파일들도 모두 삭제
	 * @param doc
	 * @param attachFileList
	 */
	private void deleteAttachFiles(Map<String, Object> map) {
		//첨부파일 정보 가져오기.
		List<Nw115m> attchFiles = nw115mMapper.select(map);
		
		for (Nw115m attachFile : attchFiles) {
			File existFile = new File(attachFile.getFilePath() + File.separator + attachFile.getAttchFileSysName());
			if (existFile.exists()) existFile.delete();			
		}
	}
	
	/**
	 * Temp Folder의 파일을 실제 저장위치로 옮긴다.
	 * @param attachFile
	 */
	private void moveTempFileToDestinationFolder(Nw115m attachFile) {
		//temp file path 가져오기
		String tempFilePath = ApplicationConfigReader.get("file.tepmUpload");
		//destination file path 가져오기
		String destFilePath = attachFile.getFilePath();
		
		File tempFile = new File(tempFilePath + File.separator + attachFile.getAttchFileSysName());
		File destDir = new File(destFilePath);
		
		try {
			FileUtils.moveFileToDirectory(tempFile, destDir, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 참석자 정보 등록
	 * @param attendList
	 */	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addAttendant(List<Nw116m> attendList) {
		if (attendList == null) return;
		
		//참석자 정보 등록
		for (Nw116m attend : attendList) {
			nw116mMapper.insert(attend);
		}
	}
	
	/**
	 * 참석자 정보 수정
	 * @param attendList
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateAttendant(ApprovalDoc doc, List<Nw116m> attendList) {
		//참석자 정보 삭제
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", doc.getCoId());
		map.put("cntnId", doc.getCntnId());
		map.put("dcmtRgsrNo", doc.getDcmtRgsrNo());
		
		apprMapper.deleteAttendantNw116m(map);
		
		if (attendList == null) return;
		
		//참석자 정보 등록
		for (Nw116m attend : attendList) {
			nw116mMapper.insert(attend);
		}
	}
	
	/**
	 * 결재문서에 대한 문서번호를 생성해서 가져온다.
	 * @param coId
	 * @param cntnId
	 * @param year
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String getApprovalDocumentNo(String coId, String cntnId, String deptCode, String year, int len) {
		String docNo = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", coId);
		map.put("cntnId", cntnId);
		map.put("deptCode", deptCode);
		map.put("year", year);
		
		Nw119m nw119m = nw119mMapper.selectByPrimaryKey(map);
		if (nw119m == null) {	//해당 연도에 특정 결재타입렬 부서별 문서번호가 생성되지 않았을 경우
			nw119m = new Nw119m();
			nw119m.setCoId(coId);
			nw119m.setCntnId(cntnId);
			nw119m.setDeptCode(deptCode);
			nw119m.setYear(year);
			nw119m.setDocNo(1);
			nw119mMapper.insert(nw119m);
		} else {	//기 등록된 문서 번호를 1 플러스 시켜서 업데이트
			nw119m.setDocNo(nw119m.getDocNo() + 1);
			nw119mMapper.updateByPrimaryKey(nw119m);
		}
		
		//docNo = year + "-" + StringUtil.getNstring(nw119m.getDocNo(), len); //년도 + 문서시리얼번호
		docNo = " " + StringUtil.getNstring(nw119m.getDocNo(), len); //문서시리얼번호
		return docNo;
	}
	
	/**
	 * 협조결재자가 결재를 해야 할 경우 true, 그렇지 않을 경우 false
	 * 협조결재 타입(협조결재 후 라인결재, 협조결재와 라인결재가 병렬로 처리 되나 라인결재가 완료되면 협조결재 불가)에 따라 협조결재 상태를 체크
	 * @param coId
	 * @param cntnId
	 * @param apprMstId
	 * @param dcmtRgsrNo
	 * @param userSession
	 * @return
	 */
	@Override
	public boolean checkCprtnApprovalPersonInfo(String coId, String cntnId, String dcmtRgsrNo, UserSession userSession) {
		boolean check = false;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", coId);
		map.put("cntnId", cntnId);		
		map.put("dcmtRgsrNo", dcmtRgsrNo);		
		map.put("lang", userSession.getLanguage());
		DateUtil date = new DateUtil();
		map.put("crntDate", date.getString());
		
		//보직자가 맞는지 확인
				
		//현재 결재문서의 정보를 가져온다.
		ApprovalDoc apprDoc = getByApprovalDocNw110m(map);
		map.put("apprMstId", apprDoc.getApprMstId());
		
		//협조결재 타입을 확인하기 위해서 결재마스터 정보를 확인
		ApprovalMaster apprMst = getApprovalMasterInfo(map);
				
		map.put("apprDgr", apprDoc.getCrntApprDgr());
		//협조결재타입이 O: 순차(협조결재 완료 후 라인결재 진행), 타입이 P: 협조결재와 라인결재가 병렬로 처리. (라인결재가 완료되면 협조결재 처리 불가)  
		if (apprMst.getCprtnIndc().equalsIgnoreCase("Y")) {
			//로그인한 사용자가 협조결재자인지 확인한다.
			List<Nw112m> nw112mList = apprMapper.selectCprtnApprovalPersonInfo(map);
			//라인결재차수와 로그인 사용자의 협조결재차수가 동일한지 체크. 
			for (int i = 0; i < nw112mList.size(); i++) {
				Nw112m nw112m = nw112mList.get(i);
				//부서코드가 같고 보직자인 경우에 협조결재를 할 수 있다.
				if (userSession.getDeptCode().equalsIgnoreCase(nw112m.getDeptCode()) && 
						!userSession.getPstnCode().isEmpty()) {
					//line 결재자가 결재하지 않았을 경우, 협재결조가 가능. 협조결재자도 대기 상태인 경우.
					if (apprDoc.getCrntApprDgr() >= nw112m.getApprDgr() && nw112m.getApprStus().equals("1") && nw112m.getApprDateTime() == null) {
						check = true;
					}
				}
			}
		}
		
		return check;
	}
	
	/**
	 * 라인결재 전에 협조결재 중 협조결재가 완료되어야 라인결재가 가능한지 여부 등을 체크
	 * @param coId
	 * @param cntnId
	 * @param apprMstId
	 * @param dcmtRgsrNo
	 * @param userSession
	 * @return
	 */
	private boolean checkLineApproveUseIndc(String coId, String cntnId, String dcmtRgsrNo, UserSession userSession) {
		boolean check = true;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", coId);
		map.put("cntnId", cntnId);		
		map.put("dcmtRgsrNo", dcmtRgsrNo);		
		map.put("lang", userSession.getLanguage());
		
		//현재 결재문서의 정보를 가져온다.
		ApprovalDoc apprDoc = getByApprovalDocNw110m(map);		
		map.put("apprMstId", apprDoc.getApprMstId());
		map.put("apprDgr", apprDoc.getCrntApprDgr());
		
		//협조결재 타입을 확인하기 위해서 결재마스터 정보를 확인
		ApprovalMaster apprMst = getApprovalMasterInfo(map);				
		
		//협조결재가 아닐 경우.
		if (apprMst.getCprtnIndc().equalsIgnoreCase("N")) {
			check = false;
			return check;
		}
				
		if (apprMst.getCprtnType().equalsIgnoreCase("O")) { //협조결재가 끝나야 라인결재가 시작인 경우
			List<Nw112m> nw112mList = apprMapper.selectCprtnApprovalPersonInfo(map);
			
			for (Nw112m nw112m : nw112mList) {
				if (nw112m.getApprStus().equals("1")) {
					check = false;
					break;
				}				
			}
		} 
		
		return check;
	}
}
