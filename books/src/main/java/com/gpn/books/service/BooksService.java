package com.gpn.books.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpn.books.model.Book;

@Service
public class BooksService {

	
	
	

	public List<Book> getBooks() throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Book>> typeReference = new TypeReference<List<Book>>() {};
		// read JSON file and map/convert to java POJO
		InputStream inputStream = TypeReference.class.getResourceAsStream("/books/books.json");
		List<Book> books = (List<Book>) mapper.readValue(inputStream, typeReference);
		
		return books;
			
	}
}
