package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProductToTXT implements Serializaer<Product>{
	@Override
	public void serialize(Product object, File output) throws IOException {
		Writer whereToWrite = new PrintWriter(output);
		whereToWrite.write(object.toString());
		whereToWrite.flush();
		whereToWrite.close();
	}
	
	@Override 
	public void serializeCollection(List<Product> objects, File output) throws IOException {
		Writer whereToWrite = new PrintWriter(output);
		for(Product z: objects) {
			whereToWrite.write(z.toString()); 
			whereToWrite.write("\n");			
		}
		whereToWrite.flush();
		whereToWrite.close();
	}
	
	
	@Override
	public Product deserialize(File input) throws FileNotFoundException {
		Scanner scanner = new Scanner(input, "UTF-8");
        String inputText = scanner.useDelimiter("\\A").next();
        Product temp =new Product();
        Pattern for_name = Pattern.compile("(?<=This is )([a-zA-Z]*)(?=,which)");
    	   Matcher match = for_name.matcher(inputText);
    	   match.find(); 
    	   
    	   String name = match.group().replaceAll("\\s+","");       	       
     
    	   
     	Pattern for_year = Pattern.compile("(?<=created on ).{4}");
     	match = for_year.matcher(inputText);
     	if(!match.find()) {
     		throw new IllegalArgumentException("Incorrect name");
     	}
     	
     	int year = Integer.parseInt(match.group(0));
     	
     	
     	Pattern for_month = Pattern.compile("(?<=-)(.*)(?=-)");
     	match = for_month.matcher(inputText);
 		if(!match.find()) {
 			throw new IllegalArgumentException();
 		}
 		int month = Integer.parseInt(match.group());

 		 Pattern for_day = Pattern.compile(".{2}(?=$)");
 		 match = for_day.matcher(inputText);
 		 if(!match.find()) {
 				throw new IllegalArgumentException();
 			}
 		 int day= Integer.parseInt(match.group());
 		 
 		 LocalDate temp_year = LocalDate.of(year, month, day);
 		 
 		 temp.setTimeToFalse(temp_year);
 		 temp.setName(name);
 		 return temp;

	}
	public List<Product> deserializeCollection(File input) throws FileNotFoundException{
		Scanner scanner = new Scanner(input, "UTF-8");
        String inputText = scanner.useDelimiter("\\A").next();
        Product temp =new Product();
        List<Product> products= new ArrayList<Product>();
        for(String s: inputText.split("\n")) {
        	if(!s.isEmpty()) {
        		 Pattern for_name = Pattern.compile("(?<=This is )(.*)(?=,which)");
        	   	   Matcher match = for_name.matcher(s);
        	   	   match.find(); 
        	   	   
        	   	   String name = match.group().replaceAll("\\s+","");       	       
        	    
        	   	   
        	    	Pattern for_year = Pattern.compile("(?<=created on ).{4}");
        	    	match = for_year.matcher(s);
        	    	match.find();
        	    	int year = Integer.parseInt(match.group(0));;
        	    	
        	    	
        	    	Pattern for_month = Pattern.compile("(?<=-)(.*)(?=-)");
        	    	match = for_month.matcher(s);
        			match.find();
        			int month = Integer.parseInt(match.group());

        			 Pattern for_day = Pattern.compile(".{2}(?=$)");
        			 match = for_day.matcher(s);
        			 match.find();
        			 int day= Integer.parseInt(match.group());
        			 
        			 LocalDate temp_year = LocalDate.of(year, month, day);
        			 
        			 temp.setTimeToFalse(temp_year);
        			 products.add(new Product(name,LocalDate.of(year,month,day)));
        	}
        }
        scanner.close();
        return products; 
	}
}