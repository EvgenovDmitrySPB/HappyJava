package net.proselyte.crud.repository.hibernate;

import net.proselyte.crud.model.Account;
import net.proselyte.crud.repository.AccountRepository;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class HibernateAccountRepository implements AccountRepository {
    private SessionFactory sessionFactory;
    public HibernateAccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Account account) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
            System.out.println("Operation save ACCOUNT. Ok");
        }catch (HibernateException e){
            System.out.println("Operation save ACCOUNT. HibernateException");
        }
    }

    @Override
    public Account getById(Long aLong) {
        Account account = null;
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            account =  (Account) session.get(Account.class, aLong);
            session.getTransaction().commit();

        }catch (HibernateException e){
            System.out.println("Operation getById ACCOUNT . HibernateException");
        }
        return account;
    }

    @Override
    public void deleteById(Long aLong)  {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Account account =  (Account) session.get(Account.class, aLong);
            session.delete(account);

//            List<Account> list = session.createCriteria(Account.class).add(Restrictions.eq("id", aLong)).setMaxResults(1).list();
//            session.delete(list.get(0));
            session.getTransaction().commit();
            System.out.println("Operation delete ACCOUNT. Ok");
        }catch (HibernateException e){
            System.out.println("Operation delete ACCOUNT. HibernateException");
        }
    }

    @Override
    public void getAll()  {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Account.class);
            List<Account> skills = criteria.list();
            int count = 0;
            for (Account skill:skills) {
                System.out.println(skill.toString());
                count++;
            }
            if (count == 0){
                System.out.println("0 element's in ACCOUNT ");
            }

        }catch (HibernateException e){
            System.out.println("Operation getAll ACCOUNT . HibernateException");
        }
    }

    @Override
    public void update(Account account) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(account);
            session.getTransaction().commit();
            System.out.println("Operation update ACCOUNT. Ok");
        }catch (HibernateException e){
            System.out.println("Operation update ACCOUNT. HibernateException");
        }
    }
}
