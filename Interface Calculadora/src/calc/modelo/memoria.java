package calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class memoria {
	
	//ESTUDAR ESSE CODIGO
	//TRATAR O ERRO DIVISAO POR ZERO
	
	private enum TipoComando {
		LIMPAR ,ZERAR, APAGAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA, MAISEMENOS;
	};

	private static final memoria instancia = new memoria();
	
	private final List<memoriaObserver> observadores = new ArrayList<>();
	
	private TipoComando ultimaOperacao = null;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";
	
	private memoria() {
	}
	
	public static memoria getInstancia() {
		return instancia;
	}
	
	public void addObserver(memoriaObserver observador) {
		observadores.add(observador);
	}
	
	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}
	
	public void processarComando(String texto) {
		
		TipoComando tipocomando = detectarTipoComando(texto);
		
		if(tipocomando == null) {
			return;
		} else if(tipocomando == TipoComando.LIMPAR) {
			textoAtual = "";
		} else if(tipocomando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		} else if(tipocomando == TipoComando.APAGAR) {
			textoAtual = textoAtual.replaceFirst(".$", "");
		} else if(tipocomando == TipoComando.NUMERO || tipocomando == TipoComando.VIRGULA) {
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		} else if(tipocomando == TipoComando.MAISEMENOS && textoAtual.contains("-")) {
			textoAtual = textoAtual.substring(1);
		} else if(tipocomando == TipoComando.MAISEMENOS && !textoAtual.contains("-")) {
			textoAtual = "-" + textoAtual;
		} else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = tipocomando;
		}
		
		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String obterResultadoOperacao() {
		if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		
		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
		
		double resultado = 0;
		
		if(ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		} else if(ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		} else if(ultimaOperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		} else if(ultimaOperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;				
		}
		
		String texto = Double.toString(resultado).replace(".", ",");
		boolean inteiro = texto.endsWith(",0");
		
		return inteiro ? texto.replace(",0", "") : texto;
	}

	private TipoComando detectarTipoComando(String texto) {
		
		if(textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		
		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;			
		} catch (NumberFormatException e) {
			if("CE".equals(texto)) {
				return TipoComando.LIMPAR;
			} else if("C".equals(texto)) {
				return TipoComando.ZERAR;
			} else if("DEL".equals(texto)) {
				return TipoComando.APAGAR;
			} else if("/".equals(texto)) {
				return TipoComando.DIV;
			} else if("x".equals(texto)) {
				return TipoComando.MULT;
			} else if("-".equals(texto)) {
				return TipoComando.SUB;
			} else if("+".equals(texto)) {
				return TipoComando.SOMA;
			} else if("=".equals(texto)) {
				return TipoComando.IGUAL;
			} else if(",".equals(texto) && !textoAtual.contains(",")) {
				return TipoComando.VIRGULA;
			} else if("+/-".equals(texto)) {
				return TipoComando.MAISEMENOS;
			}
		}
		return null;
	}
}
