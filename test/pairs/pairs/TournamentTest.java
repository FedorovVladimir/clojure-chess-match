package pairs.pairs;

import javafo.api.JaVaFoApi;
import org.junit.Test;
import pairs.Human;
import pairs.ListPlayers;
import pairs.ResultGame;
import pairs.Tournament;

import java.io.*;
import java.util.Properties;

import static org.junit.Assert.*;

public class TournamentTest {

    @Test
    public void createTour() throws IOException {
        ListPlayers ls = new ListPlayers();
        ls.addPlayer(new Human("Vitaly", "Boiarintsev", 2427));
        ls.getPlayer(1).setRegion("RUS");
        ls.getPlayer(1).setId(34128384);
        ls.getPlayer(1).setBirthDate(1982);
        ls.getPlayer(1).setTitle("FM");
        ls.addPlayer(new Human("Alexandr", "Piskunov", 2409));
        ls.getPlayer(2).setRegion("RUS");
        ls.getPlayer(2).setId(4115490);
        ls.getPlayer(2).setBirthDate(1994);
        ls.addPlayer(new Human("Kardashevskiy","Evgeny" , 2310));
        ls.getPlayer(3).setRegion("RUS");
        ls.getPlayer(3).setId(24112593);
        ls.getPlayer(3).setBirthDate(1994);
        ls.addPlayer(new Human("Gaydym","Michail", 2299));
        ls.getPlayer(4).setRegion("RUS");
        ls.getPlayer(4).setId(24108138);
        ls.getPlayer(4).setBirthDate(1994);
        ls.addPlayer(new Human("Efanov","Mikhail", 2222));
        ls.getPlayer(5).setRegion("RUS");
        ls.getPlayer(5).setId(24180211);
        ls.getPlayer(5).setBirthDate(1994);
        ls.addPlayer(new Human("Kuprin","Vladimir", 2157));
        ls.getPlayer(6).setRegion("RUS");
        ls.getPlayer(6).setId(24159620);
        ls.getPlayer(6).setBirthDate(1994);
        ls.addPlayer(new Human("Razgovorov","Yurii", 2107));
        ls.getPlayer(7).setRegion("RUS");
        ls.getPlayer(7).setId(4155440);
        ls.getPlayer(7).setBirthDate(1994);
        ls.addPlayer(new Human("Reutov","Anton", 2127));
        ls.getPlayer(8).setRegion("RUS");
        ls.getPlayer(8).setId(34101583);
        ls.getPlayer(8).setBirthDate(1994);
        ls.addPlayer(new Human("Kazantsev","Alexander", 2079));
        ls.addPlayer(new Human("Radionov","Timur", 2040));
        ls.getPlayer(10).setRegion("RUS");
        ls.getPlayer(10).setId(34135755);
        ls.addPlayer(new Human("Zakharova","Viktoriya", 0));
        ls.getPlayer(11).setRegion("RUS");
        ls.getPlayer(11).setId(34178772);
        ls.getPlayer(11).setBirthDate(1994);
        ls.addPlayer(new Human("Vasiliev","Maxim", 2016));
        ls.getPlayer(12).setRegion("RUS");
        ls.getPlayer(12).setId(0);
        ls.getPlayer(12).setBirthDate(1994);
        ls.addPlayer(new Human("Skorikov","Dmitry", 2017));
        ls.getPlayer(13).setId(24183210);
        ls.getPlayer(13).setBirthDate(1994);
        //ls.addPlayer(new Human("Kataev","Vladimir", 1900));

        ls.sort();
        ls.toNumber();
        Tournament tournament = new Tournament();
        tournament.setNameOfTurnament("XII SDS - Classic ");
        tournament.setLocation("Gelendzhik");
        tournament.setRegion("RUS");
        tournament.setStartDate("2016/06/28");
        tournament.setEndDate("2016/06/28");
        tournament.setCountOfPlayers(14);
        tournament.setCountOfPlayersWithRating(14);
        tournament.setConductionSystem("Swiss System");
        tournament.setMainArbiter("24148296 Sakhvadze Georgy");
        tournament.setTimeSystem("Standard: 90 minutes with 30 second increment from move 1");
        tournament.setCountOfTour(7);
        tournament.setListPlayers(ls);
//        tournament.createFileTournament();
        String out;
//        tournament.createTour();
//        tournament.getTourLast().random();
//        tournament.getTourLast().getGame(1).setResult(ResultGame.WHITE_WINS);
//        tournament.getTourLast().writeResult();
//
//        tournament.createTour();
//        tournament.getTourLast().random();
//        tournament.getTourLast().writeResult();
//        tournament.createTour();
//        tournament.getTourLast().random();
//        tournament.getTourLast().writeResult();
//        tournament.createTour();
//        tournament.getTourLast().random();
//        tournament.getTourLast().writeResult();
//        tournament.createTour();
//        tournament.getTourLast().random();
//        tournament.getTourLast().writeResult();
//        tournament.createTour();
//        tournament.getTourLast().random();
//        tournament.getTourLast().writeResult();
//        tournament.createTour();
//        tournament.getTourLast().random();
//        tournament.getTourLast().writeResult();

//        for (int i = 1; i < tournament.getListPlayers().size() + 1; i++) {
//            System.out.println(tournament.getListPlayers().getPlayer(i).getNumberPoint());
//        }
        tournament.getListPlayers().toFinally();
        tournament.getListPlayers().print();
        //ls.sort();
        //ls.print();

        out = JaVaFoApi.exec(1200,   new FileInputStream("test.trf"));
        System.out.println(out);

    }
}