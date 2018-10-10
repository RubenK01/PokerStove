package test.controlador;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.Assert;
import prod.baraja.Carta;
import prod.baraja.E_Carta_Palo;
import prod.baraja.E_Carta_Valor;
import prod.controlador.Controlador;
import prod.modelo.util.LecturaEscritura;
import prod.modelo.util.Utilidades;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class ControladorTest {
    @Test
    public void testDevolverArrayCartas() throws Exception {
    	Map<String, String[]> rankings = LecturaEscritura.leeRankings();
		Map<String, Float> rangos = LecturaEscritura.leeRangos();
        Controlador c = new Controlador(rankings, rangos);

        ArrayList<Carta[]> cartas = new ArrayList<Carta[]>();
        ArrayList<Carta[]> esperado = new ArrayList<Carta[]>();
        boolean contiene = false;

        //[AhAc]
        esperado.add(new Carta[]{new Carta("Ah"), new Carta("Ac")});
        cartas = Utilidades.devolverArrayCartas("AhAc");
        assertArrayEquals("AhAc no parseada correctamente", cartas.get(0), esperado.get(0));

        //[AKs]
        esperado = new ArrayList<Carta[]>();
             //AKc
        esperado.add(new Carta[]{new Carta("Ac"), new Carta("Kc")});
            //AKh
        esperado.add(new Carta[]{new Carta("Ah"), new Carta("Kh")});
            //AKd
        esperado.add(new Carta[]{new Carta("Ad"), new Carta("Kd")});
            //AKs
        esperado.add(new Carta[]{new Carta("As"), new Carta("Ks")});

        cartas = Utilidades.devolverArrayCartas("AKs");

        contiene = false;
        assertTrue("Las cartas parseadas no tienen el numero correcto de combinaciones", cartas.size() == esperado.size());
        for (Carta[] mano : esperado) {
            for (Carta[] cs : cartas) {
                contiene |= Arrays.equals(cs, mano);
            }
            assertTrue("Hand in range, but not parsed " + mano[0].toString() + mano[1].toString(), contiene);
            contiene = false;
        }

        //[AQs+]
        esperado = new ArrayList<Carta[]>();
        //AKc
        esperado.add(new Carta[]{new Carta("Ac"), new Carta("Kc")});
        //AKh
        esperado.add(new Carta[]{new Carta("Ah"), new Carta("Kh")});
        //AKd
        esperado.add(new Carta[]{new Carta("Ad"), new Carta("Kd")});
        //AKs
        esperado.add(new Carta[]{new Carta("As"), new Carta("Ks")});
        //AQc
        esperado.add(new Carta[]{new Carta("Ac"), new Carta("Qc")});
        //AQh
        esperado.add(new Carta[]{new Carta("Ah"), new Carta("Qh")});
        //AQd
        esperado.add(new Carta[]{new Carta("Ad"), new Carta("Qd")});
        //AQs
        esperado.add(new Carta[]{new Carta("As"), new Carta("Qs")});

        cartas = Utilidades.devolverArrayCartas("AQs+");

        contiene = false;
        assertTrue("Las cartas parseadas no tienen el numero correcto de combinaciones", cartas.size() == esperado.size());
        for (Carta[] mano : esperado) {
            for (Carta[] cs : cartas) {
                contiene |= Arrays.equals(cs, mano);
            }
            assertTrue("Hand in range, but not parsed " + mano[0].toString() + mano[1].toString(), contiene);
            contiene = false;
        }

        //[AQo]
        esperado = new ArrayList<Carta[]>();

        //AcQh
        esperado.add(new Carta[]{new Carta("Ac"), new Carta("Qh")});
        //AcQs
        esperado.add(new Carta[]{new Carta("Ac"), new Carta("Qs")});
        //AcQd
        esperado.add(new Carta[]{new Carta("Ac"), new Carta("Qd")});

        //AdQh
        esperado.add(new Carta[]{new Carta("Ad"), new Carta("Qh")});
        //AdQc
        esperado.add(new Carta[]{new Carta("Ad"), new Carta("Qc")});
        //AdQs
        esperado.add(new Carta[]{new Carta("Ad"), new Carta("Qs")});

        //AsQc
        esperado.add(new Carta[]{new Carta("As"), new Carta("Qc")});
        //AsQh
        esperado.add(new Carta[]{new Carta("As"), new Carta("Qh")});
        //AsQd
        esperado.add(new Carta[]{new Carta("As"), new Carta("Qd")});

        //AhQc
        esperado.add(new Carta[]{new Carta("Ah"), new Carta("Qc")});
        //AhQs
        esperado.add(new Carta[]{new Carta("Ah"), new Carta("Qs")});
        //AhQd
        esperado.add(new Carta[]{new Carta("Ah"), new Carta("Qd")});

        cartas = Utilidades.devolverArrayCartas("AQo");

        contiene = false;
        assertTrue("Las cartas parseadas no tienen el numero correcto de combinaciones", cartas.size() == esperado.size());
        for (Carta[] mano : esperado) {
            for (Carta[] cs : cartas) {
                contiene |= Arrays.equals(cs, mano);
            }
            assertTrue("Hand in range, but not parsed " + mano[0].toString() + mano[1].toString(), contiene);
            contiene = false;
        }

        //[AKs,AhAc]
        esperado = new ArrayList<Carta[]>();
        //AhAc
        esperado.add(new Carta[]{new Carta("Ah"), new Carta("Ac")});
        //AKc
        esperado.add(new Carta[]{new Carta("Ac"), new Carta("Kc")});
        //AKh
        esperado.add(new Carta[]{new Carta("Ah"), new Carta("Kh")});
        //AKd
        esperado.add(new Carta[]{new Carta("Ad"), new Carta("Kd")});
        //AKs
        esperado.add(new Carta[]{new Carta("As"), new Carta("Ks")});

        cartas = Utilidades.devolverArrayCartas("AhAc,AKs");

        contiene = false;
        assertTrue("Las cartas parseadas no tienen el numero correcto de combinaciones", cartas.size() == esperado.size());
        for (Carta[] mano : esperado) {
            for (Carta[] cs : cartas) {
                contiene |= Arrays.equals(cs, mano);
            }
            assertTrue("Hand in range, but not parsed " + mano[0].toString() + mano[1].toString(), contiene);
            contiene = false;
        }

    }

    @Test
    public void testDevolverArrayCartasOmaha() {

        ArrayList<Carta> cartas, esperado;

        //[Ac Kh 5d 5s]
        esperado = new ArrayList<Carta>();

        esperado.add(new Carta("Ac"));
        esperado.add(new Carta("Kh"));
        esperado.add(new Carta("5d"));
        esperado.add(new Carta("5s"));

        cartas = Utilidades.devolverArrayCartasOmaha("Ac 5d Kh 5s");

        assertTrue("Las cartas parseadas no tienen el numero correcto de combinaciones", cartas.size() == esperado.size());
        for (Carta c : esperado) {
            assertTrue("Carta Omaha no parseadae: " + c.toString(), cartas.contains(c));
        }
    }

    @Test
    public void testEvaluarHoldem() throws Exception {

    }
}
