package continuous.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * 各種DAOを生成するファクトリクラス.
 * 
 * @author yukung
 */
public class DaoFactory {
	
	public static <T extends AbstractDao>T create(final Class<T> clazz) {
		return null;
	}
	
	
	private Map<Class<? extends AbstractDao>, AbstractDao> instanceMap =
			new HashMap<Class<? extends AbstractDao>, AbstractDao>();
}
