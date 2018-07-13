/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.domain.Nw112m;
import org.uniworks.groupware.domain.Nw113m;
import org.uniworks.groupware.domain.Nw114m;
import org.uniworks.groupware.domain.Nw115m;
import org.uniworks.groupware.domain.Nw116m;
import org.uniworks.groupware.domain.approval.ApprovalCategory;
import org.uniworks.groupware.domain.approval.ApprovalDoc;
import org.uniworks.groupware.domain.approval.ApprovalItem;
import org.uniworks.groupware.domain.approval.ApprovalMaster;
import org.uniworks.groupware.domain.approval.LineApprover;

/**
 * @author Park Chungwan
 *
 */
@Service
public interface ApprovalService {
	/**
	 * 결제 카테고리 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalCategory> getApprovalCategory(Map<String, Object> map);
	/**
	 * 결재 카테고리별 결재 양식함 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalItem> getApprovalItemList(Map<String, Object> map);
	/**
	 * 결재 마스터 정보를 가져온다.
	 * 수신처 유무, 참조처 유무, 협조 결재 유무, 협조 결재 유형, 참석자 유무, 회람 유무, 전결 규정 유무, 결재차수 등의 정보를 가져옴.
	 * @param map
	 * @return
	 */
	ApprovalMaster getApprovalMasterInfo(Map<String, Object> map);	
	/**
	 * 작성 중인 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> getWritingApprovalDocList(Map<String, Object> map);
	/**
	 * 결재대기 중인 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> getWaitingApprovalDocList(Map<String, Object> map);
	/**
	 * 결재진행 중인 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */	
	List<ApprovalDoc> getProgressApprovalDocList(Map<String, Object> map);
	/**
	 * 결재 완료된 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> getCompleteApprovalDocList(Map<String, Object> map);	
	/**
	 * 수신 문서함 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> getCompleteReceiveApprovalDocList(Map<String, Object> map);
	/**
	 * 결재 반려된 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> getRejectApprovalDocList(Map<String, Object> map);
	/**
	 * 결재문서 정보를 가져온다.
	 * @param map
	 * @return
	 */
	ApprovalDoc getByApprovalDocNw110m(Map<String, Object> map);
	/**
	 * 라인 결재자 정보를 가져온다.
	 * @param map
	 * @return
	 */
	List<LineApprover> getLineApproverNw111m(Map<String, Object> map);
	/**
	 * 라인 결재자의 코멘트 정보를 가져온다.
	 * @param map
	 * @return
	 */
	List<LineApprover> getDocApproverCommentsList(Map<String, Object> map);	
	/**
	 * 등록된 결재문건 삭제
	 * 라인결재정보(NW111M),협조결재정보(NW112M),수신처(NW113M),참조처(NW114M),첨부파일(NW115M),참석자(NW116M) 및 결재정보(NW110M) 삭제
	 * @param map
	 */
	void removeApprovalDocument(Map<String, Object> map);
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
	public void addApprovalDocument(String lang, ApprovalDoc apprDoc, List<LineApprover> list, List<Nw112m> nw112m, 
			List<Nw113m> nw113m, List<Nw114m> nw114m, List<Nw115m> nw115m, List<Nw116m> nw116m);	
	
	/**
	 * 결재 문서 수정 시 사용. 저장만, 저장후닫기, 승인요청에서 호출.
	 * @param lang - 언어 
	 * @param apprDoc - 결재문서 정보
	 * @param list - 라인결재자 정보 
	 * @param nw112m - 협조결재자 정보
	 * @param nw113m - 수신처 정보
	 * @param nw114m - 참조처 정보
	 * @param nw115m - 첨부파일 정보
	 * @param nw116m - 참석자 정보
	 */
	public void updateApprovalDocument(String lang, ApprovalDoc apprDoc, List<LineApprover> list, List<Nw112m> nw112m, 
			List<Nw113m> nw113m, List<Nw114m> nw114m, List<Nw115m> nw115m, List<Nw116m> nw116m);
	
