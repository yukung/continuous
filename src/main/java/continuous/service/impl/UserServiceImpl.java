package continuous.service.impl;

import continuous.dao.UserDao;
import continuous.entity.User;
import continuous.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	
	public UserServiceImpl(UserDao userDao) {
		// TODO とりあえずDIは使わないで作るので、DAOファクトリクラスを作って設定する感じか
		// TODO ファクトリクラス導入したら、引数のDAOは要らなくて、ファクトリクラスから設定
		this.userDao = userDao;
	}
	
	@Override
	public User findByUserName(String name) {
		return userDao.selectByUserName(name);
	}
	
}
