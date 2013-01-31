package continuous.dao;

import java.util.Date;
import java.util.List;

import continuous.entity.Practice;

/**
 * データソースから実績の情報を取得するクラスです.
 *
 * @since 0.0.1 for yukung
 * @version $Id$
 * @author yukung
 */
public interface PracticeDao {
	
	/**
	 * 実績情報を削除します.
	 *
	 * @param Practice 実績情報
	 * @since 0.0.1
	 */
	void delete(Practice Practice);
	
	/**
	 * 指定したIDで実績情報を取得します.
	 *
	 * @param id 実績ID
	 * @return 実績情報
	 * @since 0.0.1
	 */
	Practice find(long id);
	
	/**
	 * 実績情報を全て取得します.
	 *
	 * @return 全ての実績情報
	 * @since 0.0.1
	 */
	List<Practice> findAll();
	
	/**
	 * 期間を指定して実績を取得します.
	 * @param userId ユーザID
	 * @param achievementId 目標ID
	 * @param from 開始日付
	 * @param to 終了日付
	 * @return 実績情報リスト
	 *
	 * @since 0.0.1
	 */
	List<Practice> findByRange(long userId, long achievementId, Date from, Date to);
	
	/**
	 * 実績情報を作成します.
	 *
	 * @param Practice 実績情報
	 * @since 0.0.1
	 */
	void insert(Practice Practice);
	
	/**
	 * 実績情報を更新します.
	 *
	 * @param Practice 実績情報
	 * @since 0.0.1
	 */
	void update(Practice Practice);
}
