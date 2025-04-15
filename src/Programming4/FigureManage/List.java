package FigureManage;

public class List<T> {
    private Object[] data;
    private int size;

    public List() {
        data = new Object[10];
        size = 0;
    }

    public void add(T item) {
        if (size == data.length) {
            resize();
        }
        data[size++] = item;
    }

    public void swap(int i, int j) {
        T temp = (T) data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public void remove(T item) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(item)) {
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                size--;
                break;
            }
        }
    }

    public T get(int index) {
        return (T) data[index];
    }

    public int size() {
        return size;
    }

    private void resize() {
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}