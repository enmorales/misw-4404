package model.data_structures;

public class PilaEncadenada<T extends Comparable <T>> extends ListaEncadenada<T> 
{
	public void push(T element)
	{
		try {
			this.addLastCola(element);
		} catch (NullException e) {
			e.printStackTrace();
		}
	}
	
	public T pop()
	{
		try 
		{
			return this.removeLastPila();
		} catch (VacioException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public T top()
	{
		return this.lastElement();
	}
}