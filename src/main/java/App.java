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
            return new ModelAndView(model, "index.hbs");
        }), new HandlebarsTemplateEngine());

        //route to fill Squad form
        get("/form",((request, response) -> {
            Map<String, Object> model = new HashMap();
            return new ModelAndView(model,"form.hbs");
        }),new HandlebarsTemplateEngine());

        //post form results
        post("/Squad",((request, response) -> {
            Map<String, Object> model = new HashMap();
            String squadName = request.queryParams("squadName");
            request.session().attribute("squadName", squadName);
            int maxSize =Integer.parseInt(request.queryParams("maxSize"));
            request.session().attribute("maxSize", maxSize);
            String description = request.queryParams("description");
            request.session().attribute("description", description);
            Squad squad = new Squad(squadName,maxSize,description);
            response.redirect("/Squad");
            return new ModelAndView(model,"success.hbs");
        }),new HandlebarsTemplateEngine());

        //show all squads
        get("/Squad",((request, response) -> {
            Map <String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getInstances();
            model.put("squads", squads);
            return new ModelAndView(model, "Squad.hbs");
        }),new HandlebarsTemplateEngine());


        //get individual posted squad
        get("/Squad/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int SquadIdToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Squad squad = Squad.findById(SquadIdToFind); //use it to find post
            model.put("squad", squad); //add it to model for template to display
            return new ModelAndView(model, "Squad.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());


    }


    }

