
// 큐 : 데이터의 삽입은 한쪽 끝에서 이루어지고 삭제는 반대쪽 끝에서 이루어지는 자료 구조
// 선입선출 선출 First In First Out 형태의 선형 자료 구조

public class MyQueue<T> {
  private MyLinkedList<T> list = new MyLinkedList<>();

  // enqueue
  public void enqueue(T item) {
    list.add(item);
  } //enqueue

  // dequeue
  public T dequeue() {
    // 예외처리:: 아무것도 없는데 꺼내려고하면 안됨!
    if(list.isEmpty()) {
      throw new IllegalStateException("queue empty!!");
    } //if

    T frontItem = list.get(0);
    list.delete(0);
    return frontItem;
  } //dequeue()

  // peek
  public T peek() {
    // 예외처리:: 아무것도 없는데 꺼내려고하면 안됨!
    if(list.isEmpty()) {
      throw new IllegalStateException("queue empty!!");
    } //if

    T frontItem = list.get(0);
    return frontItem;
  } //peek()

} //class
