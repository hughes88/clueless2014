/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Clue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

/**
 *
 * @author Mario
 */
public class JerseyClient {

    //public static void main(String[] args) {
    public static int game_slot; //player slot for game (slot 1= player 1)
    public static JSONObject currentgame_state;

    public JerseyClient() {

        String string = "";

        try {
            //System.out.println("jerseyclient started");
            currentgame_state = new JSONObject("{\"players\":[{\"position\":\"0,3\",\"name\":null,\"active\":\"true\",\"cards\":\"MR GREEN,CANDLESTICK,BALLROOM\",\"character\":\"scarlet\"},{\"position\":\"1,4\",\"name\":null,\"active\":\"true\",\"cards\":\"PROFESSOR PLUM,BILLARD ROOM,ROPE\",\"character\":\"mustard\"},{\"position\":\"4,3\",\"name\":null,\"active\":\"true\",\"cards\":\"DINING ROOM,REVOLVER,WRENCH\",\"character\":\"white\"},{\"position\":\"4,1\",\"name\":null,\"active\":\"true\",\"cards\":\"LIBRARY,COLONEL MUSTARD,STUDY\",\"character\":\"green\"},{\"position\":\"3,0\",\"name\":null,\"active\":\"true\",\"cards\":\"MISS SCARLET,MRS PEACOCK,KNIFE\",\"character\":\"peacock\"},{\"position\":\"1,0\",\"name\":null,\"active\":\"true\",\"cards\":\"HALL,CONSERVATORY,LOUNGE\",\"character\":\"plum\"}],\"move_state\":{\"player\":\"scarlet\",\"moves\":[[\"[0,4], [0,2]\",\"accusation\"]]},\"winner\":null}\"))");

            String username = "Mario";
            // Step1: Let's 1st read file from fileSystem
            InputStream crunchifyInputStream = new FileInputStream(
                    "/Users/" + username + "/Documents/JsonTest/JSONFile.txt");
            InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
            BufferedReader br = new BufferedReader(crunchifyReader);
            String line;
            while ((line = br.readLine()) != null) {
                string += line + "\n";
            }

            JSONObject jsonObject = new JSONObject(string);
            //System.out.println(jsonObject);

            // Step2: Now get JSON File Data from REST Service
            try {

                JsonParser Parser;
                //URL url = new URL("http://192.168.1.7:8080/CluelessServer/webresources/service/game");
                URL url = new URL("http://ec2-54-165-198-60.compute-1.amazonaws.com:8080/CluelessServer/webresources/service/game");
                URLConnection connection = url.openConnection();
                connection.setDoInput(true);
                //setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));

                //  while (in.readLine() != null) {
                //}
                System.out.print(in.readLine());
                System.out.println("\nREST Service Invoked Successfully..GET Request Sent");
                //getGameState();

               // sendPUT(ClueGameUI.jTextField2.getText());
                //send JSON to Parser
                //Parser=new JsonParser(in.readLine());
                //System.out.println("Parser called");
                // sendPUT();
                //close connection
                in.close();

            } catch (Exception e) {
                System.out.println("\nError while calling REST Service");
                System.out.println(e);
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestLogintoServer(String name) {

        try {
            for (int i = 0; i < 6; i++) {
                JSONObject jsonObject = new JSONObject(name);

                URL url = new URL("http://ec2-54-165-198-60.compute-1.amazonaws.com:8080/CluelessServer/webresources/service/player" + i);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                //setDoOutput(true);
                connection.setRequestProperty("PUT", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(jsonObject.toString());

                System.out.println("Sent PUT message for logging into server");
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    // Gets current state of game variable (player location, players cards, etc)from server
    public JSONObject getGameState() {

        JSONObject Object = null;
        try {
            URL url = new URL("http://ec2-54-165-198-60.compute-1.amazonaws.com:8080/CluelessServer/webresources/service/player1");
            URLConnection connection = url.openConnection();
            connection.setDoInput(true);
            //setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            Object = new JSONObject(in.readLine());
            //;
            // JsonParser parser=new JsonParser(in.readLine());

            //  while (in.readLine() != null) {
            //}
            // System.out.print(in.readLine());
            System.out.println(Object.getJSONArray("players").getJSONObject(0).getString("character"));
            System.out.println("\nREST Service Invoked Successfully..GET Request Sent");

            //sendPUT();
            //send JSON to Parser
            //Parser=new JsonParser(in.readLine());
            //System.out.println("Parser called");
            // sendPUT();
            //close connection
            // in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }

        return Object;

    }

    public void sendPUT(String name) {
        System.out.println("SendPUT method called");
        try {
            JSONObject jsonObject = new JSONObject("{name:" + name + "}");

            URL url = new URL("http://ec2-54-165-198-60.compute-1.amazonaws.com:8080/CluelessServer/webresources/service/game/player1");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            //setDoOutput(true);
            connection.setRequestProperty("PUT", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonObject.toString());

            System.out.println("Sent PUT message to server");
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getAvailableSlot() {

        try {
            for (int i = 1; i < 7; i++) {
                // URL url = new URL("http://192.168.1.7:8080/CluelessServer/webresources/service/game/Status");
                URL url = new URL("http://ec2-54-165-198-60.compute-1.amazonaws.com:8080/CluelessServer/webresources/service/player" + i + "/");
                URLConnection connection = url.openConnection();
                connection.setDoInput(true);
                //setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));

            //  while (in.readLine() != null) {
                //}
                System.out.println("\nGET Request for :" + "Get Available slot " + "Sent");

                if ((in.readLine()).contains("no name")) {
                    game_slot = i;
                    i = 7;
                } else {

                }
                System.out.print("You have slot # " + game_slot);
                //close connection
                in.close();
            }

        } catch (Exception e) {
            System.out.println("\nError slot taken..Checking for another open slot...");
            System.out.println(e);

        }

    }

    public void isgameReady() {
        //Check to see if all players have joined game

        try {
            // URL url = new URL("http://192.168.1.7:8080/CluelessServer/webresources/service/game/Status");
            URL url = new URL("http://ec2-54-165-198-60.compute-1.amazonaws.com:8080/CluelessServer/webresources/service/game/");
            URLConnection connection = url.openConnection();
            connection.setDoInput(true);
            //setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            //  while (in.readLine() != null) {
            //}
            System.out.println("\nGET Request for :" + "Game Ready Status?  " + "Sent");
            System.out.print(in.readLine());
            //close connection
            in.close();

        } catch (Exception e) {
            System.out.println("\nError while calling REST Get Service");
            System.out.println(e);

        }

    }

    public void sendMove() {

    }

    public void sendPOST() {
        System.out.println("POST method called");
        try {

            JSONObject jsonObject = new JSONObject("{player:Brian}");
            URL url = new URL("http://ec2-54-165-198-60.compute-1.amazonaws.com:8080/CluelessServer/webresources/service/game/");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            //setDoOutput(true);
            connection.setRequestProperty("POST", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            System.out.println(jsonObject.toString());
            out.write("{" + jsonObject.toString());

            System.out.println("Sent PUT message to server");
            out.close();

        } catch (Exception e) {
            System.out.println("\nError while calling REST POST Service");
            System.out.println(e);
        }

    }

}
