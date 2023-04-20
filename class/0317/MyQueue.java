import java.util.*;

public class MyQueue<T> extends AbstractCollection<T> implements Queue<T> {
    private ArrayList<T> list;
    private int capacity;

    public MyQueue() {
        this.list = new ArrayList<>();
        this.capacity = 70;
    }

    public MyQueue(ArrayList<T> list, int capacity) {
        this.list = list;
        this.capacity = capacity;
    }

    public MyQueue(ArrayList<T> list) {
        this.list = list;
        this.capacity = 70;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean add(T t){
        if (size() == capacity) {
            throw new IllegalStateException();
        }
        return list.add(t);
    }

    @Override
    public boolean offer(T t) {
        return list.add(t);
    }

    @Override
    public T remove() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        return list.remove(0);
    }

    @Override
    public T poll() {
        return list.remove(0);
    }

    @Override
    public T element() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return list.get(0);
    }

    @Override
    public T peek() {
        return list.get(0);
    }
}
