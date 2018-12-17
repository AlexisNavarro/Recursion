//Author: Alexis Navarro
//Lab 6
//Last modification: 11/2/18
public class StringNode {
 public String head;
 public StringNode next;

 StringNode(){}

 StringNode(String s){
 head = s;
 }

 StringNode(String s, StringNode tail){
 head = s;
 next=tail;
 }
 
}//END STRING NODE CLASS
