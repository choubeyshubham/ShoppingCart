package in.choubeyshubham.shoppingcart.service.product;

import in.choubeyshubham.shoppingcart.exception.ProductNotFoundException;
import in.choubeyshubham.shoppingcart.model.Product;
import in.choubeyshubham.shoppingcart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {


    private final ProductRepository productRepository;


    //Get methods
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }
    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }
    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }
    @Override
    public List<Product> getProductsByBrandAndCategory(String brand, String category) {
        return productRepository.findByBrandAndCategory(brand,category);
    }
    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }
    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found!"));
    }
    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }

    //Post Method----------------------------------------
    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void addAllProducts(List<Product> products) {
        for(Product product: products){
            addProduct(product);
        }
    }

    //Delete methods-------------------
    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,
                ()->{throw new ProductNotFoundException("Product not found!");});
    }
    @Override
    public void deleteAllProducts() {
      //  productRepository.deleteAll();
    }

    //Put Method-----------------------
    @Override
    public void updateProduct(Product product) {

    }


}
