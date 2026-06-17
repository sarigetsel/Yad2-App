package com.example.hand.Service;
import com.example.hand.Dto.areaDto;
import com.example.hand.Dto.categoryDto;
import com.example.hand.Dto.productDto;
import com.example.hand.Dto.sellerDto;
import com.example.hand.model.Product;
import com.example.hand.model.area;
import com.example.hand.model.category;
import com.example.hand.model.seller;
import org.mapstruct.Mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStuctMapper
{
    areaDto toAreaDto(area area);
    area toArea(areaDto areaDto);

     List<areaDto> toListAreaDto(List<area> area);
    List<area> toListArea(List<areaDto> areaDto);

    categoryDto toCategoryDto(category category);
    category toCategory(categoryDto categoryDto);

    List<categoryDto> toListCategoryDto(List<category> category);
    List<category> toListCategory(List<categoryDto> categoryDto);

    default productDto toProductDto(Product product) throws IOException {
        productDto productdto=new productDto();
        productdto.setId(product.getId());
        productdto.setArea(product.getArea());
        productdto.setNameProduct(product.getNameProduct());
        productdto.setPhoneNumber(product.getPhoneNumber());
        productdto.setCategor(product.getCategor());
        productdto.setSeller(product.getSeller());
        productdto.setPrice(product.getPrice());
        productdto.setStatus(product.getStatus());
        productdto.setDescription(product.getDescription());
        productdto.setPicture(product.getPicture());

        if(product.getPicture()!=null)
        {
            try {
                String dir=System.getProperty("user.dir")+"\\images\\";
                Path fileUrl= Paths.get(dir+product.getPicture());
                if (Files.exists(fileUrl)) {
                    productdto.setArrPicture(Files.readAllBytes(fileUrl));
                }
            } catch (IOException ignored) {}
        }
       return productdto;
    }
    default Product toProduct(productDto productdto)
    {
        Product product=new Product();
        product.setId(productdto.getId());
        product.setArea(productdto.getArea());
        product.setNameProduct(productdto.getNameProduct());
        product.setPhoneNumber(productdto.getPhoneNumber());
        product.setCategor(productdto.getCategor());
        product.setSeller(productdto.getSeller());
        product.setPrice(productdto.getPrice());
        product.setStatus(productdto.getStatus());
        product.setDescription(productdto.getDescription());

        return product;
    }

     List<productDto> toListProductDto(List<Product> product);
    List<Product> toListProduct(List<productDto> productDto);

    sellerDto toSellerDto(seller seller);
    seller toSeller(sellerDto sellerDto);

    List<sellerDto> toListSellerDto(List<seller> seller);
    List<seller> toListSeller(List<sellerDto> sellerDto);

}
