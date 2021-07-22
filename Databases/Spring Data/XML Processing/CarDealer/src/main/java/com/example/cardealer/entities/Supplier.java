package com.example.cardealer.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Column
    private String name;

    @Column(name = "is_importer")
    private boolean isImporter;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private List<Part> parts;

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

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return isImporter == supplier.isImporter && Objects.equals(name, supplier.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isImporter);
    }
}
