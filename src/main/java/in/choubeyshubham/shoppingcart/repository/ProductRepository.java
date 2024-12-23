package in.choubeyshubham.shoppingcart.repository;

import in.choubeyshubham.shoppingcart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //Get methods
    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String Brand);

    List<Product> findByCategoryNameAndBrand(String category, String Brand);

    List<Product> findByName(String name);

    List<Product> findByBrandAndCategory(String Brand, String category);

    List<Product> findByBrandAndName(String Brand, String name);

    Long countByBrandAndName(String Brand, String name);

    //-------------------------------------
    //Post methods





}
