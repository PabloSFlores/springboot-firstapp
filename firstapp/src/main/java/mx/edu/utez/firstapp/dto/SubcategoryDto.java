package mx.edu.utez.firstapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.category.Category;
import mx.edu.utez.firstapp.models.product.Product;
import mx.edu.utez.firstapp.models.subcategory.SubCategory;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubcategoryDto {
    private Long id;
    private String name;
    private Boolean status;
    private Category category;
    private List<Product> product;
    public SubCategory getSubCategory(){
        return new SubCategory(
                getId(),
                getName(),
                getStatus(),
                getCategory(),
                getProduct()
        );
    }
}
