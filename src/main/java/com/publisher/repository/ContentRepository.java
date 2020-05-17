package com.publisher.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.publisher.domain.Content;

@Repository
public interface ContentRepository extends CassandraRepository<Content, UUID> {

	@Query("SELECT * FROM content WHERE id=?0 and author_id =?1 ALLOW FILTERING")
	public Content findByAuthor_id(UUID id, UUID author_id);

}
