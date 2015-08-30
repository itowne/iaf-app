package newland.iaf.utils.dao;


import newland.iaf.utils.model.SequenceRegister;

public interface SequenceRegisterDao {
	
	public 	void lock(Integer snoId);
	
	public SequenceRegister findBySnoType(String snoType);

	public SequenceRegister save(SequenceRegister register);

	public void update(SequenceRegister register);

	public SequenceRegister findById(Integer snoId);
	
	
}
