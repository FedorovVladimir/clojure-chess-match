package pairs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tour {
    private Tournament tournament;
    private int numberOfTour;
    private int id;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    List <Game> listGame = new ArrayList<Game>();

    public void setNumberOfTour(int numberOfTour) {
        this.numberOfTour = numberOfTour;
    }

    public int getNumberOfTour() {
        return numberOfTour;
    }

    public void setLisrGame(List<Game> listGame) {
        this.listGame = listGame;
    }

    public void addGame(Game game) {
        this.listGame.add(game);
    }

    public Game getGame(int i) {
        return listGame.get(i - 1);
    }

    public int getSize() {
        return listGame.size();
    }


    public void random() {
        for (Game g: listGame) {
            g.setResult(ResultGame.randomResult());
        }
    }

    public Game findGame(int start) {
        for (Game g : listGame) {
            if (g.indexOf(start) != null) {
                return g.indexOf(start);
            }
        }
        return null;
    }

    public void writeResult() throws IOException {
        FileInputStream fstream = new FileInputStream(tournament.getFileName() + ".trf");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String str;
        List <String> strLine = new ArrayList<>();
        String finalText = "";
        String drop = "";
        while ((str = br.readLine()) != null){
            String[] lex = str.split("\\s+");
            if (lex[0].equals("001")) {
                Game g = findGame(Integer.parseInt(lex[1]));
                if (g != null) {
                    if (g.getWhite().getNumberStart() == Integer.parseInt(lex[1])) {
                        if (g.getBlack().getNumberStart() / 10 == 0) {
                            str += "   ";
                        } else if (g.getBlack().getNumberStart() / 100 == 0) {
                            str += "  ";
                        } else {
                            str += " ";
                        }
                        str += g.getBlack().getNumberStart() + " w ";
                        if (g.getResult() == ResultGame.DRAW) {
                            str += '=';
                        } else if (g.getResult() == ResultGame.WHITE_WINS) {
                            str += '1';
                        } else {
                            str += '0';
                        }
                    } else {
                        if (g.getWhite().getNumberStart() / 10 == 0) {
                            str += "   ";
                        } else if (g.getWhite().getNumberStart() / 100 == 0) {
                            str += "  ";
                        } else {
                            str += " ";
                        }
                        str += g.getWhite().getNumberStart() + " b ";
                        if (g.getResult() == ResultGame.DRAW) {
                            str += '=';
                        } else if (g.getResult() == ResultGame.WHITE_WINS) {
                            str += '0';
                        } else {
                            str += '1';
                        }
                    }
                }
                else {
                    str += "0000 - U";
                }
            }
            strLine.add(str + "  " + '\n');
        }
        try(FileWriter writer = new FileWriter(tournament.getFileName() + ".trf", false))
        {
            for (String s : strLine) {
                writer.write(s);
            }

        }
        catch(IOException ex) {

        }
    }

    private void newTourFinish() throws FileNotFoundException {
//        String out = JaVaFoApi.exec(1110,   new FileInputStream("test.trf"));
//        System.out.println(out);
    }

    public void print() {
        for (Game g: listGame) {
            System.out.println(g);
        }
    }
}
