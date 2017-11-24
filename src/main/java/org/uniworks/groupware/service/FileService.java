/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service;

import java.util.List;
import java.util.Map;

import org.uniworks.groupware.domain.Nw115m;
import org.uniworks.groupware.domain.Nw118m;

/**
 * @author Park Chungwan
 *
 */
public interface FileService {
	/**
	 * 임시 첨부파일 정보를 가져온다.
	 * @param fileId
	 * @return
	 */
	public Nw118m getTempAttchFileInfo(String fileId);
	
	/**
	 * 임시 첨부파일 정보를 추가한다.
	 * @param tempFile
	 */
	public void addTempAttchFile(Nw118m tempFile);
	
	/**
	 * 임시 첨부파일 정보를 삭제한다.
	 * @param fileId
	 */
	public void removeTempAttchFile(String fileId);
	
	/**
	 * 첨부파일 목록을 가져온다.
	 * @param map
	 * @return
	 */
	public List<Nw115m> getAttachFileList(Map<String, Object> map);
	
	/**
	 * 첨부파일 정보를 가져온다.
	 * @param map
	 * @return
	 */
	public Nw115m getAttachFile(Map<String, Object> map);
	
	/**
	 * 첨부파일 정보를 삭제한다.
	 * @param map
	 */
	public void removeAttachFile(Map<String, Object> map);
}
