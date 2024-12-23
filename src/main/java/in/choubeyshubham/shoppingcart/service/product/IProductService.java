package in.choubeyshubham.shoppingcart.service.product;

import in.choubeyshubham.shoppingcart.model.Product;
import in.choubeyshubham.shoppingcart.request.AddProductRequestDTO;

import java.util.List;

public interface IProductService {

    //Get methods
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByBrandAndCategory(String brand, String category);
    List<Product> getProductsByName( String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Product getProductById(Long id);

    //Post method add products
    Product addProduct(AddProductRequestDTO productRequestDTO);
    List<Product> addAllProducts(List<AddProductRequestDTO> productRequestDTOS);

    //Delete methods
    void deleteProductById(Long id);


    void deleteAllProducts();

    //put methods
    void updateProduct(Product product);

    Long countProductsByBrandAndName(String brand, String name);


}
