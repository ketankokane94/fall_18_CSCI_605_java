/**
 * StorageI.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 * Interface defining the methods for the data structure
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public interface StorageI<E>  {

    public boolean add(E e);	// 2
    public E get();
    public void clear();		// 2 3
    public boolean contains(E e);
    public boolean isEmpty();
    public void sort();		// 3
    public int size();		// 2 3
    public String getClassName();

}
