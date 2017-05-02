package dataStructures;


public class AVLNode {
	
	public int val;
	private int ht = 0;
	private AVLNode left = null;
	private AVLNode right = null;
	
	public AVLNode(int val) {
		this.val = val;
	}
	
	public static int minValue(AVLNode root) {
		
		if(root.left == null) return root.val;
		else return minValue(root.left);
	}
	
	public static int maxValue(AVLNode root) {
		
		if(root.right == null) return root.val;
		else return minValue(root.right);
	}
	
	public static boolean contains(AVLNode root, int val) {
		if(root == null) return false;
		if(val == root.val) return true;
		
		if(val < root.val) return contains(root.left, val);
		return contains(root.right, val);
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

    public static AVLNode delete(AVLNode root, int val) {
    	
    	if(root == null) return null;
    	
    	if(val < root.val) {
    		root.left = delete(root.left, val);
    	}
    	
    	if(val > root.val) {
    		root.right = delete(root.right, val);
    	}
    	
    	if(val == root.val) {
    		//found it
    		
    		if(root.left == null) {
    			return root.right;
    		}
    		else if(root.right == null) {
    			return root.left;
    		}
    		else {
    		
    			//complicated case (2 children)
    			root.right = swapAndDel(root.right, root);
    		}
    		
    	}
    	
    	
    	//balance and return
        return balance(root);
    }
    
    
    private static AVLNode swapAndDel(AVLNode root, AVLNode parent) {
    	
    	if(root.left != null) {
    		root.left = swapAndDel(root.left, parent);
    	}
    	else {
    		parent.val = root.val;
    		return root.right;
    	}
    	
    	
    	//balance and return
        return balance(root);
	}


	public static AVLNode insert(AVLNode root, int val) {
    
       AVLNode n = new AVLNode(val);
              
       if(root == null) return n;
       
       return ins(root, n);
       
    }

    private static AVLNode ins(AVLNode root, AVLNode n) {
        
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
        
        //balance and return
        return balance(root);
               
    }
    
    private static AVLNode balance(AVLNode root) {
    	
    	 int bfactor = balanceFactor(root);
         
         if (bfactor >= 2) {
             //one of the left rotations
             if(balanceFactor(root.left) >= 0) {
                 
                 root = left(root);
             }
             if(balanceFactor(root.left) < 0) {
                 root = leftRight(root);
             }
         }
         if(bfactor <= -2) {
         	//one of the right rotations
             if(balanceFactor(root.right) > 0) {
                 
                 root = rightLeft(root);
             }
             if(balanceFactor(root.right) <= 0) {
                 root = right(root);
             }
         }       
         
         //adjust height
         setHeight(root);
         
         return root; 
    }
}