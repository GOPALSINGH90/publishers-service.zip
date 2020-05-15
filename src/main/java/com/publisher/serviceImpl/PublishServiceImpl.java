package com.publisher.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publisher.domain.Publisher;
import com.publisher.repository.PublisherRepository;
import com.publisher.service.PublishService;

@Service
public class PublishServiceImpl implements PublishService {

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public Publisher createPublish(Publisher publisher) {
		Publisher createdPublisher = publisherRepository.save(publisher);
		ObjectMapper objectMapper = new ObjectMapper();
		String publishAsString = "";
		try {
			objectMapper.writeValue(new File("target/order.json"), createdPublisher);
			publishAsString = objectMapper.writeValueAsString(createdPublisher);
		} catch (Exception exception) {
		}

		kafkaTemplate.send("nineleaps", publishAsString);
		return createdPublisher;

	}

	@Override
	public List<Publisher> getPublisher() {
		return publisherRepository.findAll();
	}

	@Override
	public void deletePublisher(UUID id) {

	}

	@Override
	public Publisher updatePublisher(Publisher publisher, UUID id) {
		Publisher updatedPublisher; 
		Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
		if (!optionalPublisher.isEmpty()) {
			Publisher dbPublisher = optionalPublisher.get();
			dbPublisher.setAuthor(publisher.getAuthor());
			dbPublisher.setMessage(publisher.getMessage());
			dbPublisher.setTitle(publisher.getTitle());
			updatedPublisher = publisherRepository.save(dbPublisher);
		}else {
			updatedPublisher = publisherRepository.save(publisher);
		}
		
		return updatedPublisher;
	}

}
