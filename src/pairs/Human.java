package pairs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Human implements Comparable<Human> {

    String file;
    private String firstName;
    private String secondName;
    private int ratingRus;
    private int ratingWorld;
    private String sex;
    private List<Game> listGamePlayed = new ArrayList<>();
    private String title;
    private String titleRus;
    private String region;
    private int idFIDE;
    private int numberStart;
    private int numberCurrent;
    private int id;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getIdFIDE() {
        return idFIDE;
    }

    public void setIdFIDE(int idFIDE) {
        this.idFIDE = idFIDE;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    private int birthDate;

    public int getNumberStart() {
        return numberStart;
    }

    public void setNumberStart(int numberStart) {
        this.numberStart = numberStart;
    }

    public int getNumberCurrent() {
        return numberCurrent;
    }

    public void setNumberCurrent(int numberCurrent) {
        this.numberCurrent = numberCurrent;
    }

    public int getId() {
        return idFIDE;
    }

    public void setId(int idFIDE) {
        this.idFIDE = idFIDE;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleRus() {
        return titleRus;
    }

    public void setTitleRus(String titleRus) {
        this.titleRus = titleRus;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getRatingRus() {
        return ratingRus;
    }

    public void setRatingRus(int rating_rus) {
        this.ratingRus = rating_rus;
    }

    public void setRatingWorld(int rating_world) {
        this.ratingWorld = rating_world;
    }

    public int getRatingWorld() {
        return ratingWorld;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public List<Game> getListGamePlayed() {
        return listGamePlayed;
    }
    public void addGame(Game game) {
            listGamePlayed.add(game);
    }

    @Override
    public String toString() {
        return getSecondName() + " " + getFirstName() + " " + getRatingWorld();
    }

    public Human(String firstName, String secondName, int rating) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.ratingWorld = rating;
    }
    public Human(String firstName, String secondName, int rating, int numberStart, int id) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.ratingWorld = rating;
        this.numberStart = numberStart;
        this.id = id;
    }

    @Override
    public int compareTo(Human human) {
        try {
            if (human.getNumberPoint() > this.getNumberPoint()) {
                return 1;
            }
            if (human.getNumberPoint() < this.getNumberPoint()) {
                return -1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (this.getNumberPointBuchholz() < human.getNumberPointBuchholz()) {
                return 1;
            }
            if (this.getNumberPointBuchholz() > human.getNumberPointBuchholz()) {
                return -1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (human.getRatingWorld() > this.getRatingWorld()) {
            return 1;
        }
        if (human.getRatingWorld() < this.getRatingWorld()) {
            return -1;
        }
        return human.getSecondName().compareTo(this.getSecondName());
    }

    public double getNumberPoint() throws IOException {
        return numberPoint(this.numberStart);
    }

    public double getNumberPoint(int number) throws IOException {
        return numberPoint(number);
    }

    private double numberPoint(int number) throws IOException {
        double sum = 0;
        FileInputStream fstream = new FileInputStream(file + ".trf");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String str;
        while ((str = br.readLine()) != null){
            String[] lex = str.split("\\s+");
            if (lex[0].equals("001") && lex[1].equals(Integer.toString(number))) {
                for (int i = 0; i < lex.length; i++) {
                    if (lex[i].equals("0.0")) {
                        i += 4;
                        for (; i < lex.length; i += 3) {
                            if (lex[i].equals("1") || lex[i].equals("+") || lex[i].equals("U")) {
                                sum += 1;
                            }
                            else if (lex[i].equals("=")) {
                                sum += 0.5;
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

    public double getNumberPointBuchholz() throws IOException {
        double sum = 0;
        FileInputStream fstream = new FileInputStream(file + ".trf");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String str;
        while ((str = br.readLine()) != null){
            String[] lex = str.split("\\s+");
            if (lex[0].equals("001") && lex[1].equals(Integer.toString(this.numberStart))) {
                for (int i = 0; i < lex.length; i++) {
                    if (lex[i].equals("0.0")) {
                        i += 2;
                        for (; i < lex.length; i += 3) {
                            sum += getNumberPoint(Integer.parseInt(lex[i]));// lex[i].equals(Integer.toString(this.numberStart);
                        }
                    }
                }
            }
        }
        return sum;
    }

    public String convetToFile() {
        String humanInfo = "001  ";
        if (numberStart / 10 == 0) {
            humanInfo += "  " + numberStart;
        }
        else if (numberStart / 100 == 0) {
            humanInfo += " " + numberStart;
        }
        else {
            humanInfo +=  numberStart;
        }
        if (sex == null) {
            humanInfo += "    ";
        }
        else if (sex.equals("m") ){
            humanInfo += " m  ";
        }
        else {
            humanInfo += " f  ";
        }
        if (title != null) {
            humanInfo += title.charAt(0) + " ";
        }
        else {
            humanInfo += "  ";
        }
        String finalName = firstName + ' ' +secondName;
        if (finalName.length() > 33) {
            finalName = finalName.substring(    0,33);
        }
        humanInfo += finalName;
        for (int i = 0; i < 34 - finalName.length(); i++) {
            humanInfo += " ";
        }
        if (ratingWorld != 0) {
            humanInfo += ratingWorld + " ";
        }
        else {
            humanInfo += "0000 ";
        }
        if (region == null) {
            humanInfo += "   " + "    ";
        }
        else if (region.length() > 3) {
            humanInfo += region.substring(0,3) + "    ";
        }
        else {
            humanInfo += region + "    ";
        }
        int localId = idFIDE;
        String strId = "";
        for (int i = localId; i > 0; i /= 10) {
            strId += i % 10;
        }
        for (int i = 0, len = strId.length(); i < 8 - len; i++) {
            strId += " ";
        }
        if (strId.length() > 0) {
            humanInfo += new StringBuilder(strId).reverse().toString() + " ";
        }
        else {
            humanInfo += strId + " ";
        }
        if (birthDate != 0) {
            humanInfo += birthDate + "        ";
        }
        else {
            humanInfo += "            ";
        }

        humanInfo += "0.0";

        if (numberStart / 10 == 0) {
            humanInfo += "    " + numberStart;
        }
        else if (numberStart / 100 == 0) {
            humanInfo += "   " + numberStart;
        }
        else {
            humanInfo += "  " + numberStart;
        }
        humanInfo += "  \n";
        return humanInfo;
    }
}