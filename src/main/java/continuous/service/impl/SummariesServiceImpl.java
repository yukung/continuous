package continuous.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import continuous.ApplicationRuntimeException;
import continuous.dao.AchievementDao;
import continuous.dao.PracticeDao;
import continuous.dto.Summary;
import continuous.entity.Achievement;
import continuous.entity.Practice;
import continuous.service.SummariesService;

/**
 * {@link SummariesService} の実装クラス.
 *
 * @since 0.0.1 for yukung
 * @version $Id$
 * @author yukung
 */
public class SummariesServiceImpl implements SummariesService {
	
	private AchievementDao achievementDao;
	
	private PracticeDao practiceDao;
	
	
	/**
	 * インスタンスを生成する。
	 *
	 */
	public SummariesServiceImpl(AchievementDao achievementDao, PracticeDao practiceDao) {
		this.achievementDao = achievementDao;
		this.practiceDao = practiceDao;
	}
	
	@Override
	public Summary summarize(long userId) {
		Achievement achievement = achievementDao.findByUserId(userId);
		if (achievement == null) {
			throw new ApplicationRuntimeException("ユーザが見つかりません");
		}
		// 対象年月
		Calendar calendar = Calendar.getInstance();
		// 月初
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		final Date from = calendar.getTime();
		// 月末
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		final Date to = calendar.getTime();
		List<Practice> lists = practiceDao.findByRange(userId, achievement.getId(), from, to);
		return new Summary();
	}
}
