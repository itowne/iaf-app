package newland.iaf.base.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import newland.iaf.base.dao.BaseDao;
import newland.iaf.base.dao.CriteriaExecutor;
import newland.iaf.base.dao.LoanStatisticsDao;
import newland.iaf.base.model.LoanStatistics;
import newland.iaf.base.model.LoanStatisticsHis;
import newland.iaf.base.model.dict.InstType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service("loanStatisticsDao")
public class LoanStatisticsDaoImpl extends BaseDao implements LoanStatisticsDao {

	@Override
	public void save(LoanStatistics ls) {
		this.getSession().save(ls);

	}

	@Override
	public void update(LoanStatistics ls) {
		this.getSession().update(ls);

	}

	@SuppressWarnings("unchecked")
	@Override
	public LoanStatistics queryBy(Long iinst, InstType type) {
		Criteria criteria = this.getSession().createCriteria(LoanStatistics.class);
		criteria.add(Restrictions.eq("iinst", iinst)).
					add(Restrictions.eq("instType", type));
		List<LoanStatistics> list = criteria.list();
		if (CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}

	@Override
	public void copyToHis() {
		this.getSession().doWork(new Work(){

			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement stat = connection.prepareStatement(
						"insert into t_loan_statistics_his " +
					    " (cur_acce_count, cur_cancel_count,cur_audit_count," +
					    "cur_credit_amount, cur_credit_count," +
					    "cur_period_ref_amount, cur_refund_amount," +
					    "debt_amount, expire_refund," +
					    "freeze_amount,i_inst,i_loan_statis," +
					    "inst_type,loan_acce_count,loan_cancel_count,loan_amount," +
					    "loan_apl_count,loan_aud_count,loan_count," +
					    "mer_deb_count,overdue_amount, prod_count," +
					    "refund_amount,refuse_accept_count, refuse_audit_count, overdue_count) " + 
						"select " +
						" cur_acce_count, cur_cancel_count,cur_audit_count," +
					    "cur_credit_amount, cur_credit_count," +
					    "cur_period_ref_amount, cur_refund_amount," +
					    "debt_amount, expire_refund," +
					    "freeze_amount,i_inst,i_loan_statis," +
					    "inst_type,loan_acce_count,loan_cancel_count,loan_amount," +
					    "loan_apl_count,loan_aud_count,loan_count," +
					    "mer_deb_count,overdue_amount, prod_count," +
					    "refund_amount,refuse_accept_count, refuse_audit_count, overdue_count " +
						" from t_loan_statistics ");
				stat.execute();
				stat.close();
			}
		});
		
	}

	@Override
	public synchronized void cleanAll() {
		this.getSession().doWork(new Work(){

			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement stat = connection.prepareStatement(
						"update t_loan_statistics " +
					    "set " +
					    "debt_amount=0, expire_refund=0," +
					    "freeze_amount=0," +
					    "loan_acce_count=0,loan_cancel_count=0,loan_amount=0," +
					    "loan_apl_count=0,loan_aud_count=0,loan_count=0," +
					    "mer_deb_count=0,overdue_amount=0,overdue_count=0, prod_count=0," +
					    "refund_amount=0,refuse_accept_count=0, refuse_audit_count=0" + 
						" where 1=1 ");
				stat.execute();
				stat.close();
			}
		});
	}

	@Override
	public void cleanCurrent() {
		this.getSession().doWork(new Work(){

			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement stat = connection.prepareStatement(
						"update t_loan_statistics " +
					    " set " +
					    "cur_acce_count=0,cur_cancel_count=0, cur_audit_count=0," +
					    "cur_credit_amount=0, cur_credit_count=0," +
					    "cur_period_ref_amount=0, cur_refund_amount=0 " +
						" where 1=1 ");
				stat.execute();
				stat.close();
			}
		});
		
	}

	@Override
	public List<LoanStatisticsHis> queryBy(CriteriaExecutor<List<LoanStatisticsHis>> executor) {
		Criteria criteria = this.getSession().createCriteria(LoanStatisticsHis.class);
		return executor.execute(criteria);
	}


}
