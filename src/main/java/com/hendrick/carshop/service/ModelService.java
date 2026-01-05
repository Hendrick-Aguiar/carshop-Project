package com.hendrick.carshop.service;

import com.hendrick.carshop.repository.ModelRepository;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository){

        this.modelRepository = modelRepository;

    }




}
