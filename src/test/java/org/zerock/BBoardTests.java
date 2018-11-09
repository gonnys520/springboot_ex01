package org.zerock;

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
import org.zerock.persistence.BBoardRepository;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BBoardTests {

	@Setter(onMethod_=@Autowired)
	private BBoardRepository boardRepository;
	
	
	@Test
	public void testPage() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		Page<BoardVO> result = boardRepository.findByBnoGreaterThan(0L, pageable);
		
		result.getContent().forEach(vo -> log.info(""+vo));	
	}
	
	@Test
	public void testSearch1() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		
		boardRepository.findByTitleContainingAndBnoGreaterThan("7", 0L, pageable)
		.forEach(vo -> log.info(""+vo));
	}
	
	@Test
	public void testSearch2() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		
		boardRepository.findByTitleContainingOrContentContainingAndBnoGreaterThan("7", "7", 0L, pageable)
		.forEach(vo -> log.info(""+vo));
	}
	
	@Test
	public void testSearch3() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		
		boardRepository.findByTitleContainingOrContentContainingOrWriterContainingAndBnoGreaterThan("7", "7", "7", 0L, pageable)
		.forEach(vo -> log.info(""+vo));
	}
}
