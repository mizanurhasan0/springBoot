package com.tutorial.mysql.todos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoResourse {

	static List<Todos> todoArray = new ArrayList<Todos>();
	private static int id = 0;

	static {
		todoArray.add(new Todos(++id, "first Name", "Description one", new Date(), true));
		todoArray.add(new Todos(++id, "second Name", "Description Second", new Date(), true));
		todoArray.add(new Todos(++id, "Third Name", "Description Thired", new Date(), true));
		todoArray.add(new Todos(++id, "Fourth Name", "Description Fourth", new Date(), true));
		todoArray.add(new Todos(++id, "Five Name", "Description Five", new Date(), true));

	}

	public List<Todos> allTodos() {
		return todoArray;
	}

	
	public Todos saveAndUpdate(Todos todo) {
		if (todo.getId() == -1 || todo.getId()==0) {
			todo.setId(++id);
			todoArray.add(todo);
		} else {
			deleteById(todo.getId());
			todoArray.add(todo);
		}
		return todo;
	}

	public List<Todos> deleteById(long Id) {
		Todos todo = findById(Id);

		if (todo == null)
			return null;

		if (todoArray.remove(todo)) {
			return todoArray;
		}
		return null;
	}

	public Todos findById(long id) {
		// TODO Auto-generated method stub
		for (Todos todo : todoArray) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
}
