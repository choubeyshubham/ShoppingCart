package in.choubeyshubham.shoppingcart.service.image;

import in.choubeyshubham.shoppingcart.DTO.ImageDTO;
import in.choubeyshubham.shoppingcart.exception.ImageNotFoundException;
import in.choubeyshubham.shoppingcart.model.Image;
import in.choubeyshubham.shoppingcart.model.Product;
import in.choubeyshubham.shoppingcart.repository.ImageRepository;
import in.choubeyshubham.shoppingcart.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(()->new ImageNotFoundException("No Image Found at"+id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> new ImageNotFoundException("No Image Found at " + id));
    }

    @Override
    public List<ImageDTO> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDTO> savedImageDTO = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
              Image image = new Image();
              image.setFileName(file.getOriginalFilename());
              image.setFileType(file.getContentType());
              image.setImage(new SerialBlob(file.getBytes()));
              image.setProduct(product);

              String url = "/api/v1/images/image/download";
              String downloadUrl = url + image.getId();
              image.setDownloadUrl(downloadUrl);
              Image savedImage =imageRepository.save(image);

              savedImage.setDownloadUrl(url+savedImage.getId());
              imageRepository.save(savedImage);

              ImageDTO imageDTO = new ImageDTO();
              imageDTO.setImageID(savedImage.getId());
              imageDTO.setImageName(savedImage.getFileName());
              imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
              savedImageDTO.add(imageDTO);

            }catch (IOException|SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDTO;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId, Long productId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
        }catch (IOException | SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
