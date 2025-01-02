package in.choubeyshubham.shoppingcart.service.order;

import in.choubeyshubham.shoppingcart.model.Order;

public interface IOrderService {
    Order placeOrder(Long userId);

    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);


}