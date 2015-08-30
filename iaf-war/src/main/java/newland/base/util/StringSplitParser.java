package newland.base.util;

import java.util.ArrayList;
import java.util.List;

public class StringSplitParser {
	
	private static String DEFAULT_SPLIT = ",";
	
	private String source;
	
	private List<String> list = new ArrayList<String>();
	
	private String splitStr;
	
	public StringSplitParser(){
		this("", DEFAULT_SPLIT);
	}
	
	public StringSplitParser(String split){
		this.splitStr = split;
	}
	
	public StringSplitParser(String str, String splitStr){
		this.splitStr = splitStr;
		this.source = (str == null)?"":str;
		genValue();
	}

	private void genValue() {
		String[] strs = this.source.split(splitStr);
		if (strs == null || strs.length == 0) return;
		for (String str: strs){
			list.add(str);
		}
	}
	
	public List<String> getValueList(){
		return this.list;
	}
	
	public void append(String str){
		if (str == null) str = "";
		list.add(str);
	}
	
	public void append(String str, int index){
		if (str == null) str = "";
		if (list.size() < index + 1){
			int size = list.size();
			for (int i = 0 ; i < index - size + 1  ; i++){
				list.add("");
			}
		}
		this.list.set(index, str);
	}
	
	public String toString(){
		if (StringUtils.isBlank(this.source)){
			source = "";
			int index = 0;
			for (String str: list) {
				index++;
				source += str + ((index + 1 > list.size())?"":this.splitStr);
			}
		}
		return source;
	}

	public String getSplitStr() {
		return splitStr;
	}

	public void setSplitStr(String splitStr) {
		this.splitStr = splitStr;
	}
	
	public static void main(String[] args){
		StringSplitParser parser = new StringSplitParser(",");
		
		parser.append("0384878734", 0);
		parser.append("testsetes", 1);
		parser.append("9999", 2);
		parser.append("test bank", 3);
		parser.append("38839847", 4);
		System.err.println(parser.toString());
		parser = new StringSplitParser("0384878734,testsetes,9999,test bank,38839847",",");
		System.err.println(parser.getValueList().toString());
		for (String str: parser.getValueList()){
			System.err.println(str);
		}
	}

}
