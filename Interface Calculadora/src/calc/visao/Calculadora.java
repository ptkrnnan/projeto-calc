package calc.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Calculadora extends JFrame  {
	//Construtor
	public Calculadora() {
		//Inicia a "interface/janela" da app
		setVisible(true);
		
		//Adiciona titulo na barra da app
		setTitle("Calculadora");
		
		//Define o tamanho da interface 'largura, altura'
		setSize(337, 393);
		
		//Centraliza a "interface/janela"
		setLocationRelativeTo(null);
				
		//Fecha a "interface/janela" da app
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Chamando metodo para gerenciar layout
		gerenciarLayout();
		
		//Da um refresh na interface atualizando novas aplicaçoes
		SwingUtilities.updateComponentTreeUI(this); //Chamando o setVisible por ultimo nao preciso desse codigo
	}
	
	private void gerenciarLayout() {
		//Instanciando gerenciador de layout 'borderlayout' 
		setLayout(new BorderLayout());
		
		//Instanciando os JPANEL da calculadora
		display display = new display();
		teclado teclado = new teclado();
		//cabecalho cabecalho = new cabecalho();
		
		//Adicionando os JPANEL ao layout  
		add(display, BorderLayout.NORTH);
		add(teclado, BorderLayout.CENTER);
		
		//Definindo o tamanho dos JPANEL
		display.setPreferredSize(new Dimension(337, 70));
	}

	public static void main(String[] args) {
		//Chamando o construtor
		new Calculadora();
	}
}
