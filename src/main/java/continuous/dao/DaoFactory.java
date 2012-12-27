package continuous.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * 各種DAOを生成するファクトリクラス.
 * TODO インタフェース化して、enum で列挙されたデータベースの種類をジェネリクスで指定して生成するように変更したほうが良い？
 * そこまでするのも微妙か…
 * 
 * @author yukung
 */
public class DaoFactory {
	
	private static Map<Class<? extends AbstractDao>, AbstractDao> instanceMap =
			new HashMap<Class<? extends AbstractDao>, AbstractDao>();
	
	
	public static <T extends AbstractDao>T create(Class<T> clazz) {
		if (!instanceMap.containsKey(clazz)) {
			try {
				instanceMap.put(clazz, clazz.newInstance());
			} catch (InstantiationException e) {
				// TODO アプリ例外化
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO アプリ例外化
				e.printStackTrace();
			}
		}
		return clazz.cast(instanceMap.get(clazz));
	}
}
