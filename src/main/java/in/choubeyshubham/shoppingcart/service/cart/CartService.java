package in.choubeyshubham.shoppingcart.service.cart;


import in.choubeyshubham.shoppingcart.exception.ResourceNotFoundException;
import in.choubeyshubham.shoppingcart.model.Cart;
import in.choubeyshubham.shoppingcart.repository.CartItemRepository;
import in.choubeyshubham.shoppingcart.repository.CartRepository;
import in.choubeyshubham.shoppingcart.service.product.IProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CartService implements ICartService{
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final AtomicLong cartIdGenerator = new AtomicLong(0);
    private final IProductService productService;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, IProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.clearCart();
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }


    @Override
    public Cart initializeNewCart(User user) {
        return Optional.ofNullable(getCartByUserId(user.getId()))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
