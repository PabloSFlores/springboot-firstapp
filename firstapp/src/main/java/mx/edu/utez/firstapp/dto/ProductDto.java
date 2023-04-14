package mx.edu.utez.firstapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.product.Product;
import mx.edu.utez.firstapp.models.product.ProductImages;
import mx.edu.utez.firstapp.models.subcategory.SubCategory;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String brand;
    private String status;
    private SubCategory subCategory;
    private List<ProductImages> images;
    public Product getProduct(){
        return new Product(
                getId(),
                getName(),
                getDescription(),
                getPrice(),
                getBrand(),
                getStatus(),
                getSubCategory(),
                getImages()
        );
    }
}
