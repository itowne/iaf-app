package newland.iaf.utils.service;

/**
 * 允许根据外部传入一个类似前缀身成具体流水
 * <p>
 * 不强制前缀的使用方式，但一定是追加前缀生成。
 * 
 * @author lance
 */
public interface PrefixIdGenerator extends IdentifierGenerator {

	public String generate(String prefix);

}
