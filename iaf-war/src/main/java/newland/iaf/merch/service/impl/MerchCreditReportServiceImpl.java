package newland.iaf.merch.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import newland.iaf.IafApplication;
import newland.iaf.base.model.FileType;
import newland.iaf.base.model.MerchFieldSurvy;
import newland.iaf.base.model.ReportTemplate;
import newland.iaf.base.model.TFile;
import newland.iaf.base.model.TransRec;
import newland.iaf.base.model.TransReport;
import newland.iaf.base.model.condition.CreditReportCondition;
import newland.iaf.base.model.condition.DateRange;
import newland.iaf.base.model.dict.InstType;
import newland.iaf.base.model.dict.PagePosition;
import newland.iaf.base.risk.model.RiskMonitor;
import newland.iaf.base.service.CreditReportQueryService;
import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.MccService;
import newland.iaf.base.service.ReportTemplateService;
import newland.iaf.base.service.TFileService;
import newland.iaf.base.service.impl.MerchSession;
import newland.iaf.base.servlet.SessionFilter;
import newland.iaf.base.trans.model.HcInspectLog;
import newland.iaf.base.trans.model.HcInstallLog;
import newland.iaf.merch.dao.MerchInfoPermissionDao;
import newland.iaf.merch.model.AcceptType;
import newland.iaf.merch.model.HtmlFileType;
import newland.iaf.merch.model.LoanCreditReport;
import newland.iaf.merch.model.Merch;
import newland.iaf.merch.model.MerchBusiInfo;
import newland.iaf.merch.model.MerchInfoPermission;
import newland.iaf.merch.model.MerchLegalPers;
import newland.iaf.merch.model.MerchTransReport;
import newland.iaf.merch.model.MerchType;
import newland.iaf.merch.model.ReportHtmlFile;
import newland.iaf.merch.service.CreditReportService;
import newland.iaf.merch.service.MerchCreditReportService;
import newland.iaf.merch.service.MerchService;
import newland.iaf.utils.DateUtils;
import newland.iaf.utils.service.SerialNoService;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.ohuyo.commons.query.criterion.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Header;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.opensymphony.xwork2.validator.validators.DateRangeFieldValidator;

@Service("merchCreditReportService")
@Transactional(readOnly = false, rollbackFor = Throwable.class)
public class MerchCreditReportServiceImpl implements MerchCreditReportService {

	@Resource(name = "reportTemplateService")
	private ReportTemplateService reportTemplateService;

	@Resource(name = "creditReportService")
	private CreditReportService creditReportService;

	@Resource(name = "com.newland.iaf.merchService")
	private MerchService merchService;

	@Resource(name = "serialNoService")
	private SerialNoService serialNoService;

	@Resource(name = "merchInfoPermissionDao")
	private MerchInfoPermissionDao merchInfoPermissionDao;
	@Resource(name = "creditReportQueryService")
	private CreditReportQueryService queryService;

	@Resource(name = "tfileService")
	private TFileService fileService;

	@Resource(name = "mccService")
	private MccService mccService;

	static Logger logger = LoggerFactory
			.getLogger(MerchCreditReportServiceImpl.class);

	final static String pdfFontFactory = IafApplication.getPdfFontPath();

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void saveReport(LoanCreditReport rpt) throws Throwable {
		if (rpt.getIreport() != null)
			throw new Exception("经营数据报告已保存！");
//		 File file = this.convertToPdf(rpt, baseUrl, true);
//		 rpt.setReportFile(file);
//		 rpt.setFilePath(file.getName());
		rpt.setReportId(this.serialNoService.genPlanNo());
		this.genHtmlFile(rpt);
		this.creditReportService.save(rpt);
	}

	private void genHtmlFile(LoanCreditReport report) throws Throwable {
		this.writeHtmlFile(report, PagePosition.COVER);
		this.writeHtmlFile(report, PagePosition.BASIC_INFO);
		this.writeHtmlFile(report, PagePosition.FIELD_SURVY);
		this.writeHtmlFile(report, PagePosition.ROUTING_ISPECTION);
		this.writeHtmlFile(report, PagePosition.INSTALL);
		this.writeHtmlFile(report, PagePosition.TRANSFER);
		this.writeHtmlFile(report, PagePosition.OTHER_INFO);
		this.writeHtmlFile(report, PagePosition.MERCH_BUSI_DATA_VERIFICATION);
		this.writeHtmlFile(report, PagePosition.BACK_COVER);
	}

	private void writeHtmlFile(LoanCreditReport report, PagePosition position)
			throws Throwable {
		String filePath = null;
		FileOutputStream out = null;
		ReportHtmlFile file = null;
		if (position != PagePosition.BACK_COVER
				&& position != PagePosition.COVER) {
			filePath = LoanCreditReport.REPORT_PATH + "/"
					+ this.serialNoService.genFileNo() + ".html";
			out = new FileOutputStream(filePath);
			file = this.genReportHtmlFile(report, position,
					HtmlFileType.FOR_HTML);
			this.convertToHtml(report, position, out, false,
					HtmlFileType.FOR_HTML, true, null);
			file.setFilePath(filePath);
			report.addHtmlFile(file);
			file.setReport(report);
			out.flush();
			out.close();
		}
		filePath = LoanCreditReport.REPORT_PATH + "/"
				+ this.serialNoService.genFileNo() + ".html";
		out = new FileOutputStream(filePath);
		file = this.genReportHtmlFile(report, position, HtmlFileType.FOR_PDF);
		this.convertToHtml(report, position, out, false, HtmlFileType.FOR_PDF,
				true, null);
		file.setFilePath(filePath);
		report.addHtmlFile(file);
		file.setReport(report);
		out.flush();
		out.close();
	}

