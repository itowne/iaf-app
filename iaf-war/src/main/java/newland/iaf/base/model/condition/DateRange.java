package newland.iaf.base.model.condition;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import newland.iaf.base.service.DateRangeInterface;
import newland.iaf.utils.DateUtils;

public enum DateRange{
	/**
	 * 上个月
	 */
	PRE_MONTH(new DateRangeInterface(){

		@Override
		public Date getBeginDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -1);
			date = DateConvertUtils.getFirstDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}

		@Override
		public Date getEndDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -1);
			date = DateConvertUtils.getLastDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}
		
	}),
	/**
	 * 上两个月
	 */
	PRE_TWO_MONTH(new DateRangeInterface(){

		@Override
		public Date getBeginDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -2);
			date = DateConvertUtils.getFirstDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}

		@Override
		public Date getEndDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -2);
			date = DateConvertUtils.getLastDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}
		
	}),
	/**
	 * 上三个月
	 */
	PRE_THREE_MONTH(new DateRangeInterface(){

		@Override
		public Date getBeginDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -3);
			date = DateConvertUtils.getFirstDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}

		@Override
		public Date getEndDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -3);
			date = DateConvertUtils.getLastDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}
		
	}),
	/**
	 * 上四个月
	 */
	PRE_FOUR_MONTH(new DateRangeInterface(){

		@Override
		public Date getBeginDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -4);
			date = DateConvertUtils.getFirstDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}

		@Override
		public Date getEndDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -4);
			date = DateConvertUtils.getLastDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}
		
	}),
	/**
	 * 上五个月
	 */
	PRE_FIVE_MONTH(new DateRangeInterface(){

		@Override
		public Date getBeginDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -5);
			date = DateConvertUtils.getFirstDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}

		@Override
		public Date getEndDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -5);
			date = DateConvertUtils.getLastDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}
		
	}),
	/**
	 * 上季度
	 */
	PRE_SEASON(new DateRangeInterface(){

		@Override
		public Date getBeginDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -3);
			date = DateConvertUtils.getFirstDateOfSeason(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}

		@Override
		public Date getEndDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -3);
			date = DateConvertUtils.getLastDateOfSeason(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}
		
	}),
	/**
	 * 上半年
	 */
	PRE_HALF_YEAR(new DateRangeInterface(){

		@Override
		public Date getBeginDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -6);
			date = DateConvertUtils.getFirstDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}

		@Override
		public Date getEndDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -6);
			date = DateConvertUtils.getLastDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}
		
	}),
	
	/**
	 * 上半年
	 */
	HALF_YEAR(new DateRangeInterface(){

		@Override
		public Date getBeginDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -6);
			date = DateConvertUtils.getFirstDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}

		@Override
		public Date getEndDate() {
			Date date = null;
			date = DateUtils.rollDate(new Date(), Calendar.MONTH, -1);
			date = DateConvertUtils.getLastDateOfMonth(date);
			date = DateUtils.roundDate(date, Calendar.DATE);
			return date;
		}
		
	});
	
	DateRangeInterface dateRange;
	
	DateRange(DateRangeInterface dateRange){
		this.dateRange = dateRange;
	}
	
	public Date getBeginDate(){
		return this.dateRange.getBeginDate();
	}
	
	public Date getEndDate(){
		return this.dateRange.getEndDate();
	}
	
	public DateRangeInterface getDateRange(){
		return this.dateRange;
	}
	
	public static void main(String[] args){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.err.println(sdf.format(DateRange.PRE_MONTH.getBeginDate()));
		System.err.println(sdf.format(DateRange.PRE_MONTH.getEndDate()));
		System.err.println(sdf.format(DateRange.PRE_THREE_MONTH.getBeginDate()));
		System.err.println(sdf.format(DateRange.PRE_THREE_MONTH.getEndDate()));
		System.err.println(sdf.format(DateRange.PRE_SEASON.getBeginDate()));
		System.err.println(sdf.format(DateRange.PRE_SEASON.getEndDate()));
		System.err.println(sdf.format(DateRange.PRE_HALF_YEAR.getBeginDate()));
		System.err.println(sdf.format(DateRange.PRE_HALF_YEAR.getEndDate()));
	}
}
