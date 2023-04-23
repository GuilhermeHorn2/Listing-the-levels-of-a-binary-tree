package misc;

import java.util.ArrayList;
import java.util.LinkedList;



public class Graph_1 {
	
    public static class Node{
    	public int _id;
    	public ArrayList<Node> adj;
    	public int marked = 0;
    	
    	public Node(int id) {
    		_id = id;
    		adj = new ArrayList<>();
    	}
    	
    	public String toString() {
    		String s = "";
    		s += _id;
    		return s;
    	}
    	
    }
    
    public ArrayList<Node> nodes;
	
    public Graph_1() {
    	nodes = new ArrayList<>();
    }
    
    public void add(int id) {
     //the nodes will not carry any data,but this is easy to implement
    	Node novo = new Node(id); 
    	nodes.add(novo);
    }
    
    public void link(int id1,int id2){
    	if(id1 >= nodes.size() || id1 < 0) {
    		return;
    	}
    	if(id2 >= nodes.size() || id2 < 0) {
    		return;
    	}
    	if(id1 == id2) {
    		//I dont want link a node to itself
    		return;
    	}
    	
    	// id1 --> id2
    	
    	nodes.get(id1).adj.add(nodes.get(id2));
    	
    	//id2 --> id1
    	
    	nodes.get(id2).adj.add(nodes.get(id1));
  	
    }
    
    public boolean exist_path(int id1,int id2){
    	//Searching by level
    	
    	LinkedList<Node> q = new LinkedList<>(); 
    	nodes.get(id1).marked = 1;//i visited the starting node  
    	q.addFirst(nodes.get(id1));//enqueue   	
    	
    	while(!q.isEmpty()){
    		Node r = q.removeLast();//dequeue
    		
    		System.out.println("Node tested:"+r._id);
    		
    		if(r._id == id2) {
    			return true;
    		}
    		
    		for(int i = 0;i < r.adj.size();i++) {
    			if(r.adj.get(i).marked == 0) {
    				q.addFirst(r.adj.get(i));//enqueue
    				r.adj.get(i).marked = 1;
    			}
    		}    		
    	}
    	return false;
   	
    }
    
    public void reset() {
    	
    	for(int i = 0;i < nodes.size();i++){
    		nodes.get(i).marked = 0;
    	}
    	
    }
    
    public boolean exist_path2(int id1,int id2){
    	//In depth search
    	
    	if(id1 == id2) {
    		return true;
    	}
    	
    	
    	boolean exist = false;
    	
    	nodes.get(id1).marked = 1;
    	
    	for(int i = 0;i < nodes.get(id1).adj.size();i++){
    		Node r = nodes.get(id1).adj.get(i);
    		if(r.marked == 0) {
    			exist =  exist_path2(r._id,id2);
    		}
    	}
    	
    	return exist;
    }
    
    public  LinkedList<LinkedList<Node>> show_levels(){
    	
    	LinkedList<LinkedList<Node>> l = new LinkedList<>(); //the levels will be stored here
    	
    	
    	LinkedList<Node> q = new LinkedList<>();//queue
    	
    	nodes.get(0).marked = 1;
    	q.addFirst(nodes.get(0));
    	
    	int level = 0;
    	int count = 1;
    	int next = 0;
    	while(!q.isEmpty()){
    		LinkedList<Node> aux = new LinkedList<>();	
    		
    		/*
    		 * I cant just do aux = q,because they will point to the same address in memory and the item inside l will be modified
    		 * so i have to copy all elements from q to aux
    		 */
    		
    		if(count == 1) {
        		for(int i = 0;i < q.size();i++) {
        			aux.add(q.get(i));
        		}
    			l.add(aux);
    			//System.out.println("level:" +level+":" +q);
    			level++;
    		}
    		if(count == 2) {
        		for(int i = 0;i < q.size();i++) {
        			aux.add(q.get(i));
        		}
    			next = q.size() + count;
    			l.add(aux);
    			//System.out.println("level:" +level+":" +q);
    			level++;
    		}
    		if(count == next){
        		for(int i = 0;i < q.size();i++) {
        			aux.add(q.get(i));
        		}
    			l.add(aux);
    			next = q.size() + count;
    			//System.out.println("level:" +level+":" +q);
    			level++;
    		}
    		
    		Node n = q.removeLast();
    		
    		for(int i = 0;i < n.adj.size();i++){
    			if(n.adj.get(i).marked == 0) {
        			n.adj.get(i).marked = 1;
        			q.addFirst(n.adj.get(i));
    			}
    		}
            count++;
    	}
    	
    	
    	for(int i = 0;i < l.size();i++) {
    		System.out.println("level:" +i+":" +l.get(i));
    	}
    	
    	return l;
    	
    }
    

}
