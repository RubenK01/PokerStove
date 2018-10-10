package test.modelo.util;


import org.junit.Test;
import prod.baraja.Carta;
import prod.modelo.reproductor.EstadoJugador;
import prod.modelo.reproductor.EstadoMesa;
import prod.modelo.reproductor.ManoReproductor;
import prod.modelo.reproductor.acciones.AccionReproductor;
import prod.modelo.reproductor.acciones.I_AccionReproductor;
import prod.modelo.util.LecturaEscritura;
import prod.modelo.util.Utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class LecturaEscrituraTest {

    @Test
    public void testLeeArchivoPokerStarsUnaManoPreflop() {

        List<ManoReproductor> resultado = LecturaEscritura.leeArchivoPokerStars(new File("src/archivos/manos/manoTestUnaManoPreflop.txt"));

        List<I_AccionReproductor> accionesEsperadas = new ArrayList<>();

        // Estado inicial
        List<EstadoJugador> jugadores = new ArrayList<>();
        ArrayList<Carta> cartashero = new ArrayList<>();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.97, 0, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, false, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.03, null), "\nDealt to MN-UCM [9h Ks]"));

        // eljudas888 raises 0.04 to 0.06
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.91, 0.06, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, false, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "eljudas888: raises â‚¬0.04 to â‚¬0.06"));

        // kastaracing: folds
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.91, 0.06, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, false, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "kastaracing: folds "));

        // rosaenisthsa: folds
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.91, 0.06, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, true, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, false, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "rosaenithsa: folds "));

        // edgarbermejo: folds
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.91, 0.06, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, true, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, true, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "edgarbermejo: folds "));

        // MN-UCM: folds
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.91, 0.06, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, true, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, true, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, true, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "MN-UCM: folds "));

        // eljudas888 collected 0.05 from pot
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.95, 0.0, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, true, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0, true, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0, true, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, null), "Uncalled bet (â‚¬0.04) returned to eljudas888"));


        // eljudas888 collected 0.05 from pot
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2, 0.0, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, true, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0, true, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0, true, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0, null), "eljudas888 collected â‚¬0.05 from pot"));

        for (int i = 0; i < accionesEsperadas.size(); i++) {
            assertEquals("No se ha parseado la accion correctamente", accionesEsperadas.get(i), resultado.get(0).getAcciones().get(i));
        }

    }

    @Test
    public void testLeeArchivoPokerStarsUnaManoPostFlop() {
        List<ManoReproductor> resultado = LecturaEscritura.leeArchivoPokerStars(new File("src/archivos/manos/manoTestUnaManoPostFlop.txt"));

        List<I_AccionReproductor> accionesEsperadas = new ArrayList<>();

        ArrayList<Carta> cartashero = new ArrayList<>();
        cartashero.add(new Carta("2h"));
        cartashero.add(new Carta("7c"));

        ArrayList<Carta> cartasmesa = new ArrayList<>();

        // Estado inicial
        List<EstadoJugador> jugadores = new ArrayList<>();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.03, null), "\nDealt to MN-UCM [2h 7c]"));

        // kastaracing: calls 0.02
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.42, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, false, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.05, null), "kastaracing: calls â‚¬0.02"));

        // rosaenitsha: calls 0.02
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.42, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, false, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.07, null), "rosaenithsa: calls â‚¬0.02"));

        // edgarbermejo: raises 0.09 to 0.11
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.42, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.18, null), "edgarbermejo: raises â‚¬0.09 to â‚¬0.11"));

        // MN-UCM: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.42, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.18, null), "MN-UCM: folds "));

        // eljudas: calls 0.09
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.42, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.27, null), "eljudas888: calls â‚¬0.09"));

        // kastaracing: calls 0.09
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.36, null), "kastaracing: calls â‚¬0.09"));

        // rosaenithsa: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.36, null), "rosaenithsa: folds "));

        // *** FLOP *** [4c 7s Ks]
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        cartasmesa.add(new Carta("4c"));
        cartasmesa.add(new Carta("7s"));
        cartasmesa.add(new Carta("Ks"));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.36, cartasmesa), "*** FLOP *** [4c 7s Ks]"));

        // eljufas888: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.36, cartasmesa), "eljudas888: checks "));

        // kastaracing: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.36, cartasmesa), "kastaracing: checks "));

        // edgarbermejo: bets 0.18
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.15, 0.29, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.54, cartasmesa), "edgarbermejo: bets â‚¬0.18"));

        // eljudas888: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.15, 0.29, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.54, cartasmesa), "eljudas888: folds "));

        // kastaracing: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.15, 0.29, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.54, cartasmesa), "kastaracing: folds "));

        // kastaracing: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.9, cartasmesa), "Uncalled bet (â‚¬0.18) returned to edgarbermejo"));



        // kastaracing: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.6799999999999997, 0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0, cartasmesa), "edgarbermejo collected â‚¬0.35 from pot"));

        for (int i = 0; i < accionesEsperadas.size(); i++) {
            assertEquals("No se ha parseado la accion correctamente", accionesEsperadas.get(i), resultado.get(0).getAcciones().get(i));
        }
    }

    @Test
    public void testLeeArchivoPokerStarsUnaManoFlopTurnRiver() {

        List<ManoReproductor> resultado = LecturaEscritura.leeArchivoPokerStars(new File("src/archivos/manos/manoTestUnaManoFlopTurnRiver.txt"));

        List<I_AccionReproductor> accionesEsperadas = new ArrayList<>();

        ArrayList<Carta> cartashero = new ArrayList<>();
        cartashero.add(new Carta("4c"));
        cartashero.add(new Carta("7h"));

        ArrayList<Carta> cartasmesa = new ArrayList<>();

        // Estado inicial
        List<EstadoJugador> jugadores = new ArrayList<>();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.94, 0, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.76, 0, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.50, 0.01, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, false, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.03, null), "\nDealt to MN-UCM [4c 7h]"));

        // eljudas888: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.94, 0, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.76, 0, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.50, 0.01, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, false, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.03, null), "eljudas888: folds "));

        // kastaracing: calls €0.02
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.9199999999999999, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.76, 0, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.50, 0.01, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, false, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.05, null), "kastaracing: calls â‚¬0.02"));

        // rosaenithsa: raises €0.02 to €0.04
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.9199999999999999, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.50, 0.01, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, false, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "rosaenithsa: raises â‚¬0.02 to â‚¬0.04"));

        // edgarbermejo: calls €0.03
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.9199999999999999, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, false, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.12, null), "edgarbermejo: calls â‚¬0.03"));

        // MN-UCM: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.9199999999999999, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.12, null), "MN-UCM: folds "));

        // kastaracing: calls €0.02
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, null), "kastaracing: calls â‚¬0.02"));

        // *** FLOP *** [Qs 7s 5s]
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        cartasmesa.add(new Carta("Qs"));
        cartasmesa.add(new Carta("7s"));
        cartasmesa.add(new Carta("5s"));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, new ArrayList<Carta>(cartasmesa)), "*** FLOP *** [Qs 7s 5s]"));

        // edgarbermejo: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, new ArrayList<Carta>(cartasmesa)), "edgarbermejo: checks "));

        // kastaracing: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, new ArrayList<Carta>(cartasmesa)), "kastaracing: checks "));

        // rosaenithsa: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, new ArrayList<Carta>(cartasmesa)), "rosaenithsa: checks "));

        // *** TURN *** [Qs 7s 5s] [3d]
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        cartasmesa.add(new Carta("3d"));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, new ArrayList<Carta>(cartasmesa)), "*** TURN *** [Qs 7s 5s] [3d]"));

        // edgarbermejo: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, new ArrayList<Carta>(cartasmesa)), "edgarbermejo: checks "));

        // kastaracing: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, new ArrayList<Carta>(cartasmesa)), "kastaracing: checks "));

        // rosaenithsa: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, new ArrayList<Carta>(cartasmesa)), "rosaenithsa: checks "));

        // *** RIVER *** [Qs 7s 5s 3d] [7c]
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        cartasmesa.add(new Carta("7c"));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, new ArrayList<Carta>(cartasmesa)), "*** RIVER *** [Qs 7s 5s 3d] [7c]"));

        // edgarbermejo: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8999999999999999, 0.04, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.13999999999999999, new ArrayList<Carta>(cartasmesa)), "edgarbermejo: checks "));

        // kastaracing: bets €0.02
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8799999999999999, 0.06, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.72, 0.04, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.15999999999999998, new ArrayList<Carta>(cartasmesa)), "kastaracing: bets â‚¬0.02"));

        // rosaenithsa: calls €0.02
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8799999999999999, 0.06, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.70, 0.06, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.47, 0.04, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.17999999999999997,
                new ArrayList<Carta>(cartasmesa)), "rosaenithsa: calls â‚¬0.02"));

        // edgarbermejo: calls €0.02
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8799999999999999, 0.06, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.70, 0.06, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.45, 0.06, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.19999999999999996,
                new ArrayList<Carta>(cartasmesa)), "edgarbermejo: calls â‚¬0.02"));

        // edgarbermejo: calls €0.02
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 0.8799999999999999, 0.06, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.70, 0.06, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.45, 0.06, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.19999999999999996,
                new ArrayList<Carta>(cartasmesa)), "*** SHOW DOWN ***"));

        //  kastaracing: shows [7d 8h] (three of a kind, Sevens)
        List<Carta> cartaskastaracing = new ArrayList<>();
        cartaskastaracing.add(new Carta("7d"));
        cartaskastaracing.add(new Carta("8h"));
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", cartaskastaracing, 0.8799999999999999, 0.06, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.70, 0.06, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.45, 0.06, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0.02, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.19999999999999996,
                new ArrayList<Carta>(cartasmesa)), "kastaracing: shows [7d 8h] (three of a kind, Sevens)"));



        //  kastaracing collected €0.19 from pot
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2.43, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", cartaskastaracing, 1.0699999999999998, 0, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.70, 0, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.45, 0, false, false));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.75, 0, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0,
                new ArrayList<Carta>(cartasmesa)), "kastaracing collected â‚¬0.19 from pot"));

        for (int i = 0; i < accionesEsperadas.size(); i++) {
            assertEquals("No se ha parseado la accion correctamente", accionesEsperadas.get(i), resultado.get(0).getAcciones().get(i));
        }

    }

    @Test
    public void testLeeArchivoPokerStarsNombreCompuesto() {

        List<ManoReproductor> resultado = LecturaEscritura.leeArchivoPokerStars(new File("src/archivos/manos/manoTestNombreCompuesto.txt"));

        List<I_AccionReproductor> accionesEsperadas = new ArrayList<>();

        ArrayList<Carta> cartashero = new ArrayList<>();
        cartashero.add(new Carta("Jc"));
        cartashero.add(new Carta("Th"));


        // Estado inicial
        List<EstadoJugador> jugadores = new ArrayList<>();
        jugadores.add(new EstadoJugador("desfasii", null, 2.01, 0.02, false, false));
        jugadores.add(new EstadoJugador("lunasa", null, 2.11, 0, false, false));
        jugadores.add(new EstadoJugador("ChÃºck Norris", null, 1.97, 0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, false, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.03, null), "\nDealt to MN-UCM [Jc Th]"));


        for (int i = 0; i < accionesEsperadas.size(); i++) {
            assertEquals("No se ha parseado la accion correctamente", accionesEsperadas.get(i), resultado.get(0).getAcciones().get(i));
        }

    }
}
