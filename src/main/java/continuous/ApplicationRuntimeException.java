package continuous;

/**
 * アプリケーション実行時の例外クラス.
 * 
 * @since 0.0.1 for yukung
 * @version $Id$
 * @author yukung
 */
public class ApplicationRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = -6465591096433766962L;

	public ApplicationRuntimeException() {
	}
	
	public ApplicationRuntimeException(String message) {
		super(message);
	}
	
	public ApplicationRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ApplicationRuntimeException(Throwable cause) {
		super(cause);
	}
}
