package in.choubeyshubham.shoppingcart.service.category;

import in.choubeyshubham.shoppingcart.exception.AlreadyExistsException;
import in.choubeyshubham.shoppingcart.exception.CategoryNotFoundException;
import in.choubeyshubham.shoppingcart.model.Category;
import in.choubeyshubham.shoppingcart.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found"));

    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter(c->!categoryRepository
                        .existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(()-> new AlreadyExistsException(category.getName()+ " already exist"));
    }

    @Override
    public Category updateCategory(Category category,Long id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldCategory->{
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                }).orElseThrow(()->new CategoryNotFoundException("Category not found"));


    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete,()->new CategoryNotFoundException("Category not found"));
    }
}