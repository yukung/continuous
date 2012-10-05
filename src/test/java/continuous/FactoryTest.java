package continuous;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * クラスリテラルから指定のオブジェクトを生成するコードの例.
 * フモさんのエントリから
 * http://npnl.hatenablog.jp/entry/2012/07/06/004311
 * 
 * @author yukung
 */
public class FactoryTest {
	
	static class A extends Alphabet {
		
		@Override
		String whoAmI() {
			return "I'm A!";
		}
	}
	
	static abstract class Alphabet {
		
		abstract String whoAmI();
	}
	
	static interface Animal {
	}
	
	static class B extends Alphabet {
		
		@Override
		String whoAmI() {
			return "I'm B!";
		}
	}
	
	static class C extends Alphabet {
		
		@Override
		String whoAmI() {
			return "I'm C!";
		}
	}
	
	static class Cat implements Animal {
	}
	
	static class Dog implements Animal {
	}
	
	static class Factory<T> {
		
		private Map<Class<? extends T>, T> instanceMap = new HashMap<Class<? extends T>, T>();
		
		
		public <U extends T>U getOrCreate(Class<U> clazz) {
			if (!instanceMap.containsKey(clazz)) {
				try {
					instanceMap.put(clazz, clazz.newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return clazz.cast(instanceMap.get(clazz));
		}
	}
	
	
	Factory<Alphabet> factory;
	
	
	@Test
	public void createするテスト() {
		A a = factory.getOrCreate(A.class);
		assertThat(a instanceof A, is(true));
	}
	
	@Before
	public void setUp() {
		factory = new Factory<Alphabet>();
	}
	
}
