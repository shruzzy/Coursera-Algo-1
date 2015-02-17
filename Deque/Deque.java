import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
   private Node<Item> first; //first node of Deque
   private Node<Item> last; //last node of Deque
   private int N; //number of items in Deque
    
   public Deque()                           // construct an empty deque
   {
       first = null;
       last = null;
       N = 0;
   }
   
   public boolean isEmpty()                 // is the deque empty?
   {
       return size() == 0;
   }
   
   public int size()                        // return the number of items on the deque
   {
       return N;
   }
   
   public void addFirst(Item item)          // add the item to the front
   {
       Node<Item> oldFirst = first;
       first = new Node<Item>();
       first.item = item;
       if (oldFirst != null)
       {
           first.next = oldFirst;
           oldFirst.last = first;
       }
        else
       {
            last = first;
       }
       N++;
   }
  
   public void addLast(Item item)           // add the item to the end
   {
       Node<Item> oldLast = last;
       last = new Node<Item>();
       last.item = item;
       if(oldLast != null)
       {
           last.last = oldLast;
           oldLast.next = last;
       }
       else
       {
           first = last;
       }
       N++;
   }
   
   public Item removeFirst()                // remove and return the item from the front
   {
       if (first == null) throw new java.util.NoSuchElementException();
       
       Node<Item> oldFirst = first;
       if(first.next != null)
       {
           first = first.next;
           first.last = null;
       }
       
       N--;
       return oldFirst.item;
   }
   
   public Item removeLast()                 // remove and return the item from the end
   {
       if (last == null) throw new java.util.NoSuchElementException();
       
       Node<Item> oldLast = last;
       if(last.last != null)
       {
           last = last.last;
           last.next = null;
       }
       
       N--;       
       return oldLast.item;
   }
   
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
       return new ListIterator<Item>(first);
   }
   
   private class ListIterator<Item> implements Iterator<Item>
   {
       private Node<Item> current;
       
       public ListIterator (Node<Item> first)
       {
           current = first;
       }
       
       public boolean hasNext() { return current != null; }
       public void remove() { throw new java.lang.UnsupportedOperationException(); }
       
       public Item next() 
       {
           if (!hasNext()) throw new java.util.NoSuchElementException();
           Item item = current.item;
           current = current.next;
           return item;
       }
   }
   
   private static class Node<Item> 
   {
       private Item item;
       private Node<Item> next;
       private Node<Item> last;      
   }
   
   public static void main(String[] args)   // unit testing
   {
       Deque<String> d = new Deque<String>();
       while (!StdIn.isEmpty())
       {
           String item = StdIn.readString();
           if (!item.equals("-")) d.addLast(item);
           else if (!d.isEmpty()) StdOut.print(d.removeFirst() + " ");           
       }
       
       StdOut.println("(" + d.size() + " left on deque)");
   }       
}