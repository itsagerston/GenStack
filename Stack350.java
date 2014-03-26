/* INTERFACE */
public interface Stack350{
    public void push(Object datum);
    public Object pop() throws Stack350Exception;
    public Object peek() throws Stack350Exception;
    public boolean isEmpty();
    public int getSize();
}
