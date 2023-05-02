package GUI.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class txtFileHandler {
    private final String fileName;

    public txtFileHandler(String fileName) {
        this.fileName = fileName;
    }

    public void writeHighScore(String name, int score)
            throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write("\n" + name + "," + score);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Object[]> readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        ArrayList<Object[]> leaderboardList = new ArrayList<>();
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            Object[] playerScore;
            while (line != null) {
                if(line.equals("")){
                    System.out.println("empty");
                    line = reader.readLine();
                }else {
                    // file is assumed to contain one name, score pair per line
                    String[] tokens = line.split(",");
                    String name = tokens[0];
                    int score = Integer.parseInt(tokens[1]);
                    playerScore = new Object[]{name, score};
                    line = reader.readLine();
                    //adding data to the array list
                    leaderboardList.add(playerScore);
                }
            }
        }  finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
        return leaderboardList;
    }
}