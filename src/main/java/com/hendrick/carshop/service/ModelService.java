package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.ModelDTO;
import com.hendrick.carshop.model.Model;
import com.hendrick.carshop.repository.ModelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService {

    private final ModelRepository modelRepository;


    public ModelService(ModelRepository modelRepository) {

        this.modelRepository = modelRepository;

    }

    //Read: by id
    public ModelDTO findById(Long id) {

        Model model = modelRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not Found."));

        ModelDTO dto = new ModelDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());

        if (model.getBrand() != null) {
            dto.setBrand(model.getBrand().getId());
            dto.setBrandName(model.getBrand().getName());
        }

        return dto;
    }

    //Read: ListAll
    public List<ModelDTO> findAll() {

        List<Model> models = modelRepository.findAll();
        List<ModelDTO> modelDTOList = new ArrayList<>();

        for (Model model : models) {

            ModelDTO dto = new ModelDTO();
            dto.setId(model.getId());
            dto.setName(model.getName());
            dto.setBrand(model.getBrand().getId());
            dto.setBrandName(model.getBrand().getName());
            modelDTOList.add(dto);

        }
        return modelDTOList;

    }


}
