package com.publisher.domain;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("publisher")
public class Publisher {
	@PrimaryKey
	private UUID id = UUID.randomUUID();

	@Column("author")
	private String author;

	@Column("title")
	private String title;

	@Column("message")
	private String message;

	public UUID getId() {
		return id;
	}

	

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", author=" + author + ", title=" + title + ", message=" + message + "]";
	}

}
