package newland.base.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HTMLParser {
	
	Document document;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public HTMLParser(String str, String encoding) throws IOException {
		InputStream in = IOUtils.toInputStream(str, encoding);
		this.init(in);
	}

	public HTMLParser(InputStream in) throws IOException {
		this.init(in);
	}
	
	public HTMLParser(InputStream in, String encoding, boolean debug) throws IOException{
		if (debug){
			String html = IOUtils.toString(in, encoding);
			System.err.print(html);
			InputStream sin = IOUtils.toInputStream(html, encoding);
			this.init(sin);
		}else{
			this.init(in);
		}
	}

	public void init(InputStream in) throws IOException {
		document = Jsoup.parse(in, "gbk", "");
	}

	public Element getElementById(String xpath) throws Throwable {
		return document.getElementById(xpath);
	}
	
	public Map<String, String> getElementByTag(String tagName) throws Throwable{
		Elements els  = document.getElementsByTag(tagName);
		Map<String, String> map =  new HashMap<String, String>(); 
		if (els  == null) return map;
		for (Iterator<Element> it = els.iterator();it.hasNext();){
			Element el = it.next();
			if (el != null){
				map.put(el.attr("name"), el.attr("value"));
			}
		}
		return map;
	}
	
	public static void main(String[] args)throws Throwable{
		FileInputStream in = new FileInputStream("d:/tmp/callback.html");
		HTMLParser parser = new HTMLParser(in,"gbk",true);
		Map<String, String> map = parser.getElementByTag("input");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.err.print(entry.getKey() + "=" + entry.getValue());
		}
	}

}
