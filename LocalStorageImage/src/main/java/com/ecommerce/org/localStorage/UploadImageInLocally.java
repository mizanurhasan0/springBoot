package com.ecommerce.org.localStorage;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.org.model.ImageModel;
import com.ecommerce.org.model.ImageModel2;
import com.ecommerce.org.repository.ImageRepository;
import com.ecommerce.org.repository.ImageRepository2;

@RestController
public class UploadImageInLocally {

	@Autowired
	ServletContext context;

	public static String userDirect = System.getProperty("user.dir");

	@GetMapping("/")
	public String rootDirectory() {
		// String path=context.getContextPath()+"/images";
		return userDirect;
	}

	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		String name = file.getName();
		String nameOrginal = file.getOriginalFilename();
		double size = file.getSize();
		String type = file.getContentType().substring(6);
		System.out.println("name:" + name + " - size:" + size + " -type:" + type + " - orginal Name:" + nameOrginal);
		String newName = nameOrginal.replaceAll(nameOrginal, nameOrginal + "sajal" + "." + type);
		// /home/world/eclipse-workspace/Project/springBoot/LocalStorageImage/images
		File convertFile = new
		// File("/home/world/eclipse-workspace/Project/springBoot/LocalStorageImage/src/main/webapp/images/"+newName);
		File(userDirect + "/src/main/webapp/images/" + newName);

		System.out.println(newName);
		convertFile.createNewFile();

		try (FileOutputStream fout = new FileOutputStream(convertFile)) {
			fout.write(file.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "File has uploaded Successfully";
	}

	@Autowired
	ImageRepository2 imageRepository2;

	@PostMapping("/upload2")
	public ImageModel2 uploadImage(@RequestParam("myFile") MultipartFile file) throws IOException {
		// System.out.println(createThumbnail(file).toByteArray());
		long size = file.getSize();
		 System.out.println(size);

		// long size2 = createThumbnail(file, 200).size();
		// System.out.println(size2);

		try {
			// String name = file.getName();

			String nameOrginal = file.getOriginalFilename();
//			double size = file.getSize();
			String type = file.getContentType().substring(6);
			String type2 = file.getContentType();
			 System.out.println(" - size:" + size + " -type:" + type2 );
			// - orginal Name:" + nameOrginal);
			// String newName = nameOrginal.replaceAll(nameOrginal, nameOrginal + "sajal" +
			// "." + type);

			String newName = nameOrginal.replaceAll(nameOrginal, generateRandomDouble() + "." + type);
			// /home/world/eclipse-workspace/Project/springBoot/LocalStorageImage/images
			File convertFile = new
			// File("/home/world/eclipse-workspace/Project/springBoot/LocalStorageImage/src/main/webapp/images/"+newName);
			File(userDirect + "/src/main/webapp/images/" + newName);

			System.out.println(newName);
			convertFile.createNewFile();
			// ------Testing------------------------
//			try {
//				BufferedImage image = ImageIO.read(file.getInputStream());
//
//				BufferedImage resized = resize2(image, 500, 500);
//
//				File output = new File(userDirect + "/src/main/webapp/images/" + newName);
//				//ImageIO.write(resized, "jpg", output);
//
//				ImageModel2 img2 = new ImageModel2(file.getOriginalFilename(), file.getContentType(),
//						"/images/" + newName);
//
//				final ImageModel2 savedImage = imageRepository2.save(img2);
//				System.out.println("Image Saved");
//				return savedImage;
//
//				// return new ImageModel2("1","2","3");
//			} catch (Exception e) {
//				// TODO: handle exception
//				return null;
//			}
////			File input = new File("/tmp/duke.png");

			/// --------End Test------------------------------
			try (FileOutputStream fout = new FileOutputStream(convertFile)) {
				fout.write(file.getBytes());
				
				ImageModel2 img2 = new ImageModel2(file.getOriginalFilename(), file.getContentType(),
						"/images/"+ newName);
				
				final ImageModel2 savedImage = imageRepository2.save(img2);
				System.out.println("Image Saved");
				return savedImage;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		// ImageModel img = new ImageModel(file.getOriginalFilename(),
		// file.getContentType(), file.getBytes());

	}

//	private static BufferedImage resize2(BufferedImage img, int height, int width) {
//		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2d = resized.createGraphics();
//		g2d.drawImage(tmp, 0, 0, null);
//		g2d.dispose();
//		return resized;
//	}

//
	private ByteArrayOutputStream createThumbnail(MultipartFile orginalFile, int width) throws IOException {
		ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
		BufferedImage thumbImg = null;
		BufferedImage img = ImageIO.read(orginalFile.getInputStream());
		thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
		ImageIO.write(thumbImg, orginalFile.getContentType().split("/")[1], thumbOutput);
		return thumbOutput;
	}

	public static int generateRandomDouble() {
		// int x =(int) (Math.random()+1) ;
		// UUID idOne = UUID.randomUUID();
		int rand_int1 = Math.abs(ThreadLocalRandom.current().nextInt());
		return rand_int1;
	}

}
