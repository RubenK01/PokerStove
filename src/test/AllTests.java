package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.baraja.CartaTest;
import test.controlador.ControladorTest;
import test.modelo.analizador.AnalizadorTest;
import test.modelo.util.LecturaEscrituraTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ControladorTest.class,
        CartaTest.class,
        AnalizadorTest.class,
        LecturaEscrituraTest.class,
        //RealizarSimulacionesTest.class
})
public class AllTests {
}
