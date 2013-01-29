package continuous.dao;

import java.util.List;

import continuous.entity.User;

/**
 * データソースからユーザの情報を取得するクラスです.
 *
 * @since 0.0.1 for yukung
 * @version $Id$
 * @author yukung
 */
public interface UserDao {
	
	/**
	 * ユーザ情報を削除します.
	 *
	 * @param user ユーザ情報
	 * @since 0.0.1
	 */
	void delete(User user);
	
	/**
	 * 指定したIDでユーザ情報を取得します.
	 *
	 * @param id ユーザID
	 * @return ユーザ情報
	 * @since 0.0.1
	 */
	User find(long id);
	
	/**
	 * ユーザ情報を全て取得します.
	 *
	 * @return 全てのユーザ情報
	 * @since 0.0.1
	 */
	List<User> findAll();
	
	/**
	 * ユーザ情報を作成します.
	 *
	 * @param user ユーザ情報
	 * @return ユーザID
	 * @since 0.0.1
	 */
	long insert(User user);
	
	/**
	 * ユーザ情報を更新します.
	 *
	 * @param user ユーザ情報
	 * @since 0.0.1
	 */
	void update(User user);
}
