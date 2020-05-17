package com.publisher.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.publisher.domain.Author;

@Service
public interface AuthoreService {

	public Author createAuthor(Author author);

	public List<Author> getAuthors();

	public void deleteAuthor(UUID id);

	public Author updateAuthor(Author publisher, UUID id);

	public String addSubscriber(UUID id, String email);
}
