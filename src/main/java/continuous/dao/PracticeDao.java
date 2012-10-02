package continuous.dao;

import java.util.Date;
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
	
	List<Practice> selectRangeToPracticedOn(Date from, Date to);
	
	void update(Practice practice);
}
