package net.proselyte.crud.repository.hibernate;

import net.proselyte.crud.model.Developer;
import net.proselyte.crud.repository.DeveloperRepository;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class HibernateDeveloperRepository implements DeveloperRepository {
    private SessionFactory sessionFactory;
    public HibernateDeveloperRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Developer developer) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(developer);
            session.getTransaction().commit();
            System.out.println("Operation save DEVELOPER. Ok");
        }catch (HibernateException e){
            System.out.println("Operation save DEVELOPER. HibernateException");
        }
    }

    @Override
    public Developer getById(Long aLong) {
        Developer developer = null;
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            developer =  (Developer) session.get(Developer.class, aLong);

//            List<Developer> list = session.createCriteria(Developer.class).add(Restrictions.eq("id", aLong)).setMaxResults(1).list();
//            developer = list.get(0);
            session.getTransaction().commit();

        }catch (HibernateException e){
            System.out.println("Operation getById DEVELOPER . HibernateException");
        }
        return developer;
    }

    @Override
    public void deleteById(Long aLong) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Developer developer = (Developer) session.get(Developer.class, aLong);
            session.delete(developer);

            session.getTransaction().commit();
            System.out.println("Operation delete DEVELOPER. Ok");
        }catch (HibernateException e){
            System.out.println("Operation delete DEVELOPER. HibernateException");
        }
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> list = new ArrayList<>();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();

        //    List<Developer> developers  = session.createQuery("from Developer u").list();

            Criteria criteria = session.createCriteria(Developer.class);
            List<Developer> developers = criteria.list();
            int count = 0;
            for (Developer developer:developers) {
                System.out.println(developer.toString());
                count++;
            }
            if (count == 0){
                System.out.println("0 element's in DEVELOPER ");
            }

        }catch (HibernateException e){
            System.out.println("Operation getAll DEVELOPER . HibernateException");
        }
        return list;
    }

    @Override
    public void update(Developer developer) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            //сохраняем в переменную новое имя и устанавливаем его в полученный объект из БД
            String newName = developer.getFirstName();
            developer =  (Developer) session.get(Developer.class, developer.getId());
            developer.setFirstName(newName);
            session.update(developer);
            session.getTransaction().commit();
            System.out.println("Operation update DEVELOPER. Ok");
        }catch (HibernateException e){
            System.out.println("Operation update DEVELOPER. HibernateException");
        }
    }
}
