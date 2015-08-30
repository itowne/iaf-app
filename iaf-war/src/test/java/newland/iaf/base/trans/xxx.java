package newland.iaf.base.trans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.csvreader.CsvReader;

/**
 * 
 * @author rabbit
 * 
 */
public class xxx {
	public static void main(String[] args) throws IOException {
		InputStream in = xxx.class.getClassLoader().getResourceAsStream(
				"行政区划代码.csv");
		File file = new File("xxx.sql");
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "GB18030");
		CsvReader cr = new CsvReader(in, Charset.forName("GB18030"));
		while (cr.readRecord()) {
			String code = cr.get(0);
			String name = cr.get(1);
			String pre = preCode(code);
			String sql = "insert into t_province(prov_code, pre_prov_code, name, close_flag) value('"
					+ code + "', '" + pre + "', '" + name + "', true );";
			osw.write(sql);
			osw.write("\n");
		}
		IOUtils.closeQuietly(osw);

	}

	public static String preCode(String code) {
		if (!code.substring(4).equals("00")) {
			return code.substring(0, 4) + "00";
		}

		if (!code.substring(2).equals("0000")) {
			return code.substring(0, 2) + "0000";
		}
		return "000000";
	}
}
