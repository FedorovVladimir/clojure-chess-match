package pairs;

import java.io.IOException;
import java.util.*;

public class AdapterPairs {

    public static void main(String[] args) {

    }

    public void run(List<Map<String, String>> base) {
        List<Map<String, String>> playersList = getListFromClojure(base);

        ListPlayers listPlayers = new ListPlayers();
        for (int i = 0; i < playersList.size(); i++) {
            Map<String, String> players = playersList.get(i);
                Human h = new Human(players.get("first"),
                players.get("last"),
                Integer.parseInt(String.valueOf(players.get("rating_fide"))),
                i,
                Integer.parseInt(String.valueOf(players.get("id_player_list"))));
            System.out.println(h.toString());
            listPlayers.addPlayer(h);
        }
    }

    private List<Map<String, String>> getListFromClojure(List<Map<String, String>> base) {
        List<Map<String, String>> list = new LinkedList<>();
        for (Map<String, String> row : base) {
            Map<String, String> map = new HashMap<>();
            Set<String> keys = row.keySet();
            Collection<String> values = row.values();
            Iterator<String> iteratorK = keys.iterator();
            Iterator<String> iteratorV = values.iterator();
            for (int i = 0; i < keys.size(); i++) {
                String key = String.valueOf(iteratorK.next());
                map.put(key.replace(":", ""), iteratorV.next());
            }
            list.add(map);
        }
        return list;
    }
}
