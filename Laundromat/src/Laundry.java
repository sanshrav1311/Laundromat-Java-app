import java.text.SimpleDateFormat;
//import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Laundry {
	public double weight;
	public String dateDropped;
	public String day;
	public String returnDate;
	public String status;
	public String bitsId;//database se value lelena
	public Laundry(double weight, String dateDropped, String bitsId) {
		this.weight = weight;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		this.dateDropped = formatter.format(dateDropped);
		this.bitsId = bitsId;
		Date date;
		try {
			date = formatter.parse(dateDropped);
			day = new SimpleDateFormat("EEEE").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	public Laundry(double weight,String bitsId) {
		this.weight = weight;
		this.bitsId = bitsId;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		Date date = new Date();
		Date date1;
		this.dateDropped = formatter.format(date);
		try {
			date1 = formatter.parse(dateDropped);
			day = new SimpleDateFormat("EEEE").format(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int day_index = date.getDay();
		String days[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		returnDate = days[day_index+3]; 
	}
	
	public Laundry(double weight, Date dateDropped, String returnDate, String bitsId) {
		this.weight = weight;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		this.dateDropped = formatter.format(dateDropped);
		this.bitsId = bitsId;
		Date date;
		try {
			date = formatter.parse(this.dateDropped);
			day = new SimpleDateFormat("EEEE").format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.returnDate = returnDate;
	}
	
	public void setReturnDay(String returnDate) {
		this.returnDate = returnDate;
	}
	public String toString() {
		return "weight = " + weight + "KG on date = " + dateDropped +" "+day;
	}
}
