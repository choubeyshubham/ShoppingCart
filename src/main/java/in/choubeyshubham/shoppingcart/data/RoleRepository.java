package in.choubeyshubham.shoppingcart.data;


import in.choubeyshubham.shoppingcart.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String role);
}

