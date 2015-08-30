package newland.iaf.utils.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import newland.iaf.utils.service.IdentifierGenerator;
import newland.iaf.utils.service.PrefixIdGenerator;
import newland.iaf.utils.service.SerialNoService;

import org.springframework.stereotype.Service;
@Service("serialNoService")
public class SerialNoServiceImpl implements SerialNoService {
	@Resource (name = "instNoGenerator")
	private PrefixIdGenerator instgen;
	@Resource (name = "systemNoGenerator")
	private IdentifierGenerator systemgen;
	@Resource (name = "fileNoGenerator")
	private PrefixIdGenerator filegen;
	@Resource (name = "instNoGenerator")
	private PrefixIdGenerator prodgen;
	@Resource(name = "loanOrdNoGenerator")
	private IdentifierGenerator ordgen;
	@Resource(name = "planNoGenerator")
	private IdentifierGenerator plangen;
	@Resource(name = "transLogNoGenerator")
	private TransLogNoGenerator transloggen;
	@Resource(name = "fundFlowNoGenerator")
	private FundFlowNoGenerator fundflowgen;

	@Override
	public String genSystemNo() {
		return this.systemgen.generate();
	}

	@Override
	public String genInstNo() {
		return this.instgen.generate("T");
	}

	@Override
	public String genInstProdNo() {
		return this.prodgen.generate("P");
	}

	@Override
	public String genFileNo() {
		return this.filegen.generate("");
	}

	@Override
	public String genOrdNo() {
		return this.ordgen.generate();
	}

	@Override
	public String genPlanNo() {
		return this.plangen.generate();
	}

	@Override
	public String genMerchNo() {
		return this.instgen.generate("M");
	}

	@Override
	public String genMerchDebitDibNo() {
		return this.prodgen.generate("D");
	}
	@Override
	public String genFundFlowNo(){
		return this.fundflowgen.generate();
	}
	@Override
	public String genTransLogNo(){
		return this.transloggen.generate();
	}

	@Override
	public String genNewDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}

}
