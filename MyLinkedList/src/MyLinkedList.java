import java.util.Iterator;
import java.util.NoSuchElementException;

// add() : MyLinkedList 의 마지막 노드에 data 를 추가
// get(index i): MyLinkedList 의 i 번째 노드의 data 를 return
// delete(index i): MyLinkedList 의 i 번째 노드의 데이터를 삭제
//
// 위를 api를 제공하면서, data 의 타입은 LinkedList 를 생성할 때 정할 수 있도록 제네릭으로 구현
public class MyLinkedList<T> implements Iterable<T>{

  private Node<T> head;

  private int size = 0;

  public void add(T data){
    // 데이터 생성
    Node<T> node = new Node<>(data);

    if (size == 0) {  // LinkedList가 비어있는 경우
      head = node;
    } else {  // 비어있지않은 경우 => 데이터가 1개 이상인 경우
      // 마지막 노드 뒤로 데이터를 넣어줘야 함 => 마지막 노드를 탐색!!
      Node<T> current = head;

      while (current.getNext() != null) {
        current = current.getNext();
      } //while

      // 현재==current 노드는 가장 마지막 노드가 위치하고 있음
      // 마지막 노드를 next에 추가를 해줌
      current.setNext(node);
    } //if-else

    this.size++;
  } //add()

  // TODO get(index i)
  public T get(int index) {
    // linked list 양 끝 값에 대한 예외처리
    if (index <0 ||this.size <= index) {
      throw new IndexOutOfBoundsException();
    } //if

    // index 노드를 찾자!
    Node<T> current = this.head;

    for (int i = 0; i< index; i++){
      current = current.getNext();
    } //for

    return current.getData();
  } //get()

  // TODO delete(index i)
  // 중간 값을 삭제 !! 그만큼 노드를 가리키는 주소를 정리를 해줘야함
  // 1 -> 2 ->  4
  // 김   이    최
  // 3(박)을 삭제하면 2->4로 바로 가야함
  public void delete(int index) {
    // 양 끝값 경계에 대한 예외처리
    if (index <0 ||this.size <= index) {
      throw new IndexOutOfBoundsException();
    } //if

    if (index == 0) {
      head = head.getNext();
    } else {
      // index번째의 노드를 찾아야함
      Node<T> current = this.head;

      for (int i = 0; i< index - 1 ; i++){
        current = current.getNext();
      } //for

      // 직전 노드의 next를 i+1 (다음 노드) 번째 노드로 바꿔줌
      current.setNext(current.getNext().getNext());
    } //if-else

    this.size--;
  } //delete()

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node<T> current = head;

      // 맨 처음 == head 부터 순회
      @Override
      public boolean hasNext() {
        return current != null;
      } //hasNext()

      @Override
      public T next() {
        // 다음 노드가 있는 먼저 확인하고, 있으면 동작
        if (!hasNext()) {
          throw new NoSuchElementException();
        } //if
        T data = current.getData();
        current = current.getNext();
        return data;
      } //next()
    };
  } //iterator()

  public boolean isEmpty() {
    return size == 0;
  } //isEmpty()

  public int size() {
    return size;
  } //size()

} //class
