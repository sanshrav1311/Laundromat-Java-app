import java.io.*;
///import java.nio.charset.StandardCharsets;
import java.util.*;
import com.opencsv.*;
//import java.util.Scanner;



public class CSVStudent {
   
   public static ArrayList<Student> getStudentsList() throws IOException{
      ArrayList<Student> student_data = new ArrayList<Student>();
      try{
         //CSVReader csvReader = new CSVReader(new FileReader("Data\\Plans.csv"));
         Scanner sc = new Scanner(new FileReader("groupproject\\Laundromat\\Data\\Students.csv"));
         String[] next;
         
         while(sc.hasNextLine()) {
            next = sc.nextLine().split(",");
            if(next.length==1) {
               next = sc.nextLine().split(",");
            }
            Plans plan = CSVPlans.getPlan(next[4]);
            //System.out.println(next[0]);
            Student student = new Student(next[0],
                  next[1],
                  next[2],
                  Integer.parseInt(next[3]),
                  plan,
                  next[5],
                  next[6],
                  Double.parseDouble(next[7]),
                  next[8]);
            student_data.add(student);
            
         }
         sc.close();
      
         
      }
      catch(Exception e) {
         e.printStackTrace();
      }
      return student_data;
   }
   
   public static Student getStudent(String bitsId) {
      //ArrayList<Student> student_data = new ArrayList<Student>();
      Student student = null;
      try{
         //CSVReader csvReader = new CSVReader(new FileReader("Data\\Plans.csv"));
         Scanner sc = new Scanner(new FileReader("groupproject\\Laundromat\\Data\\Students.csv"));
         String[] next;
         
         while(sc.hasNextLine()) {
            next = sc.nextLine().split(",");
            if(next.length==1) {
               next = sc.nextLine().split(",");
            }
            if(next[2].equals(bitsId)) {
               sc.close();
               
               Plans plan = CSVPlans.getPlan(next[4]);
               //System.out.println(next[0]);
               student = new Student(next[0],
                  next[1],
                  next[2],
                  Integer.parseInt(next[3]),
                  plan,
                  next[5],
                  next[6], 
                  Double.parseDouble(next[7]),
                  next[8]);
               break;
            }
         }

      
      }
      catch(Exception e) {
         e.printStackTrace();
      }
      return student;
   }
   
   
   public synchronized static void addStudent(String name,String emailId, String bitsId, Integer no_of_washes_left, String plan, String hostel, String password, double lastLaundryWeight, String lastlaundryStatus) {
      CSVWriter planWriter;
      try {
         planWriter = new CSVWriter(new FileWriter("groupproject\\Laundromat\\Data\\Students.csv",true),CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.RFC4180_LINE_END);
      
      String[] data = {name, emailId, bitsId,Integer.toString(no_of_washes_left),plan,hostel,password,Double.toString(lastLaundryWeight),lastlaundryStatus};
      planWriter.writeNext(data);
      planWriter.close();
   } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
   }
   
   public synchronized static void editStudent(String name,String emailId, String bitsId, Integer no_of_washes_left, String plan, String hostel, String password, double lastWeight, String lastStatus){
      //RandomAccessFile file = new RandomAccessFile("Data\\Plans.csv", "rw");
      //String keep;
      String write = "";
      String[] next;
      Scanner sc;
      try {
         sc = new Scanner(new FileReader("groupproject\\Laundromat\\Data\\Students.csv"));
      
      while(sc.hasNextLine()) {
         next = sc.nextLine().split(",");
         if(next.length==1) {
            next = sc.nextLine().split(",");
         }
         if(next[2].equals(bitsId)) {
            next[0] = name;
            next[1] = emailId;
            next[3] = Integer.toString(no_of_washes_left);
            next[4] = plan;
            next[5] = hostel;
            next[6] = password;
            next[7] = Double.toString(lastWeight);
            next[8] = lastStatus;
         }
         write += next[0] + "," + next[1] + "," + next[2] + "," + next[3] + "," + next[4] + "," + next[5] + "," + next[6] + "," + next[7] + "," + next[8] + "\n";
      }
      sc.close();
      BufferedWriter writer = new BufferedWriter(new FileWriter("groupproject\\Laundromat\\Data\\Students.csv"));
      writer.write(write);
      writer.close();
   } catch (IOException e) {
      e.printStackTrace();
   }
   }
}