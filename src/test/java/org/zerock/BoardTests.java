package org.zerock;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.BoardVO;
import org.zerock.persistence.BoardRepository;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardTests {

	@Setter(onMethod_=@Autowired)
	private BoardRepository boardRepository;
	
	
	//count 쿼리 생성
	@Test
	public void testFind4() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		Page<BoardVO> result = boardRepository.findByBnoGreaterThan(0L, pageable);
		
		log.info(""+result);
		log.info("TOTAL PAGES: "+ result.getTotalPages());
		log.info("PAGE: " + result.getNumber());
		log.info("NEXT: " + result.hasNext());
		log.info("PREV: " + result.hasPrevious());
		
		log.info("P NEXT: " + result.nextPageable());
		log.info("P PREV: " + result.previousPageable());
		
		result.getContent().forEach(vo -> log.info(""+vo));
		
		
	}
	
	
	@Test
	public void testFind3() {
//		Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC,"bno");
//		
//		boardRepository.findByTitleContainingAndBnoGreaterThan("7", OL, pageable)
//		.forEach(vo -> log.info(""+vo));
	}
	
	
	@Test
	public void testFind2() {
		boardRepository.findByTitleContaining("2")
		.forEach(vo -> log.info(""+vo));
	}
	
	
	@Test
	public void testFind1() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		
		boardRepository.findByBnoGreaterThan(0L, pageable).forEach(vo -> log.info(""+vo));
	}
	
	
	@Test
	public void testDelete() {
		boardRepository.deleteById(10L);
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO vo = new BoardVO();
		vo.setBno(10L);
		vo.setTitle("제목 10 수정");
		vo.setContent("내용 10 수정");
		vo.setWriter("user10");
		
		boardRepository.save(vo);
		
		
//		boardRepository.findById(10L).ifPresent(vo -> {
//			
//			vo.setContent("수정된 제목입니다");
//			
//			boardRepository.save(vo);
//		});
	}
	
	
	@Test
	public void testRead() {
		boardRepository.findById(10L).ifPresent(vo -> log.info(""+vo));
	}
	
	
	@Test
	public void testInsert() {
		
		IntStream.range(0,100).forEach(i -> {
			
			BoardVO vo = new BoardVO();
			vo.setTitle("게시물"+i);
			vo.setContent("내용"+i);
			vo.setWriter("user"+(i % 10) );
			
			boardRepository.save(vo);
			
		});
	}
}
