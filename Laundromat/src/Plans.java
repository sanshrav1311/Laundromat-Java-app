import java.io.IOException;
import java.util.ArrayList;

public class Plans {
	public int no_of_washes;
	public boolean iron;
	public String name;
	public double planPrice;
	
	public static ArrayList<Plans> plansList = new ArrayList<Plans>();
	
	public Plans(String name, int washes, boolean iron,double planPrice) {
		this.name = name;
		this.iron = iron;
		this.no_of_washes = washes;
		this.planPrice = planPrice;
	}
	
	
	public static ArrayList<Plans> getPlans() {
		return plansList;
	}
	public static void viewPlans() {
		for(int i = 0; i < plansList.size(); i++) {
			System.out.println(plansList.get(i));
		}
		
	}
	public void editPlan(String name, int washes, boolean iron,double planPrice) throws IOException{
		this.name = name;
		this.iron = iron;
		this.no_of_washes = washes;
		this.planPrice = planPrice;
		CSVPlans.editCSVPlan(name, washes, iron, planPrice);
	}

	public String toString() {
		return "name = " + name + ", no. of washes = " + no_of_washes + ", iron = " + iron;
	}
}
