public class MyLinkedList<T> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int pointer = 0;

    public int size(){
        return pointer;
    }

    public boolean add(T elem) {
        Node<T> newNode = new Node<>(elem, null);
        if (firstNode == null || firstNode.getValue() == null) {
            firstNode = newNode;
            lastNode = firstNode;
        } else {
            lastNode.setNext(newNode);
            lastNode = lastNode.getNext();
        }
        pointer++;
        return true;
    }

    public boolean addAll(MyLinkedList<T> otherList) {
        for (Node<T> i = otherList.getFirstNode(); i != null; i = i.getNext()) {
            add(i.getValue());
        }
        return true;
    }

    public void clear() {
        Node<T> i = firstNode;
        while (i != null) {
            i = i.getNext();
            firstNode.setValue(null);
            if (i != null) {
                firstNode = i;
            }
        }
        pointer = 0;
    }

    public boolean contains(T s) {
        for (Node<T> i = firstNode; i != null; i = i.getNext()) {
            if (i.getValue().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(MyLinkedList<T> otherList) {
        for (Node<T> i = otherList.getFirstNode(); i != null; i = i.getNext()) {
            if (!contains(i.getValue())) {
                return false;
            }
        }
        return true;
    }

    public int indexOf(T s) {
        int index = 0;
        for (Node<T> i = firstNode; i != null; i = i.getNext()) {
            if (i.getValue().equals(s)) {
                break;
            }
            index++;
        }
        return index;
    }

    public int lastIndexOf(T s) {
        int index = 0;
        int needIndex = 0;
        for (Node<T> i = firstNode; i != null; i = i.getNext()) {
            if (i.getValue().equals(s)) {
                needIndex = index;
            }
            index++;
        }
        return needIndex;
    }

    public T set(int needIndex, T s) {
        int index = 0;
        T elem = null;
        for (Node<T> i = firstNode; i != null; i = i.getNext()) {
            if (index == needIndex) {
                elem = i.getValue();
                i.setValue(s);
            }
            index++;
        }
        return elem;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public T remove(int needIndex) {
        T elem = null;
        int index = 0;
        for (Node<T> i = firstNode; i != null; i = i.getNext()) {
            if (index == needIndex) {
                elem = i.getValue();
                pointer--;
                Node<T> j = i;
                while (j.getNext() != null) {
                    j.setValue(j.getNext().getValue());
                    if (j.getNext() == lastNode) {
                        lastNode.setValue(null);
                        lastNode = j;
                    }
                    j = j.getNext();
                }
                break;
            }
            index++;
        }
        return elem;
    }

    public boolean remove(T s) {
        remove(indexOf(s));
        return true;
    }

    public boolean removeAll(MyLinkedList<T> otherList) {
        for (Node<T> i = otherList.getFirstNode(); i != null; i = i.getNext()) {
            remove(i.getValue());
        }
        return true;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size()];
        Node<T> currentNode = firstNode;
        for (int i = 0; i < size(); i++) {
            arr[i] = currentNode.getValue();
            currentNode = currentNode.getNext();
        }
        return arr;
    }

    public MyLinkedList<T> sublist(int fromIndex, int toIndex) {
        MyLinkedList<T> sbl = new MyLinkedList<>();
        int index = 0;
        for (Node<T> i = firstNode; i != null; i = i.getNext()) {
            if (fromIndex <= index && index < toIndex) {
                sbl.add(i.getValue());
            }
            index++;
        }
        return sbl;
    }

    public Node<T> getFirstNode() {
        return firstNode;
    }

    @Override
    public String toString() {
        String s = "{" + firstNode.getValue();
        Node<T> currentNode = firstNode;
        while (currentNode.getNext() != null) {
            s += ", " + currentNode.getNext().getValue();
            currentNode = currentNode.getNext();
        }
        return s + "}";
    }

    // @Override ?
    public boolean equals(MyLinkedList<T> otherList) {
        if (size() == otherList.size()) {
            Node<T> otherNode = otherList.getFirstNode();
            for (Node<T> i = firstNode; i != null; i = i.getNext()) {
                if (!(i.getValue().equals(otherNode.getValue()))) {
                    return false;
                }
                otherNode = otherNode.getNext();
            }
            return true;
        } else {
            return false;
        }
    }
}