package newland.iaf.base.risk.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.trans.model.HcTransLogMouth;
import newland.iaf.base.trans.model.HcTransLogMouthPK;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

/**
 * 
 * @author rabbit
 * 
 */
@Service("com.newland.riskControlDao")
public class RiskControlDaoImpl extends BaseDao implements RiskControlDao {

	private String mouthFormat(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMM");
		return df.format(date);
	}

	public void insertTransLogMouth(Date date) {
		String mouth = mouthFormat(date);
		String sql = "insert into t_hc_trans_log_mouth(mouth, merch_no, amt, avg_amt, stddev_amt, charge, trans_num, trans_type) "
				+ "(SELECT '"
				+ mouth
				+ "', merch_no, sum(amt), avg(amt), stddev(amt), sum(charge), count(*), trans_type "
				+ "FROM t_hc_trans_log where settle_date >= ? and settle_date < ? "
				+ "group by merch_no, trans_type)";
		Date start = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
		Date end = DateUtils.addMonths(date, 1);
		end = DateUtils.truncate(end, Calendar.DAY_OF_MONTH);
		this.getHibernateDao().executeUpdateBySql(sql, start, end);
	}

	public void deleteTransLogMouth(Date date) {
		String sql = "delete from t_hc_trans_log_mouth where mouth=?";
		String mouth = mouthFormat(date);
		this.getHibernateDao().executeUpdateBySql(sql, mouth);
	}

	public List<HcTransLogMouth> getList(String merchNo, Date date, int amount) {
		Date begin = null;
		Date end = null;
		if (amount == 0) {
			begin = date;
			end = date;
		} else if (amount < 0) {
			begin = DateUtils.addMonths(date, amount + 1);
			end = date;
		} else {
			begin = date;
			end = DateUtils.addMonths(date, amount - 1);
		}
		return getList(merchNo, begin, end);
	}

	@Override
	public boolean hasTransLogMouth(Date date) {
		String hql = "select * from t_hc_trans_log_mouth where mouth=?";
		String mouth = mouthFormat(date);
		return this.getHibernateDao().hasRecordBySql(hql, mouth);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HcTransLogMouth> getList(String merchNo, Date begin, Date end) {
		String b = mouthFormat(begin);
		String e = mouthFormat(end);
		return this.getHibernateDao().find(
				"from HcTransLogMouth where mouth>=? and mouth<=? ", b, e);
	}

	public HcTransLogMouth getHcTransLogMouth(HcTransLogMouthPK pk) {
		return (HcTransLogMouth) this.getSession().get(HcTransLogMouth.class,
				pk);
	}

	@Override
	public int countUninstallNum(String merchNo, Date date) {
		BigInteger num = getHibernateDao()
				.countBySql(
						"select * from t_hc_install_log t1 where t1.term_stat='4' and  t1.hc_merch_no=? and t1.uninstall_date>=? ",
						merchNo, date);
		return num.intValue();
	}

	@Override
	public boolean hasTransLog(String merchNo, Date date) {
		return this
				.getHibernateDao()
				.hasRecordBySql(
						"select * from t_hc_trans_log where merch_no=? and trans_date<=? and trans_type=?",
						merchNo, date, "N");
	}

	@Override
	public BigDecimal getHcTransLogAmt(String merchNo, Date begin, Date end) {
		BigInteger bi= (BigInteger) this
				.getHibernateDao()
				.getFirstBySql(
						"select count(amt) from t_hc_trans_log where trans_date>=? and trans_date<? and merch_no=? and trans_type=?",
						begin, end, merchNo, "N");
		return new BigDecimal(bi);
//		return (BigDecimal) this
//				.getHibernateDao()
//				.getFirstBySql(
//						"select count(amt) from t_hc_trans_log where trans_date>=? and trans_date<? and merch_no=? and trans_type=?",
//						begin, end, merchNo, "N");
	}

}
