package com.example.hand.Controller;


import com.example.hand.Dto.categoryDto;
import com.example.hand.Service.MapStuctMapper;
import com.example.hand.model.category;
import  com.example.hand.Repository.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")

public class categoryController {

    private final categoryRepository categoryRepository;
    private final MapStuctMapper mapStuctMapper;
@Autowired
    public categoryController(com.example.hand.Repository.categoryRepository categoryRepository, MapStuctMapper mapStuctMapper) {
        this.categoryRepository = categoryRepository;
        this.mapStuctMapper = mapStuctMapper;
    }

    @GetMapping("/getAll")
    public List<category> getAll(){return categoryRepository.findAll();}

    @GetMapping("/getAllDto")
    public List<categoryDto> getAllDto(){
        return mapStuctMapper.toListCategoryDto(categoryRepository.findAll());
    }

    @PostMapping("/add")
    public category addCategory(@RequestBody category category)
    {
        category newCategory=categoryRepository.save(category);
        return newCategory;
    }
    @PostMapping("/addDto")
    public categoryDto addCategoryDto(@RequestBody categoryDto categoryDto)
    {
        categoryDto newCategoryDto=mapStuctMapper.toCategoryDto(categoryRepository.save(mapStuctMapper.toCategory(categoryDto)));
        return newCategoryDto;
    }
}
