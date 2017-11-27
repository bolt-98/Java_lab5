package lab5;

import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement

public class Product implements Serializable{

	@JsonIgnore
	private final static String price_rg = "([0-9]){1,}([\\.]){1}([0-9]){1,}";
	private final static String name_rg = "([a-zA-Z]){1,15}";
	
	private double price;
	private int id_num;
	private String name;
	//@JsonIgnore
	private  LocalDate timeToFalse;
	public Product () {
		price = 0.0;
		id_num = 0;
		name = "";
		timeToFalse = null;
	}
	
	/*public void writeObjectToFile(Product object) throws IOException {
		FileOutputStream fos = new FileOutputStream("temp.out");
		  ObjectOutputStream oos = new ObjectOutputStream(fos);
		  oos.writeObject(oos);
	}*/
	public  Product(String n, LocalDate t) {
		name = n;
		timeToFalse = t;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getId_num() {
		return id_num;
	}
	public void setId_num(int id_num) {
		this.id_num = id_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getTimeToFalse() {
		return timeToFalse;
	}
	public void setTimeToFalse(LocalDate timeToFalse) {
		this.timeToFalse = timeToFalse;
	}
	@Override
	public String toString() {
		return String.format("This is %s,which was created on %s", this.name, this.timeToFalse.toString());
	}
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product d = (Product) obj;
		return this.name.equals(d.name);
	}
	
	public boolean regular_pr() {
		Pattern pt = Pattern.compile(price_rg);
		Matcher m = pt.matcher(Double.toString(this.price));
		return m.matches();
	}
	public boolean regular_nm() {
		Pattern pt = Pattern.compile(name_rg);
		Matcher m = pt.matcher(this.name);
		return m.matches();
	}
	public  boolean dateOff() {
		return (LocalDate.now().compareTo(timeToFalse)==-1) ? true :  false;
		
	}
	 public static void main(String[] args) {
		 
			
	 }
	 
}
