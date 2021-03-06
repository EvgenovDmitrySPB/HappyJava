package net.proselyte.crud.model;

import javax.persistence.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skills")
public class Skill extends ClassId {

//    @Id
//    @Column(name = "id",unique = true,nullable = false,length = 5)
//    private Long id;

    @Column(name="name")
    private String name;

 //   @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "skills")
    Set<Developer> developers = new HashSet<>();


    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

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
        super.id = id;
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

    //Добавил компаратор, чтобы класс Skill сортировать по id в выдаче Developer(getAll)
    public static final Comparator<Skill> COMPARE_BY_ID = new Comparator<Skill>() {
        @Override
        public int compare(Skill leftId, Skill rightId) {
            return (leftId.getId().intValue() - rightId.getId().intValue());
        }
    };
}
