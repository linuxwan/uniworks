/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.controller.mvc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.uniworks.groupware.common.util.ApplicationConfigReader;
import org.uniworks.groupware.common.util.CommUtil;
import org.uniworks.groupware.common.util.StringUtil;
import org.uniworks.groupware.domain.Nw118m;
import org.uniworks.groupware.service.Nw118mService;

/**
 * @author Park Chungwan
 *
 */
@Controller
@RequestMapping("upload/upload_form")
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	@Inject private FileSystemResource fsResource;
	@Autowired private Nw118mService nw118mService;
	
	/**
	 * file upload form을 호출
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getUploadForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("common/popup/file_upload_form_01");
		
		mav.addObject("nw118m", new Nw118m());
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView getUpload(HttpServletRequest request, HttpServletResponse response, Nw118m nw118m, BindingResult result) throws SizeLimitExceededException {
		ModelAndView mav = new ModelAndView("common/popup/file_upload_form_01");
		String mode = StringUtil.null2void(request.getParameter("mode"));
		String attachList = StringUtil.null2void(request.getParameter("attachList"));
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				logger.debug("Error: " + error.getCode() +  " - " + error.getDefaultMessage());
			}
			return mav;
		}
		
		//logger.debug("chungwan : " + mode);
		//logger.debug("chungwan : " + fsResource.getPath());
		//logger.debug("chungwan : " + nw118m.getFileData());
		
		if (mode.equals("add") && !nw118m.getFileData().isEmpty()) {
			Nw118m tmpFile = new Nw118m();
			String fileId = CommUtil.createSequenceNo();
			String attchFileSysName = CommUtil.createSequenceNo("F");
			String filename = nw118m.getFileData().getOriginalFilename();
		    String imgExt = filename.substring(filename.lastIndexOf(".")+1, filename.length());
		    int fileSize = Integer.parseUnsignedInt(String.valueOf(nw118m.getFileData().getSize()));
		    
		    tmpFile.setAttchFileName(filename);
		    tmpFile.setAttchFileSysName(attchFileSysName);
		    tmpFile.setFileExt(imgExt);
		    tmpFile.setFileId(fileId);
		    tmpFile.setFileSize(fileSize);
		    tmpFile.setTempIndc("Y");
		    
		    //logger.debug(tmpFile.getAttchFileName() + ":" + tmpFile.getAttchFileSysName() + ":" + tmpFile.getFileExt());
		    //logger.debug(tmpFile.getFileId() + ":" + tmpFile.getFileSize() + ":" + tmpFile.getTempIndc());
		    
		    byte[] bytes = nw118m.getFileData().getBytes();
            try{
            	File lOutFile = new File(fsResource.getPath() + File.separator + attchFileSysName);
                FileOutputStream lFileOutputStream = new FileOutputStream(lOutFile);
                lFileOutputStream.write(bytes);
                lFileOutputStream.close();
                
                nw118mService.addNw118m(tmpFile);
                if (attachList.equals("")) {
                	attachList = tmpFile.getFileId() + "^" + tmpFile.getAttchFileName() + "^" + tmpFile.getFileSize() + "^" + tmpFile.getTempIndc();
                } else {
                	attachList += "|" + tmpFile.getFileId() + "^" + tmpFile.getAttchFileName() + "^" + tmpFile.getFileSize() + "^" + tmpFile.getTempIndc();
                }
                
                mav.addObject("attachList", attachList);            
            }catch(IOException ie){
                //Exception 처리
            	logger.debug("File writing error! ");            	
            	return null;
            }
            logger.debug("File upload success! ");
		} else if (mode.equals("del")) {
			String fileId = StringUtil.null2void(request.getParameter("fileId")); 
			
			//실제 물리적인 임시 첨부 파일 삭제
			String tempUploadPath = ApplicationConfigReader.get("file.tepmUpload");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fileId", fileId);
			Nw118m tempFileInfo = nw118mService.getNw118m(map);			
			logger.debug("chungwan : " + tempUploadPath + File.separator + tempFileInfo.getAttchFileSysName());
			File tempUploadFile = new File(tempUploadPath + File.separator + tempFileInfo.getAttchFileSysName());
			logger.debug("chungwan file exists: " + tempUploadFile.exists());
			tempUploadFile.delete();
			logger.debug("chungwan file exists: " + tempUploadFile.exists());
			
			//fileId에 해당하는 임시 첨부 파일을 삭제.
			nw118mService.deleteNw118m(map);
			
			mav.addObject("attachList", attachList);
		}
		return mav;
	}		
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView multipartExceptionHandler(MaxUploadSizeExceededException ex) {
		return new ModelAndView("error/file_maxsize").addObject("errorMessage", ex.getMessage());
	}
}
