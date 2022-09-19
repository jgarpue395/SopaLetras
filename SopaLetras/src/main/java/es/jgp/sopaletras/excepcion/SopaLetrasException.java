package es.jgp.sopaletras.excepcion;

public class SopaLetrasException extends Exception
{
private static final long serialVersionUID = 1L;
	
	public SopaLetrasException()
	{
		super();
	}
	
	public SopaLetrasException(String mensaje)
	{
		super(mensaje);
	}
}
