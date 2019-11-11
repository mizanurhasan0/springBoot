package com.tutorial.mysql.todos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoHardCodedService {

	@Autowired
	private TodoResourse todoService;
	
	@Autowired
	private TodoRepository todoRepository;


	@GetMapping(path = "/todos")
	public List<Todos> allTodos() {
		//return todoService.allTodos();
		return todoRepository.findAll();
	}

	@GetMapping(path = "/todos/{id}")
	public Todos todo(@PathVariable Long id) {
		//return todoService.findById(id);
		return todoRepository.findById(id).get();
	}

//	@DeleteMapping(path = "/todos/{id}")
//	public List<Todos> deleteTodoById(@PathVariable Long id) {
//		return todoService.deleteById(id);
//		
//	}

	@DeleteMapping(path = "/todos/{id}")
	public List<Todos> deleteTodoById(@PathVariable Long id) {
		todoRepository.deleteById(id);
		return todoRepository.findAll();
		
	}
	
	@PutMapping(path = "/todos/{id}")
	public ResponseEntity<Todos> Update(@PathVariable long id, @RequestBody Todos todo) {
		Todos todoUpdate = todoService.saveAndUpdate(todo);
		todoRepository.deleteById(id);
		todoRepository.save(todo);
		
		//todoRepository.(todo);
		return new ResponseEntity<Todos>(todo, HttpStatus.OK);
	}

	@PostMapping(path = "/todos/add")
	public ResponseEntity<Void> Update(@RequestBody Todos todo) {
		//Todos createdTodo = todoService.saveAndUpdate(todo);
		Todos createdTodo =todoRepository.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
