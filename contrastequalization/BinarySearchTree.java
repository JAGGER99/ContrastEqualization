
package contrastequalization;

import static java.lang.Integer.max;

/*
This BinarySearchTree class contains methods for adding a node, searching for a
node with a particular intensity value, calculating the height of the tree,
and performing an in-order traversal for setting the cumulative pixel counts
and new intensity values for each node.
*/
public class BinarySearchTree {
    
    //data fields:
    Node root = null;
  
    //default constructor:
    public BinarySearchTree() {
    }
    
    //methods:
    
    //wrapper add method:
    public void add(int data) {
        root = rAdd(root, data);
    }
    
    //recursive add method:
    public Node rAdd(Node n, int data) {
        
        //didn't find it so new Node needs to be made with "data":
        if(n == null) {
            
            n = new Node(data);
            n.pixelCount = 1;
            return n;
        }
        else {
            //if the current node's intensity > data:
            if(n.intensity > data) {
                n.leftChild = rAdd(n.leftChild, data);
                return n;
            }
            //if the current node's intensity < data:
            else {
                n.rightChild = rAdd(n.rightChild, data);   
                return n;
            }
        }
    } 
    
    //search wrapper method:
    public Node search(int i) {
        
        return rSearch(i, root);
    }
    
    //recursive search method:
    public Node rSearch(int intensity , Node n) {
        
        //didn't find it:
        if(n == null) {
            return null;
        }
        else {
            //found it:
            if(intensity == n.intensity) {
                return n;
            }
            //continue to search the right hand side:
            else if(intensity > n.intensity) {
                return rSearch(intensity, n.rightChild);
            }
            //continue to search the left hand side:
            else {
                return rSearch(intensity, n.leftChild);
            }
        }
    }
    
    //height wrapper method:
    public int height() {
        return rHeight(root);
    }
    
    //height method:
    public int rHeight(Node n) {
        
        //if we hit a dead end:
        if(n == null) {
            return 0;
        }
        else {
            //return (the greater height between the left and right child) + 1:
            return max(rHeight(n.leftChild) , rHeight(n.rightChild)) + 1;
        }
    } 
    
   
    //wrapper class for finding each node's new intensity value and cumulative count:
    public int newIntensityValues(double totalIntensities) {
        
        return rNewIntensityValues(root, 0 ,totalIntensities);
    }
    
    /*
    in-order traversal recursive method for calculating the cumulative count
    and new intensity value for each node in the tree:
    */
    public int rNewIntensityValues(Node n , int currentCount, double totalI) {
        
        /*
        hit a dead end & return the previous cumulative count (currentCount) to
        be added with n's pixelCount to get the next cumulative count: 
        */
        if(n == null) {
            
            return currentCount;
        }
        else {
            
            //visit the left side:
            n.cum_count = rNewIntensityValues(n.leftChild, currentCount , totalI) + n.pixelCount;
            
            //calculating the new intensity value for n:
            double newI = (n.cum_count / totalI) * 255;
            
            // cast to an int to cut off the decimal part:
            n.newIntensity = (int) newI;
            
            // visit the right side:
            return rNewIntensityValues(n.rightChild , n.cum_count , totalI);
        }
    }
    
}
