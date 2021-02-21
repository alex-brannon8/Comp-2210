//Set.java

import java.util.*;

public interface Set<T> extends Iterable<T>

{   

   boolean add(T element);   

   boolean remove(T element);   

   boolean contains(T element);   

   int size();   

   boolean isEmpty();   

   boolean equals(Set<T> s);   

   Set<T> union(Set<T> s);   

   Set<T> intersection(Set<T> s);   

   Set<T> complement(Set<T> s);   

   Iterator<T> iterator();

}

// LinkedSet.java



public class LinkedSet<T extends Comparable<? super T>> implements Set<T>

{  

   Node front;

   Node rear;   

   int size;

   

   public LinkedSet() {
   
      front = null;
   
      rear = null;
   
      size = 0;
   
   }

   

   @Override
   
    public String toString() {
   
      if (isEmpty()) {
      
         return "[]";
      
      }
   
      StringBuilder result = new StringBuilder();
   
      result.append("[");
   
      for (T element : this) {
      
         result.append(element + ", ");
      
      }
   
      result.delete(result.length() - 2, result.length());
   
      result.append("]");
   
      return result.toString();
   
   }

   

   public int size() {
   
   //RETURN THE NO OF ELEMENTS IN THE LINKEDSET
   
      return size;
   
   }

  

   public boolean isEmpty() {
   
   //RETURN TRUE IF NO ELEMENTS IN THE LINKEDSET
   
      return (size == 0);
   
   }

   

   public boolean add(T element) {
   
      if(element==null)
      
         throw new NullPointerException();
   
   //CHECK FOR DUPLICATES
   
      if(contains(element))
      
         return false;
   
   //ADD AS FIRST NODE IF LINKEDSET IS EMPTY
   
      if(size==0)
      
      {
      
         front=new Node(element);
      
         rear=front;
      
      }
      
      //ADD TO TEND OF THE LINKEDSET
      
      else
      
      {
      
         rear.next=new Node(element);
      
         rear.next.prev=rear;
      
         rear=rear.next;
      
      }
   
      size++;
   
      return true;
   
   }

   

   public boolean remove(T element) {
   
       
   
      if(element==null)
      
         throw new NullPointerException();
   
      Node t=front;
   
      while(t!=null)
      
      {
      
      //CHECK IF ELEMENT IS PRESENT IN LINKEDSET
      
         if(t.element.equals(element))
         
         {
         
            if(t==front)
            
            {
            
               front=front.next;
            
               front.prev=null;
            
            }
            
            else if(t==rear)
            
            {
            
               t.prev.next=null;
            
            }
            
            else
            
            {
            
               t.prev.next=t.next;
            
               t.next.prev=t.prev;
            
            }
         
            size--;
         
            return true;
         
         }
      
         t=t.next;
      
      }
   
      return false;
   
   }

   

   public boolean contains(T element)
   
   {
   
   //TEMP NODE
   
      Node t=front;
   
      while(t!=null)
      
      {
      
         if(t.element!=null&&t.element.equals(element))
         
         //RETURN TRUE IF ELEMENT IS FOUND
         
            return true;
      
         t=t.next;
      
      }
   
      return false;
   
   }

   

   public boolean equals(Set<T> s) {
   
      if(size==s.size() &&complement(s).size()==0)
      
         return true;
   
      return false;
   
   }

   

   public boolean equals(LinkedSet<T> s) {
   
      if(size==s.size() &&complement(s).size()==0)
      
         return true;
   
      return false;
   
   }

   

   public Set<T> union(Set<T> s){
   
      if(s==null)
      
         throw new NullPointerException();
   
      LinkedSet<T> returnSet=new LinkedSet<T>();
   
      Node t=front;
   
      while(t!=null)
      
      {
      
         returnSet .add(t.element);
      
         t=t.next;
      
      }
   
      Iterator<T> i=s.iterator();
   
      while(i.hasNext())
      
         returnSet.add(i.next());
   
      return returnSet;
   
       
   
   }

   

   public Set<T> union(LinkedSet<T> s){
   
      if(s==null)
      
         throw new NullPointerException();
   
      LinkedSet<T> returnSet=new LinkedSet<T>();
   
      Node t=front;
   
      while(t!=null)
      
      {
      
         returnSet.add(t.element);
      
         t=t.next;
      
      }
   
      Iterator<T> i=s.iterator();
   
      while(i.hasNext())
      
         returnSet.add(i.next());
   
      return returnSet;
   
   }

   

   public Set<T> intersection(Set<T> s) {
   
      if(s==null)
      
         throw new NullPointerException();
   
      LinkedSet<T> returnSet=new LinkedSet<T>();
   
      Node t=front;
   
      while(t!=null)
      
      {   
      
         if(s.contains((T)t.element))
         
            returnSet.add((T)t.element);
      
         t=t.next;
      
      }
   
      return returnSet;
   
   }

   

