package es.jgp.sopaletras.tabla;

import java.util.ArrayList;
import java.util.List;

import es.jgp.sopaletras.excepcion.SopaLetrasException;

public class Tabla 
{
	//Atributo - tamaño de la matriz
	private int tamano;
	//Atributo - lista de listas del tablero
	private List<List<Character>> tablero;
	//
	private char letra;
	
	/**
	 * @param tamano asigna el valor de tamaño pasado desde el main
	 * @throws SopaLetrasException llamada a excepcion en caso de que tamano sea 0 o menor
	 */
	public Tabla (int tamano) throws SopaLetrasException
	{
		this.tamano = tamano;
		//comprobamos tamaño y indicamos que salte la excepcion en caso de que sea 0 o menor
		if (tamano <= 0)
		{
			throw new SopaLetrasException("Tama\u00f1o del tablero no valido, debe ser mayor de 0");
		}
		
		//inicializamos el tablero
		this.tablero = new ArrayList<List<Character>>();
	}
	
	//Metodo - genera el tablero y se le asignan * a todas las posiciones
	public void generarTablero()
	{
		//recorremos las distintas dilas de la matriz
		for (int i = 0; i < this.tamano; i++)
		{
			//añadimos cada una de las columnas de la matriz
			this.tablero.add(new ArrayList<Character>());
			//recorremos las columnas
			for (int j = 0; j < this.tamano; j++)
			{
				this.tablero.get(i).add('*');
			}
		}
	}
	
	//verifica que la coordenadas iniciales no se salgan del tablero generado
	private void checkeoPalabra(String palabra, Coordenada coordenada) throws SopaLetrasException 
	{
		if (coordenada.getFila() > tamano)
		{
			throw new SopaLetrasException("No puedes poner una letra fuera del tablero");
		}
		
		if (coordenada.getColumna() > tamano)
		{
			throw new SopaLetrasException("No puedes poner una letra fuera del tablero");
		}
		
		if(palabra == null || palabra.isEmpty()) 
		{
			throw new SopaLetrasException("Palabra nula o vacia");
		}
	}
	
	//metodo que llama a otros metodos para colocar la palabras en horizontal y para hacer comprobaciones previas
	public void colocarPalabraHorizontal(String palabra, Coordenada coordenada) throws SopaLetrasException
	{
		checkeoPalabra(palabra, coordenada);
		//comprueba el sentido de la palabra pasada por parametro
		if(coordenada.getSentido() == Sentido.NORMAL)
		{
			colocarPalabraHorizontalNormal(palabra, coordenada);
		}
		else
		{
			colocarPalabraHorizontalInversa(palabra, coordenada);
		}
	}
	
	private void colocarPalabraHorizontalNormal(String palabra, Coordenada coordenada) throws SopaLetrasException 
	{
		//compruebo que la palabra quepa en el hueco asignado
		if(palabra.length() > this.tamano - coordenada.getColumna())
		{
			throw new SopaLetrasException("La palabra no cabe");
		}
		
		//recorro la palabra y asigno cada letra a un espacio de la matriz
		for (int i = 0; i < palabra.length(); i++)
		{
			letra = palabra.charAt(i);
			
			//compruebo que en el hueco donde quiero asignar la palabra halla un * o que la letra sea igual a la que voy a colocar, en caso de que no sea asi salta una excepcion
			if(!this.tablero.get(coordenada.getFila()).get(coordenada.getColumna()+i).equals('*') && !this.tablero.get(coordenada.getFila()).get(coordenada.getColumna()+i).equals(this.letra))
			{
				throw new SopaLetrasException("Ya existe una letra diferente a la que quieres meter en el hueco");
			}
			else
			{
				this.tablero.get(coordenada.getFila()).set(coordenada.getColumna()+i, this.letra);
			}
		}
	}

	
	private void colocarPalabraHorizontalInversa(String palabra, Coordenada coordenada) throws SopaLetrasException 
	{
		//compruebo que la palabra quepa en el hueco asignado
		if (palabra.length()-1 > coordenada.getColumna())
		{
			throw new SopaLetrasException("La palabra no cabe");
		}
		
		//recorro la palabra y asigno cada letra a un espacio de la matriz
		for (int i = 0; i < palabra.length(); i++)
		{
			letra = palabra.charAt(i);
			
			//compruebo que en el hueco donde quiero asignar la palabra halla un * o que la letra sea igual a la que voy a colocar, en caso de que no sea asi salta una excepcion
			if(!this.tablero.get(coordenada.getFila()).get(coordenada.getColumna()-i).equals('*') && !this.tablero.get(coordenada.getFila()).get(coordenada.getColumna()+i).equals(letra))
			{
				throw new SopaLetrasException("Ya existe una letra diferente a la que quieres meter en el hueco");
			}
			else
			{
				this.tablero.get(coordenada.getFila()).set(coordenada.getColumna()-i, letra);
			}
		}
	}

