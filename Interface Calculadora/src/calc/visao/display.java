package calc.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import calc.modelo.memoria;
import calc.modelo.memoriaObserver;

@SuppressWarnings("serial")
public class display extends JPanel implements memoriaObserver {

	// Instanciando label display
	private final JLabel label_display = new JLabel(memoria.getInstancia().getTextoAtual()); 
	
	//Construtor
	public display() {
		memoria.getInstancia().addObserver(this);
		
		//Define cor do fundo display
		 setBackground(Color.WHITE);
		
		//Definindo cor label display
		label_display.setForeground(Color.BLACK);
		
		//Definindo font label display
		label_display.setFont(new Font("", Font.BOLD, 40));
		
		//Direcionando label display com flowlayout 'direção, hgap, vgap'
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 15));
		
		//Adicionando label no display
		add(label_display);
	}
	
	public void valorAlterado(String novoValor) {
		label_display.setText(novoValor);
	}
}
