/* CS2210a
 * Brendan Bain
 * 251086487
*/
public class Dictionary implements DictionaryADT {
	DataNode[] hashTable = new DataNode[7559];
	int dataItemCount = 0;
	
	public Dictionary(int size) {
		DataNode[] hashTable = new DataNode[size];
	}
	
	
	private int polynomialHashFunction(String key) {	
		int HASH_PRIME = 37;
		int hashValue = (int)key.charAt(0);
		for (int i = 1; i < key.length(); i++) {
			hashValue = (hashValue*HASH_PRIME +(int)key.charAt(i)) % hashTable.length;
		}
		return hashValue;
	}
	
	
	
	public int put(Data record) throws DuplicatedKeyException{
		int result; 													//used to detect if there is collision
		
		if (get(record.getKey()) != null)									//if record is in hashTable
			throw new DuplicatedKeyException("Cannot add data: this key has been used before");
		
		if (hashTable[polynomialHashFunction(record.getKey())] != null) {		//someone has used this location
			result = 1;
			DataNode check = hashTable[polynomialHashFunction(record.getKey())];
			while (check.getNext() != null) {  									//adds new node to the end of the list
				check = check.getNext();
			}
			DataNode newRecord = new DataNode(record);
			newRecord.setPrev(check);
			check.setNext(newRecord);
		}
		else {
			result = 0;
			DataNode newRecord = new DataNode(record);
			hashTable[polynomialHashFunction(record.getKey())] = newRecord;
		}
		dataItemCount+= 1;
		return result;
	}
	
	
	
	public void remove(String key) throws InexistentKeyException{
		//node is not in the list
		if (get(key) == null) {
			throw new InexistentKeyException("Cannot remove data: data with this key not in dictionary");
		}
		
		//node is in the list
		DataNode check = hashTable[polynomialHashFunction(key)];
		if (((Data)check.getData()).getKey().equals(key)) {			//node is at the beginning of list
			if (check.getNext() == null)								//if list only has one node
				hashTable[polynomialHashFunction(key)] = null;
			else {														//if it has other nodes behind it
				hashTable[polynomialHashFunction(key)] = check.getNext();
				check.getNext().setPrev(null);
			}
		}
		else {														//target node is within list or end of list
			while (!((Data)check.getData()).getKey().equals(key))  	 									
				check = check.getNext();
			
			if (check.getNext() == null) {								//node is at the end of the list
				check.getPrev().setNext(null);
			}
			else {														//node is in the middle of the list
				check.getPrev().setNext(check.getNext());
				check.getNext().setPrev(check.getPrev());
			}
		}
		dataItemCount-= 1;
	}

	
	
	public Data get(String key) {
		if (hashTable[polynomialHashFunction(key)] == null)
			return null;
		else {
			DataNode check = hashTable[polynomialHashFunction(key)];
			while (!((Data)check.getData()).getKey().equals(key)) { //while the key in first node != key
				if (check.getNext() == null)
					return null;
				check = check.getNext();
			}
			return (Data)check.getData();
		}
	}
	
	
	public int numDataItems() {
		return dataItemCount;
	}
}

