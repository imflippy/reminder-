package webscrapingtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.telesign.MessagingClient;
import com.telesign.RestClient;



public class Scraper {
	
	public  static Date tomorrow(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		return c.getTime();
		
	}
		public static final int[] vremena= {18,20,};
	
	public static void generateTxt() {
		PrintWriter writer;
		Date dateNow = new Date();
		int i=1;
		 
		try {
			writer = new PrintWriter("C:\\xampp\\htdocs\\TadHack\\data\\events.txt", "UTF-8");
		
			try {
			
				Document doc = Jsoup.connect("https://www.cineplexx.rs/filmovi/repertoar/").get();
				Elements elements = doc.getElementsByClass("info clearfix");
				for(Element element:elements) 
			{
				String imgSrc=null, about=null, date=null;
				
				Elements slike= element.getElementsByTag("img");
				Element slika = slike.get(0);
				imgSrc = slika.attr("data-original");
				int size = element.text().length();
				date = element.text().substring(size-47, size- 37);
				about = element.text().substring(0, size - (49+37));
				int dd=0,mm=0,yy=0;
			//	System.out.println(date);
				//Pattern p = Pattern.compile("^(..).(..).(....)$");
				//Matcher m = p.matcher(date);
				//if(m.matches()) {
				
				
				if(i%3==0) {
					i=0;
					dateNow= tomorrow(dateNow);
					
				}
				int hours= (Math.random()>0.5? vremena[0]: vremena[1]); 
				dateNow.setHours(hours);
				dateNow.setMinutes(0);
				dateNow.setSeconds(0);
				i++;
				
				
				String m[] = date.split("\\.");
				dd=Integer.parseInt(m[0]);
				mm=Integer.parseInt(m[1]);
				yy=Integer.parseInt(m[2]);
				
				//|TODO: Parsiraj ovo??
				//System.out.println(dd +"" +mm + yy);
				
				//Date date_ = new Date(yy, mm, dd);
				
				Entity entitet = new Entity(about, imgSrc, dateNow);
				//entitet.print(writer); 
				//System.out.println(entitet);
				Entity.base.put(entitet.getId(), entitet);
			
			}
					

			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Entity.printAll(writer);
		writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			
		}
		
		
		
		
	}
	  

	
		public static void readTxt() {
			
			  File file = 
				      new File("C:\\xampp\\htdocs\\TadHack\\data\\send.txt"); 
				    Scanner sc=null;
					try {
						sc = new Scanner(file);
						 while (sc.hasNextLine()) {
						 String text=sc.nextLine();
						 int size=text.length();
						 String broj = text.substring(text.indexOf('|')+1,size );
						 broj=broj.stripLeading().stripTrailing();
						 try {
						 Integer id = Integer.parseInt(text.substring(0,text.indexOf("|")).stripLeading().stripTrailing());
						 Entity.base.get(id).getSubs().add(broj);
						// System.out.println(id + "  " + broj);
					}catch (NumberFormatException|NullPointerException e) {
						// TODO: handle exception
					}
						 }
						 if(sc!=null)
							  sc.close();
						 file.delete();
						 } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					} finally {
						
					}
					
				  
			
			
			
			
			
		}
		
		public static void generateTxtFromDatabase() {
			 File file = 
				      new File("C:\\xampp\\htdocs\\TadHack\\data\\events.txt"); 
			 file.delete();	 
			
			PrintWriter writer;
			try {
				writer = new PrintWriter("C:\\xampp\\htdocs\\TadHack\\data\\events.txt");
				synchronized (Entity.base) {
					
					
				}
				Entity.printAll(writer);
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	  

	      public static void send(String message, String phoneNumber) {

	          String customerId = "4E1D030E-EB3A-482E-AF1F-03E76DC72C72";
	         
	          String apiKey = "XsIF0oZ0cLdHc3RH9TfSFOIGFde6oHW8lYPH90d7J/Nw+XAdCqMH+k5XQOn4m6P41TaL1cceVZA17GoXZzy4pA==";
	         // String phoneNumber = "381649586916";
	         // String message = "You're scheduled for a dentist appointment at 2:30PM.";
	          String messageType = "ARN";

	          try {
	              MessagingClient messagingClient = new MessagingClient(customerId, apiKey);
	              RestClient.TelesignResponse telesignResponse = messagingClient.message(phoneNumber, message, messageType, null);
	              System.out.println("Your reference id is: "+telesignResponse.json.get("reference_id"));
	           System.out.println(telesignResponse.json);
	          } catch (Exception e) {
	              e.printStackTrace();
	          }
	      }
	  public static void main(String [] args) {
		 send("test", "381649586916");
		 // readTxt();
	  }
	  
}
