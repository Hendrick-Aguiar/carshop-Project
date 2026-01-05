package com.hendrick.carshop.service;

import com.hendrick.carshop.dto.ColorDTO;
import com.hendrick.carshop.model.Color;
import com.hendrick.carshop.repository.ColorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorService {

    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {

        this.colorRepository = colorRepository;

    }

    //Read: Fetch Colors By id
    //Controller: @PathVariable Long id
    public ColorDTO findById(Long id) {

        //Optional<Color> colorOptional = colorRepository.findById(id); Optional not return;
        //best practice using this Optional(orElseThrow) and not using Optional and if(!userOptional.IsPresent)
        Color color = colorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Color not found"));

        ColorDTO dto = new ColorDTO();
        dto.setId(color.getId());
        dto.setName(color.getName());

        return dto;

    }

    //Read: Read All colors
    //We will return all because this endpoint returns a combobox
    public List<ColorDTO> findAll() {
        //in: where the Colors data are(fetch Color Entities from database)
        List<Color> colors = colorRepository.findAll();
        //out: where they will be stored sent with return
        List<ColorDTO> dtoList = new ArrayList<>();
        //loop: convert entitity in a dto
        for (Color color : colors) {
            //dto receve the loop search "color" or created dto for the current color entity
            ColorDTO dto = new ColorDTO();//dto object created
            dto.setId(color.getId());//dto take the color id from color repository entity
            dto.setName(color.getName());//dto take the color name form color repository entity
            dtoList.add(dto);// dto is add to the dtoList "List<ColorDTO>"
        }

        return dtoList;
    }


}
