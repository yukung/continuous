package continuous.service;

import continuous.dto.IndexDto;

/**
 * ユーザ情報サービス
 *
 * @author yukung
 */
public interface UserService {
	
	/**
	 * ユーザーの継続状況を取得する.
	 * 
	 * 
	 * @param username ユーザ名
	 * @return ユーザーの継続状況
	 */
	IndexDto getSummary(String username);
}
