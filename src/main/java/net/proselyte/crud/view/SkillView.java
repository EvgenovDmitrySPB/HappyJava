package net.proselyte.crud.view;

import net.proselyte.crud.builders.SkillBuilder;
import net.proselyte.crud.controller.SkillController;
import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.model.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SkillView {

    private SkillController skillController;

    public SkillView(){
        skillController = new SkillController();
    }

    public void saveSkill() throws IOException {
        //TODO: get data from console
        //TODO: build Skill instance from console data
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id");
        Long id = scanner2.nextLong();
        System.out.println("Please enter name");

        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        String name = " ";
        if ((name = bufReader.readLine()) != null);

        SkillBuilder skillBuilder = new SkillBuilder();
        skillBuilder.withId(id).withName(name);
        skillController.saveSkill(skillBuilder.toSkill());
    }

    public void getSkillById(){
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id");
        Long id = scanner2.nextLong();

        Skill skill = skillController.getSkillById(id);
        if (skill.getId() != null){
            System.out.println(skill.toString());
        }else {
            System.out.println("Skill not found by id = " + id);
        }
    }

    public void deleteById(){
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id for delete:");
        Long id = scanner2.nextLong();

        skillController.deleteById(id);
    }

    public void getAllSkill(){
        skillController.getAll();
    }

    public void updateSkill() throws IOException{
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id for update:");
        Long id = scanner2.nextLong();
        System.out.println("Please enter new name:");
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        String name = " ";
        if ((name = bufReader.readLine()) != null);

        SkillBuilder skillBuilder = new SkillBuilder();
        skillBuilder.withId(id).withName(name);
        skillController.updateSkill(skillBuilder.toSkill());
    }
}
