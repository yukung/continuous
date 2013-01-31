package continuous.service;

import continuous.dto.Summary;

/**
 * 実績のサマリーを扱うサービスクラスです.
 *
 * @since 0.0.1
 * @version $Id$
 * @author yukung
 */
public interface SummariesService {
	
	/**
	 * ユーザの実績サマリーを取得します。
	 *
	 * @param userId ユーザID
	 * @return 実績サマリー情報
	 * @since 0.0.1
	 */
	Summary summarize(long userId);
}
