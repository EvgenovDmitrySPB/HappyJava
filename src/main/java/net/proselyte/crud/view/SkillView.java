package net.proselyte.crud.view;

import net.proselyte.crud.controller.SkillController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
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
        System.out.println("Please enter name");

        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        String name = "";
        if ((name = bufReader.readLine()) != null);
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);

        skillController.saveSkill(map);
    }

    public void getSkillById(){
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter id");
        Long id = scanner2.nextLong();

        skillController.getSkillById(id);
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

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);

        skillController.updateSkill(map);
    }
}
