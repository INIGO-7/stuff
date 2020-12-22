package collections.arraylist;

import java.util.*; 

public class Arraylist { 
    public static void main(String[] args) 
    { 
    	
//        int n = 3; 
//  
//        // aList is an ArrayList of ArrayLists 
//        ArrayList<ArrayList<Integer>> masterArrayList =  new ArrayList<ArrayList<Integer> >(n); 
//        
//        
//        // Create n lists one by one and append to the  
//        // master list (ArrayList of ArrayList) 
//        ArrayList<Integer> a1 = new ArrayList<Integer>(); 
//        a1.add(1); 
//        a1.add(2); 
//        masterArrayList.add(a1); 
//  
//        ArrayList<Integer> a2 = new ArrayList<Integer>(); 
//        a2.add(5); 
//        masterArrayList.add(a2); 
//  
//        ArrayList<Integer> a3 = new ArrayList<Integer>(); 
//        a3.add(10); 
//        a3.add(20); 
//        a3.add(30); 
//        masterArrayList.add(a3); 
//  
//        for (int i = 0; i < masterArrayList.size(); i++) { 
//            for (int j = 0; j < masterArrayList.get(i).size(); j++) { 
//                System.out.print(masterArrayList.get(i).get(j) + " "); 
//            } 
//            System.out.println(); 
//        }
        
        ArrayList<ArrayList<Integer>> masterArrayList = new ArrayList<>();
      
        
	    ArrayList<Integer> a1 = new ArrayList<Integer>(); 
	    a1.add(1); 
	    a1.add(2); 
	    masterArrayList.add(a1); 
	
	    ArrayList<Integer> a2 = new ArrayList<Integer>(); 
	    a2.add(5); 
	    masterArrayList.add(a2); 
	
	    ArrayList<Integer> a3 = new ArrayList<Integer>(); 
	    a3.add(10); 
        a3.add(20); 
        a3.add(30); 
        masterArrayList.add(a3); 

        for (int i = 0; i < masterArrayList.size(); i++) { 
            for (int j = 0; j < masterArrayList.get(i).size(); j++) { 
                System.out.print(masterArrayList.get(i).get(j) + " "); 
            } 
            System.out.println(); 
        }
    } 
}