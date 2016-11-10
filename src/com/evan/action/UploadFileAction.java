package com.evan.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadFileAction {
 
//	/**
//	 * 1、最原始的输入输出流复制文件
//	 */
//	@RequestMapping("/uploadFile1")
//	@ResponseBody
//	public String parserUploadFile1(MultipartFile file) throws IOException {
//		String realPath = "E:/";
//		InputStream is = file.getInputStream();
//		String fileName = file.getOriginalFilename();
//		FileOutputStream os = new FileOutputStream(realPath
//				+ new Date().getTime() + file.getOriginalFilename());
//		int i = 0;
//		while ((i = is.read()) != -1) {
//			os.write(i);
//		}
//		os.flush();
//		os.close();
//		is.close();
//		return "success";
//	}

	/**
	 * 2、通过apache自带的FileUtils工具类进行复制
	 */
	@RequestMapping("/uploadFile2")
	@ResponseBody
	public Map<String,String> parserUploadFile2(MultipartFile file,HttpServletRequest request) throws IOException {
		Map<String,String> map = new HashMap<String, String>();
		String uploadPath =  request.getSession().getServletContext().getRealPath("/upload");
		File files = new File(uploadPath);
		if (!files.exists()) {
			files.mkdirs();
			System.out.println(uploadPath);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					uploadPath, file.getOriginalFilename()));
			System.out.println("文件上传的地址"+uploadPath);
			map.put("status", "success");
			return map;
		}else {
			System.out.println(uploadPath);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					uploadPath, file.getOriginalFilename()));
			System.out.println("文件上传的地址"+uploadPath);
			map.put("status", "success");
			return map;
		}
	}
//	/**
//	 * 2、通过apache自带的FileUtils工具类进行复制
//	 */
//	@RequestMapping("/uploadFile3")
//	@ResponseBody
//	public Map<String,String> parserUploadFile3(MultipartFile file) throws IOException {
//		String uploadPath =  "E:/media/";
//			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
//					uploadPath, file.getOriginalFilename()));
//			System.out.println("文件上传成功.....");
//			Map<String,String> map = new HashMap<String, String>();
//			map.put("status", "success");
//			return map;
//	}


}
