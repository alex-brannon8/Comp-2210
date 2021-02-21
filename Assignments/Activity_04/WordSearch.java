import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
*WordSearch.java
*An engine for WordSearchGame.java
*
*@author       Alexander Brannon
*@version      4/4/2018 
*
*/
public class WordSearch implements WordSearchGame {

   private TreeSet<String> lexicon = new TreeSet<String>();
   
   private String[][] board = new String[][] {
      {"E", "E", "C", "A"},
      {"A", "L", "E", "P"},
      {"H", "N", "B", "O"},
      {"Q", "T", "T", "Y"}
   };
  
   private int size = 4;
   private boolean lexiconCall = false;

   @Override public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      File file = new File(fileName);
      Scanner scan = null;
      try {
         scan = new Scanner(file);
         while (scan.hasNext()) {
            lexicon.add(scan.next().toLowerCase());
         }
      }
      catch (FileNotFoundException e) {
         throw new IllegalArgumentException();
      }
      finally {
         if (scan != null) {
            scan.close();
         }
      }
      lexiconCall = true;
   }
   
   @Override public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      double d = Math.sqrt((double)letterArray.length);
      if (d == Math.round(d)) {
         int d2 = (int)d;
         board = new String[d2][d2];
         for (int i = 0; i < d2; i++) {
            for (int i2 = 0; i2 < d2; i2++) {
               board[i][i2] = letterArray[(i * d2 + i2)];
            }
         }
         size = d2;
      }
      else {
         throw new IllegalArgumentException();
      }
   }
   
   @Override public String getBoard() {
      StringBuilder build = new StringBuilder();
      for (int i = 0; i < size; i++) {
         for (int i2 = 0; i2 < size; i2++) {
            build.append(board[i][i2] + "");
            if (i2 == size - 1) {
               build.append("br");
            }
         }
      }
      String s = build.toString();
      return s;
   }
   
   @Override public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (!lexiconCall) {
         throw new IllegalStateException();
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      TreeSet<String> words = new TreeSet<String>();
      boolean[][] visited = new boolean[size][size];
      for (int i = 0; i < size; i++) {
         for (int i2 = 0; i2 < size; i2++) {
            getWords(minimumWordLength, i, i2, "", visited, words);
         }
      }
      return words;
   }
   
   private void getWords(int minLength, int x, int y, String string, 
      boolean[][] vis, TreeSet<String> word) {
   
      vis[x][y] = true;
      string = string + board[x][y];
      System.out.println(string + "");
      if (isValidWord(string) && string.length() >= minLength) {
         word.add(string);
      }
      if (isValidPrefix(string)) {
         for (int row = x - 1; row <= x + 1 && row < size; row++) {
            for (int column = y - 1; column <= y && column < size; column++) {
               if (row >= 0 && column >= 0 && !vis[row][column]) {
                  System.out.println("Valid Prefix");
                  getWords(minLength, row, column, string, vis, word);
                  System.out.println("Should come after valid run");
               } 
            }
         }
      }
      string = string.substring(0, string.length() - 1);
      vis[x][y] = false;
   }
   
   @Override public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (!lexiconCall) {
         throw new IllegalArgumentException();
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      Iterator<String> iterator = words.iterator();
      int total = 0;
      while (iterator.hasNext()) {
         String string = iterator.next();
         if (string.length() >= minimumWordLength && isValidWord(string) && !isOnBoard(string).isEmpty()) {
            total += 1;
            if (string.length() > minimumWordLength) {
               total += string.length() - minimumWordLength;
            }
         }
      }
      return total;
   }
   
   @Override public boolean isValidWord(String wordToCheck) {
      wordToCheck = wordToCheck.toLowerCase();
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      else if (!lexiconCall) {
         throw new IllegalStateException();
      }
      else if (lexicon.contains(wordToCheck)) {
         return true;
      }
      else {
         return false;
      }
   }
   
   @Override public boolean isValidPrefix(String prefixToCheck) {
      prefixToCheck = prefixToCheck.toLowerCase();
      if (prefixToCheck.equals(null)) {
         throw new IllegalArgumentException();
      }
      if (!lexiconCall) {
         throw new IllegalStateException();
      }
      String string = lexicon.ceiling(prefixToCheck);
      if (string.equals(null) || !string.startsWith(prefixToCheck)) {
         return false;
      }
      else if (string.startsWith(prefixToCheck) || string.equals(prefixToCheck)) {
         return true;
      }
      return false;
   }
   
   @Override public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck.equals(null)) {
         throw new IllegalArgumentException();
      }
      if (!lexiconCall) {
         throw new IllegalStateException();
      }
      boolean[][] visited = new boolean[size][size];
      ArrayList<Integer> list = new ArrayList<Integer>();
      for (int x = 0; x < size; x++) {
         for (int y = 0; y < size; y++) {
            wordOnBoard(x, y, "", wordToCheck, visited, list);
         }
      }
      return list;
   }
   
   private void wordOnBoard(int x, int y, String string, String found,
      boolean[][] vis, ArrayList<Integer> word) {
      string = string + board[x][y];
      vis[x][y] = true;
      if (string.equals(found)) {
         for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               if (vis[x][y] == true) {
                  word.add(i * size + j);
               }
            }
         }
      }
      if (found.startsWith(string)) {
         for (int row = x - 1; row <= x + 1 && row < size; row++) {
            for (int column = y - 1; column <= y + 1 && column < size; column++) {
               if (row >= 0 && column >= 0 && !vis[row][column]) {
                  wordOnBoard(row, column, string, found, vis, word);
               }
            }
         }
      }
      string = string.substring(0, string.length() - 1);
      vis[x][y] = false;
   }
   
   private class ComparePrefix implements Comparator<String> {
      @Override public int compare(String obj1, String obj2) {
         return 0;
      }
   }






}
