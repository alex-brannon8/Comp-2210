import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Alexander Brannon (adb0056@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2018-04-17
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;

   // add other fields as you need them ...
   
   String firstKGram;


   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
   
      int k = K;
      int size = 0;
      int x = 0;
      firstKGram = sourceText.substring(0, k);
   
      while (size + k <= sourceText.length()) {
      
         String empty = "";
         String kgrams = sourceText.substring(size, size + k);
      
         if (!model.containsKey(kgrams)) {
         
            int j = k;
         
            while (x + j < sourceText.length()) {
               String g = sourceText.substring(x, x + j);
            
               if (x + k >= sourceText.length()) {
                  empty += '\u0000';
               }
               if (kgrams.equals(g)) {
                  empty += sourceText.substring(x + j, x + j + 1);
               }
               x++;
            }
            model.put(kgrams, empty);
         }
         x = 0;
         size++;
      }
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      return firstKGram;
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      /**Random random = new Random();
      int x = 0;
      int size = model.size();
      int index = random.nextInt();
      
      for (String i : model.keySet()) {
         
         if (index == x) {
            return i;
         }
         x++;
      }
      return null;
      */
      int k = firstKGram.length();
      Random random = new Random();
      int x = random.nextInt(keySet.length() - k);
      return keySet.substring(x, x + k);
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      Random random = new Random();
      String empty = "";
      int x = 0;
      
      for (String i : model.keySet()) {
         
         if (i.equals(kgram)) {
            empty = model.get(kgram);
            int y = empty.length();
         
            if (y > 0) {
               x = random.nextInt(y) + 1;
            }
         }
      }
      int z = x - 1;
      
      if (!empty.equals("")) {
         return empty.charAt(z);
      }
      return '\u0000';
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
