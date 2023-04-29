import lombok.*;
import java.util.*;

public class MyMap<K extends Comparable<K>,V> implements Map<K,V> {

    private Tree<K,V> root;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        Tree<K,V> newTree = new Tree<>(null, null, null, key, value);
        if (root == null) {
            root = newTree;
            size = 1;
            return value;
        }
        if (!containsKey(key)) {
            size++;
        }
        root.add(newTree);
        return value;
    }

    @Override
    public V get(Object key) {
        return (root.get((K) key) == null) ? null : root.get((K) key).value;
    }

    @Override
    public V remove(Object key) {
        V result = (root.get((K) key) == null) ? null : root.remove((K) key);
        if (result != null) {
            size--;
        }
        return result;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
         return root.treeContainsValue(value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        Iterator<? extends Map.Entry<? extends K, ? extends V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<? extends K, ? extends V> entry = iterator.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Collection<V> values() {
        Collection<V> collection = new ArrayList<>();
        valuesHelper(root, collection);
        return collection;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new TreeSet<>();
        keySetHelper(root, set);
        return set;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new TreeSet<>((k1, k2) -> k1.getKey().compareTo(k2.getKey()));
        entrySetHelper(set, root);
        return set;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public String toString() {
        return "{" + root + "}";
    }

    private void valuesHelper(Tree<K, V> tree, Collection<V> result) {
        if (tree == null) {
            return;
        }
        valuesHelper(tree.getLeft(), result);
        result.add(tree.getValue());
        valuesHelper(tree.getRight(), result);
    }

    private void keySetHelper(Tree<K, V> tree, Set<K> result) {
        if (tree == null) {
            return;
        }
        keySetHelper(tree.getLeft(), result);
        result.add(tree.getKey());
        keySetHelper(tree.getRight(), result);
    }

    private void entrySetHelper(Set<Entry<K, V>> set, Tree<K, V> node) {
        if (node == null) {
            return;
        }
        entrySetHelper(set, node.left);
        Entry<K,V> entry = new MyEntry<>(node.key, node.value);
        set.add(entry);
        entrySetHelper(set, node.right);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class Tree<K extends Comparable<K>,V> {
        private Tree<K, V> parent;
        private Tree<K, V> left;
        private Tree<K, V> right;
        private K key;
        private V value;

        public V add(Tree<K, V> node) {
            if (node.key == this.key) {
                this.value = node.value;
                return node.value;
            }
            if (node.key.compareTo(this.key) < 0) {
                if (left == null) {
                    left = node;
                    left.parent = this;
                    return left.value;
                }
                return left.add(node);
            } else {
                if (right == null) {
                    right = node;
                    right.parent = this;
                    return right.value;
                }
                return right.add(node);
            }
        }

        public Tree<K, V> get(K key) {
            if (key.compareTo(this.key) < 0) {
                return left != null ? left.get(key) : null;
            }
            if (key.compareTo(this.key) > 0) {
                return right != null ? right.get(key) : null;
            }
            if (key.compareTo(this.key) == 0) {
                return this;
            }
            return null;
        }

        public V remove(K key) {
            Tree<K, V> needTree = get(key);
            if (needTree == null) {
                return null;
            }

            V result;
            if (needTree.left == null && needTree.right == null) {
                result = needTree.value;
                if (needTree.parent.left == needTree) {
                    needTree.parent.left = null;
                } else {
                    needTree.parent.right = null;
                }
            } else if (needTree.left == null) {
                result = needTree.value;
                needTree.key = needTree.right.key;
                needTree.value = needTree.right.value;
                needTree.left = needTree.right.left;
                needTree.right = needTree.right.right;
            } else if (needTree.right == null) {
                result = needTree.value;
                needTree.key = needTree.left.key;
                needTree.value = needTree.left.value;
                needTree.right = needTree.left.right;
                needTree.left = needTree.left.left;
            } else {
                result = needTree.value;
                Tree<K, V> minTree = needTree.right.getMinTree();
                needTree.key = minTree.key;
                needTree.value = minTree.value;
                minTree.remove(minTree.key);
            }
            return result;
        }

        @Override
        public String toString() {
            String result = "";
            result += toStringHelper(this);
            return result;

        }

        public String toStringHelper(Tree<K, V> tree) {
            if (tree == null) {
                return "";
            }
            String left = toStringHelper(tree.left);
            String right = toStringHelper(tree.right);
            return left + (left.equals("") ? "" : ", ") +
                    tree.key + "=" + tree.value +
                    (right.equals("") ? "" : ", ") + right;
        }

        private Tree<K,V> getMinTree() {
            Tree<K,V> tree = this;
            while (tree.left != null) {
                tree = tree.left;
            }
            return tree;
        }

        public boolean treeContainsValue(Object value) {
            if (this.value.equals(value)) {
                return true;
            }
            if (left != null && left.treeContainsValue(value)) {
                return true;
            }
            return right != null && right.treeContainsValue(value);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class MyEntry<K extends Comparable<K>, V> implements Map.Entry<K,V> {

        private K key;
        private V value;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        public String toString() {
            return key + "=" + value;
        }
    }
}