package org.zerock;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.QBoardVO;
import org.zerock.persistence.BoardRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardTests {

	@Setter(onMethod_=@Autowired)
	private BoardRepository boardRepository;
	
	@Test
	public void testDynamic() {
		String[] types= {"t","c"};
		String keyword="10";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoardVO board = QBoardVO.boardVO;
		
		BooleanExpression[] arr = new BooleanExpression[types.length];
				
		for(int i = 0; i < types.length; i++) {
			
			String type = types[i];
			BooleanExpression cond = null;
		
		if (type.equals("t")) {
			cond = board.title.contains(keyword);
			
		}else if(type.equals("c")) {
			cond = board.content.contains(keyword);
		}
			arr[i] = cond;
		}

		builder.andAnyOf(arr);
		
		Page<BoardVO> result =
				boardRepository.findAll(builder,
				PageRequest.of(0, 10, Sort.Direction.DESC,"bno"));
		
		log.info(""+result);
	}
	
	
	
	//작성자로 검색하는 Test code
	@Test
	public void testWriter() {
		Page<BoardVO> result
		= boardRepository.getListByWriter("3", PageRequest.of(0, 10));
		
		log.info(""+result);
		
		result.getContent().forEach(vo -> log.info(""+vo));
	}
	
	//내용으로 검색하는 Test code
	@Test
	public void testContent() {
		Page<BoardVO> result
		= boardRepository.getListByContent("10", PageRequest.of(0, 10));
		
		log.info(""+result);
		
		result.getContent().forEach(vo -> log.info(""+vo));
	}
	
	//제목으로 검색하는 Test code
	@Test
	public void testTitle() {
		Page<BoardVO> result
		= boardRepository.getListByTitle("10", PageRequest.of(0, 10));
		
		log.info(""+result);
		
		result.getContent().forEach(vo -> log.info(""+vo));
	}
	
	@Test
	public void testList() {
		
		Page<BoardVO> result
		= boardRepository.getList(PageRequest.of(0, 10));
		
		log.info(""+result);
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
		
		IntStream.range(100,1000).forEach(i -> {
			
			BoardVO vo = new BoardVO();
			vo.setTitle("gonnys"+i);
			vo.setContent("고니스"+i);
			vo.setWriter("user"+(i % 10) );
			
			boardRepository.save(vo);
			
		});
	}
}
