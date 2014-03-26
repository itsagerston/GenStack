/**
 * GenStack is a general stack class that implements interface Stack350.
 *
 * @author Aaron Gerston
 */
public class GenStack<E> implements Stack350 {
	
	private E[] arr;
	private int top;
	private int maxSize;
	
	// Constructor...
	public GenStack(int size) {
		arr = (E[]) new Object[size];
		maxSize = size;
		top = -1;
	}
	
	// Add to top of stack
	public void push(Object Ob) {
		if (isFull()) {
			maxSize = maxSize * 2;
			E[] temp = (E[]) new Object[maxSize];
			for (int i = 0; i < arr.length; ++i) {
				temp[i] = arr[i];
			}
			arr = temp;
			arr[++top] = (E) Ob;
		}
		else {
			arr[++top] = (E) Ob;
		}
	}
	
	// Remove from top of stack
	public Object pop() throws Stack350Exception {
		if (isEmpty()) {
			throw new Stack350Exception("ERROR: cannot pop from an empty stack!");
		}
		else {
			return arr[top--];
		}
	}
	
	// Return top of stack
	public Object peek() throws Stack350Exception {
		if (isEmpty()) {
			throw new Stack350Exception("ERROR: cannot peak from an empty stack!");
		}
		else {
			return arr[top];
		}
	}
	
	// Return if full
	public boolean isFull() {
		return (top == maxSize-1);
	}
	
	// Return if empty
	public boolean isEmpty() {
		return (top == -1);
	}
	
	// Return size of stack
	public int getSize() {
		return top + 1;
	}
}