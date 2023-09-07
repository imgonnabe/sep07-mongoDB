package com.qorlwn.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qorlwn.web.entity.Test;
import com.qorlwn.web.repository.TestRepository;

@Service
public class TestService {
	@Autowired
	private TestRepository testRepository;
	
	public List<Test> list() {
		return testRepository.findAll();
	}

}
