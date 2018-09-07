package net.proselyte.crud.builders;

import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.Developer;
import net.proselyte.crud.model.Skill;

import java.util.HashSet;
import java.util.Set;

public class DeveloperBuilder  {
    Long id;
    String firstName;
    String lastName;
    String specialty;
    Account account;
    Set<Skill> skills;

    public DeveloperBuilder(){

    }
    public DeveloperBuilder withId(Long id){
        this.id = id;
        return this;
    }
    public DeveloperBuilder withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }
    public DeveloperBuilder withLastName(String lastName){
        this.lastName = lastName;
        return this;
    }
    public DeveloperBuilder withSpecialty(String specialty){
        this.specialty = specialty;
        return this;
    }
    public DeveloperBuilder withAccount(Account account){
        this.account = account;
        return this;
    }
    public DeveloperBuilder withSkill(Set<Skill> skills){
        this.skills = skills;
        return this;
    }
    public Developer toDeveloper(){
        return new Developer(this.id, this.firstName, this.lastName, this.specialty, this.account, this.skills);
    }
    public Developer getDeveloper(){
        Set<Skill> setSkill = new HashSet<>();
        setSkill.add(new Skill(1L,"Tester"));
        setSkill.add(new Skill(2L,"Cprogrammer"));
        setSkill.add(new Skill(3L,"Java programmer"));

        DeveloperBuilder developerBuilder = new DeveloperBuilder()
                .withId(1L).withFirstName("Test").
                        withLastName("TestFamily").
                        withSpecialty("Testers").
                        withAccount(new Account(1L,"Tester")).
                        withSkill(setSkill);

        return developerBuilder.toDeveloper();
    }

}
