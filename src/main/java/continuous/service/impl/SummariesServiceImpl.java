package continuous.service.impl;

import java.util.Date;
import java.util.List;

import continuous.dao.AchievementDao;
import continuous.dao.PracticeDao;
import continuous.dto.IndexDto;
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
	public IndexDto summarize(long userId) {
		Achievement achievement = achievementDao.findByUserId(userId);
		List<Practice> lists = practiceDao.findByRange(userId, new Date(), new Date());
		return new IndexDto();
	}
	
}
