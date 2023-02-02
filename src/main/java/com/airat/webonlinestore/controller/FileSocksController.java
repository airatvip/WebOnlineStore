package com.airat.webonlinestore.controller;


import com.airat.webonlinestore.service.FileOperationService;
import com.airat.webonlinestore.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@RestController
@RequestMapping("/api/socks")
@Tag(name = "Носки", description = "Операции")
public class FileSocksController {

    private final OperationService operationService;
    private final FileOperationService fileOperationService;

    public FileSocksController(OperationService operationService, FileOperationService fileOperationService) {
        this.operationService = operationService;
        this.fileOperationService = fileOperationService;
    }

    @GetMapping("/export")
    @Operation(
            summary = "Скачивание данных по операциям"
    )
    public ResponseEntity<InputStreamResource> downloadTextFile() throws FileNotFoundException {
        File file = fileOperationService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "export; filename=\"Operation.json\"")
                    .body(resource);
        } else return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка данных по операциям"
    )
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile multipartFile) {
        fileOperationService.cleanDataFile();
        File dataFile = fileOperationService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(multipartFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();

    }

}
