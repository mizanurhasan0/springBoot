package com.ecommerce.org.localStorage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadImageInLocally {

	@Autowired
	ServletContext context;
	
	public static String userDirect=System.getProperty("user.dir");
	
	@GetMapping("/")
	public String rootDirectory() {
		//String path=context.getContextPath()+"/images";
		return userDirect;
	}
	
	
	@PostMapping(path = "/upload",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		String name=file.getName();
		String nameOrginal=file.getOriginalFilename();
		double size=file.getSize();
		String type=file.getContentType().substring(6);
//		System.out.println("name:"+name+" - size:"+size+" -type:"+type+" - orginal Name:"+nameOrginal);
		String newName=nameOrginal.replaceAll(nameOrginal, "sajal"+"."+type);
	//	/home/world/eclipse-workspace/Project/springBoot/LocalStorageImage/images
		File convertFile=new 
				//File("/home/world/eclipse-workspace/Project/springBoot/LocalStorageImage/src/main/webapp/images/"+newName);
				File(userDirect+"/src/main/webapp/images/"+newName);
		 
		System.out.println(newName);
	convertFile.createNewFile();
	
	try(FileOutputStream fout=new FileOutputStream(convertFile)) {
		fout.write(file.getBytes());
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return "File has uploaded Successfully";
	}
}
