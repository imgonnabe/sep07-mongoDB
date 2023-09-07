package com.qorlwn.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qorlwn.web.entity.Board;
import com.qorlwn.web.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
	public String board(Model model) {
		int count = boardService.count();
		model.addAttribute("count", count);
		List<Board> list = boardService.list();
		model.addAttribute("list", list);
		return "board";
	}
	
	@GetMapping("/write")
	public String write() {
		return "write";
	}
	
   @PostMapping("/write")
   public String write(@RequestParam("img") MultipartFile img, HttpServletRequest request) throws IOException {
      // System.out.println(board);
      //System.out.println(img.getSize());
      //System.out.println(img.getOriginalFilename());
      //System.out.println(Arrays.toString(img2Byte));
      Board board = new Board();
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setName("홍길동");
      board.setDate(LocalDateTime.now());
      if(img.getSize() > 0) {
         String fileName = img.getOriginalFilename();
         //String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
         //System.out.println("확장자" + extension);
         //String regExp = "/([^\\s]+(?=\\.(jpg|gif|png))\\.\\2)/";
         //boolean b = Pattern.matches("([^\\s]+(\\.(?i)(jpg|gif|png))?)", fileName);
         //System.out.println(b);
         
         if(Pattern.matches("([^\\s]+(\\.(?i)(jpg|gif|png))?)", fileName)) {            
            byte[] img2Byte = Base64.encodeBase64(img.getBytes());
            board.setImg("data:image/png;base64," + new String(img2Byte));         
         }
      }
      boardService.save(board);
      return "redirect:/board";
   }
   
   @GetMapping("/detail")
   public String board(@RequestParam("no") String id, Model model) {
      Board detail = boardService.findById(id).get();
      // System.out.println(detail);
      model.addAttribute("detail", detail);
      return "detail";
   }
   
   @GetMapping("/sns")
   public String sns(Model model) {
      List<Sort.Order> order = new ArrayList<Sort.Order>();
      order.add(Sort.Order.desc("date"));// 날짜순으로 정렬
      
      Page<Board> list = boardService.findAll(PageRequest.of(0, 10, Sort.by(order)));// 최신글 10개
      model.addAttribute("list", list);
      return "sns";
   }
   
   @GetMapping("/delete")
   public String delete(@RequestParam("no") String id) {
	   boardService.deleteById(id);
      return "redirect:/board";
   }
   
   @GetMapping("/update")
   public String update(@RequestParam("no") String id, Model model) {
      Board detail = boardService.findById(id).get();
      model.addAttribute("detail", detail);
      return "update";
   }
   
   @PostMapping("/update")
   public String update(Board board) {
      System.out.println(board.getTitle());
      System.out.println(board.getContent());
      board.setName("수정함.");
      board.setDate(LocalDateTime.now());
      boardService.save(board);
      return "redirect:/board";
   }
}
