package test;

import static org.junit.Assert.*;

import org.am02316.com1028.Functionality.Basket;
import org.am02316.com1028.Functionality.PastOrder;
import org.junit.Test;

public class PastOrderTest {

	@Test
	  public void testConstruction() {
	  
		PastOrder order = new PastOrder(1,"sashamar0811@gmail.com","max@gmail.com", "Harry Potter", 5);
		 
		assertEquals(1, order.getId());
		assertEquals("sashamar0811@gmail.com", order.getCustomerEmail());
		assertEquals("max@gmail.com", order.getSellerEmail());
		assertEquals("Harry Potter", order.getTitle());
		assertEquals(5, order.getQuantity());

	    
	  }
	
	
	@Test(expected = NullPointerException.class)
	  public void testInvalidCustomerConstruction() {
	
		PastOrder order = new PastOrder(1,null,"max@gmail.com", "Harry Potter", 5);
		 

	   }

	@Test(expected = NullPointerException.class)
	  public void testInvalidSellerConstruction() {
	
		PastOrder order = new PastOrder(1,"sashamar0811@gmail.com",null, "Harry Potter", 5);
		 

	   }
	@Test(expected = NullPointerException.class)
	  public void testInvalidTitleConstruction() {
	
		PastOrder order = new PastOrder(1,"sashamar0811@gmail.com","max@gmail.com", null, 5);
		 

	   }

	@Test(expected = NullPointerException.class)
	  public void testInvalidQuantityConstruction() {
	
		PastOrder order = new PastOrder(1,"sashamar0811@gmail.com","max@gmail.com", "Harry Potter", 0);
		 

	   }

}
