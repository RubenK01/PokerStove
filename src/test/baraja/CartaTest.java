package test.baraja;

import org.junit.Test;
import prod.baraja.Carta;
import prod.baraja.E_Carta_Palo;
import prod.baraja.E_Carta_Valor;

import static org.junit.Assert.*;


public class CartaTest {

    @Test
    public void constructorString() {

        Carta  c = new Carta("Ah");
        assertEquals("Valor no parseade correctamente",E_Carta_Valor.A,  c.getValor());
        assertEquals("Palo no parseade correctamente", E_Carta_Palo.HEARTS, c.getPalo());

        c = new Carta("Kh");
        assertEquals("Valor no parseade correctamente",E_Carta_Valor.K,  c.getValor());
        assertEquals("Palo no parseade correctamente", E_Carta_Palo.HEARTS, c.getPalo());

        c = new Carta("Ad");
        assertEquals("Valor no parseade correctamente",E_Carta_Valor.A,  c.getValor());
        assertEquals("Palo no parseade correctamente", E_Carta_Palo.DIAMONDS, c.getPalo());;

        try {
            c = new Carta("f5");
        } catch (Exception e) {
            assertNotNull("Unformatted cards don't parse", e);
        }
    }


    @Test
    public void testEquals() throws Exception {
        Carta c1 = new Carta("Ac");
        Carta c2 = new Carta("Ac");
        Carta c3 = new Carta("Ad");

        assertEquals("Equal cards not equal?", c1, c2);
        //assertNotEquals("Different cards equal", c2, c3);
        //assertNotEquals("Different cards equal", c1, c3);
    }
}