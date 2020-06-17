/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service.internal;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.common.util.ApplicationConfigReader;
import org.uniworks.groupware.domain.Nw115m;
import org.uniworks.groupware.domain.Nw130m;
import org.uniworks.groupware.domain.board.BoardDoc;
import org.uniworks.groupware.domain.board.BoardMaster;
import org.uniworks.groupware.mapper.BoardMapper;
import org.uniworks.groupware.mapper.Nw115mMapper;
import org.uniworks.groupware.mapper.Nw130mMapper;
import org.uniworks.groupware.service.BoardService;

/**
 * @author Park Chung Wan
 *
 */
@Service
@Transactional(readOnly = true) 
public class BoardServiceImpl implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	@Autowired BoardMapper boardMapper;
	@Autowired Nw130mMapper nw130mMapper;
	@Autowired Nw115mMapper nw115mMapper;
	
	/**
	 * 게시판 마스터 정보 가져오기
	 * @param map
	 * @return
	 */
	@Override
	public BoardMaster selectBoardMasterInfo(Map<String, Object> map) {
		return boardMapper.selectBoardMasterInfo(map);	
	}
	
	/**
	 * 게시판 목록 가져오기
	 * @param map
	 * @return
	 */
	@Override	
	public List<BoardDoc> selectBoardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardList(map);
	}
	
	/**
	 * 게시판 등록
	 * @param nw130m
	 * @param attachFileList
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int addBoardDocument(Nw130m nw130m, List<Nw115m> attachFileList) {
		int result = nw130mMapper.insert(nw130m);
		
		if (attachFileList == null) return result;
		
		int attchCnt = 0;
		//첨부파일 등록
		for (Nw115m attchFile : attachFileList) {
			moveTempFileToDestinationFolder(attchFile);
			File existFile = new File(attchFile.getFilePath() + File.separator + attchFile.getAttchFileSysName());
			//파일이 정상적으로 move되었을 경우에만 DB에 파일정보 추가.
			if (existFile.exists()) {				
				nw115mMapper.insert(attchFile);
				attchCnt++;
			}
		}
		
		return attchCnt == 0 ? result : attchCnt;
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
}
