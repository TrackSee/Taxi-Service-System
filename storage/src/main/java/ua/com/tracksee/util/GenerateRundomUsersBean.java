package ua.com.tracksee.util;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ua.com.tracksee.entities.UserEntity;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;


/**
 * Created by byte on 4/24/15.
 */
@Singleton(name = "GenerateRundomUsersEJB")
//@Startup
public class GenerateRundomUsersBean {

    @PersistenceContext
    private EntityManager em;


    public GenerateRundomUsersBean() {
    }

//    @PostConstruct
    public void generateUser() {
        try {
            URL url = new URL("http://api.randomuser.me/?results=100");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder builder = new StringBuilder();

            while (reader.ready()) {
                builder.append(reader.readLine());
            }

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(builder.toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            for (Object aJsonArray : jsonArray) {
                JSONObject jsonObject1 = (JSONObject) aJsonArray;
                JSONObject userJsonObject = (JSONObject) jsonObject1.get("user");
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail((String) userJsonObject.get("email"));
                userEntity.setPhone((String) userJsonObject.get("phone"));
                userEntity.setPassword((String) userJsonObject.get("password"));
                userEntity.setActivated(Math.random() < 0.96);
                userEntity.setDriver(Math.random() > 0.95);
                userEntity.setAdmin(Math.random() > 0.98);
                userEntity.setIgnoredTimes((int) ((Math.random() / 2) * 10));
                userEntity.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
                userEntity.setSex((userJsonObject.get("gender")).equals("female") ? "F": "M");
                em.persist(userEntity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}