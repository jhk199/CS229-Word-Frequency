
public class HashNode {
	
	String key;
	int value;
	HashNode next;
	
	public HashNode() {
		
	}
	
	public HashNode(String key) {
		this.key = key;
	}
	
	public HashNode(String key, int value) {
		this.key = key;
		this.value = value;
	}

	
	public HashNode(String key, int value, HashNode next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}

}
