package com.qorlwn.web.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qorlwn.web.entity.Board;

public interface BoardRepository extends MongoRepository<Board, Long> {

	Optional<Board> findById(String id);// id가 어떤 타입이 올지 몰라서 써준다.

	void deleteById(String id);
	
}
