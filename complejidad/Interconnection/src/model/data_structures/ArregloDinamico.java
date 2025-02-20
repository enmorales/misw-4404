package model.data_structures;

import java.util.Arrays;

public class ArregloDinamico<T extends Comparable<T>> implements ILista<T> {
		/**
		 * Capacidad maxima del arreglo
		 */
        private int tamanoMax;
		/**
		 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
		 */
        private int tamanoAct;
        /**
         * Arreglo de elementos de tamaNo maximo
         */
        private T elementos[];

        /**
         * Construir un arreglo con la capacidad maxima inicial.
         * @param max Capacidad maxima inicial
         */
		public ArregloDinamico( int max )
        {
               elementos = (T[]) new Comparable[max];
               tamanoMax = max;
               tamanoAct = 0;
        }
		
		public void addLast( T dato )
        {
               if ( tamanoAct == tamanoMax )
               {
                    tamanoMax = 2 * tamanoMax;
                    T [ ] copia = elementos;
                    elementos = (T[])new Comparable[tamanoMax];
                    for ( int i = 0; i < tamanoAct; i++)
                    {
                     	 elementos[i] = copia[i];
                    } 
               }	
               elementos[tamanoAct] = dato;
       }

		public int darCapacidad() {
			return tamanoMax;
		}

		public int size() {
			return tamanoAct;
		}

		public T getElement(int pos) throws PosException, VacioException 
		{
			if (pos<1)
			{
				 throw new PosException("La posición no es válida");
			}
			if(pos > tamanoMax)
			{
				throw new PosException("La posición no es válida");
			}
			else if(isEmpty())
			{
				throw new VacioException("La lista está vacía");
			}
			else
			{
				if (elementos[pos-1]==null)
				{
					return null;
				}
				else
				{
					return elementos[pos-1];
				}
			}
		}

		public T buscar(T dato) {
			T elemento=null;
			boolean ya=false;
			for (int i=0; i<tamanoAct && !ya; i++)
			{

				if (elementos[i].compareTo(dato)==0)
				{
					
					elemento=elementos[i];
					ya=true;
				}
				
			}
			
			return elemento;
			
		}

		public T deleteElement(T dato) throws VacioException, NullException
		{			
			T elemento=null;			

			 if (isEmpty())
			 {
				 throw new VacioException("La lista está vacía");
			 }
			 else if (dato==null)
			 {
				 throw new NullException("No es válido el elemento ingresado");
			 }
			 else
			 {
				boolean ya=false;
	
				T [ ] copia = elementos;
				elementos= (T[])new Comparable[tamanoMax];
			
				for (int i=0; i< copia.length && !ya; i++)
				{
	
					if (copia[i].compareTo(dato)==0)
					{
						for (int j = i; j < copia.length - 1; j++) 
						{
		                    elementos[j] = copia[j+1];
		                }
						
						elemento=copia[i];
						ya=true;
						tamanoAct=tamanoAct-1;
					}
					else
					{
						elementos[i]=copia[i];
					}
				}
			 }
			
			return elemento;			
		}

		@Override
		public void addFirst(T element) 
		{
			if(tamanoAct>0)
			{
				if ( tamanoAct == tamanoMax )
	            {
	                 tamanoMax = 2 * tamanoMax; 
	            }
	            T [ ] copia = elementos;
	            elementos = (T[])new Comparable[tamanoMax];
	                 
	            elementos[0]=element;
	            for ( int i = 0; i < tamanoAct; i++)
	            {
	                 elementos[i+1] = copia[i];
	            } 
			}
			else
			{
				elementos[0]=element;
			}			
		}

