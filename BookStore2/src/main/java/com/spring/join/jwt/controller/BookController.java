package com.spring.join.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.spring.join.jwt.model.Book;
import com.spring.join.jwt.model.ResponseMessage;
import com.spring.join.jwt.repositories.BookRepository;
import com.spring.join.jwt.repositories.CategoryRepository;

import org.springframework.http.MediaType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	@Autowired
	final private BookRepository bookRepository;

	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

//	@GetMapping(path = "/books")
//	public ResponseEntity<Page<Book>> getAllBookByActivation(Pageable pageable) throws URISyntaxException {
//		Page<Book> page = bookRepository.findByActive(pageable);
//		return new ResponseEntity<Page<Book>>(page, HttpStatus.OK);
//	}

	@GetMapping("/books")
	public Page<Book> findAllBook(@RequestParam Optional<String> name, @RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size, @RequestParam Optional<String> sortBy) {
		return bookRepository.findByName(name.orElse("_"),
				PageRequest.of(page.orElse(0), size.orElse(0), Sort.Direction.DESC, sortBy.orElse("id")));
	}

	@ResponseBody
	@GetMapping("/sku")
	public boolean FindDoublicateSku(@RequestParam("sku") String sku) {
			//System.out.println(sku);
			try {
				if(bookRepository.findBySku(sku)!=null) {
					return true;
				}else {
					return false;
				}
				//System.out.println("sajal:"+);
				
			} catch (Exception e) {
				// TODO: handle exception
				//return false;
			}
			return false;
		
	
	}
	
	@GetMapping("/catagory/search/books")
	public Page<Book> findAllBookByCategory(@RequestParam Optional<Integer> category,
			@RequestParam Optional<String> name, @RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size, @RequestParam Optional<String> sortBy) {
		return bookRepository.searchByCategory(category.orElse(firstCategoryId()), name.orElse(""),
				PageRequest.of(page.orElse(0), size.orElse(5), Sort.Direction.DESC, sortBy.orElse("id")));
	}

	@Autowired
	private CategoryRepository categoryRepository;

	private int firstCategoryId() {
		int id = categoryRepository.GetFirstId();
		System.out.println(id);
		return id;
	}
//	private List<Integer> getAllCategory() {
//		List<Integer> id=categoryRepository.GetAllId();
//		System.out.println(id);
//		return id;
//	}

	@GetMapping(path = "/book/category/{id}")
	public ResponseEntity<Page<Book>> getAllBookByCategory(@PathVariable("id") int id, Pageable pageable)
			throws URISyntaxException {
		Page<Book> page = bookRepository.findByCategory(id, pageable);
		return new ResponseEntity<Page<Book>>(page, HttpStatus.OK);
	}

	@GetMapping(path = "/auth/book/false")
	public ResponseEntity<Page<Book>> getAllBookByInactive(Pageable pageable) throws URISyntaxException {
		Page<Book> page = bookRepository.findByInactive(pageable);
		return new ResponseEntity<Page<Book>>(page, HttpStatus.OK);
	}

	@GetMapping("/book/{id}")
	public Book show(@PathVariable String id) {
		long bookId = Integer.parseInt(id);
		// return bookRepository.findById(bookId).orElse(new Book());
		return bookRepository.findById(bookId).orElse(new Book());
	}

//	@PostMapping("/book/search")
//	public List<Book> search(@RequestBody Map<String, String> body) {
//		String searchTerm = body.get("text");
//		return bookRepository.findByNameOrDescription(searchTerm, searchTerm);
//	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam Map<Object, Object> body,@RequestParam("file") MultipartFile file) {
		String message = "";
		System.out.println(body);
		try {
			String sku = body.get("sku").toString();
			String name = body.get("name").toString();
			String description = body.get("description").toString();
			Double price = new Double(body.get("unitPrice").toString());
			BigDecimal unitPrice = BigDecimal.valueOf(price);

			String imageUrl = "/images/"+save(file);
			
			int unitsInStock = Integer.parseInt(body.get("unitsInStock").toString());
//			Date createdOn = new SimpleDateFormat("dd/MM/yyyy").parse(body.get("createdOn").toString());
//			Date updatedOn = new SimpleDateFormat("dd/MM/yyyy").parse(body.get("updatedOn").toString());
			Date createdOn = new Date();
			Date updatedOn = new Date();
			int category = Integer.parseInt(body.get("category").toString());
			boolean active = true;
			System.out.println("category:" + category + "unitPrice:" + unitPrice + "imageUrl:" + imageUrl + "category:"
					+ category + "createdOn:");
			Book book = new Book(sku, name, description, unitPrice, imageUrl, active, unitsInStock, createdOn, updatedOn,
					category);
		    bookRepository.save(book);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
		
	}

	public String save(MultipartFile file) {
		try {
			
			 String nameOrginal = file.getOriginalFilename();
			 String type = file.getContentType().substring(6);
			 String newName = nameOrginal.replaceAll(nameOrginal, generateRandomDouble()
			 + "." + type);
			File convertFile = new File(userDirect + "/src/main/webapp/images/" + newName);

			 System.out.println(newName);
			convertFile.createNewFile();
			try (FileOutputStream fout = new FileOutputStream(convertFile)) {
				fout.write(file.getBytes());
				return newName;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(e);
				return "default";
			}

		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
			
		}
	}

