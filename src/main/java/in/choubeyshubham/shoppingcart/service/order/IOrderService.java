package in.choubeyshubham.shoppingcart.service.order;

import in.choubeyshubham.shoppingcart.dto.OrderDto;
import in.choubeyshubham.shoppingcart.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);

    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);


}