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
}
