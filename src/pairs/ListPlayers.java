package pairs;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ListPlayers {
    private int id;
    private Tournament tournament;
    private  List <Human> listHuman = new ArrayList<Human>();

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    public void addPlayer(Human human) {
        listHuman.add(human);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Human getPlayer(int i) {
        return listHuman.get(i - 1);
    }

    public int size() {
        return listHuman.size();
    }


    public void sort() {
        Collections.sort(listHuman);
    }
    public void toNumber() {
        for (int i = 0; i < listHuman.size(); i++) {
            listHuman.get(i).setNumberStart(i + 1);
        }
    }

    public void toFinally() {
        Collections.sort(listHuman);
    }



    public String convertToFile () {
        String finalString = "";
        for (Human h : listHuman) {
            finalString += h.convetToFile();
        }
        return finalString;
    }

    public Human findPlayerById(int id) {
        for (Human h : listHuman) {
            if (id == h.getId()) {
                return h;
            }
        }
        return null;
    }

    public void print() throws IOException {
        System.out.println();
        for (int i = 0; i < listHuman.size(); i++) {
            if (i < 9) {
                System.out.print("  ");
            }
            else if (i < 99) {
                System.out.print(" ");
            }
            System.out.println(i + 1 + ": " + listHuman.get(i).getNumberPoint() + " " + listHuman.get(i).getNumberPointBuchholz() + " " + listHuman.get(i));
        }
    }
}
