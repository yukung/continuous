package continuous.dao;

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
	public void delete(Practice Practice);
	
	/**
	 * 指定したIDで実績情報を取得します.
	 *
	 * @param id 実績ID
	 * @return 実績情報
	 * @since 0.0.1
	 */
	public Practice find(long id);
	
	/**
	 * 実績情報を全て取得します.
	 *
	 * @return 全ての実績情報
	 * @since 0.0.1
	 */
	public List<Practice> findAll();
	
	/**
	 * 実績情報を作成します.
	 *
	 * @param Practice 実績情報
	 * @since 0.0.1
	 */
	public void insert(Practice Practice);
	
	/**
	 * 実績情報を更新します.
	 *
	 * @param Practice 実績情報
	 * @since 0.0.1
	 */
	public void update(Practice Practice);
}
