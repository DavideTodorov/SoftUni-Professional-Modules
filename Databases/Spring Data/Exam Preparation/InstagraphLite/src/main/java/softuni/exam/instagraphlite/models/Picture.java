package softuni.exam.instagraphlite.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity()
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column(name = "path", unique = true, nullable = false)
    private String path;

    @Column(name = "size")
    private double size;

    public Picture() {

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
