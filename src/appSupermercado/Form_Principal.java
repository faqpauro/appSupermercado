package appSupermercado;

import java.util.ArrayList;



public class Form_Principal {
	
	private ArrayList<Caja> cajasAbiertas;
	
	public Form_Principal() {
		cajasAbiertas = new ArrayList<Caja>();
	}
	
	public ArrayList<Caja> getCajasAbiertas() {
		return cajasAbiertas;
	}

	public void setCajasAbiertas(ArrayList<Caja> cajasAbiertas) {
		this.cajasAbiertas = cajasAbiertas;
	}

	public void abrirCaja(Caja a) {
	
		cajasAbiertas.add(a);
	}
	
	public void cerrarCaja(Caja a) {
		
		if(a.getClientesPorAtender().size() > 0) {
			
		}
		cajasAbiertas.remove(a);
	}
	
	public void agregarCliente(Cliente a) {
		
		Caja cajaMasVacia = this.cajaMasVacia();
		cajaMasVacia.agregarClientePorAtender(a);
	}
	
	
	
	public Caja cajaMasVacia() {
		Caja masVacia = null;
		
		int mayorNumero = 0;
		
        for (int i = 0; i < cajasAbiertas.size(); i++) {
            if (cajasAbiertas.get(i).getClientesPorAtender().size() > mayorNumero) {
            	mayorNumero = cajasAbiertas.get(i).getClientesPorAtender().size();
            }
        }
        
        int menorNumero = mayorNumero;
        
        for (int u = 0; u < cajasAbiertas.size(); u++) {
            if (cajasAbiertas.get(u).getClientesPorAtender().size() <= menorNumero) {
                menorNumero = cajasAbiertas.get(u).getClientesPorAtender().size();
                masVacia = cajasAbiertas.get(u);
                
            }
        }
			
		return masVacia;
	}
	
	public int tamaño() {
		
		return cajasAbiertas.size();
	}
	
	public Caja obtener(int i) {
		
		return cajasAbiertas.get(i);
	}
	
}
