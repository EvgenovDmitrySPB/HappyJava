package net.proselyte.crud.controller.dispatcher;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

/**
 * Created by EvgenovDS on 07.11.2018.
 */
@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView main(){
        return new ModelAndView("index");
        // Запрос по точке входа "/" будет выводить нам index.jsp, а найдет он его благодаря нашему dispatcher-servlet
    }
}
