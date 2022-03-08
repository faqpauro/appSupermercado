package appSupermercado;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class Grafico extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private int acum;
	
	Form_Principal cjs = new Form_Principal();
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grafico frame = new Grafico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Grafico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{104, 79, 87, 105, 105, 0};
		gbl_panel.rowHeights = new int[]{30, 23, 30, 14, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnCerrarCaja = new JButton("Cerrar caja");
		btnCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					int filaSeleccionada = table.getSelectedRow();
					
					if(cjs.obtener(filaSeleccionada).getClientesPorAtender().size() > 0){
						Caja cajaMasVac = cjs.cajaMasVacia();
						for(int i = 0; i < cjs.obtener(filaSeleccionada).getClientesPorAtender().size(); i++) {
							cajaMasVac.agregarClientePorAtender(cjs.obtener(filaSeleccionada).getClientesPorAtender().get(i));
						}
						
					}
					
					if(filaSeleccionada >= 0) {
					cjs.cerrarCaja(cjs.obtener(filaSeleccionada));
					model.removeRow(filaSeleccionada);
					}
					
				    } catch (Exception IndexOutOfBoundsException) {
				    	JOptionPane.showMessageDialog(table, "No has seleccionado ninguna fila");
				    }
				
				
				
				
				
			}
		});
		
		JButton btnAbrirCaja = new JButton("Abrir caja");
		btnAbrirCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acum++;
				Caja nuevaCaja = new Caja("Caja Abierta " + acum);
				cjs.abrirCaja(nuevaCaja);
				
				model.setRowCount(0);
				for(int i = 0; i < cjs.tamaño(); i++) {
					
					Object[] fila= {
							cjs.obtener(i).getNombreCaja(),
							cjs.obtener(i).getClientesAtendidos().size(),
							cjs.obtener(i).getClientesPorAtender().size()
					};
					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
				    table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				    table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				    table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);	
				model.addRow(fila);
				}
				
			}
		});
		GridBagConstraints gbc_btnAbrirCaja = new GridBagConstraints();
		gbc_btnAbrirCaja.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAbrirCaja.insets = new Insets(0, 0, 5, 5);
		gbc_btnAbrirCaja.gridx = 1;
		gbc_btnAbrirCaja.gridy = 1;
		panel.add(btnAbrirCaja, gbc_btnAbrirCaja);
		GridBagConstraints gbc_btnCerrarCaja = new GridBagConstraints();
		gbc_btnCerrarCaja.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCerrarCaja.insets = new Insets(0, 0, 5, 5);
		gbc_btnCerrarCaja.gridx = 2;
		gbc_btnCerrarCaja.gridy = 1;
		panel.add(btnCerrarCaja, gbc_btnCerrarCaja);
		
		JButton btnNewButton_3 = new JButton("Atender cliente");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int filaSeleccionada = table.getSelectedRow();
					
					if(cjs.obtener(filaSeleccionada).getClientesPorAtender().size() > 0) {
						cjs.obtener(filaSeleccionada).atenderCliente(0);
						
						
					}else {
						JOptionPane.showMessageDialog(table, "No hay clientes por atender en esta caja");
					}
				    } catch (Exception IndexOutOfBoundsException) {
				    	JOptionPane.showMessageDialog(table, "No has seleccionado ninguna fila");
				    }
				
				
			}
		});
		
		JButton btnAgregarCliente = new JButton("Agregar cliente");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(cjs.getCajasAbiertas().size() > 0) {
						cjs.agregarCliente(new Cliente(JOptionPane.showInputDialog(table,"Ingresar nombre del Cliente")));
					} else {
						JOptionPane.showMessageDialog(table, "No hay cajas abiertas");
					}
														
				    } catch (Exception NullPointerException) {
				    	JOptionPane.showMessageDialog(table, "Ha ocurrido un error, intente nuevamente");
				    }
				
				
								
	           
			}
		});
		GridBagConstraints gbc_btnAgregarCliente = new GridBagConstraints();
		gbc_btnAgregarCliente.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAgregarCliente.insets = new Insets(0, 0, 5, 5);
		gbc_btnAgregarCliente.gridx = 3;
		gbc_btnAgregarCliente.gridy = 1;
		panel.add(btnAgregarCliente, gbc_btnAgregarCliente);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 4;
		gbc_btnNewButton_3.gridy = 1;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Abrir nueva caja para actualizar tabla luego de agregar o atender cliente");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model.addColumn("Cajas Abiertas");
		model.addColumn("Clientes Atendidos");
		model.addColumn("Clientes por Atender");
		
		table.setModel(model);
	}

}
