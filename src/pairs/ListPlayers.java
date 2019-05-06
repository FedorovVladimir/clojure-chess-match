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

    public List <Map <String,Integer> > showTour(int tour) {

        List <Map <String,Integer> > list = new ArrayList<>();
        for (Human h: listHuman) {
            Map <String, Integer> map = new HashMap<>();
            map.put("point", (int) h.getNumberPoint(tour));
            map.put("point_buh", (int) h.getNumberPointBuchholz(tour));
            map.put("id_human", h.getId());
            list.add(map);
        }
        Collections.sort(list, mapComparator);
        int a;
        return list;
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


    public Comparator<Map<String, Integer>> mapComparator = new Comparator<Map<String, Integer>>() {
        public int compare(Map<String, Integer> m1, Map<String, Integer> m2) {
            if (m1.get("point") > m2.get("point"))
            {
                return -1;
            }
            if (m1.get("point") < m2.get("point"))
            {
                return 1;
            }
            if (m1.get("point_buh") > m2.get("point_buh"))
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    };

}
