package com.ecommerce.org.testpagination;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.org.dto.Products;

@RestController
public class PageController {

	@Autowired
	private Testpagination studentService;

    public PageController(Testpagination studentService) {
        this.studentService = studentService;
    }

 @GetMapping("/students/classroom/{id}")
    public ResponseEntity<Page<Products>> getAllStudentsBasedOnClassroom( Pageable pageable,@PathVariable int id)
        throws URISyntaxException {           
        Page<Products> page = studentService.findByCategoryId(id, pageable);
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/students/classroom");
        return new ResponseEntity<Page<Products>>(page, HttpStatus.OK);
    }
}
