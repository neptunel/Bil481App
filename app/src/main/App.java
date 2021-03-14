import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.port;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import spark.ModelAndView;
  import spark.template.mustache.MustacheTemplateEngine;

  public class App
  {
    public String info() {
        return "Hello world. This is my first project!";
    }

    public static int findMin(ArrayList<Integer> array, int a, int b,String message) {
      System.out.println(message);
      if (array == null) return -1;
        int first=Integer.MAX_VALUE;
        int second=Integer.MAX_VALUE;

      for (int i=0; i<array.size() ; i++) {
        if (array.get(i) == a)
            first=i;
        if (array.get(i) == b)
            second=i;
      }
      
      if(first<second)
        return 1;
      else if(first>second)
        return 2;
      else 
        return -1;
    }

    public static void main(String[] args) { 
      Logger logger = LogManager.getLogger(App.class);

      int port = Integer.parseInt(System.getenv("PORT"));
      port(port);
      logger.error("Current port number:" + port);

        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));

          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
          while (sc1.hasNext())
          {
            int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            inputList.add(value);
          }
          sc1.close();
          System.out.println(inputList);


          String input2 = req.queryParams("input2").replaceAll("\\s","");
          int input2AsInt = Integer.parseInt(input2);

          String input3 = req.queryParams("input3").replaceAll("\\s","");
          int input3AsInt = Integer.parseInt(input3);

          Integer result = App.findMin(inputList, input2AsInt,input3AsInt,"Searching for min");
          Map<String, String> map = new HashMap<String, String>();

          if(result==1 || result==2)
          map.put("result", "Result "+result);

          else
          map.put("result", "DRAW");

          return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
            (rq, rs) -> {
              Map<String, String> map = new HashMap<String, String>();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}