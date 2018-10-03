package net.proselyte.crud.controller;

import net.proselyte.crud.builders.SkillBuilder;
import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.SkillRepository;
import net.proselyte.crud.repository.hibernate.HibernateSkillRepository;
import net.proselyte.crud.repository.jdbc.JDBCSkillRepositoryImpl;
import net.proselyte.crud.util.ConnectorHibernateMySQL;
import net.proselyte.crud.util.ConnectorMySQL;
import net.proselyte.crud.util.SelectConnection;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class SkillController {

    private SkillRepository skillRepository;
    private Connection connection;
    private SessionFactory sessionFactory;

    public SkillController()  {
        if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
            this.connection = ConnectorMySQL.getInstance().getConnection();

            if (this.connection == null){
                System.out.println("Warning! You don't have [JDBC] connection with MySQL");
                return;
            }else {
                skillRepository = new JDBCSkillRepositoryImpl(connection);
            }
        }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
            this.sessionFactory = ConnectorHibernateMySQL.getInstance().getSessionFactory();
            if (this.sessionFactory == null){
                System.out.println("Warning! You don't have {Hibernate} SessionFactory with MySQL");
                return;
            }else {
                skillRepository = new HibernateSkillRepository(sessionFactory);
            }
        }
    }

    public void saveSkill(Map<String, Object> map) {
        if(map.isEmpty()){
            throw new IllegalArgumentException();
        }
        String name = (String)map.get("name");

        SkillBuilder skillBuilder = new SkillBuilder();
        skillBuilder.withName(name);

        skillRepository.save(skillBuilder.toSkill());
    }

    public boolean getSkillById(Long id)  {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        Skill skill = skillRepository.getById(id);
        if (skill.getId() != null){
            System.out.println(skill.toString());
            return true;
        }else {
            System.out.println("Skill not found by id = " + id);
            return false;
        }
    }

    public void deleteById(Long id) {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        skillRepository.deleteById(id);
    }

    public void getAll() {
        skillRepository.getAll();
        List<Skill> list = skillRepository.getAll();
        for (Skill skill:list) {
            System.out.println(skill.toString());
        }
    }

    public void updateSkill(Map<String, Object> map) {
        if(map.isEmpty()){
            throw new IllegalArgumentException();
        }
        Long id     = (Long)map.get("id");
        String name = (String)map.get("name");

        SkillBuilder skillBuilder = new SkillBuilder();
        skillBuilder.withId(id).withName(name);

        skillRepository.update(skillBuilder.toSkill());
    }
}
