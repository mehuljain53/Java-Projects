import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class script1 {
 public static void main(String[] args) {

  String csvFile = "data1.csv";
  String line = "";
  String cvsSplitBy = ",";
  long count = 0;
  try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
   PrintWriter writer = new PrintWriter("data2.txt", "UTF-8");
   long counter = 1;
   while ((line = br.readLine()) != null) {
    String srt;
    String[] myline = line.split(cvsSplitBy);
    srt = "," + myline[1] + "," + myline[2] + "," + myline[3] + "," + myline[4];
    for (int i = 0; i < 6; i++) {
     StringBuffer now = new StringBuffer("");
     int m = i + 1;
     now.append("," + m);

     now.append("," + myline[5 + i]);



     writer.println(counter + srt + now);
     counter++;

     //18+3=21
     /*if(count!=0)
     for(int i=0;i<3;i++)
     {
     	StringBuffer now=new StringBuffer("");
     	int m=i+1;
     	now.append(","+m);
     	for(int j=0;j<6;j++)
     	{
     		now.append(",");
     		now.append(myline[4+i+j*3]);
     		
     	}
 	                
     	writer.println(srt+now);
     	
     }
     count++;*/

    }
    count++;
    System.out.println(count + " " + myline[0]);


   }

  } catch (IOException e) {
   e.printStackTrace();
  }
  System.out.print(count);
 }

}