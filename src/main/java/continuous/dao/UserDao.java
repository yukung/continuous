package continuous.dao;

import java.util.List;

import continuous.entity.User;

/**
 * ユーザ情報を扱うインタフェース.
 *
 * @author yukung
 */
public interface UserDao {
	
	void delete(User user);
	
	void insert(User user);
	
	User selectById(Long id);
	
	List<User> selectByIds(List<Long> ids);
	
	void update(User user);
}
