package model.data_structures;

public class ListaEncadenada <T extends Comparable <T>> implements ILista<T>{

	private Nodo<T> first;
	
	private int size;
	
	private Nodo<T> last;
	
	public ListaEncadenada()
	{
		first=null;
		last=null;
		size=0;
	}
	
	public ListaEncadenada(T element)
	{
		first= new Nodo<T>(element);
		last= first;
		size=1;
	}
	
	public void addFirst(T element)
	{
		Nodo<T> actual= new Nodo<T>(element);
		actual.setNext(first);
		first=actual;
	}
	
	public void addLast(T element)
	{
		Nodo<T> actual= new Nodo<T>(element);
		last.setNext(actual);
		last=actual;
		actual.setNext(null);
		
	}
	
	public void addLastCola(T element) throws NullException
	{

		 if (element==null)
		 {
			 throw new NullException("No es válido el elemento ingresado");
		 }
		 
		else 
		{
			if (first==null)
			{
				 Nodo<T> actual= new Nodo<T>(element);
				 last=actual;
				 first=actual;
			}
			else
			{
				Nodo<T> actual= new Nodo<T>(element);
				last.setNext(actual);
				last=actual;
				actual.setNext(null);
			}
			size++;
		}
	}
	
	public void insertElement(T elemento, int pos) throws PosException, NullException {
	    validatePosition(pos);
	    validateElement(elemento);

	    Nodo<T> nuevo = new Nodo<>(elemento);

	    if (isEmpty()) {
	        insertFirstElement(nuevo);
	    } else if (pos == 1) {
	        addFirst(elemento);
	    } else if (pos == size + 1) {
	        addLast(elemento);
	    } else {
	        insertElementAtPosition(nuevo, pos);
	    }

	    size++;
	}

	private void validatePosition(int pos) throws PosException {
	    if (pos < 1 || pos - 1 > size) {
	        throw new PosException("La posición no es válida");
	    }
	}

	private void validateElement(T elemento) throws NullException {
	    if (elemento == null) {
	        throw new NullException("No es válido el elemento ingresado");
	    }
	}

	private void insertFirstElement(Nodo<T> nuevo) {
	    first = nuevo;
	    last = first;
	}

	private void insertElementAtPosition(Nodo<T> nuevo, int pos) {
	    Nodo<T> actual = first;
	    for (int i = 0; i < pos - 2; i++) {
	        actual = actual.getNext();
	    }
	    nuevo.setNext(actual.getNext());
	    actual.setNext(nuevo);
	}

	public T removeFirst() throws VacioException
	{
		T primero= firstElement();
		if (first!=null)
		{
			first=first.getNext();
		}
	
		return primero;
		
	}
	
	public T removeLast()
	{
		Nodo<T> penultimo= first;
		while(penultimo.getNext().getNext()!=null)
		{
			penultimo=penultimo.getNext();
		}
		Nodo<T> ultimo= penultimo.getNext();
		
		penultimo.disconnectNext(penultimo);
		last=penultimo;
		
		return ultimo.getInfo();
		
	}
	
	public T removeLastPila() throws VacioException
	{
		Nodo<T> ultimo=null;
		if (isEmpty())
		{
			 throw new VacioException("La lista está vacía");
		}
		else if(first.getNext()!=null)
		{
			if(first.getNext().getNext()!=null)
			{
				Nodo<T> penultimo= first;
				
				while(penultimo.getNext().getNext()!=null)
				{
					penultimo=penultimo.getNext();
				}
				ultimo= penultimo.getNext();
				
				penultimo.disconnectNext(penultimo);
				last=penultimo;
				
				size--;
			}
			else
			{
				Nodo<T> penultimo= first;
				ultimo= penultimo.getNext();
				penultimo.disconnectNext(penultimo);
				last=penultimo;
				size--;
				
			}
			
		}
		else
		{
			ultimo= first;
			first=null;
		}
		
		return ultimo.getInfo();
		
		
	}
	
	public T deleteElement(int pos) throws PosException, VacioException {
	    validatePosition(pos);
	    validateNotEmpty();

	    T retorno = null;

	    if (pos == 1) {
	        retorno = removeFirst();
	    } else if (pos == size()) {
	        retorno = removeLast();
	    } else {
	        retorno = deleteElementAtPosition(pos);
	    }

	    size--;

	    return retorno;
	}

