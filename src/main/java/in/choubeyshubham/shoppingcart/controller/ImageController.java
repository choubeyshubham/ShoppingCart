package in.choubeyshubham.shoppingcart.controller;

import in.choubeyshubham.shoppingcart.dto.ImageDTO;
import in.choubeyshubham.shoppingcart.exception.ImageNotFoundException;
import in.choubeyshubham.shoppingcart.model.Image;
import in.choubeyshubham.shoppingcart.response.ApiResponse;
import in.choubeyshubham.shoppingcart.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId) {
        try {
            List<ImageDTO> imagesDtos = imageService.saveImage(files, productId);
            return ResponseEntity.ok(new ApiResponse("Upload sucess!", imagesDtos));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed!", e.getMessage()));
        }
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(resource);
    }

    @PutMapping("image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@RequestParam Long imageId, @RequestParam MultipartFile file) throws SQLException {

        try {
            Image image = imageService.getImageById(imageId);
            if (image == null) {
                imageService.updateImage(file, imageId);
                return ResponseEntity.ok().body(new ApiResponse("Update sucess!", null));
            }
        }catch (ImageNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse("Update failed!", e.getMessage()));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update failed!", INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@RequestParam Long imageId) throws SQLException {
        try {
            Image image = imageService.getImageById(imageId);
            if (image == null) {
                imageService.deleteImageById(imageId);
                return ResponseEntity.ok().body(new ApiResponse("Delete sucess!", null));
            }
        }catch (ImageNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete failed!", INTERNAL_SERVER_ERROR));
    }

}