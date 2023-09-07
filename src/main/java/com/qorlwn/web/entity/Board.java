package com.qorlwn.web.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "board")
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	@Id
	 private String id;
	 private String title;
	private String content;
	 private LocalDateTime date;
	 private String name;
	 private String img;
	 /*{
		  "_id": {
		    "$oid": "64f6b6bb874910553a75f88f"
		  },
		  "title": "그림",
		  "content": "그림",
		  "date": {
		    "$date": "2023-09-05T05:03:55.009Z"
		  },
		  "name": "홍길동"
		}*/
}
