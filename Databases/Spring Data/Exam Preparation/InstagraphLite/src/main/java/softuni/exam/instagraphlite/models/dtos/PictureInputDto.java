package softuni.exam.instagraphlite.models.dtos;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

public class PictureInputDto {

    @Expose
    @NotBlank
    @Column(unique = true)
    private String path;

    @Expose()
    @DecimalMin(value = "500")
    @DecimalMax(value = "60000")
    private double size;

    public PictureInputDto() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
