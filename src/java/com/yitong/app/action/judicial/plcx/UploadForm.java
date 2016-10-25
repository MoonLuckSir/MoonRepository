package com.yitong.app.action.judicial.plcx;

import java.io.File;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UploadForm  extends ActionForm{
	//Struts1提供了一个FormFile类来专门处理文件上传操作，
	//这里定义的的name,file名字必须与jsp页面一致，否则会报空指针异常
	
	private FormFile file;
	private FormFile extraFile;
	private String TASK_NAME;
	
	
	public FormFile getExtraFile() {
		return extraFile;
	}
	public void setExtraFile(FormFile extraFile) {
		this.extraFile = extraFile;
	}
	public String getTASK_NAME() {
		return TASK_NAME;
	}
	public void setTASK_NAME(String tASKNAME) {
		TASK_NAME = tASKNAME;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	
	
	
}
