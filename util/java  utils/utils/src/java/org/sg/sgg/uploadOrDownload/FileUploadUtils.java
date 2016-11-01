package org.sg.sgg.uploadOrDownload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sg.sgg.struts2.Struts2Utils;

public class FileUploadUtils {
	private static final Logger LOGGER = Logger.getLogger(FileUploadUtils.class);
	
	
	/**
	 * JAVA  普通的文件下载
	 * @param filePath - 文件路径
	 */
	public static void downloadFile(String filePath) throws Exception{
		HttpServletResponse response = Struts2Utils.getResponse();
		String webRootPath = "";
		
		try {
			webRootPath = ServletActionContext.getServletContext().getRealPath(File.separator+"updale");
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file = new File(webRootPath+File.separator+filePath);
		// 设置response的编码方式
		response.setContentType("application/x-msdownload");
		// 写明要下载的文件的大小
		response.setContentLength((int) file.length());
		
		// 解决中文乱码
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filePath.getBytes("GBK"), "iso-8859-1"));
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream buf = new BufferedInputStream(fis);
		byte[] b = new byte[1024];// 相当于我们的缓存
		long k = 0;// 该值用于计算当前实际下载了多少字节
		// 从response对象中得到输出流,准备下载
		OutputStream myout = response.getOutputStream();
		try {
			// 开始循环下载
			while (k < file.length()) {
				int j = buf.read(b, 0, 1024);
				k += j;
				//将b中的数据写到客户端的内存
				myout.write(b, 0, j);
				//将写入到客户端的内存的数据,刷新到磁盘
				myout.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
