package net.proselyte.crud.util;

import net.proselyte.crud.model.ConnectType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/SelectConnection", name = "SelectConnection")
public class SelectConnection extends HttpServlet {
    private static ConnectType connectType;
    private static SelectConnection INSTANCE = null;

    public static SelectConnection getInstance(){
        if (INSTANCE == null){
            INSTANCE = new SelectConnection();
        }
        return INSTANCE;
    }

    public ConnectType getConnectType() {
        return connectType;
    }

    public void setConnectType(ConnectType connectType) {
        this.connectType = connectType;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("Cp1251");

        if (req.getParameter("jdbc") != null){
            SelectConnection selectConnection = new SelectConnection();
            selectConnection.setConnectType(ConnectType.JDBC);
        }
        if (req.getParameter("hibernate") != null){
            SelectConnection selectConnection = new SelectConnection();
            selectConnection.setConnectType(ConnectType.HIBERNATE);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
        requestDispatcher.forward(req, resp);

    }
}


