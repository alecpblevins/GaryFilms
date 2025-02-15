// Alec Blevins, 05/01/24, CustInfo.java, a record of each customer using a BST. 
import java.io.*;
public class CustInfo implements java.io.Serializable{
	private CustRec root = null;

	public void delete(CustRec a) {
		if (root != null){
			if (a.getKey() == root.getKey()){
				root = delete2(root);
			}
			else {
				deleteR(root, a);
			}
		}
		save();
	}

	public CustRec delete2(CustRec p) {
		if (p.getLeft() == null && p.getRight() == null) {
			save();
			return null;
		} else if (p.getLeft() == null) {
			CustRec temp = p.getRight();
			p.setRight(null);
			save();
			return temp;
		} else if (p.getRight() == null) {
			CustRec temp = p.getLeft();
			p.setLeft(null);
			save();
			return temp;
		} else {
			CustRec temp = getSuccessor(p.getRight());
			delete(temp);
			temp.setRight(p.getRight());
			temp.setLeft(p.getLeft());
			p.setRight(null);
			p.setLeft(null);
			save();
			return temp;
		}
	}

	public CustRec getSuccessor(CustRec p) {
		while (p.getLeft() != null) {
			p = p.getLeft();
		}
		save();
		return p;
	}

	public void deleteR(CustRec rt, CustRec d) {
		if (rt.getLeft() != null && d.getKey() < rt.getKey()) {
			if (d.getKey() == rt.getLeft().getKey()) {
				rt.setLeft(delete2(d));
			} else {
				deleteR(rt.getLeft(), d);
			}
		} else if (rt.getRight() != null) {
			if (d.getKey() == rt.getRight().getKey()) {
				rt.setRight(delete2(d));
			} else {
				deleteR(rt.getRight(), d);
			}
		}
		save();
	}

	public void insert(CustRec p) {
		if (root == null) {
			root = p;
		} else {
			insert2(root, p);
		}
		save();
	}

	public void insert2(CustRec root, CustRec p) {
		if (p.getKey() < root.getKey()) {
			if (root.getLeft() == null) {
				root.setLeft(p);
			} else {
				insert2(root.getLeft(), p);
			}
		} else {
			if (root.getRight() == null) {
				root.setRight(p);
			} else {
				insert2(root.getRight(), p);
			}

		}
		save();
	}

	public CustRec search(int key) {
		return searchr(root, key);
	}

	public CustRec searchr(CustRec root, int key) {
		if (root == null) {
			return null;
		} else if (key == root.getKey()) {
			return root;
		} else if (key < root.getKey()) {
			return searchr(root.getLeft(), key);
		} else {
			return searchr(root.getRight(), key);
		}
	}

	public void traverse() {
		traverser(root);
		System.out.println();
	}

	public void traverser(CustRec root) {
		if (root != null) {
			traverser(root.getLeft());
			System.out.print(root.getKey() + " ");
			traverser(root.getRight());
		}
	}

	public boolean isEmptyTree() {
		return root == null;
	}

	public void printTree() {
		printTree2(root);
		System.out.println();
	}

	private void printTree2(CustRec tree) {
		if (tree != null) {
			System.out.print(tree.getKey() + " ");
			if (tree.getLeft() != null)
				System.out.print("Left: " + tree.getLeft().getKey() + " ");
			else
				System.out.print("Left: null ");
			if (tree.getRight() != null)
				System.out.println("Right: " + tree.getRight().getKey() + " ");
			else
				System.out.println("Right: null ");
			printTree2(tree.getLeft());
			printTree2(tree.getRight());
		}
	}
	public void save(){
		try {
		    FileOutputStream file = new FileOutputStream("CustInfo.ser");
		    ObjectOutputStream out = new ObjectOutputStream(file);
		    out.writeObject(this);
		    out.close();
		    file.close();
		} 
		catch (IOException e) {
		    e.printStackTrace();
	       	}	
	}

  	public static CustInfo load(){
	       	CustInfo custInfo;
		try {
		    	FileInputStream file = new FileInputStream("CustInfo.ser");
		    	ObjectInputStream in = new ObjectInputStream(file);
		    	custInfo = (CustInfo) in.readObject();
		   	in.close();
		   	file.close();
		} catch (Exception e) {
		
	       	custInfo = new CustInfo();
			CustRec xCustRec = new CustRec("John",3938, "John@gmail.com", new Wishlist());
        	CustRec yCustRec = new CustRec("Teddy",1948, "Teddy@gmail.com", new Wishlist());
			CustRec zCustRec = new CustRec("Alec",8507, "Alec@gmail.com", new Wishlist());
			CustRec kCustRec = new CustRec("Jack",8114, "Jack@AOL.com", new Wishlist());
			CustRec aCustRec = new CustRec("Grace",1850, "Grace@gmail.com", new Wishlist());
			CustRec mCustRec = new CustRec("Paisly",4846, "Paisly@conncoll.edu", new Wishlist());
			CustRec nCustRec = new CustRec("Nick",4825, "Nick@gmail.com", new Wishlist());
			CustRec oCustRec = new CustRec("Ansily",5653, "Ansily@gmail.com", new Wishlist());
			
			custInfo.insert(xCustRec);
			custInfo.insert(yCustRec);
			custInfo.insert(zCustRec);
			custInfo.insert(kCustRec);
			custInfo.insert(aCustRec);
			custInfo.insert(mCustRec);
			custInfo.insert(nCustRec);
			custInfo.insert(oCustRec);
		}
		return custInfo;
	    }
}

