package com.gpn.books.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpn.books.service.BooksService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gpn.books.model.Book;
@RestController
@RequestMapping("api/v1/books")
public class BooksController {
	
	@Autowired 
	BooksService booksService;
	
	@GetMapping
	public List<Book> getAllBooks() throws JsonParseException, JsonMappingException, IOException{
		return booksService.getBooks();
	}

}
