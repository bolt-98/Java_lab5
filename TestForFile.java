package lab5;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import java.util.List;

public class TestForFile {
	Product test_product1,test_product2,test_product3;
	List<Product> products;
  @BeforeTest
  public void start() {
	  
	   test_product1= new Product("Apple",LocalDate.of(2015,01,25));
	   test_product2= new Product("Chess",LocalDate.of(2016, 07, 13));
	   test_product3= new Product("Fish",LocalDate.of(2010,03,10));
	  products= new ArrayList<Product>();
	  products.add(test_product1);
	  products.add(test_product2);
	  products.add(test_product3);
  }
  @DataProvider
  public Object[][] dataProviderForSerializationOfOneObject(){
	  return new Object[][] {{test_product1},{test_product2},{test_product3}};
  }
  @DataProvider
  public Object[][] dataProviderForSerializationOfList(){
	  return new Object[][] {{products}};
  }
  @Test(dataProvider= "dataProviderForSerializationOfOneObject")
  public void serializationToText(Product p) throws IOException {
	  new ProductToTXT().serialize(p,new File("in.txt"));
	  Assert.assertEquals(new ProductToTXT().deserialize(new File("in.txt")), p);
  }
  @Test(dataProvider ="dataProviderForSerializationOfList")
  public void test(List<Product> products) throws FileNotFoundException, IOException {
	  new ProductToTXT().serializeCollection(products, new File("in.txt"));
	  Assert.assertEquals(new ProductToTXT().deserializeCollection(new File("in.txt")), products);
  }
  @Test(dataProvider= "dataProviderForSerializationOfOneObject")
  public void serializationToXML(Product p) throws FileNotFoundException {
	  new ProductToXML().serialize(p, new File("in2.xml"));
	  Assert.assertEquals(new ProductToXML().deserialize(new File("in2.xml")), p);
  }
  @Test(dataProvider = "dataProviderForSerializationOfList")
  public void serializationOfListToXML(List<Product> p) throws FileNotFoundException {
	  new ProductToXML().serializeCollection(p, new File("in2.xml"));
	  Assert.assertEquals(new ProductToXML().deserializeCollection(new File("in2.xml")), p); 
  }
  @Test(dataProvider = "dataProviderForSerializationOfOneObject")
  public void serializationtoJSON(Product p) throws JsonGenerationException, JsonMappingException, IOException {
	  new ProductToJSON().serialize(p, new File("in3.json"));
	  Assert.assertEquals(new ProductToJSON().deserialize(new File("in3.json")), p);
  }
  
  
  
  
  

}