package newland.iaf.utils.service;

import newland.iaf.utils.model.SequenceRegister;

/**
 * 基于顺序的序列号生成器
 * @author lance
 *
 */
public abstract class SeqIdGenerator extends AbstractIdGenerator{

	@Override
	protected Integer generateSequence(SequenceRegister register) {
		return getThenAdd(register) + 1;
	}
}
