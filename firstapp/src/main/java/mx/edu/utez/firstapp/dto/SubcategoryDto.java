package mx.edu.utez.firstapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.category.Category;
import mx.edu.utez.firstapp.models.subcategory.SubCategory;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubcategoryDto {
    private Long id;
    private String name;
    private Boolean status;
    private Category category;
    public SubCategory getSubCategory(){
        return new SubCategory(
                getId(),
                getName(),
                getStatus(),
                getCategory()
        );
    }
}
