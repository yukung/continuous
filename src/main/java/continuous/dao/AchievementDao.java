package continuous.dao;

import java.util.List;

import continuous.entity.Achievement;

/**
 * データソースから目標の情報を取得するクラスです.
 *
 * @since 0.0.1 for yukung
 * @version $Id$
 * @author yukung
 */
public interface AchievementDao {
	
	/**
	 * 目標情報を削除します.
	 *
	 * @param Achievement 目標情報
	 * @since 0.0.1
	 */
	public void delete(Achievement Achievement);
	
	/**
	 * 指定したIDで目標情報を取得します.
	 *
	 * @param id 目標ID
	 * @return 目標情報
	 * @since 0.0.1
	 */
	public Achievement find(long id);
	
	/**
	 * 目標情報を全て取得します.
	 *
	 * @return 全ての目標情報
	 * @since 0.0.1
	 */
	public List<Achievement> findAll();
	
	/**
	 * 目標情報を作成します.
	 *
	 * @param Achievement 目標情報
	 * @since 0.0.1
	 */
	public void insert(Achievement Achievement);
	
	/**
	 * 目標情報を更新します.
	 *
	 * @param Achievement 目標情報
	 * @since 0.0.1
	 */
	public void update(Achievement Achievement);
}
