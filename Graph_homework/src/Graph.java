import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;


public class Graph {
	
	private Map<String, List<String>> adjList;	
	
	public Graph() {
		this.adjList = new HashMap<String,List<String>>();
	}
	
	public void addVertex(String name) {
		adjList.putIfAbsent(name, new ArrayList<>());
	}
	
	ArrayList<Edge> edgeList = new ArrayList<Edge>();
	
	public void addEdge(String nameSource, String nameDest, int weight) {
		Edge edge = new Edge(nameSource, nameDest, weight );
		if(!adjList.get(nameSource).contains(nameDest)) {
			adjList.get(nameSource).add(nameDest);
			adjList.get(nameDest).add(nameSource);
			edgeList.add(edge);
		}
	}
	
	
	//PRINT GRAPH
	public void printGraph() {
		Object[] keys = adjList.keySet().toArray();
		System.out.println("Adjcency List:"); 
		for(int i = 0; i < adjList.keySet().size(); i++){ 
			System.out.println("\n vertex:" +  keys[i] + "--> neighboors" + adjList.get(keys[i]));
		}
		 
	}
	
	//DEGREE OF VERTEX
	public void degreeOfVertex(String name) {
		System.out.println(adjList.get(name).size());
	}
	
	//BREADTH FIRST SEARCH
	public void BFSearch(Graph graph, String source) {
        Set<String> visited = new LinkedHashSet<String>();
        Queue<String> queueBTS = new LinkedList<String>();
        queueBTS.add(source);
        visited.add(source);
        String vertex = source;
        while(!queueBTS.isEmpty()){
        	for(int i = 0; i < adjList.get(vertex).size(); i++) {
        		String a = adjList.get(vertex).get(i).toString();
        		if(!visited.contains(a)) {
        			visited.add(a);
        			queueBTS.add(a);
        		}
        	}
        	queueBTS.remove();
        	vertex = queueBTS.peek();
        	System.out.println(queueBTS);
        	System.out.println(visited);
        }
	}
	
	//DEPTH FIRST SEARCH
	Set<String> visited = new LinkedHashSet<String>();
	Stack<String> stackDFS = new Stack<String>();
	
	public void DFSearch(String source) {
		stackDFS.push(source);
		visited.add(source);
		String vertex ="";
		for(int i = 0; i < adjList.get(source).size(); i++) {
			if(!visited.contains(adjList.get(source).get(i).toString())){
				vertex = adjList.get(source).get(i).toString();
				DFSearch(vertex);
				System.out.println(vertex);
			}
		}
		if(vertex == source) {
			stackDFS.pop();
			vertex = stackDFS.lastElement().toString();
		}
		System.out.println("visited:" + visited);
	}
	
	//SELECTION SORT 
	void sort( ArrayList<Edge> EdgeList)  { 
        int n = EdgeList.size(); 
        for (int i = 0; i < n-1; i++) { 
            int min_index = i; 
            for (int j = i+1; j < n; j++) {
                if (EdgeList.get(j).getWeight()< EdgeList.get(min_index).getWeight()) 
                    min_index = j; }
            
            Edge temp = EdgeList.get(min_index);
            EdgeList.set(min_index, EdgeList.get(i));
            EdgeList.set(i, temp);
        } 
    } 

