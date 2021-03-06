package net.proselyte.crud.repository.hibernate;

import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.SkillRepository;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class HibernateSkillRepository implements SkillRepository {
    private SessionFactory sessionFactory;
    public HibernateSkillRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Skill skill) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(skill);
            session.getTransaction().commit();
            System.out.println("Operation save SKILL. Ok");
        }catch (HibernateException e){
            System.out.println("Operation save SKILL. HibernateException");
        }
    }

    @Override
    public Skill getById(Long aLong) {
        Skill skill = null;
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            skill = (Skill) session.get(Skill.class, aLong);

//            List<Skill> list = session.createCriteria(Skill.class).add(Restrictions.eq("id", aLong)).setMaxResults(1).list();
//            skill = list.get(0);
            session.getTransaction().commit();

        }catch (HibernateException e){
            System.out.println("Operation getById SKILL . HibernateException");
        }
        return skill;
    }

    @Override
    public void deleteById(Long aLong) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Skill skill = (Skill) session.get(Skill.class, aLong);
            session.delete(skill);
//            Object obj = session.load(Skill.class, aLong.intValue());
//            if (obj != null){
//                session.delete(obj);
//            }

            session.getTransaction().commit();
            System.out.println("Operation delete SKILL. Ok");
        }catch (HibernateException e){
            System.out.println("Operation delete SKILL. HibernateException");
        }
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> list = new ArrayList<>();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            //получить все Skill
            list = session.createCriteria(Skill.class).list();

            if (list.size() == 0){
                System.out.println("0 element's in SKILL ");
            }
        }catch (HibernateException e){
            System.out.println("Operation getAll SKILL . HibernateException");
        }
        return list;
    }

    @Override
    public void update(Skill skill) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(skill);
            session.getTransaction().commit();
            System.out.println("Operation update SKILL. Ok");
        }catch (HibernateException e){
            System.out.println("Operation update SKILL. HibernateException");
        }
    }
}
