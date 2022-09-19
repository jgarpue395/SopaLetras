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
		Scanner sc = new Scanner(System.in);
		System.out.println("Indica el tama\u00f1o del tablero");
		int n = sc.nextInt();
		try 
		{
			Tabla t = new Tabla(n);
			t.generarTablero();
			Coordenada c = new Coordenada(0, 0, Sentido.NORMAL);
			t.colocarPalabraHorizontal("HOLA", c);
			
			t.colocarPalabraVertical("HIERBA", c);
			System.out.println(t.toString());
		} 
		catch (SopaLetrasException e) 
		{
			System.out.println(e.getMessage());
		}
		finally 
		{
			sc.close();
		}
	}
}
