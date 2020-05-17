package com.publisher.domain;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("content")
public class Content {
	@PrimaryKey
	@Column("content_id")
	private UUID id = UUID.randomUUID();

	@Column("content_title")
	private String title;

	@Column("content_message")
	private String message;

	@Column("author_id")
	private UUID author_id;

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UUID getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(UUID author_id) {
		this.author_id = author_id;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", title=" + title + ", message=" + message + ", author_id=" + author_id + "]";
	}

}
