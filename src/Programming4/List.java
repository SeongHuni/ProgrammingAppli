package FigureManage;

public class List<T> {
  private T[] list;
  private int amount;

  public List() {
    list = (T[]) new Object[10];
    amount = 0;
  }

  public void add(T t) {
    if(amount == list.length) {
      resize();
    }
    list[amount] = t;
  }

  public T get(int index) {return  list[index];}

  public void set(int index, T t) {
    if( 0 > index || index >= amount) {
      throw new IndexOutOfBoundsException();
    }
    list[index] = t;
  }

  public int size() { return amount; }
  public void clear() { amount = 0; }

  public void remove(int index) {
    for(int i = index, maxI = amount - 1; i < maxI; i++){
      list[i] = list[i + 1];
    }
    amount--;
  }

  public void remove(T t){
    int index = indexOf(t);
    remove(index);
  }

  public int indexOf(T t) {
    for(int i = 0; i < amount; i++) {
      if(list[i].equals(t)) {
        return i;
      }
    }
    return -1;
  }

  private void resize(){
    T[] tempList = (T[]) new Object[list.length * 2];
    System.arraycopy(list, 0, tempList, 0, list.length);
    list = tempList;
  }
}
