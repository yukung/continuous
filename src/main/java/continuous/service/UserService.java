package continuous.service;

import continuous.entity.User;

/**
 * ユーザ情報サービス
 *
 * @author yukung
 */
public interface UserService {
	
	User findByUserName(String name);
}
