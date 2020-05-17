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

import com.publisher.domain.Content;
import com.publisher.serviceImpl.ContentServiceImpl;

@RestController
@RequestMapping("api/content")
public class ContentController {

	@Autowired
	private ContentServiceImpl contentService;

	@PostMapping()
	public Content createMessge(@RequestBody Content content) {
		Content createdContent = contentService.createContent(content);
		return createdContent;
	}

	@GetMapping()
	public List<Content> getPublisher() {
		return contentService.getContents();
	}

	@PutMapping("/{id}")
	public Content updatePublished(@RequestBody Content content, @PathVariable UUID id) {
		return contentService.updateContent(content, id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable UUID id) {
		contentService.deleteContent(id);
	}

	@GetMapping("/{author_id}/content/{content_id}")
	public Content publishContent(@PathVariable UUID author_id, @PathVariable UUID content_id) {
		Content contens = contentService.publishContent(author_id, content_id);
		return contens;
	}
}
