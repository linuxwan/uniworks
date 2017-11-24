/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.controller.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.domain.Nw115m;
import org.uniworks.groupware.service.Nw115mService;

/**
 * @author Park Chungwan
 *
 */
@Controller
public class CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	@Autowired private Nw115mService nw115mService;
	
	@RequestMapping("/download/cntnId/{cntnId}/dcmtRgsrNo/{dcmtRgsrNo}/fileId/{fileId}")
	public ModelAndView fileDownload(HttpServletRequest request, @PathVariable("cntnId") String cntnId, 
			@PathVariable("dcmtRgsrNo") String dcmtRgsrNo, @PathVariable("fileId") String fileId, ModelMap model) throws Exception {
		/** 첨부파일 상세정보 가져오기 **/
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("cntnId", cntnId);
		map.put("dcmtRgsrNo", dcmtRgsrNo);
		map.put("fileId", fileId);
		
		Nw115m fileInfo = nw115mService.getNw115m(map);
		Map<String, Object> file = new HashMap<String, Object>();
		file.put("fileInfo", fileInfo);
		return new ModelAndView("fileDownloadView", "downloadFile", file);
	}
}
