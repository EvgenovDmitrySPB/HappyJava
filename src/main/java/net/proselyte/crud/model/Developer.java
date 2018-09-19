package net.proselyte.crud.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @Column(name = "id",unique = true,nullable = false,length = 5)
    Long id;

    @Column(name="firstName", length = 100)
    String firstName;

    @Column(name="lastName", length = 100)
    String lastName;

    @Column(name="specialty", length = 100)
    String specialty;

    @Column(name="account", length = 100)
    Account account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "developer_skills",
            joinColumns = {@JoinColumn(name = "idDeveloper", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "idSkill", referencedColumnName = "id")})
    Set<Skill> skills;

    public Developer(){

    }

    public void removeRole(Skill skill){getSkills().remove(skill);}
    public void addRole(Skill skill){
        //skill.setSkills(this);
        getSkills().add(skill);
    }

    public Developer(Long id, String firstName, String lastName, String specialty, Account account, Set<Skill> skills){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.account = account;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                '}';
    }
}
