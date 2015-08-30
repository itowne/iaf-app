package newland.iaf.backstage.schedule.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
@Entity
@Table (name = "t_schedule")
public class ScheduleCtrl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column (name = "i_sch")
	@TableGenerator(name = "i_sch", initialValue = 0, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "i_sch")
	private Long isch;
	/**
	 * 定时器名称
	 */
	@Column (name = "schedule_name", length = 50)
	private String scheduleName;
	
	/**
	 * 运行日期
	 */
	@Column (name = "run_date")
	private Date runTime;
	/**
	 * 结束时间
	 */
	@Column (name = "end_time")
	private Date endTime;
	/**
	 * 运行状态
	 */
	@Enumerated
	@Column (name = "run_stat")
	private RunStat runStat;
	/**
	 * 错误信息
	 */
	@Column (name = "err_msg", length = 200)
	private String errMsg;
	/**
	 * ip
	 */
	@Column (name = "ip_addr", length = 32)
	private String ipaddr;

	public Long getIsch() {
		return isch;
	}

	public void setIsch(Long isch) {
		this.isch = isch;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public Date getRunTime() {
		return runTime;
	}

	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}

	public RunStat getRunStat() {
		return runStat;
	}

	public void setRunStat(RunStat runStat) {
		this.runStat = runStat;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

}
