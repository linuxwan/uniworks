/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
import org.uniworks.groupware.controller.mvc.ApprovalCaseAController;
import org.uniworks.groupware.domain.Nw115m;

/**
 * @author Park Chungwan
 *
 */
public class FileDownloadView extends AbstractView {
	private static final Logger logger = LoggerFactory.getLogger(FileDownloadView.class);
	
	public FileDownloadView() {
		// 객체가 생성될 때 Content Type을 다음과 같이 변경 
        setContentType("application/octet-stream; charset=utf-8");        
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		Map<String, Object> fileInfo = (Map<String, Object>) model.get("downloadFile");	//넘겨 받은 모델(파일 정보)
		Nw115m nw115m = (Nw115m) fileInfo.get("fileInfo");
		
		File file = new File(nw115m.getFilePath(), nw115m.getAttchFileSysName());
		
		response.setContentType(getContentType());
		logger.debug("getContentType:" + getContentType());
		response.setCharacterEncoding("UTF-8");
		response.setContentLength((int)file.length());
		String userAgent = request.getHeader("User-Agent");
        String fileName = null;        
        
        if(userAgent.indexOf("Edge") > -1 || userAgent.indexOf("Trident") > -1) {	//IE 11 or Edge 일 경우
            fileName = URLEncoder.encode(nw115m.getAttchFileName(), "UTF-8");
        } else if (userAgent.indexOf("Chrome") > -1) {
        	StringBuffer sb = new StringBuffer();
        	for(int i=0; i<file.getName().length(); i++) {
        		char c = file.getName().charAt(i);
        		if(c > '~') {
        			sb.append(URLEncoder.encode(""+c, "UTF-8"));
        		}else {
        			sb.append(c);
        		}
        	}
        	fileName = sb.toString();
        } else {	//그 외 (FireFox 등)
            fileName = new String(nw115m.getAttchFileName().getBytes("UTF-8"), "iso-8859-1");        	
        }
        
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        
        OutputStream out = response.getOutputStream();
        
        FileInputStream fis = null;
        
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            if (fis != null) {
            	try {
                    fis.close();
                } catch(IOException ex) {
                	ex.printStackTrace();
                }
            }
        }
        out.flush();
        out.close();
	}

}
