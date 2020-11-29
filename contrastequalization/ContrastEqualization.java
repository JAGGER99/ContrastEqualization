/*
File Name : ContrastEqualization
Program Author(s) : Joshua Greene
Program Tester : Micah Hays
Course Number & Title : Data Structures (COSC2203)
Assignment Number & Name : Assignment #3: Contrast Equalization
Due Date : 10/7/2020

Description : 

This program takes in a series of intensity values that simulate an image.
Using a binary search tree, this program calculates new intensity values for
each intensity value given. It then creates a histogram dividing the number of
pixel values in the grey-scale range [0,...,255] into 16 sub intervals 
( [0-15] , [16-31] , ... , [240 - 255] ). Every 20 pixels is represented by a "*"
The program then prints out the new intensity values in the same positions the
original intensity values were in to simulate the improved image.

*/

package contrastequalization;

import java.util.*;

//This is the driver class for this project:
public class ContrastEqualization {

    //This is the main method:
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        //variables:
        int rows = 0;
        int columns = 0;
        double totalIntensities = 0;
        int intensityValue = 0;
        int numOfNodes = 0;
        Node nodeReturned = null;
        
        int lower = 0;
        int higher = 0;
        int totalPixelCount = 0;
        
        //creating an object of my BinarySearchTree class:
        BinarySearchTree tree = new BinarySearchTree();
        
        rows = input.nextInt();
        columns = input.nextInt();
        
        totalIntensities = rows * columns;
        
        //creating arraylist for holding UNIQUE intensity values:
        ArrayList<Integer> myList = new ArrayList<>();
        
        //creating an array to hold EVERY intensity value:
        int[] intensityArray = new int[rows*columns];
        
        //creating the binary search tree:
        for(int j = 0; j < (rows*columns); j++) {
            
            intensityValue = input.nextInt();
            
            //filling array with the current intensity value:
            intensityArray[j] = intensityValue;
            
            //calling search method in BinarySearchTree and returning a Node object:
            nodeReturned = tree.search(intensityValue);
            
            //if there is no node with this intensity value:
            if(nodeReturned == null) {
                
                //adding new intensity value to my array list:
                myList.add(intensityValue);
                
                //calling add method from BinarySearchTree:
                tree.add(intensityValue);
                
                //incrementing node counter:
                numOfNodes = numOfNodes + 1;
            }
            //the node with the current intensity value was found:
            else { 
                
                //increase count for that intensity:
                nodeReturned.pixelCount += 1;
            }
        }
        
        //outputting height and number of nodes in the tree:
        System.out.println("Height: " + tree.height());
        System.out.println("Number of Nodes: " + numOfNodes);
        
        //setting new intensities and cumulative values:
        tree.newIntensityValues(totalIntensities);
        
        ///////////////////////////////////////////////////////////////////////
        //Outputting the Histogram:
        
        System.out.println("\nIntensity Histogram:");
        
        System.out.println("Intensity     Pixel");
        System.out.println("Range         Count     Markers");
        
        //outputting the ranges, counts, and histogram:
        for(int k = 0; k < 16; k++) {
            
            //calculating next "higher":
            higher = lower + 15;
            
            //reset total pixel count for each range:
            totalPixelCount = 0;
            
            //looping through each unique intensity value:
            for(int x = 0; x < myList.size(); x++) {
                
                //creating node object to hold returned value from search method:
                Node node = tree.search(myList.get(x));     
                
                //increase total pixel count if in the current range:
                if(node.newIntensity >= lower && node.newIntensity <= higher) {
                    
                    totalPixelCount = totalPixelCount + node.pixelCount;                    
                }   
            }
            
            //output for each range:
            System.out.println(lower + " - " + higher + 
            "        " + totalPixelCount + 
            "        " + numOfMarkers(totalPixelCount));
            
            //calculating next "lower" value:
            lower = higher + 1;
            
        } //end of for-loop.
        
        ///////////////////////////////////////////////////////////////////////
        //Outputting the new intensities:
        
        System.out.println("\nImage with New Intensities: ");
        
        for(int i = 0; i < totalIntensities; i++) {
           
           Node n = tree.search(intensityArray[i]);
           
           if( i % columns == 0) {
               System.out.println();
           }
           System.out.print(n.newIntensity + " ");  
        }      
        
    } //end of main method.
    
    //This is the method for finding the number of "*" to print to console:
    public static String numOfMarkers(int pixCount) {
           
       int times = 0;
       String stars = "";
        
       if(pixCount % 20 == 0) {
            
           times = pixCount / 20;           
       }
       else {
           times = (pixCount / 20) + 1;
       }
        
       for(int i = 0; i < times; i++) {
                
           stars += "*";        
       }
        
       return stars;
    }
    
}
