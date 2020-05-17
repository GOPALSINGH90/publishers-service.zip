package com.publisher.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.publisher.domain.Author;

@Repository
public interface AuthoreRepository  extends CassandraRepository<Author, UUID>{

}
