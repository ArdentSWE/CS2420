package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
/*
 * @Author: Adarsh Sreeram & Stewart Russell
 * @Version: 09/14/2023
 * 
 */
public class SimplePriorityQueue<Type> implements PriorityQueue<Type> {

    private int size;
    private Type[] queue;
    private Comparator<? super Type> comparator;

    @SuppressWarnings("unchecked")
    /*
     * Constructor for a SimplePriorityQueue
     * @param: None
     * @return: None
     */
    public SimplePriorityQueue()
    {
        queue = (Type[]) new Object[0];
    }

    @SuppressWarnings("unchecked")
    /*
     * Constructor for a SimplePriorityQueue
     * @param: Comparator<? super Type> comp
     * @return: None
     */
    public SimplePriorityQueue(Comparator<? super Type> comp)
    {
        comparator = comp;
        queue = (Type[]) new Object[0];
    }

    @SuppressWarnings("unchecked")
    /*
     * binarySearch method for a SimplePriorityQueue
     * @param: Type target
     * @return: int index
     */
    private int binarySearch(Type target)
    {
        if(this.queue[0] == null) return 0;
        int low = 0, high = this.size - 1, mid = high / 2;
        while(low <= high)
        {
            mid = (low + high) / 2;
            if(this.queue[mid] == null) return mid;
            int value = this.comparator == null ? ((Comparable<? super Type>)this.queue[mid]).compareTo(target) : this.comparator.compare(this.queue[mid], target);
            if(value == 0) return mid;
            else if(value < 0) high = mid - 1;
            else low = mid + 1;
        }
        return mid;
    }

    @SuppressWarnings("unchecked")
    /*
     * Resize method for a SimplePriorityQueue
     * @param: int reqSize
     * @return: None
     
     */
    private void resize(int reqSize)
    {
        int newSize;
        if(this.size() == 0) newSize = 1;
        else newSize = this.size();
        if(newSize < reqSize) while(newSize < reqSize) newSize *= 2;
        else if(newSize > reqSize) while(true)
        {
            if((newSize / 2) < reqSize) break;
            newSize /= 2;
        }
        Type[] newQueue = (Type[]) new Object[newSize];
        for(int i = 0; i < this.size(); i++)
            newQueue[i] = this.queue[i];
        this.queue = newQueue;
    }
    /*
     * findMax method that returns the maximum value in a queue
     * @param: None
     * @return: this.queue[0] -> Max value
     * @throws: NoSuchElementException
     */
    @Override
    public Type findMax() throws NoSuchElementException
    {
        if(this.isEmpty()) throw new NoSuchElementException("The array is empty.");
        return this.queue[0];
    }
    /*
     * deleteMax method that removes the maximum value from a queue
     * @param: None
     * @return: output
     * @throws: NoSuchElementException
     */
    @Override
    public Type deleteMax() throws NoSuchElementException
    {
        if(this.isEmpty()) throw new NoSuchElementException();
        Type output = this.queue[0];
        for(int i = 0; i < this.size() - 1;i++)
        {
            this.queue[i] = this.queue[i+1];
        }
        size--;
        return output;
    }
    /*
     * shiftBack method that shifts the values in a queue back
     * @param: int fromIndex
     * @return: None
     */
    private void shiftBack(int fromIndex)
    {
        for(int i = this.size - 1; i > fromIndex; i--)
        {
            if(this.queue[i - 1] == null) continue;
            this.queue[i] = this.queue[i - 1];
        }
    }
    /*
     * insert method that inserts a value into a queue
     * @param: Type item
     * @return: None
     */
    @Override
    public void insert(Type item)
    {
        if(this.size + 1 >= this.size()) resize(this.size() + 1);
        int nearestIndex = binarySearch(item);
        int comp;
        if(this.queue[nearestIndex] == null) comp = 0;
        else comp = this.comparator == null ? ((Comparable<? super Type>) this.queue[nearestIndex]).compareTo(item) : this.comparator.compare(this.queue[nearestIndex], item);
        if(comp <= 0)
        {
            shiftBack(nearestIndex);
            this.queue[nearestIndex] = item;
        } else {
            shiftBack(nearestIndex + 1);
            this.queue[nearestIndex + 1] = item;
        }
        size++;
    }
    /*
     * insertAll method that inserts a collection of values into a queue
     * @param: Collection<? extends Type> coll
     * @return: None
     */
    @Override
    @SuppressWarnings("unchecked")
    public void insertAll(Collection<? extends Type> coll)
    {
        if(this.size + coll.size() > this.size()) resize(this.size + coll.size());
        Type[] collArray = (Type[]) new Object[coll.size()];
        coll.toArray(collArray);
        for(Type item : collArray)
        {
            this.insert(item);
        }
    }
    /*
     * contains method that checks if a value is in a queue
     * @param: Type item
     * @return: boolean(True or false)
     */
    @Override
    public boolean contains(Type item)
    {
        return !this.isEmpty() && this.queue[this.binarySearch(item)] != null && this.queue[this.binarySearch(item)].equals(item);
    }
    /*
     * size method that returns the size of a queue
     * @param: None
     * @return: int size
     */
    @Override
    public int size()
    {
        return this.size;
    }
    /*
     * isEmpty method that returns true if a queue is empty
     * @param: None
     * @return: boolean(True or false)
     */
    @Override
    public boolean isEmpty()
    {
        return this.size == 0;
    }
    /*
     * clear method that clears a queue
     * @param: None
     * @return: None
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear()
    {
        this.queue = (Type[]) new Object[0];
        size = 0;
    }
}