	private ReportHtmlFile genReportHtmlFile(LoanCreditReport report,
			PagePosition position, HtmlFileType type) {
		ReportHtmlFile file = new ReportHtmlFile();
		file.setGenTime(new Date());
		file.setPosition(position);
		file.setType(type);
		file.setReport(report);
		return file;
	}

	@Override
	public LoanCreditReport genCreditReport(Long imerch, DateRange dateRange) {
		// TODO 怎么查

		LoanCreditReport report = new LoanCreditReport();
		report.setBeginDate(dateRange.getBeginDate());
		if (dateRange.name().equals("PRE_MONTH")) {
			report.setEndDate(dateRange.getEndDate());
		} else if (dateRange.name().equals("PRE_THREE_MONTH")) {
			report.setEndDate(dateRange.PRE_MONTH.getEndDate());
		} else if (dateRange.name().equals("PRE_HALF_YEAR")) {
			report.setEndDate(dateRange.PRE_MONTH.getEndDate());
		}
		report.setImerch(imerch);
		report.setStatus(new Integer(1));
		report.setReportDate(new Date());
		// TODO 商户其他资料
		return queryCreditReport(report, dateRange);
	}

	/**
	 * 生成报表数据
	 * 
	 * @param report
	 * @return
	 */
	private LoanCreditReport queryCreditReport(LoanCreditReport report,
			DateRange dateRange) {
		Merch merch = queryMerch(report);
		// TODO 商户其他资料
		queryBusiInfo(report);
		// 交易记录
		// MerchTransReport transReport = this.queryTransReport(report);
		MerchTransReport transReport = this.queryTransReportByMonth(report,
				dateRange);

		// 现场调查
		queryMerchFieldSurvy(report);

		// 巡检记录
		queryInspectLog(report, merch);

		// 装机记录
		queryInstallLog(report, merch);

		// 查询其它资料
		/*
		 * List<TFile> list = this.queryOtherInfo(merch);
		 * report.setOtherInfo(list);
		 */
		return report;
	}

	private void queryInstallLog(LoanCreditReport report, Merch merch) {
		List<HcInstallLog> hcInstallLogList = this.queryService
				.queryInstallLog(merch.getMerchNo(), report.getBeginDate(),
						report.getEndDate());
		report.setHcInstallLog(hcInstallLogList);
	}

	private void queryInspectLog(LoanCreditReport report, Merch merch) {
		List<HcInspectLog> hcInspectLogList = this.queryService
				.queryInspectLog(merch.getMerchNo(), report.getBeginDate(),
						report.getEndDate());
		report.setHcInspectLog(hcInspectLogList);
	}

	private void queryMerchFieldSurvy(LoanCreditReport report) {
		MerchFieldSurvy merchFieldSurvy = this.merchService
				.getMerchFieldSurvyByImerch(report.getImerch());
		report.setFieldSurvy(merchFieldSurvy);
	}

	private void queryBusiInfo(LoanCreditReport report) {
		MerchBusiInfo busiInfo = this.merchService
				.getMerchBusiInfoByImerch(report.getImerch());
		report.setBusiInfo(busiInfo);
		MerchLegalPers legalPers = this.merchService
				.getMerchLegalPersByImerch(report.getImerch());
		report.setLegalPers(legalPers);
	}

	private Merch queryMerch(LoanCreditReport report) {
		Merch merch = this.merchService.findMerchById(report.getImerch());
		report.setBeginDate(report.getBeginDate());
		report.setEndDate(report.getEndDate());
		report.setMerchName(merch.getMerchName());
		report.setMerch(merch);
		return merch;
	}

	private List<TFile> queryOtherInfo(Merch merch) {
		return this.fileService.queryBy(merch.getImerch(), FileType.merchFile);
	}

