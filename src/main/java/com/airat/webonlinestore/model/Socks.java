package com.airat.webonlinestore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Getter
public class Socks {

    private Color color;
    private Size size;
    private int cottonPart;
    private int quantity;

    public Socks(Color color, Size size, int cottonPart, int quantity) {
        this.color = color;
        this.size = size;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && size == socks.size && color == socks.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonPart, quantity);
    }

}

