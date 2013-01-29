package continuous.service.impl;

import continuous.dao.UserDao;
import continuous.entity.User;
import continuous.service.UserService;

/**
 * {@link UserService} の実装クラス.
 *
 * @since 0.0.1 for yukung
 * @version $Id$
 * @author yukung
 */
public class UserServiceImpl implements UserService {
	
	private UserDao dao;
	
	
	/**
	 * インスタンスを生成する。
	 *
	 * @param dao ユーザー情報Dao
	 */
	public UserServiceImpl(UserDao dao) {
		this.dao = dao;
	}
	
	@Override
	public User find(long userId) {
		return dao.find(userId);
	}
	
	@Override
	public boolean remove(User user) {
		return dao.delete(user);
	}
	
	@Override
	public long store(User user) {
		return dao.insert(user);
	}
	
	@Override
	public boolean update(User user) {
		return dao.update(user);
	}
	
}
