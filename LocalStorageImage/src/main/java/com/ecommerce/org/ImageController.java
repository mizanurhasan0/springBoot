package com.ecommerce.org;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

	@Autowired
	private ServletContext context;
	
	@GetMapping(value = "/getImages")
	public ResponseEntity<List<String>> getImages(){
		List<String> images=new ArrayList<String>();
		
		String filePath=context.getRealPath("/images");
		System.out.println("filePath: "+filePath);
		
		File fileFolder=new File(filePath);
		
		if(fileFolder!=null) {
			for(final File file:fileFolder.listFiles()) {
				if(!file.isDirectory()) {
					String encodeBased=null;
					try {
						String extension=FilenameUtils.getExtension(file.getName());
						System.out.println("Extension: "+extension);
						
						FileInputStream fileInputStram =new FileInputStream(file);
						System.out.println("fileInputStram: "+fileInputStram);
						
						byte[] bytes=new byte[(int)file.length()];
						System.out.println("bytes: "+bytes);
						
						fileInputStram.read();
						encodeBased=Base64.getEncoder().encodeToString(bytes);
						images.add("data:image/"+extension+"base64,"+encodeBased);
						fileInputStram.close();
						
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception: "+e);
					}
				}
			}
		}
		return new ResponseEntity<List<String>>(images,HttpStatus.OK);
	}
}
