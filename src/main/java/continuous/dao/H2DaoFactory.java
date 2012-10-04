package continuous.dao;

/**
 * H2 Database 用のDAOクラスを生成するファクトリクラス.
 *
 * @author yukung
 */
public class H2DaoFactory implements DaoFactory {
	
	/**
	 * H2DaoFactory のインスタンス.
	 * シングルトンオブジェクト.
	 */
	private static DaoFactory instance = null;
	
	
	/**
	 * H2DaoFactory のインスタンスを取得します.
	 *
	 * @return
	 * @since TODO
	 */
	public static DaoFactory getInstance() {
		if (instance == null) {
			instance = new H2DaoFactory();
		}
		return instance;
	}
	
	/**
	 * シングルトンオブジェクトのためコンストラクタは外部からアクセスできません.
	 *
	 */
	private H2DaoFactory() {
		// do nothing;
	}
	
	@Override
	public <D>D create(Class<D> daoClass) {
		// TODO 引数で指定したクラスから、そのインタフェースを実装したDAOクラスを返却する
		// TODO 本当は extends で指定できるクラスを限定したい
		// TODO 下の実装はあかーーーーん、適当すぎる…ほんとは戻り値をインタフェース、引数を実装クラスにしたい
		if (daoClass.isInterface()) {
			// TODO 適当なので直す
			throw new IllegalArgumentException("指定したクラスは間違いです");
		}
		D daoInstance = null;
		try {
			// Mapとかでインタフェース,実装クラスのマッピングをもつべきかしら？
			// でもそしたら複数のマッピング持てないよね
			// 文字列か・・・？
			daoInstance = daoClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return daoInstance;
	}
}
