//Author: Alexis Navarro
//Lab 6
//Last modification: 11/2/18

//PURPOSE OF THE PROGRAM:
//The purpose of this program is to allow the user to understand the different ways on how to use recursion.
//This allowed me to show my ability and way of thinking with recursion while having to use a linked list.
//I was assigned to work on the empty methods in the code and find a way to make them function by following the instructions of what that method should return or output.
//However, the Operation file highly relies on the StringNode file due to it having the constuctors and variables that will be used inside the Operations file.

import java.io.*;
import java.util.Random;
public class Operations {

 public static void main(String[] args){
    StringNode L=new StringNode("0"+getRandString(2+(int)(Math.random()*5)));
    StringNode temp = L;
    for (int i=1; i<=9;i++){
        temp.next=new StringNode(i+getRandString(2+(int)(Math.random()*5)));
        temp=temp.next;
    }

    System.out.println("All Strings in the list:");
    printMyList(L);
    System.out.println();

    boolean b = isListInOrder(L);
    System.out.println("List is ordered: "+b);
    System.out.println();

    System.out.println("Count of k-length strings");
    System.out.println("k\tNo. of Strings with length k");
    for (int k=0; k<7; k++){
        System.out.println(k+"\t"+countKLenghthStrings(L, k));
    }

    System.out.println("Longest String="+longestStringOfMyList(L));
    System.out.println("Length="+lengthOfMyList(L));

    L=reverseMyList(L);
    System.out.println("All string in the reversed list:");
    printMyList(L);
    System.out.println();


    System.out.println("Remove a given String");
    StringNode LL=removeAStringFromMyList(L, L.next.next.head);
    System.out.println("All strings in the new list:");
    printMyList(LL);
    System.out.println();

    System.out.println("All strings in the previous list:");
    printMyList(L);
    System.out.println();
    System.out.println("Insert a string in a position of the new list:");
    LL=insertAStringIntoMyList(LL, "Hello world", 3);
    printMyList(LL);
    System.out.println();

    b = isListInOrder(L);
    System.out.println("List is ordered: "+b);
    System.out.println();

    LL=insertAStringIntoMyList(LL, "ABBA", 3);
    LL=insertAStringIntoMyList(LL, "DoGeeseSeeGod", 3);

    int c = countPalindromes(LL);
    System.out.println("Found "+c+" palindromes.");

    }//END MAIN


    static String getRandString(int length) {
        String s=" ";
        Random rand = new Random();
        for(int i=0; i<length; i++){
            int c = rand.nextInt(90-65)+65;
            s=s+Character.toString((char)c);
        }
        return s;
    }//END GET RAND STRING
   
   
    /* Write a recursive method to print all the strings in separate lines.
    This method cannot contain any loop (that is, uses of for, while, do while
    are prohibited).
    */
    
    public static void printMyList(StringNode m){
        if(m==null){
            return;
        }
        System.out.println(m.head);
        printMyList(m.next);
    }//END PRINT MY LIST

   /* Write a recursive method to retrieve the number of strings that are longer
 than the length provided in the parameter. This method cannot contain any
 loop (that is, uses of for, while, do while are prohibited).
 */
    public static int countKLenghthStrings(StringNode m, int k){
        if(m==null){
            return 0;
        }
        int count=0;
        if(m.head.length()==k){
            count++;
        }
        return count+countKLenghthStrings(m.next, k);
   }//END countKLenghthStrings
   
    /* Write a recursive method to retrieve the largest String from the list.
    Assume that there is at least one String in the given list when the method
    is called from the main function. This method cannot contain any loop (that
    is, uses of for, while, do while are prohibited).
    */
    
   public static String longestStringOfMyList (StringNode m){
    String longest= m.head;
    if(m.next==null){
        return longest;
    }

    String nextString=longestStringOfMyList(m.next);//made a string variable called nextString that goes through the first base case, but goes through the method recursevly

    if(longest.length()>=nextString.length()){//if the length of my longest variable is greater than the length of the nextString variable then 
        return longest;                       // we return longest
    }
    return nextString;//if the if statement is false then we return nextString which is m.next
    }
   
