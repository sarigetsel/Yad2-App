package com.example.hand.Controller;

import com.example.hand.Dto.productDto;
import com.example.hand.Service.MapStuctMapper;
import com.example.hand.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hand.Repository.productRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class productController
{
    private String directory=System.getProperty("user.dir")+"\\images\\";
  private  final  productRepository productRepository;
  private final MapStuctMapper mapStuctMapper;
@Autowired
    public productController(com.example.hand.Repository.productRepository productRepository, MapStuctMapper mapStuctMapper) {
        this.productRepository = productRepository;
        this.mapStuctMapper = mapStuctMapper;
    }

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return productRepository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/dto")
    public ResponseEntity<productDto> getByIdDto(@PathVariable int id) throws IOException {
        return productRepository.findById(id)
                .map(p -> ResponseEntity.ok(mapStuctMapper.toProductDto(p)))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/getAlldTO")
    public List<productDto> getAllDto(){
        return  mapStuctMapper.toListProductDto(productRepository.findAll());}


    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product)
    {
        Product newProduct= productRepository.save(product);
        return product;
    }
   //@PostMapping("/addDto")
   //public productDto addProductDto(@RequestBody productDto productDto)
   //{
   //    productDto newProductDto=mapStuctMapper.toProductDto(productRepository.save(mapStuctMapper.toProduct(productDto)));
   //    return newProductDto;
   //}
    @PostMapping("/addDto")
    public ResponseEntity<productDto> addProductDto(@RequestPart("file")MultipartFile file, @RequestPart("product") productDto productdto) throws IOException {
        String dir=directory+ file.getOriginalFilename();
        Path path= Paths.get(dir);

        Files.write(path,file.getBytes());
        Product product=mapStuctMapper.toProduct(productdto);

        product.setPicture(file.getOriginalFilename());
        productDto productdto1= mapStuctMapper.toProductDto(productRepository.save(product));
        return new ResponseEntity(productdto1, HttpStatus.CREATED);


    }
}
