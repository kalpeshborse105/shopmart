package com.shope.shopmart.Services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shope.shopmart.FileStorageProperties;
import com.shope.shopmart.Entities.Product;
import com.shope.shopmart.Repository.ProductRepository;
import com.shope.shopmart.dtos.AddProductDto;
import com.shope.shopmart.dtos.UpdateProductDto;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(AddProductDto addProductDto) {
        Product product = new Product();
        product.setProductName(addProductDto.getProductName());
        product.setManufacturingDate(addProductDto.getManufacturingDate());
        product.setManufacturer(addProductDto.getManufacturer());
        product.setPrice(addProductDto.getPrice());
        product.setDescription(addProductDto.getDescription());
        product.setImageUrl(addProductDto.getImageUrl());
        productRepository.save(product);
        return product;
    }


    // GetAllProduct
    public Iterable<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    // GetProductById
    public Product getProductById(Integer id) {
        Product product = this.productRepository.findById(id).orElseGet(null);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

    // UpdateProduct
    public Product updateProduct(Integer id, UpdateProductDto updateProductDto) {
        Product product = getProductById(id);
        if (updateProductDto.getProductName() != null) {
            product.setProductName(updateProductDto.getProductName());
        }
        if (updateProductDto.getDescription() != null) {
            product.setDescription(updateProductDto.getDescription());
        }
        if (updateProductDto.getManufacturer() != null) {
            product.setManufacturer(updateProductDto.getManufacturer());
        }
        if (updateProductDto.getManufacturingDate() != null) {
            product.setManufacturingDate(updateProductDto.getManufacturingDate());
        }
        if (updateProductDto.getPrice() != null) {
            product.setPrice(updateProductDto.getPrice());
        }
        if (updateProductDto.getImageUrl() != null) {
            product.setImageUrl(updateProductDto.getImageUrl());
        }
        return this.productRepository.save(product);
    }
    // DeleteProduct

    public void deleteProduct(Integer id) {
        Product product = this.getProductById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        
        // products.remove(id);
        this.productRepository.deleteById(id);
    }
        // GetByManufacturer
        public List<Product> getByManufacturer(String manufacturer) {
            return this.productRepository.findByManufacturer(manufacturer);
        }
    
        // GetByPrice
        public List<Product> getByPrice(Double price) {
            return this.productRepository.findByPriceLessThan(price);
        }
    
        // SortByName
        public List<Product> sortByName() {
            return this.productRepository.sortByName();
        }
    
        // image upload 
        private final Path rootLocation;

    public ProductService(FileStorageProperties properties)
    {
        this.rootLocation=Paths.get(properties.getUploadDir());
        try{
            Files.createDirectories(rootLocation);
        }
        catch(IOException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"upload directory not created");
        }
    }
    public String uploadFile(Integer id, MultipartFile file) {
      Path  destinationFile=this.rootLocation.resolve(Paths.get(file.getOriginalFilename()));
      try{
        InputStream inputstream=file.getInputStream();
        Files.copy(inputstream,destinationFile,StandardCopyOption.REPLACE_EXISTING);
        inputstream.close();
      }
      catch(Exception e)
      {
       throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"file cannot be uploaded");
      }
      String imageUrl=ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/products/download")
      .path(file.getOriginalFilename())
      .toUriString();

      Product product=this.productRepository.findById(id).get();
      product.setImageUrl(imageUrl);
      this.productRepository.save(product);
      return "File Uploaded Successfully";

      }
    }
    

