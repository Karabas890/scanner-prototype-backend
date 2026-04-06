package com.example.scannerprototypebackend.service;

import com.example.scannerprototypebackend.exception.InvalidQrCodeException;
import com.example.scannerprototypebackend.model.Ankf;
import com.example.scannerprototypebackend.model.Lahta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockDataService {

    public Object getById(String id) {

        if (id == null || id.isBlank()) {
            throw new InvalidQrCodeException("ID пустой или отсутствует");
        }

        if (id.startsWith("ankf:")) {
            return getAnkf();
        }

        if (id.startsWith("LHT-")) {
            return getLahta();
        }

        throw new InvalidQrCodeException("Неизвестный формат QR-кода: " + id);
    }

    private Ankf getAnkf() {
        Ankf a = new Ankf();
        a.id = "ankf:v1:G7F9J2KM";
        a.type = "Цифровой контроль АНКФ";
        a.productName = "Задвижка клиновая литая ЗКЛ-2";
        a.manufacturer = "ООО «УЗСА»";
        a.gost = "ГОСТ 5762-2002";
        a.guarantee = "24 месяца";
        a.status = "Подтверждено / Оригинал";
        a.documents = List.of("Сертификат соответствия.pdf", "Паспорт изделия.pdf");
        return a;
    }

    private Lahta getLahta() {
        Lahta l = new Lahta();
        l.id = "LHT-SKLAD-9982";
        l.type = "Лахта Склад - Система МТО";
        l.productName = "Задвижка клиновая (учетная единица)";
        l.location = "Стеллаж B, Полка 4, Ячейка 12";
        l.stockCount = 15;
        l.unit = "шт.";
        l.warehouseStatus = "В наличии / Доступно к выдаче";
        l.responsible = "Иванов И.И.";
        return l;
    }
}