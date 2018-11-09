package org.zerock.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.BoardVO;

public interface BBoardRepository extends CrudRepository<BoardVO, Long>{

	
	public Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable);
	
	//검색 :제목
	public Page<BoardVO> findByTitleContainingAndBnoGreaterThan(String keyword, Long bno, Pageable pageable);
	
	//검색 :제목+내용
	public Page<BoardVO> findByTitleContainingOrContentContainingAndBnoGreaterThan
	(String keyword, String keyword2, Long bno, Pageable pageable);
	
	//검색 :제목+내용+작성자
	public Page<BoardVO> findByTitleContainingOrContentContainingOrWriterContainingAndBnoGreaterThan
	(String keyword, String keyword2, String keyword3, Long bno, Pageable pageable);
	

}
