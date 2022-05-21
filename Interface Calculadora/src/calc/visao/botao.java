package calc.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class botao extends JButton{

	//Construtor
	public botao(String texto_botao, Color cor_botao) {
		//Define o texto
		setText(texto_botao);
		
		//Define cor do texto 'PADRAO'
		setForeground(Color.BLACK);
		
		//Define cor do botao
		setOpaque(true); //Comando possibilita a troca de cor
		setBackground(cor_botao);
		
		//Define a font do texto 
		setFont(new Font("", Font.PLAIN, 18));
		
		//Desabilita o contorno dos numero no botao
		setFocusable(false);
	}
}
