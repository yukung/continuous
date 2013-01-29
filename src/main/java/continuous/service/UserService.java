package continuous.service;

import continuous.entity.User;

/**
 * ユーザ情報を操作するサービスクラスです.
 *
 * @since 0.0.1 for yukung
 * @version $Id$
 * @author yukung
 */
public interface UserService {
	
	/**
	 * 指定したユーザの情報を取得します.
	 *
	 * @param userId ユーザID
	 * @return ユーザ情報
	 * @since 0.0.1
	 */
	User find(long userId);
	
	/**
	 * ユーザ情報を削除します.
	 *
	 * @param user ユーザ
	 * @return 削除結果
	 * @since 0.0.1
	 */
	boolean remove(User user);
	
	/**
	 * ユーザを新規作成します.
	 *
	 * @param user ユーザ
	 * @return ユーザID
	 * @since 0.0.1
	 */
	long store(User user);
	
	/**
	 * ユーザ情報を更新します.
	 *
	 * @param user ユーザ
	 * @return 更新結果
	 * @since 0.0.1
	 */
	boolean update(User user);
	
}
