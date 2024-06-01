package com.onlineLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlineLibrary.Entity.Suggestbook;
import com.onlineLibrary.Service.Suggestservice;



@Controller
public class Suggestcontroller {

	@Autowired
	private Suggestservice serv;
	@GetMapping("/startsuggestform")
	public String start() {
		return "Suggestform";
	}
	
	@PostMapping("/savesuggest")
	public String saveSuggest(@ModelAttribute Suggestbook book) {
		serv.addSuggestbook(book);
		return "SuggestBookbyuser";
	}
}
