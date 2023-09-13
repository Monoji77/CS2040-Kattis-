import java.io.*;
import java.util.*;


public class Nicknames {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int a = io.getInt();
        AVLtree record = new AVLtree();

        for (int i = 0; i < a; ++i) {
            record.insert(io.getWord());
        }

        int b = io.getInt();
        
        for (int i = 0; i < b; ++i) {
            StringBuilder s = new StringBuilder(io.getWord());
            
            // query count of strings which contain substring
            int lowerB = record.totalCount(record.root, s.toString());
            
            // query count of strings which contain substring with last character replaced by one character higher
            char c = s.charAt(s.length()-1);
            c++;
            s.setCharAt(s.length()-1, c);
            int upperB = record.totalCount(record.root, s.toString());

            // output difference bw upper and lower bound
            io.println(upperB-lowerB);
        }
        io.close();
    }
}


class Node {
    Node leftChild, rightChild, parent;
    String myself;
    int height, size;
    
    public Node(String s) {
        this.leftChild = this.rightChild = this.parent = null;
        this.myself = s;
        height = 0;
        size = 1;
    }
}

class AVLtree {
    Node root;
        
    public AVLtree() {
        this.root = null;
    }

    int getSize(Node n) {
        if (n != null) {
            return n.size;
        }
        return 0;
    }

    int getHeight(Node n) {
        if (n != null) {
            return n.height;
        }
        return 0;
    }

    void updateHeight(Node n) {
        n.height = Math.max(getHeight(n.leftChild), getHeight(n.rightChild)) + 1;
    }

    // balance factor of leftchild - rightchild (check if diff more than 1 or -1)
    int balanceFactor(Node n) {
        if (n == null) {
            return 0;
        }
        else 
            return getHeight(n.leftChild) - getHeight(n.rightChild);
    }

    void insert(String s) {
        root = insert(root, s);
    }

    Node insert(Node n, String s) {
        
        // line 76-85, inserting new String into AVL tree
        if (n == null) {
            return new Node(s);
        }
        
        if (n.myself.compareTo(s) < 0) {
            
            n.rightChild = insert(n.rightChild, s);
            n.rightChild.parent = n;
        }
        else {
            n.leftChild = insert(n.leftChild, s);
            n.leftChild.parent = n;
        }

        n.size = getSize(n.leftChild) + getSize(n.rightChild) + 1;
        updateHeight(n);

        Node node = n;

        if (balanceFactor(n) == 2 && balanceFactor(node.leftChild) <= 1 && balanceFactor(node.leftChild) >= 0) {
            return rotateRight(node);
        }
        else if (balanceFactor(n) == 2 && balanceFactor(node.leftChild) == -1) {
            node.leftChild = rotateLeft(node.leftChild);
            return rotateRight(node);
        }
        else if (balanceFactor(n) == -2 && balanceFactor(node.rightChild) >= -1 && balanceFactor(node.rightChild) <= 0) {
            return rotateLeft(node);
        }
        else if (balanceFactor(n) == -2 && balanceFactor(node.rightChild) == 1) {
            node.rightChild = rotateRight(node.rightChild);
            return rotateLeft(node);
        }
        
        return n;
    }
    
    Node rotateRight(Node n) {
        Node newMiddle = n.leftChild;
        // set new middle's parent as current node n's parent
        newMiddle.parent = n.parent;

        // set current node n's parent to new middle
        n.parent = newMiddle;
        int sizeofLeft = getSize(newMiddle.rightChild);
        
        // set current node n's leftChild as prev leftChild's rightChild
        n.leftChild = newMiddle.rightChild;

        // update parent of adopted leftChild if the node is not Null
        if (newMiddle.rightChild != null) {
            newMiddle.rightChild.parent = n;
        }

        // update new middle node's right pointer to current node.
        newMiddle.rightChild = n;
        updateHeight(n);
        updateHeight(newMiddle);
        newMiddle.size = getSize(n);
        n.size = getSize(n.rightChild) + sizeofLeft + 1;
        return newMiddle;
    }

    Node rotateLeft(Node n) {
        Node newMiddle = n.rightChild;
        newMiddle.parent = n.parent;
        n.parent = newMiddle;
        int sizeofRight = getSize(newMiddle.leftChild);
        n.rightChild = newMiddle.leftChild;
        if (newMiddle.leftChild != null) {
            newMiddle.leftChild.parent = n;
        }
        newMiddle.leftChild = n;
        updateHeight(n);
        updateHeight(newMiddle);
        newMiddle.size = getSize(n);
        n.size = getSize(n.leftChild) + sizeofRight + 1; 
        return newMiddle;
    }

    

    int totalCount(Node n, String s) {
        int difference = n.myself.compareTo(s);
        if (difference == 0) {
            return getSize(n.leftChild) + 1;
        }
        else if (difference > 0) {
            if (n.leftChild != null) {
                return totalCount(n.leftChild, s);
            }
            else 
                return totalCount(n, n.myself);
        }
        else { // n.myself.compareTo(s) < 0
            if (n.rightChild != null) {
                return totalCount(n.rightChild, s) + getSize(n.leftChild) + 1;
            }
            else 
                return totalCount(n, n.myself) + 1;
        }
    }

    void inorder() {
        inorder(root);
    }

    void inorder(Node n) {
        if (n != null) {
            inorder(n.leftChild);
            System.out.println(n.myself + " ");
            inorder(n.rightChild);
        }
    }
}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
	super(new BufferedOutputStream(System.out));
	r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
	super(new BufferedOutputStream(o));
	r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
	return peekToken() != null;
    }

    public int getInt() {
	return Integer.parseInt(nextToken());
    }

    public double getDouble() { 
	return Double.parseDouble(nextToken());
    }

    public long getLong() {
	return Long.parseLong(nextToken());
    }

    public String getWord() {
	return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
	if (token == null) 
	    try {
		while (st == null || !st.hasMoreTokens()) {
		    line = r.readLine();
		    if (line == null) return null;
		    st = new StringTokenizer(line);
		}
		token = st.nextToken();
	    } catch (IOException e) { }
	return token;
    }

    private String nextToken() {
	String ans = peekToken();
	token = null;
	return ans;
    }
}