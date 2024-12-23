package in.choubeyshubham.shoppingcart.service.product;

import in.choubeyshubham.shoppingcart.exception.ProductNotFoundException;
import in.choubeyshubham.shoppingcart.model.Category;
import in.choubeyshubham.shoppingcart.model.Product;
import in.choubeyshubham.shoppingcart.repository.CategoryRepository;
import in.choubeyshubham.shoppingcart.repository.ProductRepository;
import in.choubeyshubham.shoppingcart.request.AddProductRequestDTO;
import in.choubeyshubham.shoppingcart.request.ProductUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

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
    public Product addProduct(AddProductRequestDTO requestDTO) {
        //check if the category found in the db
        //If yes ,set it as the new product category
        //If no save it as a new category save as the new product category
        Category category = Optional.ofNullable(categoryRepository
                .findByName(requestDTO.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category();
                    return categoryRepository.save(newCategory);
                });

        requestDTO.setCategory(category);
        return productRepository.save(ctreateProduct(requestDTO, category));
    }
    private Product ctreateProduct(AddProductRequestDTO requestDTO, Category category) {
        return new Product(
                requestDTO.getName(),
                requestDTO.getBrand(),
                requestDTO.getPrice(),
                requestDTO.getInventory(),
                requestDTO.getDescription(),
                category
        );
    }

    @Override
    public List<Product> addAllProducts(List<AddProductRequestDTO> productDTOS) {
        ArrayList<Product> list = new ArrayList<>();
        for(AddProductRequestDTO product: productDTOS){
            list.add(addProduct(product));
        }
        return list;
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
    private Product updateExistingProduct(Product exProduct, ProductUpdateRequestDTO requestDTO) {
        exProduct.setName(requestDTO.getName());
        exProduct.setBrand(requestDTO.getBrand());
        exProduct.setPrice(requestDTO.getPrice());
        exProduct.setInventory(requestDTO.getInventory());
        exProduct.setDescription(requestDTO.getDescription());
        return productRepository.save(exProduct);




    }


}
