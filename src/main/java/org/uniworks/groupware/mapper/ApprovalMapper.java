/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.mapper;

import java.util.List;
import java.util.Map;

import org.uniworks.groupware.domain.Nw112m;
import org.uniworks.groupware.domain.approval.ApprovalCategory;
import org.uniworks.groupware.domain.approval.ApprovalDoc;
import org.uniworks.groupware.domain.approval.ApprovalItem;
import org.uniworks.groupware.domain.approval.ApprovalLevel;
import org.uniworks.groupware.domain.approval.ApprovalMaster;
import org.uniworks.groupware.domain.approval.LineApprover;

/**
 * @author Park Chungwan
 *
 */
public interface ApprovalMapper {
	/**
	 * 결재 카테고리 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalCategory> selectByApprvalCategory(Map<String, Object> map);
	/**
	 * 결재 카테고리 내의 아이템 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalItem> selectByApprovalItemList(Map<String, Object> map);
	/**
	 * 결재 마스터 정보를 가져온다.
	 * @param map
	 * @return
	 */
	ApprovalMaster selectByApprovalMasterInfo(Map<String, Object> map);
	/**
	 * 결재 차수 정보를 가져온다.
	 * @param map
	 * @return
	 */
	ApprovalLevel selectByApprovalLevelNw012m(Map<String, Object> map);
	/**
	 * 작성 중인 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> selectWritingApprovalDocList(Map<String, Object> map);
	/**
	 * 결재 대기 중인 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> selectWaitingApprovalDocList(Map<String, Object> map);
	/**
	 * 결재 진행 중인 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> selectProgressApprovalDocList(Map<String, Object> map);
	/**
	 * 결재 완료된 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> selectCompleteApprovalDocList(Map<String, Object> map);
	/**
	 * 수신 문서함 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> selectCompleteReceiveApprovalDocList(Map<String, Object> map);
	/**
	 * 결재 반려된 결재문서 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<ApprovalDoc> selectRejectApprovalDocList(Map<String, Object> map);
	/**
	 * 결재문서 정보를 가져온다.
	 * @param map
	 * @return
	 */
	ApprovalDoc selectByApprovalDocNw110m(Map<String, Object> map);
	/**
	 * 라인 결재자 정보를 가져온다.
	 * @param map
	 * @return
	 */
	List<LineApprover> selectLineApproverNw111m(Map<String, Object> map);
	/**
	 * 협조 결재자 정보를 가져온다.
	 * @param map
	 * @return
	 */
	List<Nw112m> selectCprtnApproverNw112m(Map<String, Object> map);
	/**
	 * 라인 결재자 정보를 등록한다.(Nw111m)
	 * @param lineApprover
	 * @return
	 */
	int insertLineApproverNw111m(LineApprover lineApprover);
	/**
	 * 결재문서 정보를 등록한다.(Nw110m)
	 * @param apprDoc
	 * @return
	 */
	int insertApprovalDocNw110m(ApprovalDoc apprDoc);
	/**
	 * 결재문서 정보를 수정한다.(Nw110m)
	 * @param apprDoc
	 * @return
	 */
	int updateApprovalDocNw110m(ApprovalDoc apprDoc);
	/**
	 * 차기 라인결재자의 결재차수를 가져온다.
	 * @param map
	 * @return
	 */
	int selectNextApproverDgrNw111m(Map<String, Object> map);
	/**
	 * 현재 협조결재자의 결재대기 차수를 가져온다.
	 * @param map
	 * @return
	 */
	int selectCrntApproverDgrNw112m(Map<String, Object> map);
	/**
	 * 차기 협조결재자의 결재차수를 가져온다.
	 * @param map
	 * @return
	 */
	int selectNextApproverDgrNw112m(Map<String, Object> map);
	/**
	 * 결재문서 정보를 삭제한다.(Nw111m)
	 * @param apprDoc
	 * @return
	 */
	int deleteLineApproverNw111m(Map<String, Object> map);
	/**
	 * 결재문서의 협족결재자 정보를 모두 삭제
	 * @param map
	 * @return
	 */
	int deleteCprtApproverNw112m(Map<String, Object> map);
	/**
	 * 결재문서의 수신처 정보를 모두 삭제
	 * @param map
	 * @return
	 */
	int deleteRcptDestinationNw113m(Map<String, Object> map);
	/**
	 * 결재문서의 참조처 정보를 모두 삭제
	 * @param map
	 * @return
	 */
	int deleteRfncDestinationNw114m(Map<String, Object> map);
	/**
	 * 결재문서의 첨부파일을 모두 삭제
	 * @param map
	 * @return
	 */
	int deleteAttachFileListNw115m(Map<String, Object> map);
	/**
	 * 결재문서의 참석자 정보를 모두 삭제
	 * @param map
	 * @return
	 */
	int deleteAttendantNw116m(Map<String, Object> map);
	/**
	 * 결재문서의 차수에 해당하는 협조결재자 정보를 가져온다. 
	 * @param map
	 * @return
	 */
	List<Nw112m> selectCprtnApprovalPersonInfo(Map<String, Object> map);
}
