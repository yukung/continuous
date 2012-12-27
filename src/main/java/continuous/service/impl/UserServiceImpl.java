package continuous.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import continuous.dao.AchievementDao;
import continuous.dao.PracticeDao;
import continuous.dao.UserDao;
import continuous.dto.SummaryDto;
import continuous.entity.Achievement;
import continuous.entity.Practice;
import continuous.entity.User;
import continuous.service.UserService;
import continuous.util.DateUtils;
import continuous.util.DateUtils.Period;

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
		// TODO ふもさんのエントリ→ http://npnl.hatenablog.jp/entry/2012/07/06/004311 にやりたいことがまさに載ってる(*´Д`)ﾊｧﾊｧ
	}
	
	@Override
	public SummaryDto getSummary(String username) {
		User user = userDao.selectByUserName(username);
		Achievement achievement = achievementDao.selectByUserId(user.getId());
		
		Period currentPeriod = DateUtils.currentPeriod();
		
		List<Practice> practicedList =
				practiceDao.selectRangeToPracticedOn(currentPeriod.getFrom(), currentPeriod.getTo());
		Map<Date, Boolean> statusMap = new TreeMap<Date, Boolean>();
		for (Practice practice : practicedList) {
			statusMap.put(practice.getPracticedOn(), true);
		}
		SummaryDto summaryDto = new SummaryDto();
		summaryDto.setUser(user);
		summaryDto.setAchievement(achievement);
		summaryDto.setStatus(statusMap);
		return summaryDto;
	}
	
}
