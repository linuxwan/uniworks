/**
 * 박충완이 작성한 소스입니다.
 * 이 소스는 모든 개발자들이 자유롭게 수정/배포할 수 있습니다.
 * 단, 이 소스를 근간으로 애플리케이션을 개발하실 때에는 꼭 출처를 명시해 주세요.
 */
package org.uniworks.groupware.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.domain.Nw115m;
import org.uniworks.groupware.domain.Nw118m;
import org.uniworks.groupware.service.FileService;

/**
 * @author Chungwan Park
 * AttachFileUtil.java 2014. 10. 31.
 */
public class AttachFileUtil {
	protected final static Log logger = LogFactory.getLog(AttachFileUtil.class);
	//2014103114147621422566^jqGrid.pptx^53391^Y
	
	public static List<Nw115m> setAttachFileList(HttpServletRequest request, String dcmtRgsrNo, FileService fileService) {
		List<Nw115m> attach = new ArrayList<Nw115m>();
		
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String coId = userSession.getCoId();
		String cntnId = request.getParameter("cntnId");
		String attachList = request.getParameter("attachList");
		String filePathType = request.getParameter("filePathType");	//결재 첨부파일 경로를 가져온다.
		String strAttachFilePath = getAttachFilePath(filePathType, cntnId);
		
		if (attachList != null && attachList.length() > 0) {
			String[] attachFileList = StringUtil.split2Array(attachList, "|");
			
			for (int i = 0; i < attachFileList.length; i++) {
				String[] attachFileInfo = StringUtil.split2Array(attachFileList[i], "^");
				String fileId = attachFileInfo[0];
				Nw115m file = new Nw115m();
				//Temp File 유무를 체크해서 임시첨부파일 테이블(NW118M)과 첨부파일 테이블(NW115M)에서 해당 파일 정보를 가져온다.
				if (attachFileInfo[3].equalsIgnoreCase("Y")) {
					Nw118m tempFile = fileService.getTempAttchFileInfo(fileId);
					
					if (tempFile == null) {	//temp file Table(NW118M)에서 값을 가져오지 못했을 경우
						
					} else {	//temp file Table(NW118M)에서 값을 가져왔을 경우.
						file.setCoId(coId);
						file.setCntnId(cntnId);
						file.setDcmtRgsrNo(dcmtRgsrNo);
						file.setFileId(tempFile.getFileId());
						file.setSeqNo(i+1);
						file.setFilePath(strAttachFilePath);
						file.setAttchFileName(tempFile.getAttchFileName());
						file.setAttchFileSysName(tempFile.getAttchFileSysName());
						file.setFileExt(tempFile.getFileExt());
						file.setFileSize(tempFile.getFileSize());
						
						attach.add(file);
					}
				} else {	//첨부파일 테이블(NW115M)에 파일 정보가 있을 경우.
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("coId", coId);
					map.put("cntnId", cntnId);
					map.put("dcmtRgsrNo", dcmtRgsrNo);
					map.put("fileId", attachFileInfo[0]);
					file = fileService.getAttachFile(map);
					
					attach.add(file);
				}
			}
		} else {
			attach = null;
		}
		
		return attach;
	}
	
	/**
	 * 실제 첨부파일이 저장되어 있는 물리적인 Path를 반환.
	 * 첨부파일 유형이 무엇인가에 따라 폴더 위치가 달라짐.
	 * @param filePathType : A-Approval(결재), B-Board(게시판)
	 * @return
	 */
	public static String getAttachFilePath(String filePathType, String cntnId) {
		String filePath = "";
		
		if (filePathType.equalsIgnoreCase("A")) {
			filePath = ApplicationConfigReader.get("approval.attachFile.path");
		} else if (filePathType.equalsIgnoreCase("B")) {
			filePath = ApplicationConfigReader.get("board.attachFile.path");
		}
		
		//컨텐츠 ID로 폴더 구분.
		filePath += File.separator + cntnId;
		
		//디렉토리 생성 
		File desti1 = new File(filePath);
		if (!desti1.exists()) desti1.mkdirs();
		
		DateUtil dt = new DateUtil();
		//추가로 년월 정보의 폴더를 추가
		filePath += File.separator + dt.getStringYYYYMM();
		File desti2 = new File(filePath);
		if (!desti2.exists()) desti2.mkdirs(); 
		
		return filePath;
	}
	
	/**
	 * 실제 파일이 존재하는지 확인. 파일 존재할 경우 true 그렇지 않으면 false를 리턴.
	 * @param attachFile
	 * @return
	 */
	public static boolean checkFileExists(Nw115m attachFile) {
		String filePath = attachFile.getFilePath();
		String fileSysName = attachFile.getAttchFileSysName();
		
		File file = new File(filePath + File.separator + fileSysName);
		
		return file.exists();
	}
}
