package com.ecommerce.org;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadImageController {
	public static String userDirect=System.getProperty("user.dir");
	
//    @RequestMapping(value = "/sid", method = RequestMethod.GET,
//            produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<InputStreamResource> getImage() throws IOException {
//
//    	ClassPathResource imgFile = new ClassPathResource(userDirect+"/src/main/webapp/images/sajal.jpeg");
//
//    	return ResponseEntity
//    	        .ok()
//    	        .contentType(MediaType.IMAGE_JPEG)
//    	        .body(new InputStreamResource(imgFile.getInputStream()));
//    }
	
	@GetMapping(value = "/i")
	public @ResponseBody byte[] getImage() throws IOException {
	    InputStream in = getClass()
	      .getResourceAsStream(userDirect+"/src/main/webapp/images/sajal.jpeg");
	    return IOUtils.toByteArray(in);
	}
}