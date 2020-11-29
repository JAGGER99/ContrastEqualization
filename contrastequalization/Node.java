
package contrastequalization;

//The Node class contains all of the data for each node in the BinarySearchTree.
public class Node {
    
    //data fields:
    int intensity = 0;
    int pixelCount = 0;
    int cum_count = 0;
    int newIntensity = 0;
    Node leftChild = null;
    Node rightChild = null;
    
    //constructor:
    public Node(int i) {
        intensity = i;
    }
    
}
