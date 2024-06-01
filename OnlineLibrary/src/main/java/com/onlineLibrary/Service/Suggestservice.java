package com.onlineLibrary.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineLibrary.Entity.Suggestbook;
import com.onlineLibrary.repository.Suggestrepo;



@Service
public class Suggestservice {
    
	@Autowired
	private Suggestrepo repo;
	
	public void addSuggestbook(Suggestbook book) {
		
		repo.save(book);
	}
}

