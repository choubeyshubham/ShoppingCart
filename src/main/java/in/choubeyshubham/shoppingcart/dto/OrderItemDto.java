package in.choubeyshubham.shoppingcart.dto;

import java.math.BigDecimal;

public class OrderItemDto {
    private Long productId;
    private String productName;
    private String productBrand;
    private int quantity;
    private BigDecimal price;


    //Getter Setter Constructor

    public OrderItemDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderItemDto(Long productId, String productName, String productBrand, int quantity, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.quantity = quantity;
        this.price = price;
    }
    //------------End of class
}
