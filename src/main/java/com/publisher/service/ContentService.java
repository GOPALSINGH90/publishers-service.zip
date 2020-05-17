package com.publisher.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.publisher.domain.Content;

@Service
public interface ContentService {
	public Content createContent(Content conent);

	public List<Content> getContents();

	public void deleteContent(UUID id);

	public Content updateContent(Content conent, UUID id);
	
	public Content publishContent(UUID author_id, UUID content_id); 
}
