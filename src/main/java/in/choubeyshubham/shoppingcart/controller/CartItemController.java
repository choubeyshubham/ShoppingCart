package in.choubeyshubham.shoppingcart.controller;


import in.choubeyshubham.shoppingcart.exception.ResourceNotFoundException;
import in.choubeyshubham.shoppingcart.model.Cart;
import in.choubeyshubham.shoppingcart.model.User;
import in.choubeyshubham.shoppingcart.response.ApiResponse;
import in.choubeyshubham.shoppingcart.service.cart.ICartItemService;
import in.choubeyshubham.shoppingcart.service.cart.ICartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {


    private final ICartItemService cartItemService;
//    private final ICartService cartService;
//    private final IUserService userService;

    public CartItemController(ICartItemService cartItemService){//, ICartService cartService, IUserService userService) {
        this.cartItemService = cartItemService;
//        this.cartService = cartService;
//        this.userService = userService;
    }


//    @PostMapping("/item/add")
//    public ResponseEntity<ApiResponse> addItemToCart(
//            @RequestParam Long productId,
//            @RequestParam Integer quantity) {
//        try {
//            User user = userService.getAuthenticatedUser();
//            Cart cart= cartService.initializeNewCart(user);
//            cartItemService.addItemToCart(cart.getId(), productId, quantity);
//            return ResponseEntity.ok(new ApiResponse("Add Item Success", null));
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
//        }catch (JwtException e){
//            return  ResponseEntity.status(UNAUTHORIZED).body(new ApiResponse(e.getMessage(), null));
//        }
//    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            cartItemService.removeItemFromCart(cartId, itemId);
            return ResponseEntity.ok(new ApiResponse("Remove Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public  ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                           @PathVariable Long itemId,
                                                           @RequestParam Integer quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Update Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }





}
