package tao.com.arrayList;


@SuppressWarnings("unchecked")
public class ArrayList<E> {
    /*
    * 元素的数量
    * */
    private int size = 0;

    /*
       所有的元素
    */
    private E[] elements;

    // 默认数组长度
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public void add(E element) {
        add(size, element);
    }

    public int size() {
        return size;
    }
    // 在index位置插入一个元素
    private void add (int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        // index之后的数据都往后挪
        for(int i = size; i > index; i--) {
            elements[size] = elements[size - 1];
        }
        elements[index] = element;
        size++;
    }

    // 当容量不够时，扩展数组的容量
    private void ensureCapacity(int size) {
        int oldCapacity = elements.length;
        if (oldCapacity >= size) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + oldCapacity >> 1;
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    // 检查是否越界
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    // 抛出越界的错误
    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }

            builder.append(elements[i]);
        }
        builder.append("]");
        return builder.toString();
    }
}
