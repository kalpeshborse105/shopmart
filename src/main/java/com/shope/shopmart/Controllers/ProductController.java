package com.shope.shopmart.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shope.shopmart.Services.ProductService;
import com.shope.shopmart.dtos.AddProductDto;
import com.shope.shopmart.dtos.UpdateProductDto;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // AddProduct
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody AddProductDto addProductDto) {
        return ResponseEntity.ok(this.productService.createProduct(addProductDto));
    }

    // GetAllProduct
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    // GetProductById
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.productService.getProductById(id));
    }
    // UpdateProduct
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UpdateProductDto updateProductDto) {
        return ResponseEntity.ok(this.productService.updateProduct(id, updateProductDto));
    }

    // DeleteProduct
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
        // .ok("Product deleted successfully");
    }

// image upload
@PutMapping("/{id}/upload")
    public ResponseEntity<?>upload(@PathVariable Integer id,@RequestParam MultipartFile file)
{
System.out.println("File Uploaded");
return ResponseEntity.ok(this.productService.uploadFile(id,file));
}




// search product 

    @GetMapping("/search/findbymanufacturer")
    public ResponseEntity<?> getByManufacturer(@RequestParam String manufacturer) {
        return ResponseEntity.ok(this.productService.getByManufacturer(manufacturer));
    }

    @GetMapping("/search/findbyprice")
    public ResponseEntity<?> getByPrice(@RequestParam Double price) {
        return ResponseEntity.ok(this.productService.getByPrice(price));
    }

    @GetMapping("/search/sortbyname")
    public ResponseEntity<?> sortByName() {
        return ResponseEntity.ok(this.productService.sortByName());
    }

}