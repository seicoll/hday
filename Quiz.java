import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class Prova {
  int id;
  String pregunta;
  String resposta;

  Prova(int pregunta_id, String pregunta, String resposta) {
    this.id = pregunta_id;
    this.pregunta = pregunta;
    this.resposta = resposta;
  }
}

public class Quiz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String username = System.getProperty("user.name");
        Boolean jugat =  true;

        System.out.print(
                        "  _                _    _                   _             \n"+
                        " | |              | |  (_)                 | |            \n"+
                        " | |__   __ _  ___| | ___ _ __   __ _    __| | __ _ _   _ \n"+
                        " | '_ \\ / _` |/ __| |/ / | '_ \\ / _` |  / _` |/ _` | | | |\n"+
                        " | | | | (_| | (__|   <| | | | | (_| | | (_| | (_| | |_| |\n"+
                        " |_| |_|\\__,_|\\___|_|\\_\\_|_| |_|\\__, |  \\__,_|\\__,_|\\__, |\n"+
                        "                                 __/ |               __/ |\n"+
                        "                                |___/               |___/\n");
        

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "sergi", "1234");
             Statement stmt = con.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT `option`, value FROM info");
            Map<String, String> opcions = new HashMap<String, String>();

            while (rs.next()) {
                opcions.put(rs.getString("option"), rs.getString("value"));
            }

            System.out.println("");
            System.out.println("Hola " + username + "! Estàs jugant a l'esdeveniment de " + opcions.get("ciutat") + ".");
            System.out.println("");
            System.out.println(opcions.get("introduccio"));
            System.out.println("");



            rs = stmt.executeQuery("SELECT MAX(solved) as last FROM results WHERE username='" + username + "'");
            int start = 0;

            if (rs.next()) {
                start = rs.getInt("last");
            }

            List<Prova> proves = new ArrayList<Prova>();

            rs = stmt.executeQuery("SELECT id, question, answer FROM questions WHERE id > " + start + " ORDER BY id");
            while (rs.next()) {

                int question_id = rs.getInt("id");
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                proves.add(new Prova(question_id, question, answer));
            }
            
            for (int i = 0; i < proves.size(); i++) {
                jugat = true;
                Prova prova = proves.get(i);

                System.out.println("Prova " + prova.id + ": " + prova.pregunta);
                String givenanswer = sc.nextLine();
                
                if (givenanswer.equals(prova.resposta)) {
                    System.out.println("Resposta correcta");
                    stmt.executeUpdate("INSERT INTO results (username, solved) VALUES ('" + username + "', " + prova.id + ")");
                } else {
                    System.out.println("Resposta incorrecta");
                    i--;
                }
            }

            if(jugat) {
                System.out.println("Enhorabona, ja has acabat totes les proves!");
                System.out.println("");
                System.out.println(opcions.get("despedida"));
            }
            else {
                System.out.println("No hi ha cap prova disponible");
            }

        } catch (SQLException e) {
            System.out.println("Error: " +  e.getMessage());
        }
    }
}

