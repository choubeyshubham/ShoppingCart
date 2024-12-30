package in.choubeyshubham.shoppingcart.repository;

import in.choubeyshubham.shoppingcart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
