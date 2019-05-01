package pairs;

public class Game {
    private Human white;
    private Human black;
    private ResultGame resultGame;
    private int numberOfDesk;
    private int numberOfTour;

    public ResultGame getResultGame() {
        return resultGame;
    }

    public void setResultGame(ResultGame resultGame) {
        this.resultGame = resultGame;
    }

    public int getNumberOfTour() {
        return numberOfTour;
    }

    public void setNumberOfTour(int numberOfTour) {
        this.numberOfTour = numberOfTour;
    }

    public void setBlack(Human black) {
        this.black = black;
    }

    public void setWhite(Human white) {
        this.white = white;
    }

    public Human getBlack() {
        return black;
    }

    public Human getWhite() {
        return white;
    }

    public void setResult(ResultGame resultGame) {
        if (this.resultGame == null) {
            white.addGame(this);
            black.addGame(this);
        }
        this.resultGame = resultGame;
    }
    public ResultGame getResult() {
        return resultGame;
    }

    public int getNumberOfDesk() {
        return numberOfDesk;
    }

    public void setNumberOfDesk(int numberOfDesk) {
        this.numberOfDesk = numberOfDesk;
    }

    public Game(Human white, Human black) {
        this.white = white;
        this.black = black;
    }

    public Game(Human white, Human black, int numberOfDesk) {
        this.white = white;
        this.black = black;
        this.numberOfDesk = numberOfDesk;
    }
    public Game(Human white, Human black, int numberOfDesk, int numberOfTour) {
        this.white = white;
        this.black = black;
        this.numberOfDesk = numberOfDesk;
        this.numberOfTour = numberOfTour;
    }

    public Game indexOf(int start) {
        if (white.getNumberStart() == start || black.getNumberStart() == start) {
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Desk " +
                numberOfDesk + ": "+
                white + " " +
                resultGame + " " +
                black;
    }
}
