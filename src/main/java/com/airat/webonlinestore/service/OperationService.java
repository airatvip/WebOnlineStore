package com.airat.webonlinestore.service;


import com.airat.webonlinestore.model.Socks;

public interface  OperationService {
    public void addOperation (Socks socks);

    abstract void releaseOperation(Socks socks);

    abstract void removeOperation(Socks socks);

    void readFromFileOperation();
}
