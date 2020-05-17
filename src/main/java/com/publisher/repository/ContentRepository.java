package com.publisher.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.publisher.domain.Publisher;

@Repository
public interface PublisherRepository extends CassandraRepository<Publisher, UUID>{

}