    /* Write a recursive method to compute the length of a list.
    This method cannot contain any loop (that is, uses of for, while, do while
    are prohibited).
    */
   public static int lengthOfMyList(StringNode m){
        if(m==null){
            return 0;
        }
        return 1+lengthOfMyList(m.next);
    } 
   
    /* Write a recursive method named reverseMyList that will reverse a given
    linked list m. Return the head of the reversed linked list. It is fine
    if you need to modify the given linked list to obtain the reversed one.
    */
    public static StringNode reverseMyList(StringNode m){
    StringNode newHead;
        //base case with the tail of the list
        if(m==null){
            return null;
        }
        if(m.next==null){
            return m;
        }
        
        StringNode nextN = m.next;
        m.next=null;
        newHead=reverseMyList(nextN);//gets the next node in the list with recursion

        // reverse the links
        nextN.next=m;
        return newHead;
    }
    
   
    /* Write a recursive method to remove the first occurrence of a specific
    String from a list. As an example, if your list initially contains
    AA BB CC DD BB KK and if your removee is BB, the returned list should contain
    AA CC DD BB KK after the removal. Return a new head. You must make sure that
    the parameter list m remains intact after returning the new list to the main
    method. This method cannot contain any loop (that is, uses of for, while,
    do-while are prohibited).
    */
   public static StringNode removeAStringFromMyList(StringNode m, String removee){
        if(m==null){
            return m;
        }
        if(m.head.equals(removee)){
            return removeAStringFromMyList(m.next, null);//this base case is if the head is equal to the remove string while ignoring any case issues
        }
        //if none of the base cases take place then I made a new StringNode that contains the StringNode m.head, so I don't lose it.
        StringNode current = new StringNode(m.head);
        current.next=removeAStringFromMyList(m.next, removee);//current.next now goes recursively through the method to see if the string will be removed while it stores the rest of the list.
        return current;
    }
   
    /* Write a recursive method to insert a String (insertee) into a specific
    position of a list. Positions start from 0 (that is, the position of
    the head of a list is 0). This method cannot contain any loop (that is,
    uses of for, while, do-while are prohibited).
    */
   public static StringNode insertAStringIntoMyList(StringNode m,String insertee, int position){
    StringNode insert;   
    if(position<0){
            return m;
        }
    if(position==0){
        insert=new StringNode(insertee, m);//if the position is at the head then the insert variable will now have a new StringNode which is the head
        return insert;
    }
    StringNode current = insertAStringIntoMyList(m.next, insertee, position-1);//if the position is not found then we create a current StringNode that goes recursevly into the method to find the node with the same position
    m.next=current;
    return m;
    }
   
    /* Write a recursive method to verify if the strings are
    lexicographically ordered in a linked list. Return true if they are
    ordered, false otherwise. This method cannot contain any loop (that is,
    uses of for, while, do-while are prohibited).
    */
   public static boolean isListInOrder(StringNode m){
    if(m==null||m.next==null){
        return true;
    }
    if(m.head.compareTo(m.next.head)>0){//here we compare the head to next node in the linked list while making sure that the strings are lexicographically ordered
        return false;
    }
    return isListInOrder(m.next);
   }
   
    /* Write a recursive method that will count how many strings of a given
   linked list are palindromes. The method must call another recursive
    method named isPalindrome to verify if a String is a palindrome, or
    not. Palindrome checks must NOT be case-sensitive. Neither countPalindromes
    nor isPalindrome may contain loops (that is, uses of for, while, do-while
    are prohibited).
    */
    
   public static int countPalindromes(StringNode m){
    if(m==null){
        return 0;
    }
    int count=0;
    if(isPalindrome(m.head)){
        System.out.println("Palindrome:" + m.head);
        count++;
    }
    return count+countPalindromes(m.next);
   }//END COUNT PALINDROMES
   
  public static boolean isPalindrome(String s){
    if(s.length()==0||s.length()==1){
        return true;
    }
    
        return (Character.toLowerCase(s.charAt(0)) == Character.toLowerCase(s.charAt(s.length() - 1))) &&  //returned this line in order to avoid having 12 palindromes by comparing the first character of the string to the last character of the string
         isPalindrome(s.substring(1,s.length()-1));//goes to the next character of the string while it decreases the length of the end of the string
    }//END IS PALINDROME
   }//end class