package org.zerock.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.BoardVO;

public interface BoardRepository extends CrudRepository<BoardVO, Long>{
// ID에는 기본자료형(int) 들어갈 수 없음.	
	
	public Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable);
	
	//findBy는 접두어
	//페이징
	//public List<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable);

	//검색
	public List<BoardVO> findByTitleContaining(String keyword);
	
	//'7'로 제목으로 검색해서 5개씩 페이징
	//index타입이기 때문에...
	public List<BoardVO> findByTitleContainingAndBnoGreaterThan
						(String keyword, Pageable pageable);
}
