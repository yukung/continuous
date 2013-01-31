package continuous.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import continuous.ApplicationRuntimeException;
import continuous.dao.AchievementDao;
import continuous.dao.PracticeDao;
import continuous.dto.Cell;
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
		// TODO 現在日時から、月末月初を返すユーティリティにリファクタリングする
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
		
		List<Practice> entities = practiceDao.findByRange(userId, achievement.getId(), from, to);
		// TODO サマリー作成ロジック。指針としては、ビュー向けにDTOへ変換するユーティリティをかまして Summary で返す
		List<List<Cell>> practices = convert(entities);
		return new Summary();
	}
	
	/**
	 * TODO for A12323
	 * @param to
	 * @param from
	 *
	 * @param lists
	 * @return
	 * @since TODO
	 */
	private List<List<Cell>> convert(List<Practice> practices) {
		// TODO 一旦ベタ書き。ユーティリティに移すかはあとで考える
		if (practices == null || practices.size() == 0) {
			return null;
		}
		Date firstPracticeDate = practices.get(0).getPracticedOn();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstPracticeDate);
		int row = 0;
		int column = calendar.get(Calendar.DAY_OF_WEEK) - 1; // 開始の列
		Cell[][] matrix = new Cell[6][7];
		for (int i = 1; i <= calendar.getActualMaximum(Calendar.DATE); i++) {
			// TODO practice をなめて i と practicedOn が一致したら、 matrix に入れる
		}
		return null;
	}
}
