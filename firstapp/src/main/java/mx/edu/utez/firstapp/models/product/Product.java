package mx.edu.utez.firstapp.models.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.subcategory.SubCategory;

import javax.persistence.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "fileBlob", columnDefinition = "longblob")
    private byte[] filebase64;
    @Column()
    private int cuantity;
    @Column()
    private double price;
    @Column(columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "subcategogry_id")
    private SubCategory subCategory;
}
