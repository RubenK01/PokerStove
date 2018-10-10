package prod.controlador;

public class ResultadoSimulacion {

	private double[] resultados;
	private int[] ganadas;
	private int[] empates;
	private int ejecuciones;
	
	public ResultadoSimulacion(double[] resultados, int[] ganadas,
			int[] empates, int ejecuciones) {
		super();
		this.resultados = resultados;
		this.ganadas = ganadas;
		this.empates = empates;
		this.ejecuciones = ejecuciones;
	}

	/**
	 * @return the resultados
	 */
	public double[] getResultados() {
		return resultados;
	}

	/**
	 * @return the ganadas
	 */
	public int[] getGanadas() {
		return ganadas;
	}

	/**
	 * @return the empates
	 */
	public int[] getEmpates() {
		return empates;
	}

	/**
	 * @return the ejecuciones
	 */
	public int getEjecuciones() {
		return ejecuciones;
	}
	
}
