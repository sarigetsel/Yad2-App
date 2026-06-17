package com.example.hand.Controller;

import com.example.hand.Dto.sellerDto;
import com.example.hand.Service.MapStuctMapper;
import com.example.hand.model.seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.hand.Repository.sellerRepository;
import java.util.List;

@RestController
@RequestMapping("api/seller")
public class sellerController
{
    private final sellerRepository sellerRepository;
    private final MapStuctMapper mapStuctMapper;
@Autowired
    public sellerController(com.example.hand.Repository.sellerRepository sellerRepository, MapStuctMapper mapStuctMapper) {
        this.sellerRepository = sellerRepository;
        this.mapStuctMapper = mapStuctMapper;
    }

    @GetMapping("/getAll")
    public List<seller> getAll(){return sellerRepository.findAll();}

    @GetMapping("/getAllDto")
    public List<sellerDto> getAllDto(){
        return mapStuctMapper.toListSellerDto(sellerRepository.findAll());
    }

    @PostMapping("/add")
    public seller addSeller(@RequestBody seller seller)
    {

        seller newSeller=sellerRepository.save(seller);
        return newSeller;
    }
    @PostMapping("/addDto")
    public sellerDto addSellerDto(@RequestBody sellerDto sellerDto)
    {

        sellerDto newSellerDto=mapStuctMapper.toSellerDto(sellerRepository.save(mapStuctMapper.toSeller(sellerDto)));
        return newSellerDto;
    }

}
