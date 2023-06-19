import java.io.*;
import java.util.*;
import com.opencsv.*;
//import java.util.Scanner;

public class CSVPlans {
	
	CSVPlans(){
	}
	
	public static ArrayList<Plans> getPlansList() throws IOException{
		ArrayList<Plans> plan_data = new ArrayList<Plans>();
		try{
			//CSVReader csvReader = new CSVReader(new FileReader("Data\\Plans.csv"));
			Scanner sc = new Scanner(new FileReader("groupproject\\Laundromat\\Data\\Plans.csv"));
			String[] next;
	        //sc.nextLine();
			while(sc.hasNextLine()) {
				next = sc.nextLine().split(",");
				//System.out.println(next[0]);
				Plans plan_temp = new Plans(next[0],
						Integer.parseInt(next[1]),
						Boolean.parseBoolean(next[2]),
						Double.parseDouble(next[3]));
				plan_data.add(plan_temp);
				
			}
			sc.close();
		
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return plan_data;
	}
	public void addPlan(String name, int washes, boolean iron,double planPrice) throws IOException {
		CSVWriter planWriter = new CSVWriter(new FileWriter("groupproject\\Laundromat\\Data\\Plans.csv",true),CSVWriter.DEFAULT_SEPARATOR,
			    CSVWriter.NO_QUOTE_CHARACTER,
			    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
			    CSVWriter.RFC4180_LINE_END);
		String[] data = {name, Integer.toString(washes), Boolean.toString(iron),Double.toString(planPrice)};
		planWriter.writeNext(data);
		planWriter.close();
	}
	public static Plans getPlan(String name) throws IOException{
		Scanner sc = new Scanner(new FileReader("groupproject\\Laundromat\\Data\\Plans.csv"));
		String[] next;
		Plans plan_temp = null;

		while(sc.hasNextLine()) {
		next = sc.nextLine().split(",");
		if(next.length==1) {
		next = sc.nextLine().split(",");
		}
		if (next[0].equals(name)) {
		plan_temp = new Plans(next[0],
		Integer.parseInt(next[1]),
		Boolean.parseBoolean(next[2]),
		Double.parseDouble(next[3]));
		break;
		}
		}
		return plan_temp;
		}
	public static void editCSVPlan(String name, int washes, boolean iron, double planPrice) throws IOException{
		//RandomAccessFile file = new RandomAccessFile("Data\\Plans.csv", "rw");
		//String keep;
		String write = "";
		String[] next;
		Scanner sc = new Scanner(new FileReader("groupproject\\Laundromat\\Data\\Plans.csv"));
		while(sc.hasNextLine()) {
		next = sc.nextLine().split(",");
		if(next.length==1) {
		next = sc.nextLine().split(",");
		}
		if(next[0].equals(name)) {
		next[1] = Integer.toString(washes);
		next[2] = Boolean.toString(iron);
		next[3] = Double.toString(planPrice);
		}
		write += next[0] + "," + next[1] + "," + next[2] + "," + next[3] + "\n";
		}
		sc.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter("groupproject\\Laundromat\\Data\\Plans.csv"));
		writer.write(write);
		writer.close();
	}
	public static void deleteCSVPlan(String name) throws IOException{
		   //RandomAccessFile file = new RandomAccessFile("Data\\Plans.csv", "rw");
		   //String keep;
		   String write = "";
		   String[] next;
		   Scanner sc = new Scanner(new FileReader("groupproject\\Laundromat\\Data\\Plans.csv"));
		   while(sc.hasNextLine()) {
		      next = sc.nextLine().split(",");
		      if(next.length==1) {
		         next = sc.nextLine().split(",");
		      }
		      if(next[0].equals(name)) {
		         continue;
		      }
		      write += next[0] + "," + next[1] + "," + next[2] + "," + next[3] + "\n";
		   }
		   sc.close();
		   BufferedWriter writer = new BufferedWriter(new FileWriter("groupproject\\Laundromat\\Data\\Plans.csv"));
		   writer.write(write);
		   writer.close();
		}

	
}
