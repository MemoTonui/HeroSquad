import model.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        //home route
        get("/",((request, response) -> {
            Map<String, Object> model = new HashMap();
            ArrayList<Squad> squads = Squad.getInstances();
            model.put("squads", squads);
            return new ModelAndView(model, "index.hbs");
        }), new HandlebarsTemplateEngine());

    }


    }