	//MINIMUM SPANNING TREE ALGORITHM - KRUSKAL
	public void KruskalsMST() {
		sort(edgeList);
		int numberOfVertices = adjList.keySet().size();
		int numberOfAddedEgdes = 0;
		ArrayList <Edge> spanniglist = new ArrayList<Edge>();
		int i = 0;
	   while(numberOfAddedEgdes <numberOfVertices-1){
		Edge newEdge = edgeList.get(i);
		ArrayList<String> vertexList = new ArrayList<String>(); 
		for(int j = 0; j < spanniglist.size(); j++ ) {
			vertexList.add(spanniglist.get(j).getDest());
			vertexList.add(spanniglist.get(j).getSource());
		}
		if(!(vertexList.contains(newEdge.getDest()) && vertexList.contains(newEdge.getSource())) ) {
			spanniglist.add(newEdge);
			numberOfAddedEgdes++;}
		i++;
	}for(int k = 0; k < spanniglist.size(); k++) {
		System.out.println("spanningl list: " + spanniglist.get(k).getSource() + spanniglist.get(k).getDest());	
	}
}
	
int sum = 0;
		
public void DijkstraSP(String source) {
	Map<String, Integer> shortestDistance =  new HashMap<String, Integer>();
	Map<String, String> prevVertex = new HashMap<String, String>();
	Object[] keys = adjList.keySet().toArray();
	ArrayList<String> visited = new ArrayList<String>();
	ArrayList<String> unvisited = new ArrayList<String>();
	String current = source;
	
	for(int i = 0; i < keys.length; i++) {
		int dist = 1000;
		if(keys[i].toString() == current) {
			shortestDistance.put(keys[i].toString(), 0);
		}
		else {
			shortestDistance.put(keys[i].toString(), dist);
		}
		prevVertex.put(keys[i].toString(), "");
		unvisited.add(keys[i].toString()); 
	}
	
	
while(!unvisited.isEmpty())	{
	System.out.println(current);
    for (int i = 0; i < edgeList.size(); i++) {  
    	if(edgeList.get(i).getSource() == current && !visited.contains(edgeList.get(i).getDest())) {
        	int sum = shortestDistance.get(current) + edgeList.get(i).getWeight();    
        	System.out.println(sum);
    		if(sum < shortestDistance.get(edgeList.get(i).getDest())) {
    			shortestDistance.replace( edgeList.get(i).getDest(), sum);
    			prevVertex.replace( edgeList.get(i).getDest(), current);

    			}
    	}
    	else if(edgeList.get(i).getDest() == current && !visited.contains(edgeList.get(i).getSource())) {
        	int sum = shortestDistance.get(current) + edgeList.get(i).getWeight();
        	System.out.println(sum);
    		if(sum < shortestDistance.get(edgeList.get(i).getSource())) {
    			shortestDistance.replace( edgeList.get(i).getSource(), sum);
    			prevVertex.replace( edgeList.get(i).getSource(), current);
    			}
    	}
    }
    
    visited.add(current);
    unvisited.remove(current);
    
   int min = 1000;
   for(int i = 0; i < keys.length; i++) {
	   if(shortestDistance.get(keys[i].toString()) < min  && !visited.contains(keys[i].toString())){
		   min = shortestDistance.get(keys[i].toString());
		   current = keys[i].toString();
	   }
   }
} 
for(int i = 0; i <keys.length; i++){ 
	System.out.println("\n vertex:" +  keys[i] + "--> Distance " + shortestDistance.get(keys[i]) +  "--> Prev Vertex " + prevVertex.get(keys[i]));
	}
}
    
 	
	//EULERÝAN CHECK
	public boolean isEulerianCircuit() {
		int numberOfOddDegree = 0;
		Object[] keys = adjList.keySet().toArray();
		for(int i = 0; i < adjList.keySet().size(); i++) {
			if (adjList.get(keys[i]).size() % 2 != 0) {
				numberOfOddDegree ++;
			}
		}
				System.out.println(numberOfOddDegree);
				if(numberOfOddDegree >2) {
					System.out.println("Graph is not Eulerian");
					return false;
				} 
				else if(numberOfOddDegree ==2) {
					System.out.println("Graph is semi Eulerian");
					return false;
				}
				else if(numberOfOddDegree == 0) {
					System.out.println("Graph is Eulerian");
					return true;
				}
				else return false;
	}
	
	//EULERIAN CIRCUIT
		public void eulerianCircuit() {
			if(isEulerianCircuit()) {
				Object[] keys = adjList.keySet().toArray();
				 for (int i = 0; i <adjList.get(keys[i]).size(); i++) 
			        { 
					 if(adjList.get(keys[i]).size() == 1) {
						 System.out.println("edge is a bridge");
					 }
					 
//			            Integer v = adj[u].get(i); 
//			            // If edge u-v is a valid next edge 
//			            if (isValidNextEdge(u, v))  
//			            { 
//			                System.out.print(u + "-" + v + " "); 
//			                  
//			                // This edge is used so remove it now 
//			                removeEdge(u, v);  
//			                printEulerUtil(v); 
//			            } 
			        } 
			}
		}
	
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		
		graph.addVertex("a");
		graph.addVertex("h");
		graph.addVertex("3");
		graph.addVertex("j");
		graph.addVertex("5");
		graph.addVertex("1");


		
		
		graph.addEdge("a", "1", 5);
		graph.addEdge("a", "3", 1);
		graph.addEdge("a", "h", 2);
		graph.addEdge("5", "3", 9);
		graph.addEdge("1", "j", 10);
		graph.addEdge("j", "h", 11);
		graph.addEdge("a", "5", 12);



		
		graph.printGraph();
		
		
		//graph.degreeOfVertex("a");
				
		//graph.KruskalsMST();
		
		//graph.getVerticexandEdges();
		
		graph.DijkstraSP("5");
		
		// graph.isEulerianCircuit();
		

		
		//graph.DFSearch("3");
		
	}
	

}
