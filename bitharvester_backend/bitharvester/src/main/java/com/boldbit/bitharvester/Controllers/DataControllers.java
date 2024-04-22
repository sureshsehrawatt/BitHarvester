package com.boldbit.bitharvester.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boldbit.bitharvester.Harvester.compiler.source.StaticSourceFile;
import com.boldbit.bitharvester.Services.DataService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DataControllers {

    @Autowired
    DataService dataService;

    @PostMapping("api/processcode")
    public ResponseEntity<?> processcode(@RequestPart("file") MultipartFile file) {
        try {
            // Check if file is empty
            if (file.isEmpty()) {
                return new ResponseEntity<>("Please upload a file!", HttpStatus.BAD_REQUEST);
            }

            StaticSourceFile staticSourceFile = new StaticSourceFile(file.getOriginalFilename(),
                    new String(file.getBytes()), file.getSize());

            String response = dataService.processcode(staticSourceFile);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error in file processing", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/test/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            System.out.println("test done...");
            return ResponseEntity.ok().body(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Id not found", HttpStatus.NOT_FOUND);
        }
    }
}
