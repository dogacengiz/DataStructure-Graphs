
public class Edge {

	int weight;
	String source, dest;
	
	Edge(){
		this.source = null;
		this.weight = 0;
		this.dest = null;
	}
	
	Edge(String source, String dest, int weight){
		this.source = source;
		this.weight = weight;
		this.dest = dest;
		
	}
	
	Edge(String source, String dest){
		this.source = source;
		this.dest = dest;

	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public void setDest(String dest) {
		this.dest = dest;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public String getDest() {
		return this.dest;
	}
	
	public int getWeight() {
		return this.weight;
	}
}
