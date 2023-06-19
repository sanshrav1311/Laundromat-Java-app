import java.io.IOException;

public abstract class LoginAndRegistration{
	private String emailId;
	private String password;
	
	public LoginAndRegistration(String emailId, String password) {
		this.emailId = emailId;
		this.password = password;
	}
	public static void logout() {
		
	}
	public String getPassword() {
		return password;
	}
	public static void register(String name, String emailId, String bitsId, Plans plan, String hostel, String password) throws IOException {
		Admin.addRevenue(plan.planPrice);
		CSVRevenue.setRevenue(Admin.getRevenue());
		CSVStudent.addStudent(name, emailId, bitsId, plan.no_of_washes, plan.name, hostel, password,0.0,"none");
	}
}
