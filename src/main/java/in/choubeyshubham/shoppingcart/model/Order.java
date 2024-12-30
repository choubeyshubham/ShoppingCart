package in.choubeyshubham.shoppingcart.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}