	//metodo que llama a otros metodos para colocar la palabras en vertical y para hacer comprobaciones previas
	public void colocarPalabraVertical(String palabra, Coordenada coordenada) throws SopaLetrasException
	{
		checkeoPalabra(palabra, coordenada);
		
		//comprueba el sentido de la palabra pasada por parametro
		if (coordenada.getSentido() == Sentido.NORMAL)
		{
			this.colocarPalabraVerticalNormal(palabra, coordenada);
		}
		else
		{
			this.colocarPalabraVerticalInversa(palabra, coordenada);
		}
	}

	private void colocarPalabraVerticalNormal(String palabra, Coordenada coordenada) throws SopaLetrasException 
	{
		//compruebo que la palabra quepa en el hueco asignado
		if(palabra.length() > this.tamano - coordenada.getFila())
		{
			throw new SopaLetrasException("La palabra no cabe");
		}
		
		//compruebo que en el hueco donde quiero asignar la palabra halla un * o que la letra sea igual a la que voy a colocar, en caso de que no sea asi salta una excepcion
		for (int i = 0; i < palabra.length(); i++)
		{
			this.letra = palabra.charAt(i);
			
			if (!this.tablero.get(coordenada.getFila()+i).get(coordenada.getColumna()).equals('*') && !this.tablero.get(coordenada.getFila()+i).get(coordenada.getColumna()).equals(this.letra))
			{
				throw new SopaLetrasException("Ya existe una letra diferente a la que quieres meter en el hueco");
			}
			
			this.tablero.get(coordenada.getFila()+i).set(coordenada.getColumna(), this.letra);
		}
	}

	private void colocarPalabraVerticalInversa(String palabra, Coordenada coordenada) throws SopaLetrasException 
	{
		//compruebo que la palabra quepa en el hueco asignado
		if(palabra.length()-1 > coordenada.getFila())
		{
			throw new SopaLetrasException("La palabra no cabe");
		}
		
		//recorro la palabra y asigno cada letra a un espacio de la matriz
		for(int i = 0; i < palabra.length(); i++)
		{
			this.letra = palabra.charAt(i);
			
			//compruebo que en el hueco donde quiero asignar la palabra halla un * o que la letra sea igual a la que voy a colocar, en caso de que no sea asi salta una excepcion
			if(!this.tablero.get(coordenada.getFila()-i).get(coordenada.getColumna()).equals('*') && !this.tablero.get(coordenada.getFila()-i).get(coordenada.getColumna()).equals(this.letra))
			{
				throw new SopaLetrasException("Ya existe una letra diferente a la que quieres meter en el hueco");
			}
			else
			{
				this.tablero.get(coordenada.getFila()-i).set(coordenada.getColumna(), this.letra);
			}
		}
	}

	//guardo en una variable todas las celdas de la matriz
	@Override
	public String toString() 
	{
		String letras = "";
		for (int i = 0; i < this.tamano; i++)
		{
			for (int j = 0; j < this.tamano; j++)
			{
				letras += this.tablero.get(i).get(j) + " ";
			}
			letras += "\n";
		}
		return letras;
	}

	public int getTamano() 
	{
		return this.tamano;
	}
	
}
