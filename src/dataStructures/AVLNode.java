package dataStructures;


public class AVLNode {
	
	public final int val;
	private int ht = 0;
	private AVLNode left = null;
	private AVLNode right = null;
	
	public AVLNode(int val) {
		this.val = val;
	}
	
	
	private static AVLNode leftRight(AVLNode n) {
        
        AVLNode child = n.left;
        n.left = right(child);
        return left(n);        
    }   

    private static AVLNode rightLeft(AVLNode n) {
        AVLNode child = n.right;
        n.right = left(child);
        return right(n);
        
    }

    private static AVLNode left(AVLNode n) {
        
        AVLNode child = n.left;
        n.left = child.right;
        child.right = n; 
        
        setHeight(n);
        setHeight(child);
        
        return child;
    }

    private static AVLNode right(AVLNode n) {
        System.err.println("Rotating node: " + n.val + " left with children left: " + n.left + ", right: " + n.right);
        AVLNode child = n.right;
        n.right = child.left;
        child.left = n; 
        
        setHeight(n);
        setHeight(child);
        
        return child;
    }

    private static void setHeight(AVLNode n) {
        n.ht = Math.max((n.right == null ? -1 : n.right.ht), (n.left == null ? -1 : n.left.ht)) + 1;
    }

    private static int balanceFactor(AVLNode n) {
        if(n == null)
            return 0;
        
        int lheight = n.left == null ? -1 : n.left.ht;
        int rheight = n.right == null ? -1 : n.right.ht;
        
        return lheight - rheight;       
    }

   public static AVLNode insert(AVLNode root, int val)
    {
       AVLNode n = new AVLNode(val);
              
       if(root == null) return n;
       
       root = ins(n, root);
       
       return root;
       
    }

    private static AVLNode ins(AVLNode n, AVLNode root) {
        
        if(n.val < root.val) {
            if(root.left == null) {
                root.left = n;
            }
            else {
                root.left = ins(n,root.left);
            }
            
        }
        else {
            if(root.right == null) {
                root.right = n;
            }
            else {
                root.right = ins(n, root.right);
            }
            
        }
        
        //balance
        
        int bfactor = balanceFactor(root);
        
        if (bfactor >= 2) {
            //one of the left rotations
            if(balanceFactor(root.left) > 0) {
                
                root = left(root);
            }
            if(balanceFactor(root.left) <= 0) {
                root = leftRight(root);
            }
        }
        if(bfactor <= -2) {
        	//one of the right rotations
            if(balanceFactor(root.right) >= 0) {
                
                root = rightLeft(root);
            }
            if(balanceFactor(root.right) < 0) {
                root = right(root);
            }
        }       
        
        //adjust height
        setHeight(root);
        
        return root;        
    }
}