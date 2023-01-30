package com.airat.webonlinestore.service.impl;

import com.airat.webonlinestore.exception.FileErrorException;
import com.airat.webonlinestore.service.FileSocksService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileSocksServiceImpl implements FileSocksService {
    @Value("${path.to.data.files}")
    private String dataFilePath;
    @Value("${nameSocks.of.data.files}")
    private String nameDataFile;

    @Override
    public boolean saveToFile(String json) {
        try {
            Files.writeString(Path.of(dataFilePath, nameDataFile), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFile() {
        try {
            return Files.readString(Path.of(dataFilePath, nameDataFile));

        } catch (IOException e) {
            e.printStackTrace();
            throw new FileErrorException("Ошибка при чтении файла");
        }

    }


}