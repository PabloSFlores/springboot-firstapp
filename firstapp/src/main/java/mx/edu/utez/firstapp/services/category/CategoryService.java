package mx.edu.utez.firstapp.services.category;

import mx.edu.utez.firstapp.models.category.Category;
import mx.edu.utez.firstapp.models.category.CategoryRepository;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    //Servicio para obtener todos los registros de categories
    @Transactional(readOnly = true)
    public CustomResponse<List<Category>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //Servicio para obtener una categoría
    @Transactional(readOnly = true)
    public CustomResponse<Category> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }

    //Servicio para insertar a una categoría
    //Valida duplicados por id
    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<Category> insert(Category category){
        if(this.repository.existsByName(category.getName()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "La categoría ya ha sido registrada"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(category),
                false,
                200,
                "Categoría registrada correctamente"
        );
    }

    //Servicio para actualizar una categoría
    //Valida existencia por id
    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<Category> update(Category category){
        if(!this.repository.existsById(category.getId()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "La categoría no existe"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(category),
                false,
                200,
                "Categoría actualizada correctamente"
        );
    }

    //Servicio para cambiar el status de una categoría
    //valida existencia por id
    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<Integer> changeStatus(Category category){
        if(!this.repository.existsById(category.getId())){
            return new CustomResponse<>(
                    0,
                    true,
                    400,
                    "La categoría no existe"
            );
        }
        return new CustomResponse<>(
                this.repository.updateStatusById(
                        category.getStatus(), category.getId()
                ),
                false,
                200,
                "Categoría actualizada correctamente"
        );
    }
}
