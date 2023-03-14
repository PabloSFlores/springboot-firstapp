package mx.edu.utez.firstapp.services.subcategory;

import mx.edu.utez.firstapp.models.subcategory.SubCategory;
import mx.edu.utez.firstapp.models.subcategory.SubCategoryRepositorio;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class SubcategoryService {
    @Autowired
    private SubCategoryRepositorio repository;
    @Transactional(readOnly = true)
    public CustomResponse<List<SubCategory>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<SubCategory> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<SubCategory> insert(SubCategory subCategory){
        if(this.repository.existsByName(subCategory.getName()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "La subcategoría ya ha sido registrada"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(subCategory),
                false,
                200,
                "Subcategoría registrada correctamente"
        );
    }

    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<SubCategory> update(SubCategory subCategory){
        if(!this.repository.existsById(subCategory.getId()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "La subcategoría no existe"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(subCategory),
                false,
                200,
                "Subcategoría actualizada correctamente"
        );
    }

    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<Boolean> changeStatus(SubCategory subCategory){
        if(!this.repository.existsById(subCategory.getId())){
            return new CustomResponse<>(
                    false,
                    true,
                    400,
                    "La subcategoría no existe"
            );
        }
        return new CustomResponse<>(
                this.repository.updateStatusById(
                        subCategory.getStatus(), subCategory.getId()
                ) == 1,
                false,
                200,
                "Subcategoría actualizada correctamente"
        );
    }
}