	private void validateNotEmpty() throws VacioException {
	    if (isEmpty()) {
	        throw new VacioException("La lista está vacía");
	    }
	}

	private T deleteElementAtPosition(int pos)throws PosException, VacioException {
	    Nodo<T> actual = first;
	    Nodo<T> anterior = null;

	    while (actual.getNext() != null && !actual.getInfo().equals(getElement(pos - 1))) {
	        anterior = actual;
	        actual = actual.getNext();
	    }

	    T retorno = actual.getInfo();
	    anterior.disconnectNext(anterior);

	    return retorno;
	}
	
	public T firstElement() throws VacioException
	{
		if (isEmpty())
		{
			throw new VacioException("La lista está vacía");
		}
		else
		{
			return first.getInfo();
		}
	}
	
	public T lastElement()
	{
		if (isEmpty())
		{
			return null;
		}
		else
		{
			return last.getInfo();
		} 
		
	}
	
	public T getElement(int pos) throws PosException, VacioException
	{
		if (pos<1 || pos >size)
		{
			 throw new PosException("La posición no es válida");
		}
		else if(isEmpty())
		{
			throw new VacioException("La lista está vacía");
		}
		else
		{
			Nodo<T> actual= first;
			
			for(int i=0; i<pos-1;i++)
			{
				actual=actual.getNext();
			}
			return actual.getInfo();
		}
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return first==null;
	}
	
	public int isPresent(T element) throws VacioException, NullException, PosException
	{
		int pos =-1;
		if (element ==null)
		{
			throw new NullException("No es válido el elemento ingresado");
		}
		else if (isEmpty())
		{
			throw new VacioException("La lista está vacía");
		}
		else
		{
			boolean end=false;
			for(int i =0; i<size &&!end;i++)
			{
				if(getElement(i).equals(element))
				{
					pos=i;
					end=true;
				}
			}
		}

		return pos+1;
	}
	
	public void exchange(int pos1, int pos2) throws PosException, VacioException
	{
		 if (pos1>size|| pos2>size || pos1<1 || pos2<1)
		 {
			 throw new PosException("La posición no es válida");
		 }
		 else if(isEmpty())
		 {
			 throw new VacioException("La lista está vacía");
		 }
		 else if ( pos1!=pos2 && size>1)
		{
			
			Nodo<T> actual1= first;
			
			while(actual1.getNext()!=null && !actual1.getInfo().equals(getElement(pos1)))
			{
				actual1=actual1.getNext();
			}
			
			Nodo<T> actual2= first;
			
			while(actual2.getNext()!=null && !actual2.getInfo().equals(getElement(pos2)))
			{
				actual2=actual2.getNext();
			}
			
			Nodo<T> cambiado= actual1;
			actual1.change(actual2.getInfo());
			actual2.change(cambiado.getInfo());

		}
	}
	
	public void changeInfo(int pos, T element) throws PosException, VacioException, NullException
	{
		if (pos<1 || pos >size)
		{
			 throw new PosException("La posición no es válida");
		}
		else if (isEmpty())
		{
			throw new VacioException("La lista está vacía");
		}
		else if(element==null)
		{
			throw new NullException("No es válido el elemento ingresado");
		}
		else
		{
			Nodo<T> actual= first;
			for(int i=0; i<pos-1;i++)
			{
				actual=actual.getNext();
			}
			
			actual.change(element);
			 
		}

	}
	
	public ILista<T> sublista(int pos, int numElementos) throws PosException, VacioException, NullException
	{
		if (isEmpty())
		{
			throw new VacioException("La lista está vacía");
		}
		else if (numElementos<0)
		{
			throw new PosException("La cantidad de elementos no es válida");
		}
		else if (numElementos >= size())
		{
			return this;
		}
		else
		{
			ILista<T> copia= new ListaEncadenada();
			
			int contador=pos;
			for(int i=0; i<numElementos; i++)
			{
				copia.insertElement(this.getElement(contador), i+1);
				contador++;
			}
			
			return copia;
		}
		
	}

	@Override
	public int compareTo(ILista o) {
		return 0;
	}
}
