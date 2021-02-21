import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Alexander Brannon (adb0056@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2018-01-15
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      int min = Integer.MAX_VALUE;
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      else {
         for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
               min = a[i];
            }
         }
      }
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      int max = Integer.MIN_VALUE;
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      else {
         for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
               max = a[i];
            }
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      int min = 0;
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int[] c = new int[b.length];
      c[0] = b[0];
      int dup = 1;
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            c[dup] = b[i];
            dup++;
         }
      }
      if (k < 1 || k > c.length) {
         throw new IllegalArgumentException();
      }
      else {
         int i = c.length - 1;
         int num = c.length;
         while (c[i] == 0) {
            num--;
            i--;
         }
         int[] d = Arrays.copyOf(c, num);
         if (k > d.length) {
            throw new IllegalArgumentException();
         }
         else {
            min = d[k - 1];  
         } 
      }
      return min;
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      int max = 0;
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int[] c = new int[b.length];
      c[0] = b[0];
      int dup = 1;
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            c[dup] = b[i];
            dup++;
         }
      }
      if (k < 1 || k > c.length) {
         throw new IllegalArgumentException();
      }
      else {
         int i = c.length - 1;
         int num = c.length;
         while (c[i] == 0) {
            num--;
            i--;
         }
         int[] d = Arrays.copyOf(c, num);
         if (k > d.length) {
            throw new IllegalArgumentException();
         }
         else {
            max = d[d.length - k];  
         }
      }
      return max;
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      int num = 0;
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            num++;
         }
      }
      int[] b = new int[num];
      num = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            b[num] = a[i];
            num++;
         }
      }
      return b;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      int dif = Integer.MAX_VALUE;
      int small = Integer.MAX_VALUE;
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= key && a[i] - key < dif) {
            small = a[i];
            dif = a[i] - key;
         }
      }
      if (small == Integer.MAX_VALUE) {
         throw new IllegalArgumentException();
      }
      return small;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      int dif = Integer.MAX_VALUE;
      int small = Integer.MAX_VALUE;
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key && key - a[i] < dif) {
            small = a[i];
            dif = key - a[i];
         }
      }
      if (small == Integer.MAX_VALUE) {
         throw new IllegalArgumentException();
      }
      return small;
   }

}
