/* CS2210a
 * Brendan Bain
 * 251086487
*/
public class DataNode<Data> {
	private DataNode<Data> next;
	private DataNode<Data> prev;
	private Data data;
	
	public DataNode() {
		next = null;
		prev = null;
	}
	
	public DataNode(Data newData) {
		next = null;
		prev = null;
		data = newData;
	}
	
	public DataNode<Data> getNext(){
		return next;
	}
	
	public DataNode<Data> getPrev(){
		return prev;
	}
	
	public Data getData() {
		return data;
	}
	
	public void setNext(DataNode<Data> nextNode) {
		next = nextNode;
	}

	public void setPrev(DataNode<Data> prevNode) {
		prev = prevNode;
	}
	
	public void setData (Data newData) {
		data = newData;
	}
	
}
