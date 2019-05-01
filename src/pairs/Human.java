package pairs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Human implements Comparable<Human> {

    private String firstName;
    private String secondName;
    private int ratingRus;
    private int ratingWorld;
    private boolean sex;
    private List<Game> listGamePlayed = new ArrayList<>();
    private String title;
    private String titleRus;
    private String region;
    private int id;
    private int numberStart;
    private int numberCurrent;

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
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
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
    public Human(String firstName, String secondName, int rating, int numberStart) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.ratingWorld = rating;
        this.numberStart = numberStart;
    }

    @Override
    public int compareTo(Human human) {
        if (human.getRatingWorld() > this.getRatingWorld()) {
            return 1;
        }
        if (human.getRatingWorld() < this.getRatingWorld()) {
            return -1;
        }
        return human.getSecondName().compareTo(this.getSecondName());
    }

    public String convetToFile() {
        String humanInfo = "001";
        if (numberStart / 10 == 0) {
            humanInfo += "    " + numberStart;
        }
        else if (numberStart / 100 == 0) {
            humanInfo += "   " + numberStart;
        }
        else {
            humanInfo += "  " + numberStart;
        }
        if (sex == false) {
            humanInfo += " m";
        }
        else {
            humanInfo += " f";
        }
        if (title != null) {
            humanInfo += title;
            for (int i = 0; i < 4 - title.length(); i++) {
                humanInfo += " ";
            }
        }
        else {
            humanInfo += "    ";
        }
        String finalName = firstName + ',' +secondName;
        if (finalName.length() > 33) {
            finalName = finalName.substring(0,33);
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
        int localId = id;
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
            humanInfo += birthDate + "  ";
        }
        else {
            humanInfo += "      ";
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
        humanInfo += '\n';
        return humanInfo;
    }
}