package calc.visao;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import calc.modelo.memoria;

@SuppressWarnings("serial")
public class teclado extends JPanel implements ActionListener {

	//private final Color COR_CINZA_CLARO = new Color(99, 99, 99);
	private final Color COR_BOTAO_RESULTADO = new Color(135, 206, 250);
	//private final Color COR_PRETO = new Color(0, 0, 0);
	private final Color COR_BOTAO_PADRAO = Color.WHITE;
	
	//Construtor
	public teclado() {
		//Define cor do fundo teclado
		setBackground(Color.WHITE);
		
		setLayout(new GridLayout(5, 4));
		
		adicionarBotao("CE", COR_BOTAO_PADRAO);
		adicionarBotao("C", COR_BOTAO_PADRAO);
		adicionarBotao("DEL", COR_BOTAO_PADRAO);
		adicionarBotao("/", COR_BOTAO_PADRAO);
		
		adicionarBotao("7", COR_BOTAO_PADRAO);
		adicionarBotao("8", COR_BOTAO_PADRAO);
		adicionarBotao("9", COR_BOTAO_PADRAO);
		adicionarBotao("x", COR_BOTAO_PADRAO);
		
		adicionarBotao("4", COR_BOTAO_PADRAO);
		adicionarBotao("5", COR_BOTAO_PADRAO);
		adicionarBotao("6", COR_BOTAO_PADRAO);
		adicionarBotao("-", COR_BOTAO_PADRAO);
		
		adicionarBotao("1", COR_BOTAO_PADRAO);
		adicionarBotao("2", COR_BOTAO_PADRAO);
		adicionarBotao("3", COR_BOTAO_PADRAO);
		adicionarBotao("+", COR_BOTAO_PADRAO);
		
		adicionarBotao("+/-", COR_BOTAO_PADRAO);
		adicionarBotao("0", COR_BOTAO_PADRAO);
		adicionarBotao(",", COR_BOTAO_PADRAO);
		adicionarBotao("=", COR_BOTAO_RESULTADO);
	}
	
	//Metodo adicionar botao
	private void adicionarBotao(String texto, Color cor) {
		//Instanciando classe botao
		botao botao = new botao(texto, cor);
		
		//
		botao.addActionListener(this);
		
		//Adicionando botao no label
		add(botao);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton botao = (JButton) e.getSource();
			memoria.getInstancia().processarComando(botao.getText());
		}
	}
}
