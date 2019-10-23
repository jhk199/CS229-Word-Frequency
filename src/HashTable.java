

public interface HashTable {
	// Do not modify this file.
	// Your hash table implementation should implement this interface
	
	
	/**
		This method computes a hash code for the key.
		Make sure to check that key cannot be null.
		In order to calculate the hash code of the given key, 
		here is the hash function:
		
			the absolute value of key.hashCode() % size of the hash table

	 */
	public int hashFunction(String key);  
	
	
	/**
	 	This method adds a (key, value) pair to the hash table. 
	 	If the key already exists, update the current value to the given value.
	 */
	
	public void put (String key, int value);
	
	/**
		This method returns true if the given key and its corresponding value can be removed from the hash table.
		False otherwise.
	 */
	
	public boolean removeKey (String key);
	
	/**
	 	This method returns true if the given key exists in the hash table.
	 	False otherwise.
	 */
	
	public boolean containsKey (String key) ;
	
	/**
		 This method returns the value that maps to the given key.
		 You may assume the key exists in the hash table.
	 */
	
	public int get (String key);
	
	/**
	 	This method returns a formatted String in the following format if you are implementing open addressing:
	 	
	 		index   (key,value)
	 		0		("hello", 3)
	 		1		("bye", 8)
	 		
	 	This method returns a formatted String in the following format if you are implementing separate chaining:

	 		
	 		index   (key,value)
	 		0		("hello", 3) -> ("halo", 5) -> ("dog", 8)
	 		1		("bye", 8)
	 		
	 */
	
	public  String toString();
	
}
