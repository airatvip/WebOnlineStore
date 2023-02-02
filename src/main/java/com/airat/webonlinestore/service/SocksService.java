package com.airat.webonlinestore.service;

import com.airat.webonlinestore.model.Color;
import com.airat.webonlinestore.model.Size;
import com.airat.webonlinestore.model.Socks;


public interface SocksService {
    void addSocks(Socks sock);

    void releaseSocks(Socks sock);


    void removeSocks(Socks sock);

    String getRemainsOfSocks(Color color, Size size, int minCottonPart, int maxCottonPart);

    void saveFile();

    void readFromFile();


}
