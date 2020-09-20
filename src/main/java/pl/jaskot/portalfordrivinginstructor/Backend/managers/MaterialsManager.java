package pl.jaskot.portalfordrivinginstructor.Backend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Material;
import pl.jaskot.portalfordrivinginstructor.Backend.repository.MaterialsRepo;

import java.util.List;

@Service
public class MaterialsManager {

    @Autowired
    MaterialsRepo materialsRepo;

    public MaterialsManager(MaterialsRepo materialsRepo)
    {
        this.materialsRepo = materialsRepo;
    }

    public void addMaterial(Material material){
        materialsRepo.save(material);
    }

    public List<Material> getMaterials(){
        return (List<Material>) materialsRepo.findAll();
    }
}