   public Set<T> intersection(LinkedSet<T> s) {
   
      if(s==null)
      
         throw new NullPointerException();
   
      LinkedSet<T> returnSet=new LinkedSet<T>();
   
      Node t=front;
   
      while(t!=null)
      
      {   
      
         if(s.contains((T)t.element))
         
            returnSet.add((T)t.element);
      
         t=t.next;
      
      }
   
      return returnSet;
   
   }

   

   public Set<T> complement(Set<T> s) {
   
      if(s==null)
      
         throw new NullPointerException();
   
      LinkedSet<T> returnSet=new LinkedSet<T>();
   
      Node t=front;
   
      while(t!=null)
      
      {   
      
         if(!s.contains((T)t.element))
         
            returnSet.add((T)t.element);
      
         t=t.next;
      
      }
   
      return returnSet;
   
   }

   

   public Set<T> complement(LinkedSet<T> s) {
   
      if(s==null)
      
         throw new NullPointerException();
   
      LinkedSet<T> returnSet=new LinkedSet<T>();
   
      Node t=front;
   
      while(t!=null)
      
      {   
      
         if(!s.contains((T)t.element))
         
            returnSet.add((T)t.element);
      
         t=t.next;
      
      }
   
      return returnSet;
   
   }

   

   public Iterator<T> iterator() {
   
      return new LinkedIterator(front);
   
   }

   

   public Iterator<T> descendingIterator() {
   
      return new myDescendingtIterator(rear);
   
   }

   

   public Iterator<Set<T>> powerSetIterator() {
   
      return new myPowerIterator(front,size);
   
   }

   private class LinkedIterator implements Iterator<T>
   
   {
   
      Node t;
   
      public LinkedIterator(Node front)
      
      {
      
         t=front;
      
      }
   
      public boolean hasNext()
      
      {
      
         return t!=null &&t.element!=null;
      
      }
   
      public T next()
      
      {
      
         if(t!=null)
         
         {            
         
            T r=(T)t.element;
         
            t=t.next;
         
            return r;
         
         }
      
         return null;
      
      }
   
      public void remove()
      
      {
      
         throw new UnsupportedOperationException();
      
      }
   
   }

   private class myDescendingtIterator implements Iterator<T>
   
   {
   
      Node t;
   
      public myDescendingtIterator(Node rear)
      
      {
      
         t=rear;
      
      }
   
      public boolean hasNext()
      
      {
      
      //RETURN TRUE IF NEXT ELEMENT IS PRESENT
      
         return t!=null &&t.element!=null;
      
      }
   
      public T next()
      
      {
      
         if(t!=null)
         
         {            
         
            T r=(T)t.element;
         
            t=t.next;
         
            return r;
         
         }
      
         return null;
      
      }
   
      public void remove()
      
      {
      
         throw new UnsupportedOperationException();
      
      }
   
   }

   

   private class myPowerIterator implements Iterator<Set<T>>
   
   {
   
      Node t;
   
      int c;
   
      int current1;
   
      int bit1=0;
   
      public myPowerIterator(Node front,int size)
      
      {
      
         t=front;
      
         c=size;
      
         current1=0;
      
         bit1=0;
      
      }
   
      public boolean hasNext()
      
      {
      
         return (current1<(int)Math.pow(2,c));
      
      }
   
      public Set<T> next()
      
      {
      
         Set<T> r=new LinkedSet<T>();
      
         int m=1;
      
         for(int k1=0;k1<c;k1++)
         
         {
         
            if((bit1&m)!=0)
            
            {
            
               r.add(t.element);
            
               t=t.next;
            
            }
            
            else
            
               t=t.next;
         
         }
      
         current1++;
      
         bit1=0;
      
         t=front;
      
         return r;
      
      }
   
      public void remove()
      
      {
      
         throw new UnsupportedOperationException();
      
      }
   
   }

    /**

     * Defines a node class for a doubly-linked list.

     */

   class Node {
   
        /** the value stored in this node. */
   
      T element;
   
        /** a reference to the node after this node. */
   
      Node next;
   
        /** a reference to the node before this node. */
   
      Node prev;
   
        /**
   
         * Instantiate an empty node.
   
         */
   
      public Node() {
      
         element = null;
      
         next = null;
      
         prev = null;
      
      }
   
        /**
   
         * Instantiate a node that containts element
   
         * and with no node before or after it.
   
         */
   
      public Node(T e) {
      
         element = e;
      
         next = null;
      
         prev = null;
      
      }
   
   }

}

//DRIVER.java



public class LinkedSetIMP{

   public static void main(String []args){
   
      LinkedSet<Integer> set1 = new LinkedSet<Integer>();
   
         
   
      set1.add(99);
   
         
   
      System.out.println("Linked set " + set1);
   
        
   
      set1.add(1);
   
      set1.add(2);
   
      set1.add(3);
   
         
   
      Iterator<Integer> i = set1.iterator();
   
         
   
      System.out.print("Linked Set elements");
   
      while (i.hasNext()) {
      
         System.out.print(i.next() + " ");
      
      }
   
        
   
   }

}