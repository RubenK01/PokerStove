package prod.modelo.util;

import prod.baraja.Carta;
import prod.modelo.reproductor.EstadoJugador;
import prod.modelo.reproductor.EstadoMesa;
import prod.modelo.reproductor.ManoReproductor;
import prod.modelo.reproductor.acciones.AccionReproductor;
import prod.modelo.reproductor.acciones.I_AccionReproductor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LecturaEscritura {

	public static HashMap<String, String[]> leeRankings() {
		
		HashMap<String, String[]> rankings = new HashMap<String, String[]>();
		String cadena, nombreRanking;
		String[] manosRanking;
		
		File archivo = new File("src/archivos/rankings.txt");
		if(!archivo.exists())
			System.out.println("El archivo no existe.");
			
		else {
			BufferedReader bf;
			try {
				
				bf = new BufferedReader(new FileReader(archivo));
				while ((cadena = bf.readLine()) != null) {
					nombreRanking = cadena;
					cadena = bf.readLine();
					manosRanking = cadena.split(" ");
					rankings.put(nombreRanking, manosRanking);
				}
				bf.close();
				
			} catch (FileNotFoundException e1) {
				
			} catch (IOException e) {
				
			} finally {
				
			}
		}
		
		return rankings;
	}
	
	public static HashMap<String, Float> leeRangos() {
		HashMap<String, Float> rangos = new HashMap<String, Float>();
		String cadena, nombreRango;
		String[] datos;
		Float porcentaje;
		
		File archivo = new File("src/archivos/rangos.txt");
		if(!archivo.exists())
			System.out.println("El archivo no existe.");
			
		else {
			BufferedReader bf;
			try {
				
				bf = new BufferedReader(new FileReader(archivo));
				while ((cadena = bf.readLine()) != null) {
					datos = cadena.split(" : ");
					nombreRango = datos[0];
					porcentaje = Float.parseFloat(datos[1]);
					rangos.put(nombreRango, porcentaje);
				}
				bf.close();
				
			} catch (FileNotFoundException e1) {
				
			} catch (IOException e) {
				
			}
		}
		
		return rangos;
	}
	
	public static void escribeRanking(String nombre, String[] manos) {
		FileWriter fw = null;
        BufferedWriter bw = null;
        String line = "";
        
        try {
            fw = new FileWriter("src/archivos/rankings.txt", true);
            bw = new BufferedWriter(fw);
            
            bw.newLine();
            bw.write(nombre);
            
            bw.newLine();
            for (int i = 0; i < manos.length; i++) {
				line += manos[i] + " ";
			}
            bw.write(line);
           
            bw.close();
            fw.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public static void escribeRango(String line) {
		FileWriter fw = null;
        BufferedWriter bw = null;
        
        try {
            fw = new FileWriter("src/archivos/rangos.txt", true);
            bw = new BufferedWriter(fw);
            
            bw.newLine();
            bw.write(line);
            
            bw.close();
            fw.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	public static String leeAyuda() {
		String cadena, texto = "";
		
		File archivo = new File("src/archivos/help.txt");
		if(!archivo.exists())
			System.out.println("El archivo no existe.");
			
		else {
			BufferedReader bf;
			try {
				
				bf = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF8"));
				while ((cadena = bf.readLine()) != null) {
					texto += cadena + '\n';
				}
				bf.close();
				
			} catch (FileNotFoundException e1) {
				
			} catch (IOException e) {
				
			} finally {
				
			}
		}
		
		return texto;
	}

	public static List<ManoReproductor> leeArchivoPokerStars(File archivo) {
		List<ManoReproductor> manos = new ArrayList<>();
		List<I_AccionReproductor> acciones = new ArrayList<>();

		if(!archivo.exists()) {
			System.out.println("El archivo no existe.");

		} else {

			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(archivo));
				// Variables locales
				int seatDelBoton = 0;
				int indiceDelBoton = 0;
				List<String> nombresJugadores = new ArrayList<>();
				List<Double> stacksJugadores = new ArrayList<>();
				List<Double> apuestasJugadores = new ArrayList<>();
				String nombreHero = "";
				List<Carta> cartasHero = new ArrayList<>();
				List<Carta> cartasEnMesa = new ArrayList<>();
				List<Boolean> hasFolded = new ArrayList<>();
				
				List<List<Carta>> cartasJugadores = new ArrayList<>();

				double pot = 0.0;
				boolean sigueContadoIndices = true;

				// Bucle de lectura
				String linea;
				String[] tokens; // Solo se hace split cuando hace falta.
				while ((linea = reader.readLine()) != null) {
					// Casos de parseo
					if (linea.matches("(\\** SUMMARY \\**)")) {
						//Resetea acciones, y crea una nueva mano
						manos.add(new ManoReproductor(new ArrayList<I_AccionReproductor>(acciones)));
						acciones.clear();
						//Vaciar los arrays y demas
						seatDelBoton = 0;
						nombresJugadores.clear();
						stacksJugadores.clear();
						apuestasJugadores.clear();
						cartasHero.clear();
						cartasEnMesa.clear();
						hasFolded.clear();
						pot  = 0;
						indiceDelBoton = 0;
						sigueContadoIndices = true;
						cartasJugadores.clear();
					} else if (linea.matches("(.* Seat #\\d is the button)"))  { // Numero de jugadores, seat del button
						seatDelBoton = parseaSeatDelBoton(linea);
					} else if (linea.matches("(Seat \\d:.* in chips.\\s*)")){
						tokens = linea.split(" ");
						nombresJugadores.add(parseaNombreJugador(linea, tokens));
						stacksJugadores.add(parseaStackJugador(linea, tokens));
						if (!haPasadoElIndiceDelBoton(linea, tokens, seatDelBoton) && sigueContadoIndices) {
							indiceDelBoton++;
						} else {
							sigueContadoIndices = false;
						}
					} else if (linea.matches("(.* posts small blind .*)")) {
						tokens = linea.split(" ");
						for (String i : nombresJugadores) {
							apuestasJugadores.add(0.0);
						}
						for (String i : nombresJugadores) {
							hasFolded.add(false);
						}
						for (String i : nombresJugadores) {
							cartasJugadores.add(new ArrayList<Carta>());
						}
						for (int i = 0; i < cartasJugadores.size(); i++) {
							cartasJugadores.set(i, null);
						}
						double ciegaPequena = parseaCiegaPequena(linea, tokens);
						int seatCiegaPequena = (indiceDelBoton + 1) % stacksJugadores.size();
						stacksJugadores.set(seatCiegaPequena, stacksJugadores.get(seatCiegaPequena) - ciegaPequena);
						apuestasJugadores.set(seatCiegaPequena, ciegaPequena);
						pot += ciegaPequena;
					} else if (linea.matches("(.* posts big blind .*)")){
						tokens = linea.split(" ");
						double ciegaGrande = parseaCiegaGrande(linea, tokens);
						int seatCiegaGrande = (indiceDelBoton + 2) % stacksJugadores.size();
						stacksJugadores.set(seatCiegaGrande, stacksJugadores.get(seatCiegaGrande) - ciegaGrande);
						apuestasJugadores.set(seatCiegaGrande, ciegaGrande);
						pot += ciegaGrande;
					} else if (linea.matches("(Dealt to .*\\s[\\[][AKQJT2-9][hscd] [AKQJT2-9][hscd].)")) {
						tokens = linea.split(" ");
						nombreHero = parseaNombreHero(linea, tokens);
						cartasHero = parseaCartasHero(linea, tokens);
						acciones.add(construyeAccion('\n' + linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
														apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					} else if (linea.matches("(.*: raises .*)")) {
						tokens = linea.split(" ");
						int iraiser = nombresJugadores.indexOf(pareseaNombreApuesta(linea, tokens));
						double apuesta = parseaApuestaRaise(linea, tokens);
						pot += apuesta  - apuestasJugadores.get(iraiser);
						stacksJugadores.set(iraiser, stacksJugadores.get(iraiser) - (apuesta - apuestasJugadores.get(iraiser)));
						apuestasJugadores.set(iraiser, apuesta);
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					} else if (linea.matches("(.*: folds .*)")) {
						tokens = linea.split(" ");
						int ifolded = nombresJugadores.indexOf(pareseaNombreApuesta(linea, tokens));
						hasFolded.set(ifolded, true);
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					} else if (linea.matches("(.*: calls .*)")) {
						tokens = linea.split(" ");
						int icaller = nombresJugadores.indexOf(pareseaNombreApuesta(linea, tokens));
						double apuesta = parseaApuestaCall(linea, tokens);
						pot += apuesta;
						stacksJugadores.set(icaller, stacksJugadores.get(icaller) - apuesta);
						apuestasJugadores.set(icaller, apuestasJugadores.get(icaller) + apuesta);
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					} else if (linea.matches("(.*: checks .*)")) {
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					}  else if (linea.matches("(.*: bets .*)")) {
						tokens = linea.split(" ");
						int ibettor = nombresJugadores.indexOf(pareseaNombreApuesta(linea, tokens));
						double apuesta = parseaApuestaBet(linea, tokens);
						pot += apuesta;
						stacksJugadores.set(ibettor, stacksJugadores.get(ibettor) - apuesta);
						apuestasJugadores.set(ibettor, apuestasJugadores.get(ibettor) + apuesta);
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					} else if (linea.matches("(.*: shows .([AKQJT2-9][hsdc]\\s*){2}.*)")) {
						tokens = linea.split(" ");
						int ishower = nombresJugadores.indexOf(tokens[parseaIndTokens(tokens)].substring(0, tokens[0].length()-1));
						cartasJugadores.set(ishower, parseaCartasShower(linea, tokens));
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
 					} else if (linea.matches("(\\** FLOP \\** .([AKQJT2-9][hcsd]\\s*)*.)")) {
						tokens = linea.split(" ");
						cartasEnMesa.addAll(parseaCartasFlop(linea, tokens));
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					} else if (linea.matches("(\\** TURN \\** .([AKQJT2-9][hcsd]\\s*)*.\\s.([AKQJT2-9][hcsd])*.)")) {
						tokens = linea.split(" ");
						cartasEnMesa.add(parseaCartaTurn(linea, tokens));
						for (int i = 0; i < apuestasJugadores.size(); i++) {
							apuestasJugadores.set(i, 0.0);
						}
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					} else if (linea.matches("(\\** RIVER \\** .([AKQJT2-9][hcsd]\\s*)*.\\s.([AKQJT2-9][hcsd])*.)")) {
						tokens = linea.split(" ");
						cartasEnMesa.add(parseaCartaRiver(linea, tokens));
						for (int i = 0; i < apuestasJugadores.size(); i++) {
							apuestasJugadores.set(i, 0.0);
						}
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					} else if (linea.matches("(\\** SHOW DOWN \\**)")) {
						tokens = linea.split(" ");
						for (int i = 0; i < apuestasJugadores.size(); i++) {
							apuestasJugadores.set(i, 0.0);
						}
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					} else if (linea.matches("(Uncalled bet \\(.*\\) returned to .*)")) {
						tokens = linea.split(" ");
						int iganador = nombresJugadores.indexOf(parseaNombreUncalled(linea, tokens));
						double collected = parseaUncalledBet(linea, tokens);
						stacksJugadores.set(iganador, stacksJugadores.get(iganador) + collected);
						apuestasJugadores.set(iganador, apuestasJugadores.get(iganador) - collected);
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					} else if (linea.matches("(.* collected .* from pot)")) {
						tokens = linea.split(" ");
						int iganador = nombresJugadores.indexOf(parseaNombreGanadorPot(linea, tokens));
						double collected = parseaGanadoDelPot(linea, tokens);
						stacksJugadores.set(iganador, stacksJugadores.get(iganador) + collected);
						for (int i = 0; i < apuestasJugadores.size(); i++) {
							apuestasJugadores.set(i, 0.0);
						}
						pot = 0;
						acciones.add(construyeAccion(linea, pot, cartasEnMesa, nombresJugadores, stacksJugadores,
								apuestasJugadores, hasFolded, indiceDelBoton, nombreHero, cartasHero, cartasJugadores));
					}
				}
				reader.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return manos;
	}

	private static Object parseaNombreGanadorPot(String linea, String[] tokens) {
		int ind = 0;
		boolean finish = false;
		while (!finish) {
			if (tokens[ind].equals("collected")) {
				finish = true;
			}
			ind++;
		}

		ind -= 1;

		String nombre = "";

		for (int i = 0; i < ind; i++ ){
			nombre += tokens[i] + " ";
		}

		return nombre.trim();
	}

	private static String parseaNombreUncalled(String linea, String[] tokens) {
		int ind = 0;
		boolean finish = false;
		while (!finish) {
			if (tokens[ind].equals("to")) {
				finish = true;
			}
			ind++;
		}

		String nombre = "";

		for (int i = ind; i < tokens.length; i++ ){
			nombre += tokens[i] + " ";
		}

		return nombre.trim();
	}

	private static AccionReproductor construyeAccion(String linea,
													 double pot,
													 List<Carta> cartasEnMesa,
													 List<String> nombresJugadores,
													 List<Double> stacksJugadores,
													 List<Double> apuestasJugadores,
													 List<Boolean> hasFolded,
													 int seatButton,
													 String nombreHero,
													 List<Carta> cartasHero, List<List<Carta>> cartasJugadores) {

		List<EstadoJugador> estadosJugadores = new ArrayList<>();
		boolean isHero, isButton;
		for (int i = 0; i < nombresJugadores.size(); i++) {
			isHero = (nombresJugadores.get(i).equals(nombreHero));
			isButton = (i == seatButton);

			estadosJugadores.add(new EstadoJugador(nombresJugadores.get(i),
													(isHero ? new ArrayList<Carta>(cartasHero) : cartasJugadores.get(i)),
													stacksJugadores.get(i),
													apuestasJugadores.get(i),
													hasFolded.get(i),
													isButton));

		}

		EstadoMesa estadoMesa = new EstadoMesa(estadosJugadores, pot, (cartasEnMesa.size() > 0 ? new ArrayList<>(cartasEnMesa) : null));
		AccionReproductor accion = new AccionReproductor(estadoMesa, linea);

		return accion;
	}


	private static List<Carta> parseaCartasShower(String linea, String[] tokens) {
		List<Carta> res = new ArrayList<>();

		int ind = 0;
		boolean finish = false;
		while(!finish) {
			if (tokens[ind].contains("[")) {
				finish = true;
			} else {
				ind++;
			}
		}

		res.add(new Carta(tokens[ind].substring(1, 3)));
		res.add(new Carta(tokens[ind + 1].substring(0, 2)));
		return res;
	}

	private static Carta parseaCartaRiver(String linea, String[] tokens) {
		return new Carta(tokens[7].substring(1, 4));
	}

	private static Carta parseaCartaTurn(String linea, String[] tokens) {
		return new Carta(tokens[6].substring(1, 4));
	}

	private static double parseaApuestaBet(String linea, String[] tokens) {
		return round(Double.parseDouble(tokens[parseaIndTokens(tokens) + 2].substring(3)), 2);
	}

	private static List<Carta> parseaCartasFlop(String linea, String[] tokens) {
		List<Carta> res = new ArrayList<>();
		res.add(new Carta(tokens[3].substring(1, 3)));
		res.add(new Carta(tokens[4]));
		res.add(new Carta(tokens[5].substring(0, 2)));
		return res;
	}

	private static boolean haPasadoElIndiceDelBoton(String linea, String[] tokens, int seatBoton) {
		return (seatBoton < Integer.parseInt(tokens[1].substring(0,1)));
	}

	private static double parseaApuestaCall(String linea, String[] tokens) {
		return round(Double.parseDouble(tokens[parseaIndTokens(tokens) + 2].substring(3)), 2);
	}


	private static List<Carta> parseaCartasHero(String linea, String[] tokens) {
		List<Carta> res = new ArrayList<>(2);

		int ind = 0;
		boolean finish = false;
		while(!finish) {
			if (tokens[ind].contains("[")) {
				finish = true;
			} else {
				ind++;
			}
		}

		res.add(new Carta(tokens[ind].substring(1)));
		res.add(new Carta(tokens[ind +1].substring(0, 3)));
		return res;
	}

	private static String parseaNombreHero(String linea, String[] tokens) {
		String nombre = "";
		boolean join = false;
		for (String s : tokens) {

			if (s.equals("to") && !join) {
				join = true;
			} else if (s.contains("[")){
				join = false;
			} else if (join) {
				nombre += s;
			}
		}
		return nombre;
	}

	private static double parseaCiegaGrande(String linea, String[] tokens) {
		return round(Double.parseDouble(tokens[parseaIndTokens(tokens) + 4].substring(3)), 2);
	}

	private static double parseaUncalledBet(String linea, String[] tokens) {
		return round(Double.parseDouble(tokens[2].substring(4, tokens[2].length() - 1)), 2);
	}

	private static double parseaCiegaPequena(String linea, String[] tokens) {
		return round(Double.parseDouble(tokens[parseaIndTokens(tokens) + 4].substring(3)), 2);
	}

	private static double parseaStackJugador(String linea, String[] tokens) {
		int ind = 0;
		boolean finish = false;
		while (ind < tokens.length-2 && !finish) {
			if (tokens[ind].contains("(")) {
				if (tokens[ind+1].equals("in") && tokens[ind+2].equals("chips)")) {
					finish = true;
				}
			} else {
				ind++;
			}
		}
		return round(Double.parseDouble(tokens[ind].substring(4)), 2);
	}

	private static String parseaNombreJugador(String linea, String[] tokens) {
		return linea.substring(linea.indexOf(":") + 2, linea.lastIndexOf("(") - 1);
	}

	private static int parseaSeatDelBoton(String linea) {
		int i = linea.indexOf('#');
		return Integer.parseInt(linea.substring(i + 1, i + 2)) - 1;
	}

	private static double parseaApuestaRaise(String linea, String[] tokens) {
		return round(Double.parseDouble(tokens[parseaIndTokens(tokens) + 4].substring(3)), 2);
	}

	private static double parseaGanadoDelPot(String linea, String[] tokens) {

		int ind = 0;
		boolean finish = false;
		while (!finish) {
			if (tokens[ind].equals("collected")) {
				finish = true;
			}
			ind++;
		}

		return round(Double.parseDouble(tokens[ind].substring(3)), 2);
	}

	private static int parseaIndTokens(String[] tokens) {
		int i = 0;
		boolean finish = false;
		while (!finish) {
			if (tokens[i].substring(tokens[i].length() - 1).equals(":")) {
				finish = true;
			} else {
				i++;
			}
		}

		return i;
	}

	private static String pareseaNombreApuesta(String linea, String[] tokens) {
		return (linea.substring(0, linea.indexOf(":")));
	}


	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
