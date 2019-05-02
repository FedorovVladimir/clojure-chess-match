package pairs;

import convert.Convert;

import java.io.IOException;
import java.util.*;

public class AdapterPairs {

    public static void main(String[] args) {

    }

    public List<Integer> run(List<Map<String, String>> base, int countOfTour) throws IOException {
        List<Map<String, String>> playersList = Convert.getListFromClojure(base);

        ListPlayers listPlayers = new ListPlayers();
        for (int i = 0; i < playersList.size(); i++) {
            Map<String, String> players = playersList.get(i);

            Human h = new Human(players.get("first"),
                players.get("last"),
                Integer.parseInt(String.valueOf(players.get("rating_fide"))),
                i + 1,
                Integer.parseInt(String.valueOf(players.get("id_player_list"))));

            listPlayers.addPlayer(h);
        }

        Tournament tournament = new Tournament();
        tournament.setListPlayers(listPlayers);
        tournament.setFileName("test");
        tournament.setCountOfTour(countOfTour);
        tournament.createFileTournament();
        tournament.createTour();

        List<Integer> listList = new ArrayList<>();
        for (int i = 1; i < tournament.getTour(1).getSize() + 1; i++ ){
            listList.add(tournament.getTour(1).getGame(i).getWhite().getId());
            listList.add(tournament.getTour(1).getGame(i).getBlack().getId());
        }
        return listList;
    }
}
