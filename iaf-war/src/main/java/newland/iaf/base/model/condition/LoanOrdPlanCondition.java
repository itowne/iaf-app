package newland.iaf.base.model.condition;

import newland.iaf.base.model.LoanOrdPlan;
import newland.iaf.base.model.LoanOrdPlan.PlanStat;

import org.ohuyo.commons.query.criterion.DetachedCriteriaEx;
import org.ohuyo.commons.query.criterion.annotation.CriteriaClass;
import org.ohuyo.commons.query.criterion.annotation.Expression;
import org.ohuyo.commons.query.criterion.annotation.Operator;

@CriteriaClass(LoanOrdPlan.class)
public class LoanOrdPlanCondition extends DetachedCriteriaEx {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Expression(operator = Operator.eq, propertyName = "iloanOrd")
	private Long iloanOrd;
	
	@Expression(operator = Operator.eq, propertyName = "period")
	private Integer period;
	
	@Expression(operator = Operator.eq, propertyName = "stat")
	private PlanStat planStat;
	@Expression(operator = Operator.lt, propertyName = "stat")
	private PlanStat ltstat ;

	public Long getIloanOrd() {
		return iloanOrd;
	}

	public void setIloanOrd(Long iloanOrd) {
		this.iloanOrd = iloanOrd;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public PlanStat getPlanStat() {
		return planStat;
	}

	public void setPlanStat(PlanStat planStat) {
		this.planStat = planStat;
	}

	public PlanStat getLtstat() {
		return ltstat;
	}

	public void setLtstat(PlanStat ltstat) {
		this.ltstat = ltstat;
	}

}
