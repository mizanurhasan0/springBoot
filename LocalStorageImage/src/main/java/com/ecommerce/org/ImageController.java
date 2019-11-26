package com.ecommerce.org;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

	@Autowired
	private ServletContext context;

	@GetMapping(value = "/getImages2")
	@ResponseBody
	public ResponseEntity<String> getImages2() throws IOException {

		//List<byte[]> images = new ArrayList<byte[]>();

		String filePath = context.getRealPath("/images");
		System.out.println("filePath: " + filePath);

		File fileFolder = new File(filePath);
		System.out.println("File Folder: " + fileFolder);

		byte[] fileContent = FileUtils.readFileToByteArray(new File(fileFolder+"/a.jpg"));
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		
//		if (fileFolder != null) {
//			for (final File file : fileFolder.listFiles()) {
//				if (!file.isDirectory()) {
//
//					byte[] encodeBased = null;
//					try {
//						
//						String extension = FilenameUtils.getExtension(file.getName());
//						System.out.println("Extension: " + extension);
//
//						FileInputStream fileInputStram = new FileInputStream(file);
//						System.out.println("fileInputStram: " + fileInputStram);
//
//						byte[] bytes = new byte[(int) file.length()];
//						System.out.println("bytes: " + bytes);
//
//						fileInputStram.read();
//						encodeBased = Base64.getEncoder().encode(bytes);
//						images.add(encodeBased);
//						fileInputStram.close();
//
//					} catch (Exception e) {
//						// TODO: handle exception
//						System.out.println("Exception: " + e);
//					}
//				}
//			}
//		}
		return new ResponseEntity<String>(encodedString, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getImages")
	public ResponseEntity<List<String>> getImages() {

		List<String> images = new ArrayList<String>();

		String filePath = context.getRealPath("/images");
		System.out.println("filePath: " + filePath);

		File fileFolder = new File(filePath);
		System.out.println("File Folder: " + fileFolder);

		if (fileFolder != null) {
			for (final File file : fileFolder.listFiles()) {
				if (!file.isDirectory()) {

					String encodeBased = null;
					try {
						
						String extension = FilenameUtils.getExtension(file.getName());
						System.out.println("Extension: " + extension);

						FileInputStream fileInputStram = new FileInputStream(file);
						System.out.println("fileInputStram: " + fileInputStram);

						byte[] bytes = new byte[(int) file.length()];
						System.out.println("bytes: " + bytes);

						fileInputStram.read();
						encodeBased = Base64.getEncoder().encodeToString(bytes);
						images.add("data:image/" + extension + ";base64," + encodeBased);
						fileInputStram.close();

					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception: " + e);
					}
				}
			}
		}
		return new ResponseEntity<List<String>>(images, HttpStatus.OK);
	}



	@GetMapping(value = "/images1")
	@ResponseBody
	public List<byte[]> getImages1() throws IOException {

		List<byte[]> images = new ArrayList<byte[]>();

		String filePath = context.getRealPath("/images");
		System.out.println("filePath: " + filePath);

		File fileFolder = new File(filePath);

		if (fileFolder != null) {
			for (final File file : fileFolder.listFiles()) {
				if (!file.isDirectory()) {
					// String encodeBased=null;
					try {
						String extension = FilenameUtils.getExtension(file.getName());
						System.out.println("Extension: " + extension);

						FileInputStream fileInputStram = new FileInputStream(file);
						System.out.println("fileInputStram: " + fileInputStram);

						byte[] bytes = new byte[(int) file.length()];
						System.out.println("bytes: " + bytes);

						fileInputStram.read();
						// encodeBased=Base64.getEncoder().encodeToString(bytes);
						images.add(bytes);
						fileInputStram.close();

					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception: " + e);
					}
				}
			}
		}
//		 InputStream in = servletContext.getResourceAsStream("/WEB-INF/images/image-example.jpg");
//		    return IOUtils.toByteArray(in);
//		    
		return images;
		// return new ResponseEntity<List<String>>(images,HttpStatus.OK);
	}
	
    @GetMapping(value = "/api/image/logo")
    public ResponseEntity<InputStreamResource> getImage() throws IOException {
 
        ClassPathResource imgFile = new ClassPathResource("/src/main/webapp/images/sajal.jpeg");
        //InputStream in = context.getResourceAsStream("/images/a.jpg");
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
    
	
//	@CrossOrigin
//	@RequestMapping(value = "/image-byte-array", method = RequestMethod.GET)
//	public @ResponseBody byte[] getImageAsByteArray() throws IOException {
//	    InputStream in = context.getResourceAsStream("/images/a.jpg");
//	    return IOUtils.toByteArray(in);
//	}
//	



	@RequestMapping(value = "/image2", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImageAsResponseEntity() throws IOException {
		
		
	    HttpHeaders headers = new HttpHeaders();
	    InputStream in = context.getResourceAsStream("/images/a.jpg");
	    byte[] media = IOUtils.toByteArray(in);
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	     
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}
	
	@RequestMapping(value = "/image3", method = RequestMethod.GET)
	public ResponseEntity<List<byte[]>> getImageAsResponseEntity3() throws IOException {
		List<byte[]> images=new ArrayList<byte[]>();
		
	    HttpHeaders headers = new HttpHeaders();
	    InputStream in = context.getResourceAsStream("/images/a.jpg");
	    byte[] media = IOUtils.toByteArray(in);
	    
	    images.add(media);
	    //HttpHeaders headers2 = new HttpHeaders();
	    InputStream in2 = context.getResourceAsStream("/images/a.jpg");
	    byte[] media2 = IOUtils.toByteArray(in2);
	    images.add(media2);
	    
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	     
	    ResponseEntity<List<byte[]>> responseEntity = new ResponseEntity<>(images, headers, HttpStatus.OK);
	    return responseEntity;
	}
	
	@RequestMapping(value = "/image-byte-array", method = RequestMethod.GET)
	public @ResponseBody void getImageAsByteArray2(HttpServletResponse response) throws IOException {
		
	    InputStream in = context.getResourceAsStream("/images/a.jpg");
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    //IOUtils.copy(in, response.getOutputStream());
	    IOUtils.copy(in,response.getOutputStream());
	}
	
	List<String> files = new ArrayList<String>();
	
//	@GetMapping("/getallfiles")
//	public ResponseEntity<List<String>> getListFiles(Model model) {
//	    List<String> fileNames = files
//	            .stream().map(fileName -> MvcUriComponentsBuilder
//	                    .fromMethodName(UploadController.class, "getFile", fileName).build().toString())
//	            .collect(Collectors.toList());
//
//	    return ResponseEntity.ok().body(fileNames);
//	}
	
//	
//	@RequestMapping(value = "/image-resource", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity<Resource> getImageAsResource() {
//	    HttpHeaders headers = new HttpHeaders();
//	    Resource resource = 
//	      new ServletContextResource(context, "/images/a.jpg");
//	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//	}
//	
}
