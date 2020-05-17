package com.publisher.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.publisher.domain.Publisher;

public interface PublishService {
	public Publisher createPublish(Publisher publisher);

	public List<Publisher> getPublisher();

	public void deletePublisher(UUID id);

	public Publisher updatePublisher(Publisher publisher, UUID id);
}
