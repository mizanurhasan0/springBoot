package com.ecommerce.org;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MultiFileUploadController {

	private static String uploadDirectory=
			System.getProperty("user.dir")+"/upload/";
	
	@RequestMapping("/upload1")
	public String upload(@RequestParam("file") MultipartFile[] files) {
		StringBuilder fileName=new StringBuilder();
		
		for(MultipartFile file:files) {
			Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename());
			fileName.append(file.getOriginalFilename());
			
			try {
				Files.write(fileNameAndPath, file.getBytes());
				//return "Successfully uploaded";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				//return "error uploaded"+e;
			}
			
		}
		return "Successfully uploaded";
		
	}
}
