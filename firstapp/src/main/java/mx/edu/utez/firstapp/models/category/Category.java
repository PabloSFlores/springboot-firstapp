package mx.edu.utez.firstapp.models.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.subcategory.SubCategory;

import javax.persistence.*;
import javax.swing.text.StyledEditorKit;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false,length = 150)
    private String name;
    @Column(name = "status",nullable = false,columnDefinition = "tinyint default 1")
    private Boolean status;

    @OneToMany(mappedBy="category")
    @JsonIgnore
    private List<SubCategory> subcategories;
}
