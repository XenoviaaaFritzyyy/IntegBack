package com.petsociety.backend.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Service
public class ImageService {

    @PostConstruct
    private void init() throws IOException {
        Path imagesPath = Paths.get("images");
        if (!Files.exists(imagesPath) && !Files.isDirectory(imagesPath)) {
            Files.createDirectories(imagesPath);
        }
    }

    public void uploadImage(MultipartFile file) throws IOException {
        Path imagesPath = Paths.get("images");
        if(file.getOriginalFilename() == null){
            throw new IOException("Original name is null");
        }
        String originalFilename = file.getOriginalFilename();
        Path filePath = imagesPath.resolve(originalFilename);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public byte[] downloadImage(String fileName) throws IOException {
        Path imagesPath = Paths.get("images");
        Path filePath = imagesPath.resolve(fileName);
        return Files.readAllBytes(filePath);
    }

    public void deleteFile(String fileName) throws IOException {
        Path imagesPath = Paths.get("images");
        Path filePath = imagesPath.resolve(fileName);
        Files.deleteIfExists(filePath);
    }

}