package com.example.cardealer.entities.dtos;

import com.google.gson.annotations.Expose;

public class SupplierInputDto {

    @Expose
    private String name;

    @Expose
    private boolean isImporter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
