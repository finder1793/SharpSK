package me.sharpjaws.sharpSK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
public class Updater {
	public static String ucheck() {
		String result = null;
		try {
			URL url = new URL("https://raw.githubusercontent.com/Sharpjaws/SharpSK/master/Version.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String v = in.readLine();
			in.close();
			result = v;
		}
		catch (MalformedURLException e) {
			return result;
		}
		catch (IOException e) {
			return result;
		}
		return result;
	}

}
