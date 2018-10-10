package test.modelo.analizador;

import org.junit.Test;
import prod.baraja.Carta;
import prod.modelo.analizador.Analizador;
import prod.modelo.analizador.E_Jugada_Tipo;
import prod.modelo.analizador.JugadaValor;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AnalizadorTest {

    @Test
    public void testAnalizaManoHighCard() {

        JugadaValor jugada, esperado;

        ArrayList<Carta> cartas = new ArrayList<>();

        cartas.add(new Carta("2h"));
        cartas.add(new Carta("Kc"));
        cartas.add(new Carta("3s"));
        cartas.add(new Carta("4d"));
        cartas.add(new Carta("Ah"));

        jugada = Analizador.analizaMano(cartas);

        esperado = new JugadaValor(E_Jugada_Tipo.HIGH_CARD);

        assertEquals("Jugada es " + jugada.toString() + " y deberia ser " + esperado.toString() ,
                       esperado.getTipo(), jugada.getTipo() );

        assertEquals("La carta alta deberia ser Ah y es ",
                jugada.getCartasImportantes().get(0).toString(), "Ah");

    }

    @Test
    public void testAnalizaManoPair() {

        JugadaValor jugada, esperado;

        ArrayList<Carta> cartas = new ArrayList<>();

        cartas.add(new Carta("2h"));
        cartas.add(new Carta("Ac"));
        cartas.add(new Carta("3s"));
        cartas.add(new Carta("4d"));
        cartas.add(new Carta("Ah"));

        jugada = Analizador.analizaMano(cartas);

        esperado = new JugadaValor(E_Jugada_Tipo.PAIR);

        assertEquals("Jugada es " + jugada.toString() + " y deberia ser " + esperado.toString(),
                esperado.getTipo(), jugada.getTipo());

        ArrayList<Carta> jugadaImportantes = new ArrayList<>();
        jugadaImportantes.add(jugada.getCartasImportantes().get(0));

        ArrayList<Carta> esperandoImportantes = new ArrayList<>();
        esperandoImportantes.add(new Carta("Ac"));
        esperandoImportantes.add(new Carta("Ah"));

        for (Carta c : jugadaImportantes) {
            assertTrue("La carta importante"+c.toString()+" es incorrecta.",
                    esperandoImportantes.contains(c));
        }

    }

    @Test
    public void testAnalizaManoSet() {

        JugadaValor jugada, esperado;

        ArrayList<Carta> cartas = new ArrayList<>();

        cartas.add(new Carta("2h"));
        cartas.add(new Carta("Ac"));
        cartas.add(new Carta("3s"));
        cartas.add(new Carta("Ad"));
        cartas.add(new Carta("Ah"));

        jugada = Analizador.analizaMano(cartas);

        esperado = new JugadaValor(E_Jugada_Tipo.THREE_OF_A_KIND);

        assertEquals("Jugada es " + jugada.toString() + " y deberia ser " + esperado.toString(),
                esperado.getTipo(), jugada.getTipo());

        ArrayList<Carta> jugadaImportantes = new ArrayList<>();
        jugadaImportantes.add(jugada.getCartasImportantes().get(0));

        ArrayList<Carta> esperandoImportantes = new ArrayList<>();
        esperandoImportantes.add(new Carta("Ac"));
        esperandoImportantes.add(new Carta("Ah"));
        esperandoImportantes.add(new Carta("Ad"));

        for (Carta c : jugadaImportantes) {
            assertTrue("La carta importante"+c.toString()+" es incorrecta.",
                    esperandoImportantes.contains(c));
        }

    }

    @Test
    public void testAnalizaManoTwoPair() {

        JugadaValor jugada, esperado;

        ArrayList<Carta> cartas = new ArrayList<>();

        cartas.add(new Carta("2h"));
        cartas.add(new Carta("Ac"));
        cartas.add(new Carta("3s"));
        cartas.add(new Carta("2d"));
        cartas.add(new Carta("Ah"));

        jugada = Analizador.analizaMano(cartas);

        esperado = new JugadaValor(E_Jugada_Tipo.TWO_PAIR);

        assertEquals("Jugada es " + jugada.toString() + " y deberia ser " + esperado.toString(),
                esperado.getTipo(), jugada.getTipo());

        ArrayList<Carta> jugadaImportantes = new ArrayList<>();
        jugadaImportantes.add(jugada.getCartasImportantes().get(0));
        jugadaImportantes.add(jugada.getCartasImportantes().get(1));

        ArrayList<Carta> esperandoImportantes = new ArrayList<>();
        esperandoImportantes.add(new Carta("Ac"));
        esperandoImportantes.add(new Carta("Ah"));
        esperandoImportantes.add(new Carta("2d"));
        esperandoImportantes.add(new Carta("2h"));

        for (Carta c : jugadaImportantes) {
            assertTrue("La carta importante"+c.toString()+" es incorrecta.",
                    esperandoImportantes.contains(c));
        }

    }

    @Test
    public void testAnalizaManoStraight() {

        JugadaValor jugada, esperado;

        ArrayList<Carta> cartas = new ArrayList<>();

        cartas.add(new Carta("Th"));
        cartas.add(new Carta("9c"));
        cartas.add(new Carta("8s"));
        cartas.add(new Carta("7d"));
        cartas.add(new Carta("6h"));

        jugada = Analizador.analizaMano(cartas);

        esperado = new JugadaValor(E_Jugada_Tipo.STRAIGHT);

        assertEquals("Jugada es " + jugada.toString() + " y deberia ser " + esperado.toString(),
                esperado.getTipo(), jugada.getTipo());

        ArrayList<Carta> jugadaImportantes = new ArrayList<>();
        jugadaImportantes.add(jugada.getCartasImportantes().get(0));

        ArrayList<Carta> esperandoImportantes = new ArrayList<>();
        esperandoImportantes.add(new Carta("Th"));

        for (Carta c : jugadaImportantes) {
            assertTrue("La carta importante"+c.toString()+" es incorrecta.",
                    esperandoImportantes.contains(c));
        }

    }

    @Test
    public void testAnalizaManoStraightWheel() {

        JugadaValor jugada, esperado;

        ArrayList<Carta> cartas = new ArrayList<>();

        cartas.add(new Carta("2h"));
        cartas.add(new Carta("Ac"));
        cartas.add(new Carta("3s"));
        cartas.add(new Carta("4d"));
        cartas.add(new Carta("5h"));

        jugada = Analizador.analizaMano(cartas);

        esperado = new JugadaValor(E_Jugada_Tipo.STRAIGHT);

        assertEquals("Jugada es " + jugada.toString() + " y deberia ser " + esperado.toString(),
                esperado.getTipo(), jugada.getTipo());

        ArrayList<Carta> jugadaImportantes = new ArrayList<>();
        jugadaImportantes.add(jugada.getCartasImportantes().get(0));

        ArrayList<Carta> esperandoImportantes = new ArrayList<>();
        esperandoImportantes.add(new Carta("Ac"));

        for (Carta c : jugadaImportantes) {
            assertTrue("La carta importante"+c.toString()+" es incorrecta.",
                    esperandoImportantes.contains(c));
        }

    }

    @Test
    public void testAnalizaManoFlush() {

        JugadaValor jugada, esperado;

        ArrayList<Carta> cartas = new ArrayList<>();

        cartas.add(new Carta("2h"));
        cartas.add(new Carta("Ah"));
        cartas.add(new Carta("6h"));
        cartas.add(new Carta("Jh"));
        cartas.add(new Carta("Th"));

        jugada = Analizador.analizaMano(cartas);

        esperado = new JugadaValor(E_Jugada_Tipo.FLUSH);

        assertEquals("Jugada es " + jugada.toString() + " y deberia ser " + esperado.toString(),
                esperado.getTipo(), jugada.getTipo());

        ArrayList<Carta> jugadaImportantes = new ArrayList<>();
        jugadaImportantes.add(jugada.getCartasImportantes().get(0));

        ArrayList<Carta> esperandoImportantes = new ArrayList<>();
        esperandoImportantes.add(new Carta("Ah"));

        for (Carta c : jugadaImportantes) {
            assertTrue("La carta importante "+c.toString()+" es incorrecta.",
                    esperandoImportantes.contains(c));
        }

    }

    @Test
    public void testAnalizaManoFullHouse() {

        JugadaValor jugada, esperado;

        ArrayList<Carta> cartas = new ArrayList<>();

        cartas.add(new Carta("2h"));
        cartas.add(new Carta("Ac"));
        cartas.add(new Carta("2s"));
        cartas.add(new Carta("2d"));
        cartas.add(new Carta("Ah"));

        jugada = Analizador.analizaMano(cartas);

        esperado = new JugadaValor(E_Jugada_Tipo.FULL_HOUSE);

        assertEquals("Jugada es " + jugada.toString() + " y deberia ser " + esperado.toString(),
                esperado.getTipo(), jugada.getTipo());

        ArrayList<Carta> jugadaImportantes = new ArrayList<>();
        jugadaImportantes.add(jugada.getCartasImportantes().get(0));
        jugadaImportantes.add(jugada.getCartasImportantes().get(1));

        ArrayList<Carta> esperandoImportantes = new ArrayList<>();
        esperandoImportantes.add(new Carta("Ac"));
        esperandoImportantes.add(new Carta("Ah"));
        esperandoImportantes.add(new Carta("2d"));
        esperandoImportantes.add(new Carta("2h"));
        esperandoImportantes.add(new Carta("2s"));

        for (Carta c : jugadaImportantes) {
            assertTrue("La carta importante"+c.toString()+" es incorrecta.",
                    esperandoImportantes.contains(c));
        }

    }

    @Test
    public void testAnalizaManoFourOfAKind() {

        JugadaValor jugada, esperado;

        ArrayList<Carta> cartas = new ArrayList<>();

        cartas.add(new Carta("2h"));
        cartas.add(new Carta("2c"));
        cartas.add(new Carta("2s"));
        cartas.add(new Carta("2d"));
        cartas.add(new Carta("Ah"));

        jugada = Analizador.analizaMano(cartas);

        esperado = new JugadaValor(E_Jugada_Tipo.FOUR_OF_A_KIND);

        assertEquals("Jugada es " + jugada.toString() + " y deberia ser " + esperado.toString(),
                esperado.getTipo(), jugada.getTipo());

        ArrayList<Carta> jugadaImportantes = new ArrayList<>();
        jugadaImportantes.add(jugada.getCartasImportantes().get(0));

        ArrayList<Carta> esperandoImportantes = new ArrayList<>();
        esperandoImportantes.add(new Carta("2c"));
        esperandoImportantes.add(new Carta("2h"));
        esperandoImportantes.add(new Carta("2d"));
        esperandoImportantes.add(new Carta("2h"));

        for (Carta c : jugadaImportantes) {
            assertTrue("La carta importante"+c.toString()+" es incorrecta.",
                    esperandoImportantes.contains(c));
        }

    }

    @Test
    public void testAnalizaManoStraightFlush() {

        JugadaValor jugada, esperado;

        ArrayList<Carta> cartas = new ArrayList<>();

        cartas.add(new Carta("2h"));
        cartas.add(new Carta("Ah"));
        cartas.add(new Carta("3h"));
        cartas.add(new Carta("4h"));
        cartas.add(new Carta("5h"));

        jugada = Analizador.analizaMano(cartas);

        esperado = new JugadaValor(E_Jugada_Tipo.STRAIGHT_FLUSH);

        assertEquals("Jugada es " + jugada.toString() + " y deberia ser " + esperado.toString(),
                esperado.getTipo(), jugada.getTipo());

        ArrayList<Carta> jugadaImportantes = new ArrayList<>();
        jugadaImportantes.add(jugada.getCartasImportantes().get(0));

        ArrayList<Carta> esperandoImportantes = new ArrayList<>();
        esperandoImportantes.add(new Carta("Ah"));

        for (Carta c : jugadaImportantes) {
            assertTrue("La carta importante"+c.toString()+" es incorrecta.",
                    esperandoImportantes.contains(c));
        }

    }

    @Test
    public void testAnalizaManoNoHayDraws() {

        JugadaValor jugada, esperado;
        ArrayList<Carta> cartas = new ArrayList<>();

        // Flush draw y straight draw
        cartas.add(new Carta("2h"));
        cartas.add(new Carta("Ah"));
        cartas.add(new Carta("3h"));
        cartas.add(new Carta("4h"));

        jugada = Analizador.analizaMano(cartas);

        assertTrue("Hay Draws cuando no deberia haberlos", hayDraws(jugada));

        cartas.clear();

        // Gutshot y FD
        cartas.add(new Carta("2h"));
        cartas.add(new Carta("Ah"));
        cartas.add(new Carta("4h"));
        cartas.add(new Carta("5d"));

        jugada = Analizador.analizaMano(cartas);

        assertTrue("Hay Draws cuando no deberia haberlos", hayDraws(jugada));

        cartas.clear();

        // OESD
        cartas.add(new Carta("2h"));
        cartas.add(new Carta("3h"));
        cartas.add(new Carta("4h"));
        cartas.add(new Carta("5d"));

        jugada = Analizador.analizaMano(cartas);

        assertTrue("Hay Draws cuando no deberia haberlos", hayDraws(jugada));

        cartas.clear();


    }

    private boolean hayDraws(JugadaValor jugada) {
        return !(jugada.getFlushDraw() ||
                    jugada.getGutShot() ||
                    jugada.getOESD() ||
                    jugada.getStraightDraw());
    }
}
