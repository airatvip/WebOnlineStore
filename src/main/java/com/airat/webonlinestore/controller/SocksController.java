package com.airat.webonlinestore.controller;


import com.airat.webonlinestore.model.Socks;
import com.airat.webonlinestore.service.SocksService;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/socks")
@Tag(name = "Носки", description = "Операции")
public class SocksController {

    private final SocksService socksService;


    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @Operation(
            summary = "Добавление носков")
    @PostMapping()
    public void addSocks(@RequestBody Socks socks) {
        socksService.addSocks(socks);
    }

    @Operation(
            summary = "Реализация носков")
    @PutMapping()
    public void releaseSocks(@RequestBody Socks socks) {
        socksService.releaseSocks(socks);
    }

    @Operation(
            summary = "Получить количество носков по цвету, размеру и содержанию хлопка",
            description = "Выберите цвет, размер и минимальное и максимальное количество хлопка"
    )
    @GetMapping()
    @JsonValue
    public String getRemainsOfSocks(@RequestParam(defaultValue = "Черный") String color,
                                    @RequestParam(defaultValue = "23") int size,
                                    @RequestParam(defaultValue = "0") int minCottonPart,
                                    @RequestParam(defaultValue = "100") int maxCottonPart) {
        return socksService.getRemainsOfSocks(color, size, minCottonPart, maxCottonPart);
    }

    @Operation(
            summary = "Удаление носков")
    @DeleteMapping()
    public void deleteSocks(@RequestBody Socks socks) {
        socksService.removeSocks(socks);
    }
}