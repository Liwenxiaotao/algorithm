package tao.com.linkedList;
/*
* 双向链表
* */
public class LinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;
        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
             if (prev != null) {
                 sb.append(prev.element);
             } else {
                 sb.append("null");
             }
             sb.append("_").append(element).append("_");
            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }
            return sb.toString();
        }
    }
    // 清除所有元素
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }
    // 根据索引获取元素
    public E get(int index) {
        return node(index).element;
    }
    // 设置某个位置的元素
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }
    // 添加元素到尾部
    public void add(E element) {
        add(size, element);
    }
    // 新增元素
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // 往最后添加元素
        if (index == size) {
            Node<E> oldLast = last;
            last = new Node<E>(oldLast, element, null);
            if (oldLast == null) { // 添加第一个节点
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node(prev, element,next);
            next.prev = node;
            if (prev == null) { // index==0
                first = node;
            } else {
                prev.next = node;
            }
        }
        size++;
    }
    // 删除指定索引的元素
    public E remove(int index) {
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
    }
    public E remove(int index) {

    }
    // 获取index位置对应的节点对象
    public Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node;
        // 判断是从前往后查找还是从后往前查找
        if(index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for(int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }


    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }
    // 获取时边界检查
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    // 新增时边界检查
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}
