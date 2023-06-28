package ra.service;


import ra.model.CartItem;
import ra.model.Catalog;
import ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService implements IService<CartItem, Integer>  {
    private List<CartItem> cartItems = new ArrayList<>();
    @Override
    public List<CartItem> getAll() {
        return cartItems;
    }

    @Override
    public CartItem findById(Integer id) {
        for (CartItem cartItem : cartItems
        ) {
            if (cartItem.getCartItemId()==(id)) {
                return cartItem;
            }
        }
        return null;
    }

    @Override
    public void save(CartItem cartItem) {

    }


    @Override
    public void delete(Integer deleteId) {
        if(findById(deleteId) == null){
            System.err.println("ID không tồn tại");
            return;
        }
        cartItems.remove(findById(deleteId));
    }
    public int getNewId(){
        int max=0;
        for (CartItem item : cartItems) {
            if(item.getCartItemId()> max){
                max = item.getCartItemId();
            }
        }
        return max+1;
    }
}
