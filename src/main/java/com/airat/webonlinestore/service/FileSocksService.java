package com.airat.webonlinestore.service;

public interface FileSocksService {
    boolean saveToFile(String json);

    String readFile();

}
