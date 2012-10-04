package continuous.dao;

/**
 * 各種DAOを生成するファクトリーインタフェース.
 *
 * @author yukung
 */
public interface DaoFactory {
	
	/**
	 * 指定したDAOクラスを生成します.
	 *
	 * @param daoClass 生成するDAOクラス
	 * @return
	 */
	<D>D create(Class<D> daoClass);
	
}
