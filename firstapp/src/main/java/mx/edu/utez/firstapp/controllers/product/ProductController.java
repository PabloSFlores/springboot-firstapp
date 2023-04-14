package mx.edu.utez.firstapp.controllers.product;

import mx.edu.utez.firstapp.dto.ProductDto;
import mx.edu.utez.firstapp.models.product.Product;
import mx.edu.utez.firstapp.services.product.ProductService;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api-market/product")
@CrossOrigin(origins = {"*"})
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/loadfile/{uid}")
    public ResponseEntity<Resource> loadfile(@PathVariable("uid") String uid) throws IOException {
        return this.service.getImage(uid);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Object>> insert(@Valid @RequestBody ProductDto product){
        return new ResponseEntity<>(
                this.service.insert(product.getProduct()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Product>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.CREATED
        );
    }
}
