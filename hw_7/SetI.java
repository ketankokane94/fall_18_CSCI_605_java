public interface SetI<E> {
    boolean	add(E e);
    boolean	addAll(SetI<? extends E> c);
    boolean	containsAll(SetI<?> c);
    boolean	removeAll(SetI<?> c);
    void	clear();
    boolean	contains(Object o);
    boolean	equals(Object o);
    int	hashCode();
    boolean	isEmpty();
    boolean	remove(Object o);
    int	size();
    Object[]	toArray();
    // <T> T[]	toArray(T[] a); try it, not required
}