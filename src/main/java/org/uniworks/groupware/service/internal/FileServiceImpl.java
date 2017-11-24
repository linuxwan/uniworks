/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service.internal;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.common.util.AttachFileUtil;
import org.uniworks.groupware.domain.Nw115m;
import org.uniworks.groupware.domain.Nw118m;
import org.uniworks.groupware.mapper.Nw115mMapper;
import org.uniworks.groupware.mapper.Nw118mMapper;
import org.uniworks.groupware.service.FileService;

/**
 * @author Park Chungwan
 *
 */
@Service
@Transactional(readOnly = true) 
public class FileServiceImpl implements FileService {
	@Autowired Nw115mMapper nw115mMapper;
	@Autowired Nw118mMapper nw118mMapper;
	
	/* (non-Javadoc)
	 * @see org.uniworks.groupware.service.FileService#getTempAttchFileInfo(java.lang.String)
	 * 임시 점부파일 정보를 가져온다.
	 * @param fileId
	 * @return Nw118m
	 */
	@Override
	public Nw118m getTempAttchFileInfo(String fileId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", fileId);
		Nw118m nw118m = nw118mMapper.selectByPrimaryKey(map);
		return nw118m;
	}

	/* (non-Javadoc)
	 * @see org.uniworks.groupware.service.FileService#addTempAttchFile(org.uniworks.groupware.domain.Nw118m)
	 * 임시 첨부파일 정보를 추가한다.
	 * @param nw118m
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addTempAttchFile(Nw118m nw118m) {
		// TODO Auto-generated method stub
		nw118mMapper.insert(nw118m);
	}

	/* (non-Javadoc)
	 * @see org.uniworks.groupware.service.FileService#removeTempAttchFile(java.lang.String)
	 * 임시 첨부파일 정보를 삭제한다.
	 * @param fileId
	 */
	@Override
	public void removeTempAttchFile(String fileId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", fileId);
		nw118mMapper.deleteByPrimaryKey(map);
	}

	/* (non-Javadoc)
	 * @see org.uniworks.groupware.service.FileService#getAttachFileList(java.util.Map)
	 * 첨부파일 목록을 가져온다.
	 * @param map
	 * @return List<Nw115m>
	 */
	@Override
	public List<Nw115m> getAttachFileList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Nw115m> list = nw115mMapper.select(map);
		return list;
	}

	/* (non-Javadoc)
	 * @see org.uniworks.groupware.service.FileService#getAttachFile(java.util.Map)
	 * 점부파일 정보를 가져온다.
	 * @param map
	 * @return nw115m
	 */
	@Override
	public Nw115m getAttachFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Nw115m nw115m = nw115mMapper.selectByPrimaryKey(map);
		return nw115m;
	}

	/* (non-Javadoc)
	 * @see org.uniworks.groupware.service.FileService#removeAttachFile(java.util.Map)
	 * 첨부파일 정보를 삭제한다.
	 * @param map
	 */
	@Override
	public void removeAttachFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Nw115m nw115m = getAttachFile(map);
		boolean fileDelete = false;
		boolean fileExists = AttachFileUtil.checkFileExists(nw115m);
		
		//물리적인 파일도 삭제.
		if (fileExists) {
			File existsFile = new File(nw115m.getFilePath() + File.separator + nw115m.getAttchFileSysName());
			fileDelete = existsFile.delete();
		}
		
		//물리적인 파일이 삭제되어야만 DB정보도 삭제한다.
		if (fileDelete) nw115mMapper.deleteByPrimaryKey(map);
	}

}
