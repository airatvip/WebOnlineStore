package com.airat.webonlinestore.service;

import java.io.File;

public interface FileOperationService {
    boolean saveToFile(String json);

    String readFile();

    boolean cleanDataFile();

    File getDataFile();
}
