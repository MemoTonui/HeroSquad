import model.Hero;
import model.Squad;
import spark.ModelAndView;
import spark.Spark;
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
        Spark.staticFiles.location("/public");


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

        //post Squad form results
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
            model.put("squads", squad); //add it to model for template to display
            return new ModelAndView(model, "squaddetails.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //route to fill Squad form
        get("/Heroform",((request, response) -> {
            Map<String, Object> model = new HashMap();
            return new ModelAndView(model,"Heroform.hbs");
        }),new HandlebarsTemplateEngine());



        //shows all heroes in template Heroes.hbs
        get("/Heroes",((request, response) -> {
            Map <String, Object> model = new HashMap<>();
            ArrayList<Hero> heroes = Hero.getHeroes();
            model.put("heroes", heroes);
            return new ModelAndView(model, "Heroes.hbs");
        }),new HandlebarsTemplateEngine());


        //post ressults from heroes form to heroes page
        post("/Heroes",((request, response) -> {
            Map<String, Object> model = new HashMap();
            String heroName = request.queryParams("heroName");
            request.session().attribute("heroName", heroName);
            int age =Integer.parseInt(request.queryParams("age"));
            request.session().attribute("age", age);
            String superPower = request.queryParams("superPower");
            request.session().attribute("superPower", superPower);
            String weakness = request.queryParams("weakness");
            request.session().attribute("weakness", weakness);
            Hero hero = new Hero(heroName,age,superPower,weakness);
            return new ModelAndView(model,"success.hbs");
        }),new HandlebarsTemplateEngine());

        //new Hero forms
        get("Squad/:id/Heroes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "Heroform.hbs");
        }, new HandlebarsTemplateEngine());


        // route to handle a form for adding new heroes to squads specific squad using the squad id
      /*  get("/Squad/:id/Heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad squad = Squad.findById(Integer.parseInt(request.queryParams("id")));
            model.put("squads", squad);
            return new ModelAndView(model,"Heroform.hbs");
        }, new HandlebarsTemplateEngine());
*/
        //post new Hero form to a squad
        post("/Squad/:id/Heroes/new",((request, response) -> {
            Map<String, Object> model = new HashMap();
            Squad squad = Squad.findById(Integer.parseInt(request.queryParams(":id")));
            String heroName = request.queryParams("heroName");
            request.session().attribute("heroName", heroName);
            int age =Integer.parseInt(request.queryParams("age"));
            request.session().attribute("age", age);
            String superPower = request.queryParams("superPower");
            request.session().attribute("superPower", superPower);
            String weakness = request.queryParams("weakness");
            request.session().attribute("weakness", weakness);
            Hero hero = new Hero(heroName,age,superPower,weakness);

            if (squad.getHeroes().size() >= squad.getMaxSize()) {
                String fullSquad = "Squad is full";
                model.put("fullSquad", fullSquad);
            }
            // add the hero
            else{
                squad.addHero(hero);
                
            }

            model.put("squads",squad);
           // model.put("hero",squad);
            response.redirect("/Squad");
            return new ModelAndView(model,"success.hbs");
        }),new HandlebarsTemplateEngine());

        get("/Heroes/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Hero deleteHero = Hero.findById(idOfHeroToDelete); //use it to find post
            deleteHero.deleteHero();
            return new ModelAndView(model, "delete.hbs");
        }, new HandlebarsTemplateEngine());

    }


    }

