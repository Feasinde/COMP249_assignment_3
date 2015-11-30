import java.util.NoSuchElementException;

public class CellList{
	
	//////////////////////////
	//BEGIN INNER NODE CLASS//
	//////////////////////////
	public class CellNode{
		private CellPhone phone;
		private CellNode link;

		public CellNode(){
			phone = null;
			link = null;
		}

		public CellNode(CellPhone phone, CellNode link){
			this.phone = phone;
			this.link = link;
		}

		//Assignment requires the following two methods. Not really sure what's the point.
		public CellNode(CellNode cellNode){
			phone = cellNode.phone.clone(cellNode.phone.getSerialNum());
			link = cellNode.link;
		}

		public CellNode clone(){
			return new CellNode(this.phone.clone(this.phone.getSerialNum()), this.link);
		}

	}
	////////////////////////
	//END INNER NODE CLASS//
	////////////////////////

	////////////////////////
	//BEGIN ITERATOR CLASS//
	////////////////////////
	public class CellListIterator{
		private CellNode position;
		private CellNode previous;

		public CellListIterator(){
			position = head;
			previous = null;
		}

		public void restart(){
			position = head;
			previous = null;
		}

		public CellPhone next(){
			if (!hasNext()){
				throw new NoSuchElementException();
			}
			CellPhone toReturn = position.phone;
			previous = position;
			position = position.link;
			return toReturn;
		}

		public boolean hasNext(){
			return (position != null);
		}

		public CellPhone peek(){
			if (!hasNext()){
				throw new IllegalStateException();
			}
			return position.phone;
		}
	}
	//////////////////////
	//END ITERATOR CLASS//
	//////////////////////

	private CellNode head;
	private int size;

	//default constructor

	public CellList(){
		head = null;
	}

	//TO DO: copy constructor of CellList

	//addToStart() adds a CellPhone object at the beginning of the list
	public void addToStart(CellPhone cellPhone){	
		head = new CellNode(cellPhone, head);
	}

	//size() returns the number of nodes in the list
	public int size(){
		int count = 0;
		CellNode position = head;

		while (position != null){
			count++;
			position = position.link;
		}

		return count;
	}

	//deleteFromStart() deletes the head node and returns true if the list
	//contains at least one node. Returns false if the list is empty.
	public boolean deleteFromStart(){
		if (head != null){
			head = head.link;
			return true;
		}

		return false;
	}

	//find() takes a CellPhone serial number and returns the pointer
	//of the node that matches it. Returns null if the serial is not
	//found
	public CellNode find(long serial){
		CellNode position = head;
		long serialAtPostion;
		while (position != null){
			serialAtPostion = position.phone.getSerialNum();
			if (serialAtPostion == serial){
				return position;
			}
			position = position.link;
		}
		return null;
	}

	//contains() takes a CellPhone serial number and returns true
	//if the number matches a CellPhone object that belongs in a
	//CellNode
	public boolean contains(long serial){
		return find(serial) != null;
	}

	//showContents() outputs the contents of the list
	public void showContents(){
		CellNode position = head;
		while (position != null){
			System.out.print("["+position.phone+"] ---> ");
			position = position.link;
		}
	}

	//TO DO: clone() method
}