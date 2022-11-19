package com.msc.easybiz.easybizservice.contoller;

import com.msc.easybiz.easybizservice.service.ProductService;
import com.msc.easybiz.easybizservice.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

    @RestController("/product")
    @RequestMapping(path = "/management/product", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public class ProductController extends BaseController {

        private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

        @Autowired
        private ProductService productService;

        @GetMapping(path = "/")
        public ResponseEntity<?> getProducts() {
            logger.info("Request to get all products");
            return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
        }

        @GetMapping(path = "/{productCode}")
        public ResponseEntity<?> getProduct(@PathVariable("productCode") String productCode) {
            logger.info("Request to get product details for code : {}", productCode);
            return new ResponseEntity<>(productService.get(productCode), HttpStatus.OK);
        }

        @PostMapping(path = "/")
        public ResponseEntity<?> addProject(@RequestBody String body) {
            logger.info("Request to add a new product");
            Map<String, String> data = util.getData(body);
            return new ResponseEntity<>(productService.insert(data.get("product_code"), data.get("description")), HttpStatus.OK);
        }

        @PutMapping(path = "/{productCode}")
        public ResponseEntity<?> updateProject(@PathVariable("productCode") String productCode, @RequestBody String body) {
            logger.info("Request to update product details for code : {}", productCode);
            Map<String, String> data = util.getData(body);
            return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
        }

        @DeleteMapping(path = "/{productCode}")
        public ResponseEntity<?> deleteProject(@PathVariable("productCode") String productCode) {
            logger.info("Request to delete product with code: {}", productCode);
            productService.delete(productCode);
            return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
        }
    }

