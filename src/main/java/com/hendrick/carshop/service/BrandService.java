package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.BrandDTO;
import com.hendrick.carshop.model.Brand;
import com.hendrick.carshop.repository.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandrepository){

        this.brandRepository = brandrepository;

    }


    //Read: readbyid
    public BrandDTO findById(Long id){

        Brand brand = brandRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found"));

        BrandDTO dto = new BrandDTO();
        dto.setId(brand.getId());
        dto.setName(brand.getName());

        return dto;

    }

    //Read: fetch all
    public List<BrandDTO> findAll(){
       //in
       List<Brand> brands= brandRepository.findAll();
       //out
       List<BrandDTO> brandList = new ArrayList<>();

       //Fetch brandRepository
       for (Brand brand: brands){

           //brand find send to dto
           BrandDTO dto = new BrandDTO();
           dto.setId(brand.getId());
           dto.setName(brand.getName());
           brandList.add(dto);

       }
       return brandList;



    }


}