		@Override
		public void insertElement(T elemento, int pos) throws PosException, NullException {
		    if (pos < 1 || pos > tamanoMax + 1) {
		        throw new PosException("La posición no es válida");
		    }

		    if (pos == 1) {
		        addFirst(elemento);
		    } else if (pos == tamanoAct + 1) {
		        addLast(elemento);
		    } else {
		        if (tamanoAct == tamanoMax) {
		            resizeArray();
		        }

		        T[] copia = Arrays.copyOf(elementos, tamanoMax);
		        
		        System.arraycopy(copia, 0, elementos, 0, pos - 1);
		        elementos[pos - 1] = elemento;
		        System.arraycopy(copia, pos - 1, elementos, pos, tamanoAct - pos + 1);
		        
		        tamanoAct++;
		    }
		}

		private void resizeArray() {
		    tamanoMax *= 2;
		    elementos = Arrays.copyOf(elementos, tamanoMax);
		}

		@Override
		public T removeFirst() 
		{
			T elemento=elementos[0];

			T [ ] copia = elementos;
			elementos= (T[])new Comparable[tamanoMax];


			for (int i=0; i<tamanoAct-1; i++)
			{
				elementos[i]=copia[i+1];
			}
			
			tamanoAct--;
			return elemento;
		}

		@Override
		public T removeLast() 
		{
			T elemento=elementos[tamanoAct-1];
			elementos[tamanoAct-1]=null;
			tamanoAct--;
			return elemento;
		}

		@Override
		public T deleteElement(int pos) throws PosException, VacioException {
		    if (pos < 1 || pos > tamanoAct) {
		        throw new PosException("La posición no es válida");
		    }

		    if (isEmpty()) {
		        throw new VacioException("La lista está vacía");
		    }

		    T elemento = elementos[pos - 1];

		    if (pos == 1) {
		        removeFirst();
		    } else if (pos == tamanoAct) {
		        removeLast();
		    } else {
		        System.arraycopy(elementos, pos, elementos, pos - 1, tamanoAct - pos);
		        tamanoAct--;
		    }

		    return elemento;
		}

		@Override
		public T firstElement() throws VacioException 
		{
			T retorno=null;
			if (tamanoAct==0)
			{
				 throw new VacioException("La lista está vacía");
			}
			else
			{
				retorno= elementos[0];
			}
			
			return retorno;
		}

		@Override
		public T lastElement() throws VacioException 
		{
			if (tamanoAct==0)
			{
				 throw new VacioException("La lista está vacía");
			}
			else
			{
				return elementos[tamanoAct-1];
			}
			
		}

		@Override
		public boolean isEmpty() 
		{
			return tamanoAct<0;
		}

		@Override
		public int isPresent(T element) throws NullException, VacioException 
		{
			int pos=-1;
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
				boolean ya=false;
				for (int i=0; i<tamanoAct && !ya; i++)
				{
					if (elementos[i].compareTo(element)==0)
					{	
						pos=i;
						ya=true;
					}
					
				}
			}

			return pos+1;
		}

		@Override
		public void exchange(int pos1, int pos2) throws PosException, VacioException {
		    if (pos1 < 1 || pos1 > tamanoAct || pos2 < 1 || pos2 > tamanoAct) {
		        throw new PosException("La posición no es válida");
		    }

		    if (isEmpty()) {
		        throw new VacioException("La lista está vacía");
		    }

		    if (pos1 != pos2 && tamanoAct > 1) {
		        T elemento1 = elementos[pos1 - 1];
		        T elemento2 = elementos[pos2 - 1];

		        elementos[pos1 - 1] = elemento2;
		        elementos[pos2 - 1] = elemento1;
		    }
		}
		
		@Override
		public void changeInfo(int pos, T element) throws PosException, VacioException, NullException 
		{
			if (pos<1 || pos >tamanoMax)
			{
				 throw new PosException("La posición no es válida");
			}
			else if (pos >tamanoMax)
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
				elementos[pos-1]=element;
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
				ILista<T> copia= new ArregloDinamico(numElementos);
				
				int contador=pos;
				for (int i=0; i<numElementos; i++)
				{
					T elemento= this.getElement(contador);
					copia.insertElement(elemento,i+1);
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
