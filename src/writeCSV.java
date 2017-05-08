import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class writeCSV {
	public static String input = "/Users/mislam/Desktop/new/out/part-r-00000";
	public static void main(String[] args) throws IOException {
		File in =new File(input);
		BufferedReader bf = new BufferedReader(new FileReader(in));
		int count =0;
		PrintWriter pw =new PrintWriter(new FileWriter(new File("story.csv")));
		pw.println("County, Latitude, Longitude, roadtmpc,pcpn, srad, snwd, drct, time , speed, total");
		while(true)
		{
			String line =bf.readLine();
			
			line =line.substring(6);
			String [] strs = line.split("\\]\\[");
			line= Arrays.asList(strs).toString();
			line =line.replaceAll("\\[", "");
			line =line.replaceAll("\\]", "");
			String [] ls = line.split("\\s+=\\s+");
			line =ls[0];
			int total = Integer.parseInt(ls[1]);
			String [] fields =line.split(",\\s+");
			String county = fields[0];
			double lat = Double.parseDouble(fields[1]);
			double lon = Double.parseDouble(fields[2]);
			double roadtmpc = Double.parseDouble(fields[3]);
			double pcpn = Double.parseDouble(fields[4]);
			double srad = Double.parseDouble(fields[5]);
			double snwd = Double.parseDouble(fields[6]);
			double drct = Double.parseDouble(fields[7]);
			long time = Long.parseLong(fields[8]);
			int speed= Integer.parseInt(fields[9]);
			String timestr= convertToDate(time);
			//System.out.println(timestr);
			pw.println(county+ ","+lat+","+lon+","+roadtmpc+","+pcpn+","+ srad+","+ snwd+","+ drct+","+ timestr+","+speed+","+total);;
			count++;
			if(count == -10)
			{
				break;
			}
			System.out.println(count);
		}
	}
	
	public static String convertToDate(long time)
	{
		time =time /1000000;
		Date date =new Date(time);
		Format format = new SimpleDateFormat("MM-dd-yyyy'T'HH:mm:ss'Z'");
		return format.format(date);
	}
}