	private MerchTransReport queryTransReportByMonth(LoanCreditReport report,
			DateRange dr) {
		MerchTransReport transReport = new MerchTransReport();
		int i = 0;
		if (dr.name().equals("PRE_MONTH")) {
			i = 1;
		} else if (dr.name().equals("PRE_THREE_MONTH")) {
			i = 3;
		} else if (dr.name().equals("PRE_HALF_YEAR")) {
			i = 6;
		}

		BigDecimal bankCardTotal = BigDecimal.ZERO;
		BigDecimal cashTotal = BigDecimal.ZERO;
		BigDecimal payTotal = BigDecimal.ZERO;
		BigDecimal loanTotal = BigDecimal.ZERO;
		BigDecimal refundTotal = BigDecimal.ZERO;
		BigDecimal amountTotal = BigDecimal.ZERO;

		Long bankCardTotalCount = new Long(0);
		Long cashTotalCount = new Long(0);
		Long payTotalCount = new Long(0);
		Long loanTotalCount = new Long(0);
		Long refundTotalCount = new Long(0);
		Long conutTotal = new Long(0);

		Double bankCardTotalAvg = new Double(0);
		Double cashTotalAvg = new Double(0);
		Double payTotalAvg = new Double(0);
		Double loanTotalAvg = new Double(0);
		Double refundTotalAvg = new Double(0);
		Double dailyAvgTotal = new Double(0);

		BigDecimal bankCardTotalMonth = BigDecimal.ZERO;
		BigDecimal cashTotalMonth = BigDecimal.ZERO;
		BigDecimal payTotalMonth = BigDecimal.ZERO;
		BigDecimal loanTotalMonth = BigDecimal.ZERO;
		BigDecimal refundTotalMonth = BigDecimal.ZERO;
		BigDecimal monthTotal = BigDecimal.ZERO;
		DateRange dateRange = DateRange.PRE_MONTH;
		int days = 0;
		for (int j = 1; j <= i; j++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
			if (j == 1) {
				dateRange = DateRange.PRE_MONTH;
			} else if (j == 2) {
				dateRange = DateRange.PRE_TWO_MONTH;
			} else if (j == 3) {
				dateRange = DateRange.PRE_THREE_MONTH;
			} else if (j == 4) {
				dateRange = DateRange.PRE_FOUR_MONTH;
			} else if (j == 5) {
				dateRange = DateRange.PRE_FIVE_MONTH;
			} else if (j == 6) {
				dateRange = DateRange.PRE_HALF_YEAR;
			}
			TransRec transRec = new TransRec();
			days+=DateUtils.compareDays(IafApplication.roundToBeginDate(dateRange.getBeginDate()),IafApplication.roundToEndDate(dateRange.getEndDate()));
			transRec.setDate(sdf.format(dateRange.getBeginDate()));

			// 收款流水
			TransReport tr = this.queryService.queryReciveTrans(report.getMerch()
					.getMerchNo(), IafApplication.roundToBeginDate(dateRange
					.getBeginDate()), IafApplication.roundToEndDate(dateRange
					.getEndDate()));
			transRec.setBankCardTrans(tr.getAmount());
			transRec.setBankCardCount(tr.getTransCount());
			transRec.setBankCardAvg(tr.getDailyAvgAmount());

			bankCardTotal = bankCardTotal.add(tr.getAmount()).setScale(2);
			// cashTotal = cashTotal.add(tr.getAmount()).setScale(2);
			bankCardTotalCount = bankCardTotalCount + tr.getTransCount();
			// cashTotalCount=cashTotalCount+tr.getTransCount();
			bankCardTotalAvg += tr.getDailyAvgAmount();

			// 付款流水
			tr = this.queryService.queryPayTrans(report.getMerch()
					.getMerchNo(), IafApplication.roundToBeginDate(dateRange
					.getBeginDate()), IafApplication.roundToEndDate(dateRange
					.getEndDate()));
			transRec.setPayTrans(tr.getAmount());
			transRec.setPayCount(tr.getTransCount());
			transRec.setPayAvg(tr.getDailyAvgAmount());

			payTotal = payTotal.add(tr.getAmount()).setScale(2);
			payTotalAvg += tr.getDailyAvgAmount();
			payTotalCount = payTotalCount + tr.getTransCount();

			// 平台内贷款流水
			tr = this.queryService.queryLoanTrans(
					report.getMerch().getImerch(),
					IafApplication.roundToBeginDate(dateRange.getBeginDate()),
					IafApplication.roundToEndDate(dateRange.getEndDate()));
			transRec.setLoanTrans(tr.getAmount());
			transRec.setLoanCount(tr.getTransCount());
			transRec.setLoanAvg(tr.getDailyAvgAmount());

			loanTotal = loanTotal.add(tr.getAmount()).setScale(2);
			loanTotalAvg += tr.getDailyAvgAmount();
			loanTotalCount = loanTotalCount + tr.getTransCount();

			// 平台内还款流水
			tr = this.queryService.queryRefundTrans(report.getMerch()
					.getImerch(), IafApplication.roundToBeginDate(dateRange
					.getBeginDate()), IafApplication.roundToEndDate(dateRange
					.getEndDate()));
			transRec.setRefundTrans(tr.getAmount());
			transRec.setRefundCount(tr.getTransCount());
			transRec.setRefundAvg(tr.getDailyAvgAmount());

			refundTotal = refundTotal.add(tr.getAmount()).setScale(2);
			refundTotalAvg += tr.getDailyAvgAmount();
			refundTotalCount = refundTotalCount + tr.getTransCount();

			transReport.getTrList().add(transRec);
		}
		// 月均统计
		bankCardTotalMonth = bankCardTotal.divide(new BigDecimal(i), 2);
		payTotalMonth = payTotal.divide(new BigDecimal(i), 2);
		loanTotalMonth = loanTotal.divide(new BigDecimal(i), 2);
		refundTotalMonth = refundTotal.divide(new BigDecimal(i), 2);
		monthTotal = bankCardTotalMonth.add(cashTotalMonth);

		// 统计
		TransRec transRecTotal = new TransRec();

		transRecTotal.setBankCardTotal(bankCardTotal);
		transRecTotal.setCashTotal(cashTotal);
		transRecTotal.setPayTotal(payTotal);
		transRecTotal.setLoanTotal(loanTotal);
		transRecTotal.setRefundTotal(refundTotal);
		transRecTotal.setAmountTotal(bankCardTotal.add(cashTotal));

		transRecTotal.setBankCardTotalCount(bankCardTotalCount);
		transRecTotal.setCashTotalCount(cashTotalCount);
		transRecTotal.setPayTotalCount(payTotalCount);
		transRecTotal.setLoanTotalCount(loanTotalCount);
		transRecTotal.setRefundTotalCount(refundTotalCount);
		transRecTotal.setConutTotal(bankCardTotalCount + cashTotalCount);

		BigDecimal resultBank = bankCardTotal.divide(new BigDecimal(days),2,BigDecimal.ROUND_HALF_UP);
		
		BigDecimal resultPay = payTotal.divide(new BigDecimal(days),2,BigDecimal.ROUND_HALF_UP);
		
		BigDecimal resultLoan =loanTotal.divide(new BigDecimal(days),2,BigDecimal.ROUND_HALF_UP);
		
		BigDecimal resultRefund =refundTotal.divide(new BigDecimal(days),2,BigDecimal.ROUND_HALF_UP);
		
		transRecTotal.setBankCardTotalAvg(resultBank.doubleValue());
		transRecTotal.setCashTotalAvg(cashTotalAvg);
		transRecTotal.setPayTotalAvg(resultPay.doubleValue());
		transRecTotal.setLoanTotalAvg(resultLoan.doubleValue());
		transRecTotal.setRefundTotalAvg(resultRefund.doubleValue());
		transRecTotal.setDailyAvgTotal(resultBank.doubleValue());

		transRecTotal.setBankCardTotalMonth(bankCardTotalMonth);
		transRecTotal.setCashTotalMonth(cashTotalMonth);
		transRecTotal.setPayTotalMonth(payTotalMonth);
		transRecTotal.setLoanTotalMonth(loanTotalMonth);
		transRecTotal.setRefundTotalMonth(refundTotalMonth);
		transRecTotal.setMonthTotal(monthTotal);
		
		transReport.setTrTotal(transRecTotal);
		
		BigDecimal avgDayTrade=BigDecimal.ZERO;
		BigDecimal avgDayNum=BigDecimal.ZERO;
		BigDecimal avgMonthTrade =BigDecimal.ZERO;
		BigDecimal avgMonthNum =BigDecimal.ZERO;
		BigDecimal avgTrade=BigDecimal.ZERO;
		
		Merch mer = merchService.findMerchById(report.getImerch());
		if(mer!=null){
			if(transRecTotal.getConutTotal()!=0){
				avgTrade= transRecTotal.getAmountTotal().divide(new BigDecimal(transRecTotal.getConutTotal()),2,BigDecimal.ROUND_HALF_UP);
			}
			avgDayTrade=transRecTotal.getAmountTotal().divide(new BigDecimal(days),2, BigDecimal.ROUND_HALF_UP);
			avgDayNum=new BigDecimal(transRecTotal.getConutTotal()).divide(new BigDecimal(days),2, BigDecimal.ROUND_HALF_UP);
			avgMonthTrade = transRecTotal.getAmountTotal().divide(new BigDecimal(i),2, BigDecimal.ROUND_HALF_UP);
			avgMonthNum = new BigDecimal(transRecTotal.getConutTotal()).divide(new BigDecimal(i),2, BigDecimal.ROUND_HALF_UP);
		}
		
		transReport.setAvgTrade(avgTrade);
		transReport.setAvgDayTrade(avgDayTrade);
		transReport.setAvgDayNum(avgDayNum);
		transReport.setAvgMonthNum(avgMonthNum);
		transReport.setAvgMonthTrade(avgMonthTrade);
		
		TransReport trt = this.queryService.queryReciveTrans(report.getMerch()
				.getMerchNo(), IafApplication.roundToBeginDate(DateRange.HALF_YEAR
				.getBeginDate()), IafApplication.roundToEndDate(DateRange.HALF_YEAR
				.getEndDate()));
		transReport.setPreHalfYearMonthRecive(trt);
		
		Collections.reverse(transReport.getTrList());
		report.setTransReport(transReport);
		return transReport;
	}

