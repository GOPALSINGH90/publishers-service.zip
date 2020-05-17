package com.publisher.domain;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("author")
public class Author {
	@PrimaryKey
	private UUID id = UUID.randomUUID();

	@Column("author_name")
	private String name;

	@Column("author_subscirbers")
	private List<String> subscriber;

	@Column("author_content")
	private List<UUID> content;

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(List<String> subscriber) {
		this.subscriber = subscriber;
	}

	public List<UUID> getContent() {
		return content;
	}

	public void setContent(List<UUID> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", subscriber=" + subscriber + ", content=" + content + "]";
	}

}
