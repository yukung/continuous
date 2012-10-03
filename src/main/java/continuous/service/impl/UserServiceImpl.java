package continuous.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import continuous.dao.AchievementDao;
import continuous.dao.PracticeDao;
import continuous.dao.UserDao;
import continuous.dto.IndexDto;
import continuous.entity.Achievement;
import continuous.entity.Practice;
import continuous.entity.User;
import continuous.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	private AchievementDao achievementDao;
	
	private PracticeDao practiceDao;
	
	
	public UserServiceImpl() {
		// TODO とりあえずDIは使わないで作るので、DAOファクトリクラスを作って設定する感じか
		// TODO ファクトリクラス導入したら、引数のDAOは要らなくて、ファクトリクラスから設定
		// this.userDao = DaoFactory.create(daoName); みたいな
		// https://github.com/yukung/tasklet/blob/master/src/main/java/org/yukung/tasklet/factory/DaoFactory.java
		// の各DAOを返すメソッドを抽象クラスかインタフェースを返すようなメソッド定義にして一つにまとめて、
		// 引数で生成するインスタンスを判断するようにする。
		userDao = null;
	}
	
	@Override
	public IndexDto getSummary(String username) {
		User user = userDao.selectByUserName(username);
		Achievement achievement = achievementDao.selectByUserId(user.getId());
		
		// TODO プライベートメソッド or ユーティリティクラスとして実装
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int actualMinimum = cal.getActualMinimum(Calendar.DATE);
		cal.clear();
		cal.set(year, month, actualMinimum);
		Date from = cal.getTime();
		
		Date to = Calendar.getInstance().getTime();
		
		List<Practice> practicedList = practiceDao.selectRangeToPracticedOn(from, to);
		// TODO List 回して PracticedOn をキーにして true を入れる
		Map<Date, Boolean> map = new HashMap<Date, Boolean>();
		IndexDto indexDto = new IndexDto();
		indexDto.setUser(user);
		indexDto.setAchievement(achievement);
		return null;
	}
	
}
