import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
*MinOfThreeTest.java
*
*Illustrates JUnit tests for the MinOfThree class.
*
* @author Alexander Brannon 
* @version 2018-1-23
*
*/
public class MinOfThreeTest {


   /** A test for min1() method. **/
   @Test public void min1Test() {
      int expected = 0;
      int actual = MinOfThree.min1(0, 1, 2);
      assertEquals(expected, actual);
   }
      
      /** A test for min2() method. **/
   @Test public void min2Test() {
      int expected = 0;
      int actual = MinOfThree.min2(0, 1, 2);
      assertEquals(expected, actual);
   }
}
