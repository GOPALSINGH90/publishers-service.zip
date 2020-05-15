package com.publisher.controller;

import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publisher.domain.Publisher;
import com.publisher.serviceImpl.PublishServiceImpl;

@RestController
@RequestMapping("publisher")
public class PublisherController {

	@Autowired
	private PublishServiceImpl publishService;

	@PostMapping("/")
	public Publisher createMessge(@RequestBody Publisher publisher) {
		Publisher createdPublisher = publishService.createPublish(publisher);
		return createdPublisher;
	}

	@GetMapping("/")
	public List<Publisher> getPublisher() {
		return publishService.getPublisher();
	}

	@PutMapping("/publish/{id}")
	public Publisher updatePublished(@RequestBody Publisher publisher, @PathParam(value = "id") UUID id) {
		return publishService.updatePublisher(publisher, id);
	}

	@DeleteMapping("/publish/{id}")
	public void delete(@PathParam(value = "id") UUID id) {
		publishService.deletePublisher(id);
	}
}
