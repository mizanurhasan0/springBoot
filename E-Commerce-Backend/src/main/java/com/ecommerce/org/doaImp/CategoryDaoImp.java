package com.ecommerce.org.doaImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.org.dao.CategoryDao;
import com.ecommerce.org.dto.Category;

@Service
public class CategoryDaoImp implements CategoryDao {

private static List<Category> lists=new ArrayList<Category>();
	
	static {
		Category cat=new Category();
		cat.setId(1);
		cat.setName("Mobile");
		cat.setDescription("this is description");
		cat.setImageUrl("a.jpg");
		lists.add(cat);
		
		 cat=new Category();
		cat.setId(2);
		cat.setName("Electronics");
		cat.setDescription("this is description");
		cat.setImageUrl("b.jpg");
		lists.add(cat);
		
		 cat=new Category();
			cat.setId(3);
			cat.setName("grocary");
			cat.setDescription("this is description");
			cat.setImageUrl("c.jpg");
			lists.add(cat);
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return lists;
	}
}
