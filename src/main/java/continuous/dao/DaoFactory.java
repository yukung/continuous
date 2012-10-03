package continuous.dao;

/**
 * 各種DAOを生成するファクトリーインタフェース.
 * 
 * @author yukung
 */
public interface DaoFactory {
	
	// TODO 設計的におかしい、、、ジェネクリスじゃなくてstaticファクトリメソッドか
	<T>T create(Class<?> daoClass);
}