	private MerchTransReport queryTransReport(LoanCreditReport report) {
		MerchTransReport transReport = new MerchTransReport();
		// 查询支付记录
		TransReport tr = this.queryService
				.queryPayTrans(report.getMerch().getMerchNo(), IafApplication
						.roundToBeginDate(DateRange.PRE_MONTH.getBeginDate()),
						IafApplication.roundToEndDate(DateRange.PRE_MONTH
								.getEndDate()));
		transReport.setPreMonthPayment(tr);

		tr = this.queryService
				.queryPayTrans(report.getMerch().getMerchNo(), IafApplication
						.roundToBeginDate(DateRange.PRE_THREE_MONTH
								.getBeginDate()), IafApplication
						.roundToEndDate(DateRange.PRE_THREE_MONTH.getEndDate()));
		transReport.setPreThreeMonthPayment(tr);

		tr = this.queryService.queryPayTrans(report.getMerch().getMerchNo(),
				IafApplication.roundToBeginDate(DateRange.PRE_HALF_YEAR
						.getBeginDate()), IafApplication
						.roundToEndDate(DateRange.PRE_HALF_YEAR.getEndDate()));
		transReport.setPreHalfYearMonthPayment(tr);
		/**
		 * 查询收款记录
		 */
		tr = this.queryService.queryReciveTrans(report.getMerch().getMerchNo(),
				IafApplication.roundToBeginDate(DateRange.PRE_MONTH
						.getBeginDate()), IafApplication
						.roundToEndDate(DateRange.PRE_MONTH.getEndDate()));
		transReport.setPreMonthRecive(tr);

		tr = this.queryService
				.queryReciveTrans(report.getMerch().getMerchNo(),
						IafApplication
								.roundToBeginDate(DateRange.PRE_THREE_MONTH
										.getBeginDate()), IafApplication
								.roundToEndDate(DateRange.PRE_THREE_MONTH
										.getEndDate()));
		transReport.setPreThreeMonthRecive(tr);

		tr = this.queryService.queryReciveTrans(report.getMerch().getMerchNo(),
				IafApplication.roundToBeginDate(DateRange.PRE_HALF_YEAR
						.getBeginDate()), IafApplication
						.roundToEndDate(DateRange.PRE_HALF_YEAR.getEndDate()));
		transReport.setPreHalfYearMonthRecive(tr);

		// 查询贷款记录
		tr = this.queryService.queryLoanTrans(report.getMerch().getImerch(),
				IafApplication.roundToBeginDate(DateRange.PRE_MONTH
						.getBeginDate()), IafApplication
						.roundToEndDate(DateRange.PRE_MONTH.getEndDate()));
		transReport.setPreMonthLoan(tr);

		tr = this.queryService
				.queryLoanTrans(report.getMerch().getImerch(), IafApplication
						.roundToBeginDate(DateRange.PRE_THREE_MONTH
								.getBeginDate()), IafApplication
						.roundToEndDate(DateRange.PRE_THREE_MONTH.getEndDate()));
		transReport.setPreThreeMonthLoan(tr);

		tr = this.queryService.queryLoanTrans(report.getMerch().getImerch(),
				IafApplication.roundToBeginDate(DateRange.PRE_HALF_YEAR
						.getBeginDate()), IafApplication
						.roundToEndDate(DateRange.PRE_HALF_YEAR.getEndDate()));
		transReport.setPreHalfYearMonthLoan(tr);

		// 查询还款记录
		tr = this.queryService.queryRefundTrans(report.getMerch().getImerch(),
				IafApplication.roundToBeginDate(DateRange.PRE_MONTH
						.getBeginDate()), IafApplication
						.roundToEndDate(DateRange.PRE_MONTH.getEndDate()));
		transReport.setPreMonthRefund(tr);

		tr = this.queryService
				.queryRefundTrans(report.getMerch().getImerch(), IafApplication
						.roundToBeginDate(DateRange.PRE_THREE_MONTH
								.getBeginDate()), IafApplication
						.roundToEndDate(DateRange.PRE_THREE_MONTH.getEndDate()));
		transReport.setPreThreeMonthRefund(tr);

		tr = this.queryService.queryRefundTrans(report.getMerch().getImerch(),
				IafApplication.roundToBeginDate(DateRange.PRE_HALF_YEAR
						.getBeginDate()), IafApplication
						.roundToEndDate(DateRange.PRE_HALF_YEAR.getEndDate()));
		transReport.setPreHalfYearMonthRefund(tr);

		report.setTransReport(transReport);
		return transReport;

	}

