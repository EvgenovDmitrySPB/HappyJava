package net.proselyte.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "skills")
public class Skill extends ClassId {

//    @Id
//    @Column(name = "id",unique = true,nullable = false,length = 5)
//    private Long id;

    @Column(name="name")
    private String name;

    public Skill() {
    }

    public Skill(Long id, String name) {
        super.id = id;
        this.name = name;
    }

    public Long getId() {
        return super.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
