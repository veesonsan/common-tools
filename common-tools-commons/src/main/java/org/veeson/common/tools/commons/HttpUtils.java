/**
 * 
 */
package org.veeson.common.tools.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 覃禹顺
 * @createTime 2016年2月14日下午4:37:47
 */
public class HttpUtils{
	public static final String CHARSET = "UTF-8";

	public static String getGoogleHosts(){
		String lineSeparator = System.getProperty("line.separator");
		String hostsString = "127.0.0.1 localhost" + lineSeparator;
		hostsString += "127.0.0.1 l.qq.com" + lineSeparator;
		hostsString += "127.0.0.1 ra.gtimg.com" + lineSeparator;
		hostsString += "127.0.0.1 livec.l.qq.com" + lineSeparator;
		hostsString += "127.0.0.1 atm.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 Fvid.atm.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 html.atm.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 valb.atm.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 valf.atm.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 valo.atm.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 valp.atm.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 lstat.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 speed.lstat.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 urchin.lstat.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 stat.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 static.lstat.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 valc.atm.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 vid.atm.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 walp.atm.youku.com" + lineSeparator;
		hostsString += "127.0.0.1 afp.qiyi.com" + lineSeparator;
		hostsString += "127.0.0.1 focusbaiduafp.allyes.com" + lineSeparator;
		hostsString += "127.0.0.1 dcads.sina.com.cn" + lineSeparator;
		hostsString += "127.0.0.1 pp2.pptv.com" + lineSeparator;
		hostsString += "127.0.0.1 pro.letv.com" + lineSeparator;
		hostsString += "127.0.0.1 images.sohu.com" + lineSeparator;
		Pattern pattern = Pattern.compile("[a-zA-z]+://[^\\s]*txt");
		String result = get("http://zeus.softweek.net/item-slt-1.html");
		if (result != null) {
			Matcher matcher = pattern.matcher(result);
			if (matcher.find()) {
				String url = matcher.group();
				String hosts = get(url);
				if (hosts != null && hosts.contains("www.google.com")) {
					hostsString += hosts;
				}
			}
		}
		return  hostsString;
	}
	public static void saveAsFileWriter(String content, File file) {
		FileWriter fwriter = null;
		try {
			fwriter = new FileWriter(file);
			fwriter.write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fwriter.flush();
				fwriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static String get(String url) {
		HttpURLConnection con = null;
		OutputStream osw = null;
		InputStream ins = null;
		try {
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");
			int resCode = con.getResponseCode();
			if (resCode < 400) {
				ins = con.getInputStream();
			} else {
				ins = con.getErrorStream();
			}
			return readContent(ins);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (osw != null) {
					osw.close();
				}
				if (ins != null) {
					ins.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static final String readContent(InputStream ins) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(ins, CHARSET));
		if (ins != null) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append(System.getProperties().getProperty("line.separator"));
			}
		}
		return sb.toString();
	}
}
