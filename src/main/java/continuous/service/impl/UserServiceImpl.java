package continuous.service.impl;

import continuous.dao.UserDao;
import continuous.entity.User;
import continuous.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	
	public UserServiceImpl(UserDao userDao) {
		// TODO とりあえずDIは使わないで作るので、DAOファクトリクラスを作って設定する感じか
		// TODO ファクトリクラス導入したら、引数のDAOは要らなくて、ファクトリクラスから設定
		// this.userDao = DaoFactory.create(daoName); みたいな
		// https://github.com/yukung/tasklet/blob/master/src/main/java/org/yukung/tasklet/factory/DaoFactory.java
		// の各DAOを返すメソッドを抽象クラスかインタフェースを返すようなメソッド定義にして一つにまとめて、
		// 引数で生成するインスタンスを判断するようにする。
		this.userDao = userDao;
	}
	
	@Override
	public User findByUserName(String name) {
		return userDao.selectByUserName(name);
	}
	
}
