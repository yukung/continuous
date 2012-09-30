package continuous.dao;

import java.util.List;

import continuous.entity.Practice;

/**
 * 達成状況情報を扱うインタフェース.
 *
 * @author yukung
 */
public interface PracticeDao {
	
	void delete(Practice practice);
	
	void insert(Practice practice);
	
	Practice selectById(Long id);
	
	List<Practice> selectByIds(List<Long> ids);
	
	void update(Practice practice);
}
