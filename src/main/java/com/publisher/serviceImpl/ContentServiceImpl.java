package com.publisher.serviceImpl;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publisher.domain.Content;
import com.publisher.repository.ContentRepository;
import com.publisher.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentRepository contentRepository;

	@Autowired
	private AuthorServiceImpl authorServiceImpl;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public Content createContent(Content content) {
		Content createdContent = contentRepository.save(content);
		authorServiceImpl.addContentsInAuthor(createdContent.getId(), createdContent.getAuthor_id());
		return createdContent;
	}

	@Override
	public List<Content> getContents() {
		List<Content> contents = contentRepository.findAll();
		return contents;
	}

	@Override
	public void deleteContent(UUID id) {
		Optional<Content> optionalContent = contentRepository.findById(id);
		if (!optionalContent.isEmpty()) {
			contentRepository.deleteById(id);
		}
	}

	@Override
	public Content updateContent(Content content, UUID id) {
		Optional<Content> optionalContent = contentRepository.findById(id);
		Content dbContent;
		if (!optionalContent.isEmpty()) {
			dbContent = optionalContent.get();
			dbContent.setTitle(content.getTitle());
			dbContent.setMessage(content.getMessage());
			contentRepository.save(dbContent);
		} else {
			dbContent = contentRepository.save(content);
		}
		return dbContent;
	}

	public Content publishContent(UUID author_id, UUID content_id) {
		Content contents = contentRepository.findByAuthor_id(content_id, author_id);
		ObjectMapper objectMapper = new ObjectMapper();
		String contentAsString = "";
		try {
			objectMapper.writeValue(new File("target/contents.json"), contents);
			contentAsString = objectMapper.writeValueAsString(contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendMessage(contentAsString);

		return contents;
	}

	private void sendMessage(String contentAsString) {
		kafkaTemplate.send("nineleaps", contentAsString);
	}

}