
public class SCHashTable implements HashTable {

	HashNode[] array;
	HashNode head;
	private int count;

	public SCHashTable(int size) {
		array = new HashNode[size];
	}

	public int size() {
		return count;
	}

	/**
	 * This method computes a hash code for the key. Make sure to check that key
	 * cannot be null. In order to calculate the hash code of the given key, here is
	 * the hash function:
	 * 
	 * the absolute value of key.hashCode() % size of the hash table
	 * 
	 */
	public int hashFunction(String key) {
		int hashValue = Math.abs(key.hashCode() % array.length);
		return hashValue;
	}

	/**
	 * This method adds a (key, value) pair to the hash table. If the key already
	 * exists, update the current value to the given value.
	 */

	public void put(String key, int value) {
		if (key != null) {
			int index = hashFunction(key);
			HashNode node = new HashNode(key, value);
			if (array[index] == null) {
				array[index] = node;
			}
			HashNode current = array[index];
			if (containsKey(key)) {
				while (!current.key.equals(key)) {
					current = current.next;
				}
				current.value = value;
			} else {
				while (current.next != null) {
					current = current.next;
				}
				current.next = node;
				count++;
			}
		}
	}

	/**
	 * This method returns true if the given key and its corresponding value can be
	 * removed from the hash table. False otherwise.
	 */

	public boolean removeKey(String key) {
		if (containsKey(key)) {
			int index = hashFunction(key);
			HashNode current = array[index];
			if (current.key == key) {
				array[index] = current.next;
				return true;
			}
			while (current.next != null && current.next.key != key) {
				current = current.next;
			}
			current.next = current.next.next;
			count--;
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the given key exists in the hash table. False
	 * otherwise.
	 */

	public boolean containsKey(String key) {
		int index = hashFunction(key);
		HashNode current = array[index];
		while (current != null) {
			if (current.key.equals(key)) {
				return true;
			} else {
				current = current.next;
			}
		}
		return false;
	}

	/**
	 * This method returns the value that maps to the given key. You may assume the
	 * key exists in the hash table.
	 */

	public int get(String key) {
		int index = hashFunction(key);
		HashNode current = array[index];
		while (current != null) {
			if (current.key.equals(key)) {
				return current.value;
			}
			current = current.next;
		}
		return -1;
	}
	
	/**
	 * This method returns a formatted String in the following format if you are
	 * implementing open addressing:
	 * 
	 * index (key,value) 0 ("hello", 3) 1 ("bye", 8)
	 * 
	 * This method returns a formatted String in the following format if you are
	 * implementing separate chaining:
	 * 
	 * 
	 * index (key,value) 0 ("hello", 3) -> ("halo", 5) -> ("dog", 8) 1 ("bye", 8)
	 * 
	 */

	public String toString() {
		String str = "index   (key, value)";
		for (int i = 0; i < array.length; i++) {
			HashNode current = array[i];
			str += "\n" + i + "	";
			while (current != null) {
				str += "(\"" + current.key + "\", " + current.value + ")";
				if (current.next != null) {
					str += " -> ";
				}
				current = current.next;
			}
		}
		return str;
	}
}
