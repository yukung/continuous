package continuous.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Summary summary = new Summary();
		summary.setDate(new Date());
		summary.setAchievement(achievement);
		summary.setPractices(practices);
		return summary;
	}
	
	private List<List<Cell>> convert(List<Practice> practices) {
		// TODO 一旦ベタ書き。ユーティリティに移すかはあとで考える
		if (practices == null || practices.size() == 0) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// TODO practices を Map<String, Practice> に変換する
		Map<String, Practice> map = new HashMap<String, Practice>();
		for (Practice practice : practices) {
			Date practicedOn = practice.getPracticedOn();
			if (practicedOn != null) {
				map.put(format.format(practicedOn), practice);
			}
		}
		Date firstPracticeDate = practices.get(0).getPracticedOn(); // 最初のデータを取って対象月とする
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstPracticeDate);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE)); // 1日に設定
		int extraDays = calendar.getActualMaximum(Calendar.DAY_OF_WEEK) - calendar.get(Calendar.DAY_OF_WEEK);
		int necessaryCellsCount = calendar.getActualMaximum(Calendar.DATE) + extraDays; // 月の日数にカレンダー開始曜日の日数分足す
		int rows = necessaryCellsCount / calendar.getActualMaximum(Calendar.DAY_OF_WEEK);
		if (necessaryCellsCount % calendar.getActualMaximum(Calendar.DAY_OF_WEEK) != 0) {
			// トータル件数が7日で割り切れない場合は、1行増やす
			++rows;
		}
		List<List<Cell>> result = new ArrayList<List<Cell>>();
		int current = 1;
		loop: for (int i = 0; i < rows; i++) { // １ヶ月の週分
			List<Cell> list = new ArrayList<Cell>();
			for (int j = 0; j < calendar.getActualMaximum(Calendar.DAY_OF_WEEK); j++) { // 1週間分
				if (current == necessaryCellsCount) {
					break loop;
				}
				// practices を map にして、日付を key にして問い合わせて、見つかったらCellに入れる
				String calendarKey = format.format(calendar.getTime());
				Practice practice = map.get(calendarKey);
				if (practice != null) {
					Cell cell = new Cell();
					cell.setDate(practice.getPracticedOn());
					cell.setCarriedOut(true);
					cell.setDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
					list.add(cell);
				}
				calendar.add(Calendar.DATE, 1); // 次の日へ
				++current;
			}
			result.add(list);
		}
		return result;
	}
}
