package pairs;

import convert.Convert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdapterPairs {

    public static void main(String[] args) {

    }

    public static List<Object> createTournament(List<Map<String, String>> base, int countOfTour, String file) {
        ListPlayers listPlayers = createListPlayer(base);
        Tournament tournament = new Tournament(file, countOfTour);
        tournament.setListPlayers(listPlayers);
        if (tournament.getTournamentFile() == null)
            tournament.createFileTournament();
        tournament.createTour();
        List<Object> list = new ArrayList<>();
        list.add(tournament.getTournamentFile());
        for (int i = 1; i < tournament.getTour(1).getSize() + 1; i++ ){
            list.add(tournament.getTour(1).getGame(i).getWhite().getId());
            list.add(tournament.getTour(1).getGame(i).getBlack().getId());
        }
        return list;
    }

    public static String setResultTour(List<Map<String, String>> base, List<Map<String, String>> games,  String file) {
        ListPlayers listPlayers = createListPlayer(base);
        Tournament tournament = new Tournament(file);
        tournament.setListPlayers(listPlayers);
        tournament.createTourFromBD(Convert.getListFromClojure(games));

        return tournament.getTournamentFile();
    }

    private static ListPlayers createListPlayer(List<Map<String, String>> base) {
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
        return listPlayers;
    }
}
