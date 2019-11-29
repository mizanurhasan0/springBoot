package com.blog.jwt.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blog.jwt.model.Blog;
import com.blog.jwt.repositories.BlogRepository;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {

	final private BlogRepository blogRepository;

	public BlogController(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

//    @GetMapping("/blog")
//    public List<Blog> index(){
//        return blogRepository.findAll();
//    }
//	@GetMapping("/blog")
//	public List<Blog> index() {
//		return blogRepository.findByActive();
//	}
	
	@GetMapping(path = "/blog")
	public ResponseEntity<Page<Blog>> getAllBlogByActivation(Pageable pageable)
			throws URISyntaxException {
		Page<Blog> page = blogRepository.findByActive(pageable);
		return new ResponseEntity<Page<Blog>>(page, HttpStatus.OK);
	}

	@GetMapping(path = "/blog/profile/{userid}")
	public ResponseEntity<Page<Blog>> getAllBlogSelfByActivation(@PathVariable int userid,Pageable pageable)
			throws URISyntaxException {
		Page<Blog> page = blogRepository.singleUserPosts(userid,pageable);
		return new ResponseEntity<Page<Blog>>(page, HttpStatus.OK);
	}
	
	@GetMapping("/blog/{id}")
	public Blog show(@PathVariable String id) {
		int blogId = Integer.parseInt(id);
		return blogRepository.findById(blogId).orElse(new Blog());
	}

	@PostMapping("/blog/search")
	public List<Blog> search(@RequestBody Map<String, String> body) {
		String searchTerm = body.get("text");
		return blogRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
	}

	@PostMapping("/addblog")
	public Blog create(@RequestBody Map<String, String> body) {
		String title = body.get("title");
		String content = body.get("content");
		String userId = body.get("userid");
		return blogRepository.save(new Blog(title, content, userId));
	}

	@PutMapping("/blog/{id}")
	public Blog update(@PathVariable String id, @RequestBody Map<String, String> body) {
		int blogId = Integer.parseInt(id);
		// getting blog
		Blog blog = blogRepository.findById(blogId).orElse(new Blog());
		blog.setTitle(body.get("title"));
		blog.setContent(body.get("content"));
		return blogRepository.save(blog);
	}

	@PutMapping("/blog/delete/{id}")
	public void activeOrInActive(@PathVariable String id, @RequestBody Map<String, Boolean> body) {
		int blogId = Integer.parseInt(id);
		boolean active = body.get("active");
		blogRepository.updateStatus(active, blogId);

	}

	@DeleteMapping("blog/{id}")
	public boolean delete(@PathVariable String id) {
		int blogId = Integer.parseInt(id);
		blogRepository.deleteById(blogId);
		return true;
	}

}
