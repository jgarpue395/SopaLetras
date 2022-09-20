package es.jgp.sopaletras;

import java.util.Scanner;

import es.jgp.sopaletras.excepcion.SopaLetrasException;
import es.jgp.sopaletras.tabla.Coordenada;
import es.jgp.sopaletras.tabla.Sentido;
import es.jgp.sopaletras.tabla.Tabla;

public class Main 
{
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Indica el tama\u00f1o del tablero");
		int tamano = scanner.nextInt();
		try 
		{
			Tabla tabla = new Tabla(tamano);
			tabla.generarTablero();
			Coordenada coord = new Coordenada(0, 0, Sentido.NORMAL);
			tabla.colocarPalabraHorizontal("HOLA", coord);
			
			tabla.colocarPalabraVertical("HIERBA", coord);
			
			System.out.println(tabla.toString());
			
			tabla.mostrarResumenDeLetras();
		} 
		catch (SopaLetrasException e) 
		{
			System.out.println(e.getMessage());
		}
		finally 
		{
			scanner.close();
		}
	}
}
