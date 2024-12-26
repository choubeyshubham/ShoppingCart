package in.choubeyshubham.shoppingcart.service.image;

import in.choubeyshubham.shoppingcart.dto.ImageDTO;
import in.choubeyshubham.shoppingcart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDTO> saveImage(List<MultipartFile> file, Long productId);

    void updateImage(MultipartFile file, Long imageId);


}
