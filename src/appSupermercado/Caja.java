package appSupermercado;

import java.util.ArrayList;

public class Caja {
	
	private String nombreCaja;
	private boolean estaAbierta;
    
	private ArrayList<Cliente> clientesAtendidos;
	private ArrayList<Cliente> clientesPorAtender;
    
    public Caja(String n) {
    	nombreCaja = n;
    	estaAbierta = true;
    	clientesAtendidos = new ArrayList<Cliente>();
    	clientesPorAtender = new ArrayList<Cliente>();
    	
    }
    
	
	public String getNombreCaja() {
		return nombreCaja;
	}



	public void setNombreCaja(String nombreCaja) {
		this.nombreCaja = nombreCaja;
	}



	public boolean isEstaAbierta() {
		return estaAbierta;
	}



	public void setEstaAbierta(boolean estaAbierta) {
		this.estaAbierta = estaAbierta;
	}


	public ArrayList<Cliente> getClientesAtendidos() {
		return clientesAtendidos;
	}


	public void setClientesAtendidos(ArrayList<Cliente> clientesAtendidos) {
		this.clientesAtendidos = clientesAtendidos;
	}


	public ArrayList<Cliente> getClientesPorAtender() {
		return clientesPorAtender;
	}


	public void setClientesPorAtender(ArrayList<Cliente> clientesPorAtender) {
		this.clientesPorAtender = clientesPorAtender;
	}

	public void agregarClientePorAtender(Cliente a) {
		clientesPorAtender.add(a);
	}
	
	
	
	public void atenderCliente(int c) {
			Cliente clienteAtendido = clientesPorAtender.remove(c);
			clientesAtendidos.add(clienteAtendido);
		
	}

	

	
	

}
