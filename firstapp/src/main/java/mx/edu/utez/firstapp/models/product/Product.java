package mx.edu.utez.firstapp.models.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.subcategory.SubCategory;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private String brand;
    private String status;
    @ManyToOne
    @JoinColumn(name = "subcategogry_id")
    private SubCategory subCategory;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImages> images;
}
