package net.proselyte.crud.builders;

import net.proselyte.crud.model.Skill;

public class SkillBuilder {

    private Long id;
    private String name;

    public SkillBuilder() {
    }

    public SkillBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SkillBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Skill toSkill() {
        return new Skill(this.id, this.name);
    }

    public Skill getSkill(){
        SkillBuilder skillBuilder = new SkillBuilder()
                .withId(1L)
                .withName("TEST");

        return skillBuilder.toSkill();
    }
}
