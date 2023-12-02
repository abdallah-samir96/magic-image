package com.example.magicimage.controllers;

import com.example.magicimage.service.FileConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/files")
public class FileConverterController {

    private final FileConverterService fileConverterService;
    public FileConverterController(FileConverterService service){
        this.fileConverterService = service;
    }

    @PostMapping
    public ResponseEntity<Object> convert(@RequestParam("pdf_path") String pdfPath, @RequestParam("image_path") String imagePath){
        try {
            fileConverterService.convert(pdfPath, imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.noContent().build();
    }
}