	@Override
	public File convertToPdf(LoanCreditReport report, String baseUrl,
			boolean wrapper, DateRange dateRange) throws Throwable {
		List<MerchInfoPermission> list = this.queryInfoPermission(report
				.getMerch());
		boolean allow = false;
		if (wrapper) {
			if (CollectionUtils.isEmpty(list)) {
				throw new RuntimeException("商户未设置信息公开");
			} else {
				for (MerchInfoPermission perm : list) {
					if (perm.getAcceptType().isAllow()) {
						allow = true;
						break;
					}
				}
			}
		}
		if (wrapper && allow == false) {
			throw new RuntimeException("商户未设置信息公开");
		}
		File path = new File(LoanCreditReport.REPORT_PATH);
		File files[] = new File[9];
		files[0] = this.getPdfFile(path, PagePosition.COVER, report, baseUrl,
				false);
		files[1] = this.getPdfFile(path, PagePosition.BASIC_INFO, report,
				baseUrl, wrapper);
		files[2] = this.getPdfFile(path, PagePosition.FIELD_SURVY, report,
				baseUrl, wrapper);
		files[3] = this.getPdfFile(path, PagePosition.TRANSFER, report,
				baseUrl, wrapper);
		files[4] = this.getPdfFile(path, PagePosition.ROUTING_ISPECTION,
				report, baseUrl, wrapper);
		files[5] = this.getPdfFile(path, PagePosition.INSTALL, report, baseUrl,
				wrapper);
		files[6] = this.getPdfFile(path, PagePosition.OTHER_INFO, report,
				baseUrl, wrapper);
		files[7] = this.getPdfFile(path, PagePosition.MERCH_BUSI_DATA_VERIFICATION, report,
				baseUrl, false);
		files[8] = this.getPdfFile(path, PagePosition.BACK_COVER, report,
				baseUrl, false);
		//添加商户经营数据审核情况的pdf文件
		File file = this.mergePdfFiles(files, report.getReportId() + ".pdf",
				LoanCreditReport.REPORT_PATH);
		report.setPdfFileName(file.getAbsolutePath());
		for (File temp : files) {
			if (temp != null && temp.exists()) {
				temp.delete();
			}
		}
		return file;
	}

