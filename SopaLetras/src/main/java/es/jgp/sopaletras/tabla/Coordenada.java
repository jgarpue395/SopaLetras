package es.jgp.sopaletras.tabla;

import es.jgp.sopaletras.excepcion.SopaLetrasException;

public class Coordenada 
{
	private final int fila;
	
	private final int columna;
	
	private final Sentido sentido;
	
	public Coordenada(int fila, int columna, Sentido sentido) throws SopaLetrasException 
	{
		if(fila < 0)
		{
			throw new SopaLetrasException("No puedes poner una letra fuera del tablero");
		}
		
		if(columna < 0)
		{
			throw new SopaLetrasException("No puedes poner una letra fuera del tablero");
		}
		
		if(sentido == null)
		{
			throw new SopaLetrasException("En que direccion quieres escribir la palabra");
		} 
		
		this.fila = fila;
		
		this.columna = columna;
		
		this.sentido = sentido;
	}

	protected int getFila() 
	{
		return this.fila;
	}

	protected int getColumna() 
	{
		return this.columna;
	}

	protected Sentido getSentido() 
	{
		return this.sentido;
	}
}
