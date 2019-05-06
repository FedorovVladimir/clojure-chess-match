package pairs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void loadFromList(List<Map<String, String>> list, int tour) {
        for (Map<String, String> m : list) {
            if (tour == Integer.parseInt(String.valueOf(m.get("number")))) {
                Game game = new Game(tournament.getListPlayers().findPlayerById(Integer.parseInt(String.valueOf(m.get("id_player_white")))),
                        tournament.getListPlayers().findPlayerById(Integer.parseInt(String.valueOf(m.get("id_player_black")))));
                game.setResult(ResultGame.loadFromBD(m.get("code")));
                listGame.add(game);
            }
        }
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

    public String writeResult(String file) {
        List <String> strLine = new ArrayList<>();
        String finalText = "";
        String[] fileT = file.split("\n");
        for (String str : fileT) {
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
        for (String s : strLine) {
            finalText += s;
        }
        return finalText;
    }

    public void print() {
        for (Game g: listGame) {
            System.out.println(g);
        }
    }
}
