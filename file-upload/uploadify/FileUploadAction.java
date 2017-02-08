package com.htjs.action.fileHandle;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.htjs.base.BaseAction;

public class FileUploadAction extends BaseAction{
	/* 
     * 成员变量的名称不能随意更改,  
     * private File file;           变量的名称必须和jsp中上传文件标签中的name属性的值一致. 
     * private String fileFileName;     变量的名称必须为"上传文件的名称+FileName". 
     * private String fileContentType;  变量的名称必须为"上传文件的名称+ContentType",  
     */  
	
	private File file; //和在JS中指定的fileObjName的值相同
	private String fileFileName; //[fileName]FileName    获得上传文件的名称
	private String fileContentType; //[fileName]ContentType  获得上传文件的类型
	
	
    @SuppressWarnings("deprecation")  
    public String fileUpload() throws Exception {  
        String path = getSavePath()+"\\"; //存储地址为D盘根目录
        FileOutputStream fos = new FileOutputStream(path+fileFileName); //输出流
        FileInputStream fis = new FileInputStream(file);    //输出流
 
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len=fis.read(buffer))!=-1){
            fos.write(buffer);;
        }
        fos.close();
        fis.close();
        return null;    //这里不需要返回任何东西
    } 
    
    public String toFilePage(){
    	return "success";
    }

    
    
    
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
    
    
    
}
