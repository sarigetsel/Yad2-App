package com.example.hand.Controller;

import com.example.hand.Dto.areaDto;
import com.example.hand.Service.MapStuctMapper;
import com.example.hand.model.area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import  com.example.hand.Repository.areaRepository;
import java.util.List;

@RestController
@RequestMapping("api/area")
public class areaController {

    private final areaRepository areaRepository;
    private final MapStuctMapper mapStuctMapper;
@Autowired
    public areaController(com.example.hand.Repository.areaRepository areaRepository, MapStuctMapper mapStuctMapper) {
        this.areaRepository = areaRepository;
        this.mapStuctMapper = mapStuctMapper;
    }

    @GetMapping("/getAll")
    public List<area> getAll()
    {
        return areaRepository.findAll();
    }

    @GetMapping("/getAllDto")
    public List<areaDto> getAllDto()
    {
        return mapStuctMapper.toListAreaDto(areaRepository.findAll());
    }

    @PostMapping("/add")
    public area addArea(@RequestBody area area)
    {
        area newArea=areaRepository.save(area);
        return newArea;
    }
    @PostMapping("/addDto")
    public areaDto addAreaDto(@RequestBody areaDto areaDto)
    {
        areaDto newAreaDto=mapStuctMapper.toAreaDto(areaRepository.save(mapStuctMapper.toArea(areaDto)));
        return newAreaDto;
    }
}
