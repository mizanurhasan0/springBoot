package com.example.image.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.image.model.ImageModel;
import com.example.image.model.repository.ImageRepository;

import net.coobird.thumbnailator.Thumbnails;
import org.imgscalr.Scalr; 

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

	@Autowired
	ImageRepository imageRepository;

	@PostMapping("/upload")
	public ImageModel uploadImage(@RequestParam("myFile") MultipartFile file) throws IOException {
		//System.out.println(createThumbnail(file).toByteArray());
		long size=file.getSize();
		System.out.println(size);
		
		long size2=createThumbnail(file,200).size();
		System.out.println(size2);
		
		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(), createThumbnail(file,200).toByteArray());
		
		//ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
		final ImageModel savedImage = imageRepository.save(img);
		System.out.println("Image Saved");
		return savedImage;
	}

	private ByteArrayOutputStream createThumbnail(MultipartFile orginalFile,int width) throws IOException {
		ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
		BufferedImage thumbImg = null;
		BufferedImage img = ImageIO.read(orginalFile.getInputStream());
		thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
		ImageIO.write(thumbImg, orginalFile.getContentType().split("/")[1], thumbOutput);
		return thumbOutput;
	}

	@GetMapping("/all")
	public List<ImageModel> viewImages() throws IOException {
		// ImageModel img = new ImageModel(file.getOriginalFilename(),
		// file.getContentType(), file.getBytes());

		return imageRepository.findAll();
		// System.out.println("Image Saved");
		// return savedImage;
	}

	@GetMapping(path = "/all2")
	public ResponseEntity<Page<ImageModel>> getAllImages(Pageable pageable) throws URISyntaxException {
		Page<ImageModel> page = imageRepository.findAll(pageable);
		return new ResponseEntity<Page<ImageModel>>(page, HttpStatus.OK);
	}

	@Autowired
	ServletContext context;

	@GetMapping(path = "/getImages")
	@CrossOrigin
	public ResponseEntity<List<String>> getImages() {
		List<String> images = new ArrayList<String>();
		String filePath = context.getRealPath("/images");
		File fileFolder = new File(filePath);

		if (fileFolder != null) {
			for (final File file : fileFolder.listFiles()) {
				if (!file.isDirectory()) {
					String encodeBase64 = null;
					try {
						String extension = FilenameUtils.getExtension(file.getName());
						FileInputStream fileInputStream = new FileInputStream(file);
						byte[] bytes = new byte[(int) file.length()];
						fileInputStream.read(bytes);
						encodeBase64 = Base64.getEncoder().encodeToString(bytes);
						images.add("data:image/" + extension + ";base64," + encodeBase64);
						fileInputStream.close();
					} catch (Exception e) {
						System.out.println("Error: " + e);
					}
				}
			}
		}
		return new ResponseEntity<List<String>>(images, HttpStatus.OK);
	}

	@GetMapping(path = "/getImages2")
	@CrossOrigin
	public ResponseEntity<List<String>> getImages2() {
		List<String> images = new ArrayList<String>();
		String filePath = context.getRealPath("/images");
		File fileFolder = new File(filePath);

		for (final File file : fileFolder.listFiles()) {
			if (!file.isDirectory()) {
				String encodeBase64 = null;
				try {
					String extension = FilenameUtils.getExtension(file.getName());
					FileInputStream fileInputStream = new FileInputStream(file);
					byte[] bytes = new byte[(int) file.length()];
					fileInputStream.read(bytes);
					encodeBase64 = Base64.getEncoder().encodeToString(bytes);
					images.add("data:image/" + extension + ";base64," + encodeBase64);
					fileInputStream.close();
				} catch (Exception e) {
					System.out.println("Error: " + e);
				}
			}
		}

		return new ResponseEntity<List<String>>(images, HttpStatus.OK);
	}

}