	private File mergePdfFiles(File[] files, String newfile, String path) {
		File file = new File(path + "/" + newfile);
		com.itextpdf.text.Document document = null;
		try {
			document = new com.itextpdf.text.Document(new PdfReader(
					files[0].getAbsolutePath()).getPageSize(1));
			PdfCopy copy = new PdfCopy(document, new FileOutputStream(file));
			document.open();
			for (int i = 0; i < files.length; i++) {
				if (files[i] != null) {
					PdfReader reader = new PdfReader(files[i].getAbsolutePath());
					String title = PagePosition.values()[i].getDesc();
					int n = reader.getNumberOfPages();
					for (int j = 1; j <= n; j++) {
						document.newPage();
						PdfImportedPage page = copy.getImportedPage(reader, j);
						copy.addPage(page);
					}
					copy.flush();
				}

			}

		} catch (Exception e) {
			logger.error("合并文件失败", e);
		} finally {
			if (document != null)
				document.close();
			for (File f : files) {
				if (f != null)
					f.delete();
			}
		}
		return file;
	}

	private ITextRenderer build() throws Throwable {
		ITextRenderer renderer = new ITextRenderer();
		ITextFontResolver resolver = renderer.getFontResolver();
		//renderer.getSharedContext().setBaseURL(IafApplication.getApplicationPath());
		resolver.addFont(pdfFontFactory, BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		return renderer;
	}

	private File createPdf(ITextRenderer renderer, String content, File path,
			String baseUrl) throws Throwable {
		FileOutputStream fileout = null;
		try {
			if (StringUtils.isBlank(baseUrl)) {
				renderer.setDocumentFromString(content);
			} else {
				renderer.setDocumentFromString(content, baseUrl);
			}
			renderer.layout();
			File file = File.createTempFile("TEMP_", ".pdf", path);
			fileout = new FileOutputStream(file);
			renderer.createPDF(fileout);
			logger.debug("文件生成成功！");
			return file;
		} finally {
			if (fileout != null) {
				fileout.close();
			}
		}
	}

	private File getPdfFile(File path, PagePosition position,
			LoanCreditReport report, String baseUrl, boolean wrapper)
			throws Throwable {
		try {
			AcceptType type = AcceptType.ALLOW;
			if (wrapper) {
				type = this.queryAcceptType(report.getMerch(), position);
			}
			if (type == AcceptType.ALLOW) {
				ReportTemplate rt = this.reportTemplateService.queryBy(
						position, HtmlFileType.FOR_PDF);
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				logger.debug("生成pdf文件：" + rt.getFileName());
				this.convertToHtml(report, position, bout, false,
						HtmlFileType.FOR_PDF, false, null);
				ITextRenderer renderer = this.build();

				File file = this.createPdf(renderer, bout.toString(), path,
						baseUrl);
				return file;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("生成Pdf出错", e);
			throw new RuntimeException("生成PDF出错", e);
		}
	}

	@Override
	public boolean convertToHtml(LoanCreditReport report, PagePosition position,
			OutputStream out, boolean wrapper, HtmlFileType fileType,
			boolean gen,InstType instType)
			throws Throwable {
		if (report.getMerch() == null) {
			Merch merch = this.merchService.findMerchById(report.getImerch());
			report.setMerch(merch);
		}
		AcceptType type = AcceptType.ALLOW;
		if (wrapper) {
			type = this.queryAcceptType(report.getMerch(), position);
		}
		
		String viewType = "";
		if(instType==InstType.MERCH){
			viewType="MERCH";
		}else if(instType==InstType.INST){
			viewType="INST";
		}else if(instType==InstType.HICARD){
			viewType="HICARD";
		}
		
		if (type == AcceptType.ALLOW) {
			ReportHtmlFile file = report.getHtmlFile(position, fileType);
			
			if (gen || file == null) {
				ReportTemplate rt = this.reportTemplateService.queryBy(position, fileType);
				Map<String, Object> root = new HashMap<String, Object>();
				if (fileType == HtmlFileType.FOR_PDF) root.put("pdf", "true");
				switch (position) {
				case COVER:
					root.put("merchName", report.getMerchName());
					root.put("merchId", report.getMerch().getMerchNo());
					root.put("reportId", report.getReportId());
					root.put("reportDate", report.getReportDate());
					root.put("beginDate", report.getBeginDate());
					root.put("endDate", report.getEndDate());
					break;
				case BASIC_INFO:
					String industry="";
					if(StringUtils.isNotBlank(report.getMerch().getIndustry())){
						Pattern pattern = Pattern.compile("[0-9]*");
						if(pattern.matcher(report.getMerch().getIndustry()).matches()){
							industry=mccService.queryById(report.getMerch().getIndustry()).getName();
						}
					}
					root.put("industry", industry+"("+report.getMerch().getIndustry()+")");
					root.put("merch", report.getMerch());
					root.put("legalPers", (report.getLegalPers() == null)?new MerchLegalPers():report.getLegalPers());
					root.put("reportDate", report.getReportDate());
					root.put("busiInfo", (report.getBusiInfo() == null)?new MerchBusiInfo():report.getBusiInfo());
					root.put("transReport", report.getTransReport());
					break;
				case FIELD_SURVY:
					root.put("fieldSurvy", (report.getFieldSurvy() == null)?new MerchFieldSurvy():report.getFieldSurvy());
					root.put("reportDate", report.getReportDate());
					root.put("merch", report.getMerch());
					root.put("viewType", viewType);
					break;
				case TRANSFER:
					root.put("avgTrade",report.getTransReport().getAvgTrade() );
					root.put("avgDayTrade",report.getTransReport().getAvgDayTrade());
					root.put("avgDayNum", report.getTransReport().getAvgDayNum());
					root.put("avgMonthTrade", report.getTransReport().getAvgMonthTrade());
					root.put("avgMonthNum",report.getTransReport().getAvgMonthNum());
					String busTime="0小时/日";
					if(report.getFieldSurvy()!=null){
						String start [] ={};
						String end [] ={};
						String startHour="";
						String startSec="";
						String endHour="";
						String endSec="";
						String BussinessHours []={};
					if(StringUtils.isNotBlank(report.getFieldSurvy().getManageBussinessHours())){
						try {
							BussinessHours= report.getFieldSurvy().getManageBussinessHours().split("--");
							if(BussinessHours.length>=0){
								start = BussinessHours[0].split(":");
									if(BussinessHours.length>=1){
										end=BussinessHours[1].split(":");
										if(end.length>=0){
											endHour=end[0];
											if(end.length>=1){
												endSec=end[1];
											}
										}
									}
									if(start.length>=0){
										startHour=start[0];
										if(start.length>=1){
											startSec=start[1];
										}
									}
								int endx= Integer.parseInt(endHour)*60+Integer.parseInt(endSec);
								int startx=Integer.parseInt(startHour)*60+Integer.parseInt(startSec);
								int res = endx -startx;
								if(res!=0&&res>0){
									Integer H= res/60;
									Integer S= res%60;
									busTime=H.toString()+"."+S.toString()+"小时/日";
								}
								}
						} catch (Exception e) {
							busTime="--";
							logger.error("[获取时间出错] ---"+BussinessHours.toString(), e);
						}
					}
				 }
					root.put("busTime", busTime);//ftl
					root.put("fieldSurvy", (report.getFieldSurvy() == null)?new MerchFieldSurvy():report.getFieldSurvy());
					root.put("transReport", report.getTransReport());
					root.put("trList", report.getTransReport().getTrList());
					root.put("trTotal", report.getTransReport().getTrTotal());
					root.put("reportDate", report.getReportDate());
					root.put("viewType", viewType);
					break;
				case ROUTING_ISPECTION:
					if (CollectionUtils.isEmpty(report.getHcInspectLog()) == false)
						root.put("inspectLog", report.getHcInspectLog());
					else
						root.put("inspectLog", new ArrayList());
//					if (CollectionUtils.isEmpty(report.getHcInstallLog()) == false)
//						//root.put("installLog", report.getHcInstallLog());
//					//else
//						root.put("installLog", new ArrayList());
					root.put("reportDate", report.getReportDate());
					root.put("viewType", viewType);
					break;
				case INSTALL:
					if (CollectionUtils.isEmpty(report.getHcInstallLog()) == false)
						root.put("installLog", report.getHcInstallLog());
						else
						root.put("installLog", new ArrayList());
					root.put("reportDate", report.getReportDate());
					root.put("viewType", viewType);
					break;
				case OTHER_INFO:
					//List<TFile> tFileList=this.fileService.queryBy(report.getImerch(), FileType.merchFile);
					List<TFile> merchFile_count=this.fileService.queryBy(report.getImerch(), FileType.merchFile);
					List<TFile> YYZZ_count=this.fileService.queryBy(report.getImerch(), FileType.YYZZ);
					List<TFile> SWDJZ_count=this.fileService.queryBy(report.getImerch(), FileType.SWDJZ);
					List<TFile> ZZJGDMZ_count=this.fileService.queryBy(report.getImerch(), FileType.ZZJGDMZ);
					List<TFile> FRSFZ_count=this.fileService.queryBy(report.getImerch(), FileType.FRSFZ);
					List<TFile> tFileList=this.fileService.queryByImerch(report.getImerch());
					report.setOtherInfo(tFileList);
					if (CollectionUtils.isEmpty(report.getOtherInfo()) == false)
						root.put("otherInfo", report.getOtherInfo());
					else
						root.put("otherInfo", new ArrayList());
//					if (fileType == HtmlFileType.FOR_PDF){
//						RiskMonitor risk = this.queryService.queryRisk(report.getMerch(), report.getEndDate());
//						root.put("risk", risk);
//					}
					root.put("reportDate", report.getReportDate());
					root.put("merchFile_count", merchFile_count.size());
					root.put("YYZZ_count", YYZZ_count.size());
					root.put("SWDJZ_count", SWDJZ_count.size());
					root.put("ZZJGDMZ_count", ZZJGDMZ_count.size());
					root.put("FRSFZ_count", FRSFZ_count.size());
					//root.put("merch", report.getMerch());
					Merch merch = merchService.findMerchById(report.getMerch().getImerch());
					root.put("memo", merch.getMemo()==null?" ":merch.getMemo());
					root.put("viewType", viewType);
					String action = "";
					if(viewType.equals("MERCH")){
						action="/merch/merchInfoUpload!addMemo";
					}else if(viewType.equals("HICARD")){
						action="/backstage/merchcreditreport/backMerchCredit!addMemo";
					}
					root.put("action",action);
					break;
				case MERCH_BUSI_DATA_VERIFICATION:
					List<TFile> tFileLists=this.fileService.queryByImerch(report.getImerch());
					report.setBusiVerifition(tFileLists);
					if(CollectionUtils.isEmpty(report.getBusiVerifition()) == false){
						root.put("busiVerifition", report.getBusiVerifition());
					}else{
						root.put("busiVerifition", new ArrayList());
					}
					Merch merchs = merchService.findMerchById(report.getMerch().getImerch());
					root.put("reportDate", report.getReportDate());
					root.put("verification", merchs.getBusiDataVerification()==null?" ":merchs.getBusiDataVerification());
					root.put("viewType", viewType);
					action = "";
					if(viewType.equals("HICARD")){
						action = "/backstage/merchcreditreport/backMerchCredit!addVerification";
					}else if(viewType.equals("INST")){
						action="/inst/loanord/merchCreditReport!addVerification";
					}
					root.put("action",action);
					break;
				case BACK_COVER:
				}
				this.reportTemplateService.renderTemplate(rt.getFileName(),
						root, out);
			} else {
				this.readHtmlFile(file.getFilePath(), out);
			}
			return true;
		} else {
			this.reportTemplateService.renderTemplate(
					ReportTemplateService.DEFAULT_DENY,
					new HashMap<String, Object>(), out);
			return false;
		}
	}

	private void readHtmlFile(String filePath, OutputStream out)
			throws Exception {
		FileInputStream in = new FileInputStream(filePath);
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = in.read(bytes)) > 0) {
			out.write(bytes, 0, len);
			out.flush();
		}
		try {
			in.close();
		} catch (Exception e) {
		}
	}