//	@PostMapping(path = "/addbook", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public Book create(@RequestParam Map<Object, Object> body, @RequestParam("image") MultipartFile file) {
////		String sku = body.get("sku").toString();
//		String sku = String.valueOf(new Date());
//		String name = body.get("name").toString();
//		String description = body.get("description").toString();
//		Double price = new Double(body.get("unitPrice").toString());
//		BigDecimal unitPrice = BigDecimal.valueOf(price);
//
//		String imageUrl = null;
//
//		try {
//			imageUrl = getImageName(file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Images Error:" + e);
//			// imageUrl="/images/default.png";
//		}
//
//		int unitsInStock = Integer.parseInt(body.get("unitsInStock").toString());
////		Date createdOn = new SimpleDateFormat("dd/MM/yyyy").parse(body.get("createdOn").toString());
////		Date updatedOn = new SimpleDateFormat("dd/MM/yyyy").parse(body.get("updatedOn").toString());
//		Date createdOn = new Date();
//		Date updatedOn = new Date();
//		int category = Integer.parseInt(body.get("category").toString());
//		boolean active = true;
//		System.out.println("category:" + category + "unitPrice:" + unitPrice + "imageUrl:" + imageUrl + "category:"
//				+ category + "createdOn:");
//		Book book = new Book(sku, name, description, unitPrice, imageUrl, active, unitsInStock, createdOn, updatedOn,
//				category);
//
//		// return bookRepository.save(book);
//		return null;
//
//	}

	// Upload images
	public static String userDirect = System.getProperty("user.dir");

	public String getImageName(MultipartFile file) throws IOException {

		String nameOrginal = file.getOriginalFilename();
		String type = file.getContentType().substring(6);
		String newName = nameOrginal.replaceAll(nameOrginal, generateRandomDouble() + "." + type);
		File convertFile = new File(userDirect + "/src/main/webapp/images/" + newName);

		System.out.println(newName);
		convertFile.createNewFile();
		try (FileOutputStream fout = new FileOutputStream(convertFile)) {
			fout.write(file.getBytes());
			return "/images/" + newName;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "/images/default.png";
		}

	}

	public static int generateRandomDouble() {
		int rand_int1 = Math.abs(ThreadLocalRandom.current().nextInt());
		return rand_int1;
	}
	// End Images

	@PutMapping("/auth/book/{id}")
	public Book update(@PathVariable String id, @RequestBody Map<String, String> body) {
		long blogId = Integer.parseInt(id);

		Book book = bookRepository.findById(blogId).orElse(new Book());
		try {

			book.setSku(body.get("sku").toString());
			book.setName(body.get("name").toString());
			book.setDescription(body.get("description").toString());
			Double price = new Double(body.get("unitPrice").toString());
			book.setUnitPrice(BigDecimal.valueOf(price));
			book.setImageUrl(body.get("imageUrl").toString());
			book.setUnitsInStock(Integer.parseInt(body.get("unitsInStock").toString()));
			book.setCreatedOn(new SimpleDateFormat("dd/MM/yyyy").parse(body.get("createdOn").toString()));
			book.setUpdatedOn(new SimpleDateFormat("dd/MM/yyyy").parse(body.get("updatedOn").toString()));
			book.setCategory(Integer.parseInt(body.get("category").toString()));
			// boolean active=true;
			book.setActive(Boolean.parseBoolean(body.get("active").toString()));
			return bookRepository.save(book);
		} catch (Exception e) {
			return null;
		}
	}

//
	@PutMapping("/auth/book/delete/{id}")
	public void activeOrInActive(@PathVariable String id, @RequestBody Map<String, Boolean> body) {
		int blogId = Integer.parseInt(id);
		boolean active = body.get("active");
		bookRepository.updateStatus(active, blogId);

	}
//
//	@DeleteMapping("blog/{id}")
//	public boolean delete(@PathVariable String id) {
//		int blogId = Integer.parseInt(id);
//		blogRepository.deleteById(blogId);
//		return true;
//	}

}
