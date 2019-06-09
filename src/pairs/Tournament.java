package pairs;

import javafo.api.JaVaFoApi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Tournament {
    private String tournamentFile;
    private String nameOfTurnament;
    private String location;
    private String region;
    private String startDate;
    private String endDate;
    private int countOfPlayers;
    private int countOfPlayersWithRating;
    private String conductionSystem;
    private String mainArbiter;
    private String timeSystem;
    private int countOfTour;
    //private String fileName;
    List<Tour> listTour = new ArrayList<>();
    ListPlayers listPlayers;
    private int id;

    public List<Tour> getListTour() {
        return listTour;
    }

    public void setListTour(List<Tour> listTour) {
        this.listTour = listTour;
    }

    public Tournament(String tournamentFile) {
        this.tournamentFile = tournamentFile;
    }

    public String getTournamentFile() {
        return tournamentFile;
    }

    public void setTournamentFile(String tournamentFile) {
        this.tournamentFile = tournamentFile;
    }

    public Tournament(String tournamentFile, int countOfTour) {
        this.tournamentFile = tournamentFile;
        this.countOfTour = countOfTour;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfTurnament() {
        return nameOfTurnament;
    }

    public void setNameOfTurnament(String nameOfTurnament) {
        this.nameOfTurnament = nameOfTurnament;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getCountOfPlayers() {
        return countOfPlayers;
    }

    public void setCountOfPlayers(int countOfPlayers) {
        this.countOfPlayers = countOfPlayers;
    }

    public int getCountOfPlayersWithRating() {
        return countOfPlayersWithRating;
    }

    public void setCountOfPlayersWithRating(int countOfPlayersWithRating) {
        this.countOfPlayersWithRating = countOfPlayersWithRating;
    }

    public String getConductionSystem() {
        return conductionSystem;
    }

    public void setConductionSystem(String conductionSystem) {
        this.conductionSystem = conductionSystem;
    }

    public String getMainArbiter() {
        return mainArbiter;
    }

    public void setMainArbiter(String mainArbiter) {
        this.mainArbiter = mainArbiter;
    }

    public String getTimeSystem() {
        return timeSystem;
    }

    public void setTimeSystem(String timeSystem) {
        this.timeSystem = timeSystem;
    }

    public int getCountOfTour() {
        return countOfTour;
    }

    public void setCountOfTour(int countOfTour) {
        this.countOfTour = countOfTour;
    }

    public void addPlayer(Human human) {
        human.setFile(this.tournamentFile);
        listPlayers.addPlayer(human);
    }

    public ListPlayers getListPlayers() {
        return listPlayers;
    }

    public void setListPlayers(ListPlayers listPlayers) {
        this.listPlayers = listPlayers;
        listPlayers.setTournament(this);
        for (int i = 1; i < listPlayers.size() + 1; i++) {
            listPlayers.getPlayer(i).setFile(this.tournamentFile);
        }
    }

    public void createFileTournament() {
        String text = "";
        text += "012 " + nameOfTurnament + '\n';
        text += "022 " + location + '\n';
        text += "032 " + region + '\n';
        text += "042 " + startDate + '\n';
        text += "052 " + endDate + '\n';
        text += "062 " + countOfPlayers + '\n';
        text += "072 " + countOfPlayersWithRating + '\n';
        text += "092 " + conductionSystem + '\n';
        text += "102 " + mainArbiter + '\n';
        text += "122 " + timeSystem + '\n';
        text += "132                                                                                      " + '\n';
        text += "XXR " + countOfTour + '\n';
        text += listPlayers.convertToFile();
        tournamentFile = text;
    }

    public Tour getTour(int i) {
        return listTour.get(i - 1);
    }

    public Tour getTourLast() {
        return listTour.get(listTour.size() - 1);
    }

    public void createTourFromBD(List<Map<String, String>> list) {
        int max = 0;
        for (Map<String, String> m : list) {
            System.out.println(m);
            Integer in = Integer.valueOf( String.valueOf(m.get("number")));
            if (max < in) {
                max = in;
            }
        }
        for (int i = 1; i < max + 1; i++) {
            Tour new_t = new Tour();
            new_t.setTournament(this);
            new_t.loadFromList(list, i);
            tournamentFile = new_t.writeResult(tournamentFile);
            listTour.add(new_t);
        }
    }

    public void createTour()  {
        Tour new_t = new Tour();
        new_t.setTournament(this);
        String pairs;
        System.out.println(tournamentFile);
        pairs = JaVaFoApi.exec(1000, tournamentFile);
        String[] pair = pairs.split("\\n");
        System.out.println(pairs);

        int countPairs = pair.length;
        if (listPlayers.size() % 2 == 1) {
            countPairs -= 1;
        }
        for (int i = 1; i < countPairs; i++) {
            String[] s = pair[i].split( " |\\r");
            new_t.addGame(new Game(
                    listPlayers.getPlayer(Integer.valueOf(s[0])),
                    listPlayers.getPlayer(Integer.valueOf(s[1])),
                    i, listTour.size() + 1));
        }
        listTour.add(new_t);
    }

    public List <Map <String,Integer> > showTour(int tour) {
        return listPlayers.showTour(tour);
    }
}
