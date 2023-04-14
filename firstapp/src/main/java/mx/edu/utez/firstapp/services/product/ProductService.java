package mx.edu.utez.firstapp.services.product;

import mx.edu.utez.firstapp.models.product.Product;
import mx.edu.utez.firstapp.models.product.ProductImages;
import mx.edu.utez.firstapp.models.product.ProductImagesRepository;
import mx.edu.utez.firstapp.models.product.ProductRepository;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImagesRepository productImagesRepository;

    @Value("${spring.os}")
    private String rootPath;

    private String separator = FileSystems.getDefault().getSeparator();
    private String BASEURL = "http://localhost:8080/api-market/product/loadfile/";

    public ResponseEntity<Resource> getImage(String uid) throws IOException {
        Path path = Paths.get(rootPath + separator + uid);
        ByteArrayResource resource = new ByteArrayResource(
                Files.readAllBytes(path)
        );
        //consulta ProductImages -> MimeType
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }

    @Transactional(rollbackFor = {SQLException.class, IOException.class})
    public CustomResponse<Object> insert(Product product) {
        List<ProductImages> imagesList = new ArrayList<>();
        product.getImages().forEach(image -> {
            byte[] bytes = Base64.decodeBase64(image.getFilebase64());
            String uid = UUID.randomUUID().toString();
            try (OutputStream stream = new FileOutputStream(
                    rootPath + separator + uid + image.getMimeType())
            ) {
                stream.write(bytes);
                image.setUrl(BASEURL + uid + image.getMimeType());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        if (this.productRepository.existsByName(product.getName()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El producto ya existe"
            );
        return new CustomResponse<>(
                this.productRepository.saveAndFlush(product),
                false,
                200,
                "Producto registrado correctamente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<List<Product>> getAll(){
        return new CustomResponse<>(
                this.productRepository.findAll(),
                false,
                200,
                "ok"
        );
    }
}
