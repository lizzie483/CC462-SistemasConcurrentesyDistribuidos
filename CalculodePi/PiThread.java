/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;
/**
 *
 * @author Lizeth
 */
import java.math.BigDecimal;
import java.math.MathContext;


public class PiThread extends Thread {
	
	private int desde;
	private int hasta;
	private int presicion;
	private BigDecimal resultado;
	
        //Valores constantes de la sumatoria
        //Series Infinitas
        //Bailey-Borwein-Plouffe
	static final BigDecimal one = BigDecimal.ONE;
	static final BigDecimal two = new BigDecimal(2);
	static final BigDecimal four = new BigDecimal(4);
	static final BigDecimal sixteen = new BigDecimal(16);
        
        
        //Constructor
	public PiThread(int from, int to, int presicion) {
		this.desde = from;
		this.hasta = to;
		this.presicion = presicion;
	}
	
	@Override
	public void run() {
		BigDecimal pi = getPi(desde, hasta, presicion);
		resultado = pi;
	}
	
        
        //Metodos de la clase
	public BigDecimal getResultado() {
		return resultado;
	}

	public static BigDecimal getPi(int limit) {
		return getPi(0, limit, limit);
	}

	public static BigDecimal getPi(int desde, int hasta, int precision) {
		return getPi(desde, hasta, new MathContext(precision));
	}
        
        
        //Calculo de PI usando el metodo de series infinitas
	public static BigDecimal getPi(int desde, int hasta, MathContext precision) {
		BigDecimal pi = BigDecimal.ZERO;
		for (int k = desde; k < hasta; k++) {
			pi = pi.add(one.divide(sixteen.pow(k), precision)
                                .multiply(four.divide(new BigDecimal(8 * k + 1), precision)
                                   .subtract(two.divide(new BigDecimal(8 * k + 4), precision))
                                   .subtract(one.divide(new BigDecimal(8 * k + 5), precision))
                                   .subtract(one.divide(new BigDecimal(8 * k + 6), precision))));
		}
		return pi;
	}

}
