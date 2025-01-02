package in.choubeyshubham.shoppingcart.service.cart;


import in.choubeyshubham.shoppingcart.model.Cart;
import in.choubeyshubham.shoppingcart.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
