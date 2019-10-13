package webscrapingtest;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Entity {
	private LinkedList<String> subscribers= new LinkedList<String>();
	public static HashMap<Integer, Entity> base = new HashMap<Integer, Entity>();
	private static int ID=1;
	private int id=ID++;
	private String about;
	private String imgSrc;
	private Date date;
	public String dateS;
	public Entity(String about, String imgSrc, Date date) {
		this.imgSrc=imgSrc;
		this.date=date;
		this.about=about;
	}
	public Entity(String about, String imgSrc, String date) {
		this.imgSrc=imgSrc;
		this.dateS=date;
		this.about=about;
		SimpleDateFormat sTo = new SimpleDateFormat("dd.MM.yyyy");
		try {
			this.date= sTo.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(String date) {
		this.dateS = date;
		SimpleDateFormat sTo = new SimpleDateFormat("dd.MM.yyyy");
		try {
			Date date2= sTo.parse(date);
			this.date.setMonth(date2.getMonth());
			this.date.setDate(date2.getDay());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print(PrintWriter writer) {
		writer.print(this.imgSrc + "|" + this.about + "|" + this.date.toString() + "|" + this.id + "\n");
		
		
		
	}
	
	public static void printAll(PrintWriter writer) {
		base.values().forEach(l->{l.print(writer); System.out.println(l);});
		
		
		
	}
	public LinkedList<String> getSubs(){return subscribers;}
	public String toString() {
		return  this.about + "|" +this.date+  "|" + this.id + '\n';
		
	}
	

}
