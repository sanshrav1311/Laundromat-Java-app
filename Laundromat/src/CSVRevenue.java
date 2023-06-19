
import java.io.*;
import java.util.*;
import com.opencsv.*;

public class CSVRevenue{
public static double getTotalRevenue() throws IOException{
   
Scanner sc = new Scanner(new FileReader("groupproject\\Laundromat\\Data\\Revenue.csv"));
double rev = Double.parseDouble(sc.next());
sc.close();
return rev;
}
public static void setRevenue(double rev) throws IOException{
CSVWriter planWriter = new CSVWriter(new FileWriter("groupproject\\Laundromat\\Data\\Revenue.csv"),CSVWriter.DEFAULT_SEPARATOR,
   CSVWriter.NO_QUOTE_CHARACTER,
   CSVWriter.DEFAULT_ESCAPE_CHARACTER,
   CSVWriter.RFC4180_LINE_END);
String[] arr = new String[]{Double.toString(rev)};
planWriter.writeNext(arr);
planWriter.close();
}
}
