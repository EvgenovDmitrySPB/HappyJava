package net.proselyte.crud.controller.servlets;

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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(value = "/skill", name = "skill")
public class SkillServlet extends HttpServlet {
    private SkillRepository skillRepository;
    private Connection connection;
    private SessionFactory sessionFactory;

    public SkillServlet() {
        checkCreateRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("Cp1251");

        if (SelectConnection.getInstance().getConnectType() != null){

            if (skillRepository == null){
                checkCreateRepository();
            }

            List<Skill> list = skillRepository.getAll();
            req.setAttribute("skillList", list);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("skill/listSkill.jsp");
            requestDispatcher.forward(req, resp);
        }else{
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("404.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("Cp1251");

        Long id = null;
        String name = "";
        String method = "";
        if (req.getParameter("id") != null){
            id = Long.parseLong(req.getParameter("id"));
        }
        if (req.getParameter("name") != null){
            name =req.getParameter("name");
        }
        if (req.getParameter("method") != null){
            method = req.getParameter("method");
        }

        if (method.equals("POST") && name != ""){
            SkillBuilder skillBuilder = new SkillBuilder();
            skillBuilder.withName(name);
            skillRepository.save(skillBuilder.toSkill());

        }else if (method.equals("PUT") && name != "" && id != null){

            SkillBuilder skillBuilder = new SkillBuilder();
            skillBuilder.withId(id).withName(name);

            skillRepository.update(skillBuilder.toSkill());
        }else if (method.equals("DELETE") && name != "" && id != null){
            skillRepository.deleteById(id);
        }

        List<Skill> list = skillRepository.getAll();
        req.setAttribute("skillList", list);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("skill/listSkill.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
    }

    private void checkCreateRepository(){
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
}
