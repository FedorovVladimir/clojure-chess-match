package pairs.pairs;

import javafo.api.JaVaFoApi;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ListOfPlayersTest {
    @Test
    public void addPlayer() throws FileNotFoundException {
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        System.out.println(JaVaFoApi.exec(1210,5,new FileInputStream("/home/vemce/IdeaProjects/ChessMatch/TRF.trf")));
        System.out.println(JaVaFoApi.exec(1210,5,new FileInputStream("/home/vemce/IdeaProjects/ChessMatch/TRF.trf")));
    }
}