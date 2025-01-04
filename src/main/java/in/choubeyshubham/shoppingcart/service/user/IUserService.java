package in.choubeyshubham.shoppingcart.service.user;

import in.choubeyshubham.shoppingcart.dto.UserDto;
import in.choubeyshubham.shoppingcart.model.User;
import in.choubeyshubham.shoppingcart.request.CreateUserRequest;
import in.choubeyshubham.shoppingcart.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);

    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

    User getAuthenticatedUser();
}
