//  장바구니: 두개 이상의 상품을 담을 수 있어야함 (ex. 우유 2개, 화장지 3개)
//  - items를 가지고 있음
//
//  다음과 같은 함수를 가지고 있어야 함
//  - showItems()
//  - addProduct()
//  - removeProduct()

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {

    //상품과 상품의 개수!!
    private Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product, int amount) {
        //현재 items 에 있으면 갯수를 더하고, 처음 담기는 거면 맵에 추가를 해줌
//    if (items.containsKey(product)) {
//      items.put(product, items.get(product) + amount);
//    } else {
//      items.put(product, amount);
//    }

        items.merge(product, amount, Integer::sum);
    } //addProduct()

    public void removeProduct(Product product, int amount) {
        //현재 items 맵에 없으면 아무것도 하지 않고, 있으면 갯수를 뺀다
        if (items.containsKey(product) && items.get(product) > 0) {
            items.put(product, items.get(product) - amount);
        } //if
    } //removeProduct()

    public void showItems() {
        //entrySet을 이용해서 순환을 통해 데이터 출력
        System.out.println(" show items in the cart !!!");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey().getName() + " : " + entry.getValue() + "개");
        } //for
    } //showItems()

    public Map<String, Integer>  showItemsStream() {
        return items.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().getName(), e -> e.getValue()));
    } //showItemsStream()

} //class
