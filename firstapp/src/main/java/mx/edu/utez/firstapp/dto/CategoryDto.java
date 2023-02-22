package mx.edu.utez.firstapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.category.Category;
import mx.edu.utez.firstapp.models.subcategory.SubCategory;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 150)
    private String name;
    private Boolean status;
    private List<SubCategory> subcategories;

    public Category getCategory() {
        return new Category(
                getId(),
                getName(),
                getStatus(),
                getSubcategories());
    }
}
