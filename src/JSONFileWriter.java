import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
    Borowy
    Żochowska
    Jęczmieniowski
 */

public class JSONFileWriter {

    public void appendToJson(User user) {
        JSONObject userData = new JSONObject();
        userData.put("Imię", user.getName());
        userData.put("Nazwisko", user.getSurname());
        userData.put("PESEL", user.getPESEL());
        userData.put("Płeć", user.getGender());
        userData.put("Data urodzenia", user.getBirthDate());
        userData.put("Adres e-mail", user.getEmail());
        userData.put("Numer telefonu", user.getPhoneNumber());

        JSONArray usersList = new JSONArray();

        try {
            FileReader fileReader = new FileReader("UsersData.json");
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(fileReader);
            if (obj != null) {
                usersList = (JSONArray) obj;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Utworzono plik UsersData");
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JSONObject userWrapper = new JSONObject();
        int userNumber = usersList.size() + 1;
        String userKey = "User" + userNumber;
        userWrapper.put(userKey, userData);

        usersList.add(userWrapper);

        try (FileWriter file = new FileWriter("UsersData.json", false)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(usersList);
            file.write(jsonOutput);
            file.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Tworzę plik UsersData...");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