	@Override
	public void readPdf(LoanCreditReport report, OutputStream out)
			throws Throwable {
		File file = new File(report.getPdfFileName());
		if (file.exists() == false)
			throw new FileNotFoundException(file.getAbsolutePath());
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			byte[] bufs = new byte[1024];
			int len = 0;
			while ((len = fin.read(bufs)) > 0) {
				out.write(bufs, 0, len);
				out.flush();
			}
		} finally {
			if (fin != null)
				fin.close();
		}
	}

	@Override
	public List<LoanCreditReport> queryReoprtList(Long imerch, Page page) {
		CreditReportCondition cond = new CreditReportCondition();
		cond.setImerch(imerch);
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("ireport"));
		cond.setOrders(orderList);
		List<LoanCreditReport> list = this.creditReportService.queryBy(cond,
				page);
		return list;
	}

	@Override
	public void setInfoPermission(Merch merch, PagePosition position,
			AcceptType type) {
		MerchInfoPermission perm = this.query(merch, position);
		if (perm == null) {
			perm = new MerchInfoPermission();
			perm.setGenTime(new Date());
			perm.setImerch(merch.getImerch());
			perm.setPosition(position);
			perm.setAcceptType(type);
			this.merchInfoPermissionDao.save(perm);
			return;
		}
		perm.setAcceptType(type);
		this.merchInfoPermissionDao.update(perm);
	}

	@Override
	public List<MerchInfoPermission> queryInfoPermission(Merch merch) {
		MerchInfoPermission perm = new MerchInfoPermission();
		List<MerchInfoPermission> list = this.merchInfoPermissionDao.query(
				perm, merch);
		return list;
	}

	@Override
	public AcceptType queryAcceptType(Merch merch, PagePosition position) {
		MerchInfoPermission perm = this.query(merch, position);
		if (perm == null)
			return AcceptType.DENY;
		else
			return perm.getAcceptType();
	}

	private MerchInfoPermission query(Merch merch, PagePosition position) {
		MerchInfoPermission perm = new MerchInfoPermission();
		perm.setImerch(merch.getImerch());
		perm.setPosition(position);
		List<MerchInfoPermission> list = this.merchInfoPermissionDao.query(
				perm, merch);
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

	@Override
	public LoanCreditReport genCreditReport(LoanCreditReport report,
			DateRange dateRange) {
		return this.queryCreditReport(report, dateRange);

	}

	@Override
	public File createPdfFile(LoanCreditReport report, String baseUrl,
			boolean wrapper, DateRange dateRange) throws Throwable {
		this.genCreditReport(report, dateRange);
		File file = this.convertToPdf(report, baseUrl, wrapper, dateRange);
		report.setPdfFileName(file.getAbsolutePath());
		this.creditReportService.update(report);
		return file;
	}

	@Override
	public void deleteReport(LoanCreditReport report) throws Exception {
		Set<ReportHtmlFile> set = report.getReportFile();
		if (CollectionUtils.isEmpty(set) == false) {
			for (ReportHtmlFile html : set) {
				File file = new File(html.getFilePath());
				if (file.exists())
					file.delete();
			}
		}
		if (StringUtils.isNotBlank(report.getPdfFileName())) {
			File file = new File(report.getPdfFileName());
			if (file.exists())
				file.delete();
		}
		this.creditReportService.delete(report);
	}
}
