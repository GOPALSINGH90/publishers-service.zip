package com.publisher.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publisher.domain.Author;
import com.publisher.repository.AuthoreRepository;
import com.publisher.service.AuthoreService;

@Service
public class AuthoreServiceImpl implements AuthoreService {

	@Autowired
	private AuthoreRepository authoreRepository;

	@Override
	public Author createAuthor(Author author) {
		return authoreRepository.save(author);
	}

	@Override
	public List<Author> getAuthors() {
		return authoreRepository.findAll();
	}

	@Override
	public void deleteAuthor(UUID id) {
		authoreRepository.deleteById(id);
	}

	@Override
	public Author updateAuthor(Author author, UUID id) {
		Optional<Author> optionalAuthor = authoreRepository.findById(id);
		Author dbAuthor;
		if (!optionalAuthor.isEmpty()) {
			dbAuthor = optionalAuthor.get();
			dbAuthor.setName(dbAuthor.getName());
			dbAuthor = authoreRepository.save(dbAuthor);

		} else {
			dbAuthor = authoreRepository.save(author);
		}
		return dbAuthor;
	}

	@Override
	public String addSubscriber(UUID id, String email) {
		Optional<Author> authorOpt = authoreRepository.findById(id);
		if (!authorOpt.isEmpty()) {
			Author author = authorOpt.get();
			List<String> subscriber = author.getSubscriber();
			subscriber.add(email);
			author.setSubscriber(subscriber);
			authoreRepository.save(author);
		}
		return "added";
	}

	public void addContentsInAuthor(UUID content_id, UUID author_id) {
		Optional<Author> authorOpt = authoreRepository.findById(author_id);
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
			authoreRepository.save(author);
		}
	}
}
