package net.proselyte.crud.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer extends ClassId  {

//    @Id
//    @Column(name = "id",unique = true,nullable = false,length = 5)
//    Long id;

    @Column(name="firstName", length = 100)
    String firstName;

    @Column(name="lastName", length = 100)
    String lastName;

    @Column(name="specialty", length = 100)
    String specialty;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account")
    Account account;

    @ManyToMany(fetch = FetchType.LAZY)
    @OrderBy(value = "id")
    @JoinTable(
            name = "developer_skills",
            joinColumns = {@JoinColumn(name = "idDeveloper", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "idSkill", referencedColumnName = "id")})
    Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "iDeveloper", fetch = FetchType.LAZY)
    List<Phones> phones;

    public Developer(){

    }

    public Developer(Long id, String firstName, String lastName, String specialty, Account account, Set<Skill> skills){
        super.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.account = account;
        this.skills = skills;
    }

    public List<Phones> getPhones() {
        return phones;
    }

    public void setPhones(List<Phones> phones) {
        this.phones = phones;
    }

    public Long getId() {
        return super.id;
    }

    public void setId(Long id) {
        super.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", account=" + account +
                ", skills=" + skills +
                ", phones=" + phones +
                '}';
    }
}