	/**
	 * 결재문서 정보(NW110M)를 등록한다.
	 * @param apprDoc
	 */
	public void addApprovalMasterDocument(ApprovalDoc apprDoc);
	/**
	 * 결재문서 정보(NW100M)를 수정한다.
	 * @param apprDoc
	 */
	public void updateApprovalMasterDocument(ApprovalDoc apprDoc);	
	/**
	 * 라인 결재자 정보 등록.
	 * @param lineList
	 */
	public void addLineApprover(List<LineApprover> list);
	/**
	 * 라인 결재자 정보를 업데이트
	 * @param list
	 */
	public void updateLineApprover(ApprovalDoc apprDoc, List<LineApprover> list);
	/**
	 * 협조 결재자 정보 등록
	 * @param cprtList
	 */
	public void addCprtApprover(List<Nw112m> cprtList);
	/**
	 * 협조 결재자 정보 수정(Nw112m)
	 * @param cprtList
	 */
	public void updateCprtApprover(ApprovalDoc doc, List<Nw112m> cprtList);
	/**
	 * 수신처 정보 등록 
	 * @param rcptList
	 */
	public void addRcptDestination(List<Nw113m> rcptList);
	/**
	 * 수신처 정보 수정(Nw113m)
	 * @param rcptList
	 */
	public void updateRcptDestination(ApprovalDoc doc, List<Nw113m> rcptList);
	/**
	 * 참조처 정보 등록
	 * @param rfncList
	 */
	public void addRfncDestination(List<Nw114m> rfncList);
	/**
	 * 참조처 정보 수정
	 * @param rfncList
	 */
	public void updateRfncDestination(ApprovalDoc doc, List<Nw114m> rfncList);
	/**
	 * 첨부파일 등록
	 * @param nw115m
	 */
	public void addAttachFile(List<Nw115m> attchFileList);
	/**
	 * 첨부파일 수정
	 * @param attchFileList
	 */
	public void updateAttachFile(ApprovalDoc doc, List<Nw115m> attchFileList);
	/**
	 * 참석자 정보 등록
	 * @param attendList
	 */
	public void addAttendant(List<Nw116m> attendList);
	/**
	 * 참석자 정보 수정
	 * @param attendList
	 */
	public void updateAttendant(ApprovalDoc doc, List<Nw116m> attendList);
	/**
	 * 결재문서의 라인결재 승인/반려 시의 문서 결재상태 및 라인결재 정보를 변경한다.
	 * @param coId - 회사코드
	 * @param cntnId - Content ID
	 * @param dcmtRgsrNo - 문서등록번호
	 * @param lang - 사용언어
	 * @param userId - 로그인한 사용자의 사번(세션정보에서 가져와야 함)
	 * @param steadApprIndc - 대결유무 
	 * @param apprStus 결재자의 결재승인 정보 (5:반려, 7:승인) 
	 * @param comment 결재 코멘트
	 * @param map
	 */
	public void changeLineApproverStatus(String coId, String cntnId, String dcmtRgsrNo, String lang, String userId, String steadApprIndc, String apprStus, String comment);
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
	public void changeCprtnApproverStatus(String coId, String cntnId, String dcmtRgsrNo, String lang, UserSession userSession, ApprovalDoc apprDoc, String steadApprIndc, String apprStus, String comment);
	
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
	public boolean checkApprovalDocModifyAuthrity(String coId, String cntnId, String dcmtRgsrNo, UserSession userSession);
	/**
	 * 결재문서에 대한 문서번호를 생성해서 가져온다.
	 * @param coId
	 * @param cntnId
	 * @param year
	 * @return
	 */
	public String getApprovalDocumentNo(String coId, String cntnId, String deptCode, String year, int len);
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
	public boolean checkCprtnApprovalPersonInfo(String coId, String cntnId, String dcmtRgsrNo, UserSession userSession);
}
