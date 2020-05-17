package com.publisher.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publisher.domain.Author;
import com.publisher.repository.AuthorRepository;
import com.publisher.service.AuthoreService;

@Service
public class AuthorServiceImpl implements AuthoreService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Author createAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public List<Author> getAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public void deleteAuthor(UUID id) {
		authorRepository.deleteById(id);
	}

	@Override
	public Author updateAuthor(Author author, UUID id) {
		Optional<Author> optionalAuthor = authorRepository.findById(id);
		Author dbAuthor;
		if (!optionalAuthor.isEmpty()) {
			dbAuthor = optionalAuthor.get();
			dbAuthor.setName(dbAuthor.getName());
			dbAuthor = authorRepository.save(dbAuthor);

		} else {
			dbAuthor = authorRepository.save(author);
		}
		return dbAuthor;
	}

	@Override
	public String addSubscriber(UUID id, String email) {
		Optional<Author> authorOpt = authorRepository.findById(id);
		if (!authorOpt.isEmpty()) {
			Author author = authorOpt.get();
			List<String> subscriber = author.getSubscriber();
			subscriber.add(email);
			author.setSubscriber(subscriber);
			authorRepository.save(author);
		}
		return "added";
	}

	public void addContentsInAuthor(UUID content_id, UUID author_id) {
		Optional<Author> authorOpt = authorRepository.findById(author_id);
		if (!authorOpt.isEmpty()) {
			Author author = authorOpt.get();
			List<UUID> contents = author.getContent();
			if (contents != null) {
				contents.add(content_id);
				author.setContent(contents);
			} else {
				List<UUID> content_ids = new ArrayList<UUID>();
				content_ids.add(content_id);
				author.setContent(content_ids);
			}
			authorRepository.save(author);
		}
	}
}
