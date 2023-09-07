package com.qorlwn.web.entity;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "test")
@AllArgsConstructor
@NoArgsConstructor
public class Test {
	@Id
	private String id;
	private int age;
	private String name;
	private Map<String, String> hobby;
}
