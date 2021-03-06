//-----------------------------------------------------------------------
//Assignment 3
//Part 1
//Written by: 
//Sviki Gabbay - 27490968 
//Andrés Lou 24712374
//Victoria Avgoustis 27529198
//Laura Elena González 27217323	
//
//COMP 249 - Section D
// This class has the various methods and constructors that will initialize the linked
// lists, modify them (adding nodes/removing), search for serial and numbers and others.
// Above each method the will be a brief description on what the method does and how.
//-----------------------------------------------------------------------
import java.util.NoSuchElementException;
public class CellList{

	//////////////////////////
	//BEGIN INNER NODE CLASS//
	//////////////////////////
	public class CellNode{
		private CellPhone phone;
		private CellNode link;
		//default constructor
		public CellNode(){
			phone = null;
			link = null;
		}

		//parametrised constructor
		public CellNode(CellPhone phone, CellNode link){
			this.phone = phone;
			this.link = link;
		}

		//Assignment requires the following two methods. Not really sure what's the point.
		public CellNode(CellNode cellNode){
			phone = cellNode.phone.clone();
			link = cellNode.link;
		}

		public CellNode clone(){
			return new CellNode(this);
		}
		//Accessors and Mutators//
		public void setCellPhone(CellPhone cellPhone){
			phone = cellPhone;
		}

		public void setLink(CellNode link){
			this.link = link;
		}

		public CellPhone getCellPhone(){
			return phone;
		}

		public CellNode getLink(){
			return link;
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
	private int counter;

	//default constructor
	public CellList(){
		head = null;
		size = 0;
	}

	public void setCounter(int increment){
		this.counter = increment;
	}
	
	public int getCounter(){
		return counter;
	}
	//copy constructor of CellList. This is an adaptation of the
	//code written by Prof Aiman Hanna (C) 1993 - 2014
	//http://aimanhanna.com/concordia/comp249/LinkedList5.java
	public CellList(CellList cellList){
		if (cellList == null) throw new NullPointerException();
		if (cellList.head == null){
			head = null;
		}
		else{
			head = null;

			//temporary CellNode pointers that will reference the 
			//contents of each other as we populate the list
			CellNode temp1, temp2, temp3;

			//first pointer references the first node on the original
			//list
			temp1 = cellList.head;

			//second pointer will reference the first object on the
			//copied list only once
			temp2 = null;

			//third pointer will reference the most recently copied
			//node on the copied list
			temp3 = null;

			while(temp1 != null){
				if (temp2 == null){				//only for the first node
					temp2 = new CellNode(temp1);
					head = temp2;
				}
				else{
					temp3 = new CellNode(temp1);
					temp2.setLink(temp3);
					temp2 = temp3;
				}
				temp1 = temp1.getLink();
			}
		}
	}

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

	//Laura's contribution
	//InsertAtIndex() takes a given position and adds an object at that point using iterators//
	public void insertAtIndex(CellPhone cellPhone, int index){
		try{
			if (index < 0 || index > (this.size()-1)){
				throw new NullPointerException();
			}
			else if (index == 0)
				this.addToStart(cellPhone);
			else{
				CellListIterator iterator = new CellListIterator();
				int count = 0;
				while(count < index){
					iterator.next();
					count++;
				}
				CellNode temp = new CellNode(cellPhone, iterator.position);
				iterator.previous.link = temp;			
			}
			size++;
		}
		catch (Exception e){
			System.out.println("Invalid Index! Terminating Program.");
			System.exit(0);	
		}
	}

	//Laura's contribution
	//deleteFromIndex() removes a node from the list at the specified index
	public void deleteFromIndex(int index){
		try{	
			if (index < 0 || index > (this.size()-1)){
				throw new NullPointerException();
			}
			else if(index == 0)
				this.deleteFromStart();
			else{
				CellListIterator iterator = new CellListIterator();
				int count = 0;
				while(count < index){
					iterator.next();
					count++;
				}
				iterator.previous.link = iterator.position.link;
				iterator.position = iterator.position.link;
			}	
			size--;	
		}
		catch (Exception e){
			System.out.println("Invalid Index! Terminating Program.");
			System.exit(0);	
		}

	}

	//Laura's Contribution
	//ReplaceatIndex takes a node and replaces it with another node without changing the size of the node//
	public void ReplaceAtIndex(CellPhone cellPhone, int index){
		try{
			if (index < 0 || index > (this.size()-1))
				throw new NullPointerException();
							
			else if (index == 0)			
				head =  new CellNode(cellPhone, head);
			else{
				CellListIterator iterator = new CellListIterator();
				int count = 0;
				while(count < index){
					iterator.next();
					count++;
				}
				iterator.position.phone = cellPhone;
				iterator.previous.link = iterator.position;
			}
		}
		catch (Exception e){
			System.out.println("Invalid Index! Terminating Program.");
			System.exit(0);	
		}		
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
		int count = 0;
		CellNode position = head;
		long serialAtPostion;
		while (position != null){
			count++;
			setCounter(count);
			serialAtPostion = position.phone.getSerialNum();
			if (serialAtPostion == serial){
				getCounter();
				return position;
			}
			position = position.link;
		}	
		getCounter();
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
		int node_per_line = 0;
		
		System.out.println("The current size of the list is "+this.size()+". Here are the contents of the list.");
		System.out.println("====================================================================== \n");
		while (position != null){
			if (node_per_line !=3){
				node_per_line++;
				System.out.print("["+position.phone+"] ---> ");
				position = position.link;
			}
			else if (node_per_line == 3){
				node_per_line = 1;
				System.out.print("\n["+position.phone+"] ---> ");
				position = position.link;
			}
		}
		System.out.print("X\n");
	}

	//clone() method
	public CellList clone(){
		return new CellList(this);
	}

	//equals() method. See the book on how to write an equals
	//method of a linked list

	public boolean equals(Object otherObject){
		if	(otherObject == null)
				return	false;
		else if	(getClass( ) != otherObject.getClass( ))
				return	false;
		else{
			CellList otherList = (CellList)otherObject;
				if (this.size() != otherList.size())
					return	false;
				
				CellNode position = head;
				CellNode otherPosition = otherList.head;
				
				while (position != null){
					if (!(position.phone.equals(otherPosition.phone)))
						return	false;
					position = position.link;
					otherPosition = otherPosition.link;
				}
				return	true; 
			}
	}
}

