package net.proselyte.crud.model;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class Phones extends ClassId{

    @Column(name="name", length = 100)
    String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idDeveloper", referencedColumnName = "id")
    Developer iDeveloper;

    public Phones() {

    }

    public Phones(long id, Developer developer) {
        this.id = id;
        this.iDeveloper = developer;
    }

    public long getId() {
        return super.id;
    }

    public void setId(long id) {
        super.id = id;
    }

    public Developer getDeveloper() {
        return iDeveloper;
    }

    public void setDeveloper(Developer developer) {
        this.iDeveloper = developer;
    }

    @Override
    public String toString() {
        return "Phones{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
