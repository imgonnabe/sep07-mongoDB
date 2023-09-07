package com.qorlwn.web.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qorlwn.web.entity.Test;

public interface TestRepository extends MongoRepository<Test, Long> {

}
