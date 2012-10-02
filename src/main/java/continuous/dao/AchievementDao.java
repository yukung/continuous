package continuous.dao;

import java.util.List;

import continuous.entity.Achievement;

/**
 * 達成目標情報を扱うインタフェース.
 *
 * @author yukung
 */
public interface AchievementDao {
	
	void delete(Achievement achievement);
	
	void insert(Achievement achievement);
	
	Achievement selectById(Long id);
	
	List<Achievement> selectByIds(List<Long> ids);
	
	Achievement selectByUserId(Long userId);
	
	void update(Achievement achievement);
}
