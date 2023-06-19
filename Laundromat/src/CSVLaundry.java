import java.io.*;
//import java.io.charset.StandardCharsets;
import java.util.*;
import com.opencsv.*;
import java.text.SimpleDateFormat;
//import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
//import java.util.Scanner;



public class CSVLaundry {
		
	CSVLaundry(){
		}
		static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		public ArrayList<Laundry> getLaundryList() throws IOException{
			ArrayList<Laundry> laundry_data = new ArrayList<Laundry>();
			try{
				//CSVReader csvReader = new CSVReader(new FileReader("Data\\Laundry.csv"));
				Scanner sc = new Scanner(new FileReader("groupproject\\Laundromat\\Data\\Laundry.csv"));
				String[] next;
				
				while(sc.hasNextLine()) {
					next = sc.nextLine().split(",");
					if(next.length==1) {
						next = sc.nextLine().split(",");
					}
					//System.out.println(next[0]);
					Laundry laundry_temp = new Laundry(Double.parseDouble(next[0]),
					formatter.parse(next[1]),
							next[2],
							next[3]);
					laundry_data.add(laundry_temp);
				}
				sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return laundry_data;
		}
		
		public static ArrayList<Laundry> getStudentLaundry(String bitsId) {
			ArrayList<Laundry> laundry_data = new ArrayList<Laundry>();
			try{
				//CSVReader csvReader = new CSVReader(new FileReader("Data\\Laundry.csv"));
				Scanner sc = new Scanner(new FileReader("groupproject\\Laundromat\\Data\\Laundry.csv"));
				String[] next;
				
				while(sc.hasNextLine()) {
					next = sc.nextLine().split(",");
					if(next.length==1) {
						next = sc.nextLine().split(",");
					}
					//System.out.println(next[0]);
					if(next[3].equals(bitsId)) {
					Laundry laundry_temp = new Laundry(Double.parseDouble(next[0]),
					formatter.parse(next[1]),
							next[2],
							next[3]);
					laundry_data.add(laundry_temp);
					}
				}
				sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return laundry_data;
		}
			
		public static void addLaundry(double weight, String dateDropped, String returnDate, String bitsId) throws IOException {
			CSVWriter planWriter = new CSVWriter(new FileWriter("groupproject\\Laundromat\\Data\\Laundry.csv",true),CSVWriter.DEFAULT_SEPARATOR,
				    CSVWriter.NO_QUOTE_CHARACTER,
				    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
				    CSVWriter.RFC4180_LINE_END);
			String[] data = {Double.toString(weight), dateDropped, returnDate, bitsId};
			planWriter.writeNext(data);
			planWriter.close();
		}

}
