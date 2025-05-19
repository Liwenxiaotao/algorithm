package tao.com.linkedList;
// 单向循环链表
public class SingleCircleLinkedList<E> {
    private static final int ELEMENT_NOT_FOUND = -1;

    private Node<E> first;
    private int size = 0;

    private static class Node<E> {
        E element;
        Node<E> next;
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(element).append("_").append(next.element);
            return sb.toString();
        }
    }

    // 获取元素个数
    public int size() {
        return size;
    }

    // 是否是空
    public boolean isEmpty() {
        return size == 0;
    }
    // 是否包含某个元素
    public boolean contain(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    // 清空链表
    public void clear() {
        size = 0;
        first = null;
    }

    // 获取index位置的元素
    public E get(int index) {
        return node(index).element;
    }

    // 设置index位置的元素
    public E set (int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    // 新增
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            Node<E> newFirst = new Node<E>(element, first);
            // 拿到最后一个节点
            Node<E> last = (size == 0)  ? newFirst : node(size - 1);
            last.next = newFirst;
            first = newFirst;
        } else {
            Node<E> prev = node(index  - 1);
            prev.next = new Node<E>(element, prev.next);
        }
        size ++;
    }
    // 删除
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0) {
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = node(size - 1);
                first = node.next;
                last.next = first;

            }
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    // 获取某个元素的索引
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    // 根据索引获取节点
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(size).append(",[");
        Node<E> node = first;
        for(int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(node);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
