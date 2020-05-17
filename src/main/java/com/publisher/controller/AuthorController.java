package com.publisher.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publisher.domain.Author;
import com.publisher.serviceImpl.AuthorServiceImpl;

@RestController
@RequestMapping("api/author")
public class AuthorController {

	@Autowired
	private AuthorServiceImpl authoreService;

	@PostMapping()
	public Author createMessge(@RequestBody Author author) {
		Author createdAuthor = authoreService.createAuthor(author);
		return createdAuthor;
	}

	@GetMapping()
	public List<Author> getAuthors() {
		return authoreService.getAuthors();
	}

	@PutMapping("/{id}")
	public Author updateAuthor(@RequestBody Author author, @PathVariable UUID id) {
		return authoreService.updateAuthor(author, id);
	}

	@DeleteMapping("/{id}")
	public void deleteAuthor(@PathVariable UUID id) {
		authoreService.deleteAuthor(id);
	}

	@PutMapping("/{id}/subscriber/{email}")
	public String addSubscriber(@PathVariable UUID id, @PathVariable String email) {
		authoreService.addSubscriber(id, email);
		return "subscribed successfully!";
	}
	
}
