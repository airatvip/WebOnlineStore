package com.airat.webonlinestore.service.impl;

import com.airat.webonlinestore.exception.FileErrorException;
import com.airat.webonlinestore.exception.InCorrectInputException;
import com.airat.webonlinestore.exception.QuantityException;
import com.airat.webonlinestore.model.Color;
import com.airat.webonlinestore.model.Size;
import com.airat.webonlinestore.model.Socks;
import com.airat.webonlinestore.service.FileSocksService;
import com.airat.webonlinestore.service.OperationService;
import com.airat.webonlinestore.service.SocksService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocksServiceImpl implements SocksService {

    List<Socks> socks = new ArrayList<>();

    private final FileSocksService fileSocksService;
    private final OperationService operationService;


    public SocksServiceImpl(FileSocksService fileSocksService, OperationService operationService) {
        this.fileSocksService = fileSocksService;
        this.operationService = operationService;

    }

    @PostConstruct
    private void init() {
        readFromFile();
        }

    private void validateValue(Socks socks) {
        if (socks.getColor() == null || socks.getSize() == null) {
            throw new InCorrectInputException("Заполните поля");
        }
        if (socks.getCottonPart() < 0 || socks.getCottonPart() > 100) {
            throw new InCorrectInputException("Процентное содержание хлопка 0-100");
        }
        if (socks.getQuantity() <= 0) {
            throw new InCorrectInputException("Количество носков не может быть отрицательным или равным 0");
        }

    }

    @Override
    public void addSocks(Socks sock) {
        validateValue(sock);
        if (socks.contains(sock)) {
            operationService.addOperation(sock);
            int index = socks.indexOf(sock);
            socks.get(index).setQuantity(socks.get(index).getQuantity() + sock.getQuantity());
            saveFile();
        } else socks.add(sock);
        saveFile();
    }


    @Override
    public void releaseSocks(Socks sock) {
        validateValue(sock);
        if (socks.contains(sock)) ;
        operationService.releaseOperation(sock);
        int index = socks.indexOf(sock);
        if (socks.get(index).getQuantity() >= sock.getQuantity()) {
            operationService.releaseOperation(sock);
            socks.get(index).setQuantity(socks.get(index).getQuantity() - sock.getQuantity());
            saveFile();
        } else throw new QuantityException("Носков на складе осталось меньшее количество");

    }

    @Override
    public void removeSocks(Socks sock) {
        validateValue(sock);
        if (socks.contains(sock)) ;
        int index = socks.indexOf(sock);
        if (socks.get(index).getQuantity() >= sock.getQuantity()) {
            operationService.removeOperation(sock);
            socks.get(index).setQuantity(socks.get(index).getQuantity() - sock.getQuantity());
            saveFile();
        } else throw new QuantityException("Носков на складе осталось меньшее количество");

    }


    @Override
    public String getRemainsOfSocks(Color color, Size size, int minCottonPart, int maxCottonPart) {
        int i = 0;
        if (minCottonPart < 0) {
            throw new InCorrectInputException("Процентное содержание хлопка не может быть отрицательным");
        }
        if (maxCottonPart > 100) {
            throw new InCorrectInputException("Процентное содержание хлопка не может быть больше 100");
        }
        for (int j = 0; j < socks.size(); j++) {
            if (socks.get(j).getColor().equals(color) &&
                    socks.get(j).getSize().equals(size) &&
                    (socks.get(j).getCottonPart() >= minCottonPart) &&
                    (socks.get(j).getCottonPart() <= maxCottonPart)) {
                i = i + socks.get(j).getQuantity();

            }

        }
        return String.valueOf(i);
    }


    @Override
    public void saveFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(socks);
            fileSocksService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileErrorException("Некорректный ввод данных");
        }
    }

    @Override
    public void readFromFile() {
        String json = fileSocksService.readFile();
        try {
            socks = new ObjectMapper().readValue(json, new TypeReference<ArrayList<Socks>>() {
                    }
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileErrorException("Файл не найден");
        }
    }

}


