package com.airat.webonlinestore.service.impl;
import com.airat.webonlinestore.exception.FileErrorException;
import com.airat.webonlinestore.exception.InCorrectInputException;
import com.airat.webonlinestore.model.Operation;
import com.airat.webonlinestore.model.Socks;
import com.airat.webonlinestore.model.TypeOperation;
import com.airat.webonlinestore.service.FileOperationService;
import com.airat.webonlinestore.service.FileSocksService;
import com.airat.webonlinestore.service.OperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    List<Operation> operations = new ArrayList<>();

    private final FileSocksService fileSocksService;
    private final FileOperationService fileOperationService;

    public OperationServiceImpl(FileSocksService fileSocksService, FileOperationService fileOperationService) {
        this.fileSocksService = fileSocksService;
        this.fileOperationService = fileOperationService;
    }

    @PostConstruct
    private void init() {
        readFromFileOperation();
    }

    @Override
    public void addOperation(Socks socks) {
        operation(TypeOperation.ADD, socks);
        saveFileOperation();

    }

    @Override
    public void releaseOperation(Socks socks) {
        operation(TypeOperation.RELEASE, socks);
        saveFileOperation();
    }

    @Override
    public void removeOperation(Socks socks) {
        operation(TypeOperation.REMOVE, socks);
        saveFileOperation();
    }

    private void operation(TypeOperation typeOperation, Socks socks) {
        this.operations.add(new Operation(typeOperation, LocalDateTime.now(), socks));
    }


    private void saveFileOperation() {
        try {

            String json = new ObjectMapper().findAndRegisterModules().writeValueAsString(operations);
            fileOperationService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new InCorrectInputException("Некорректный ввод данных");
        }
    }

    @Override
    public void readFromFileOperation() {
        String json = fileOperationService.readFile();
        try {
            operations = new ObjectMapper().findAndRegisterModules().readValue(json, new TypeReference<ArrayList<Operation>>() {
                    }
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileErrorException("Файл не найден");
        }
    }


}
