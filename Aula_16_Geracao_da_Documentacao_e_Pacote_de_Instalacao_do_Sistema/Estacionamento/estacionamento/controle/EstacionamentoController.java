package impacta.ead.estacionamento.controle;

import impacta.ead.estacionamento.negocio.Movimentacao;
import impacta.ead.estacionamento.negocio.Vaga;
import impacta.ead.estacionamento.negocio.Veiculo;
import impacta.ead.estacionamento.persistencia.DAOEstacionamento;
import impacta.ead.estacionamento.utilitario.EstacionamentoUtil;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Coordena todos os fluxos dos casos de uso do sistema
 *
 * @author Romulo Vieira
 *
 */
public class EstacionamentoController {

	/**
	 * A partir dos dados do veiculo informados pelo operador realiza
	 * o fluxo de entrada do veiculo no estacionamento registrando
	 * a movimentacao gerada.
	 *
	 * @param placa Placa do veiculo
	 * @param marca Marca do veiculo
	 * @param modelo Modelo do veiculo
	 * @param cor Cor do veiculo
	 * @throws EstacionamentoException Quando estacionamento estiver lotado.
	 * @throws VeiculoException Quando o padrao da placa for invalido.
	 */
	public void processarEntrada(String placa, String marca, String modelo, String cor) throws EstacionamentoException,
            VeiculoException {
		//verificar se o estacionamento est� lotado
		if(!Vaga.temVagaLivre()){
			throw new EstacionamentoException("Estacionamento lotado!");
		}

		//verificar o padrao de string da placa
		if(!EstacionamentoUtil.validarPadraoPlaca(placa)){
			throw new VeiculoException("Placa informada inv�lida!");
		}
		//criar uma instancia do veiculo
		Veiculo veiculo = new Veiculo(placa,marca,modelo,cor);
		//criar a movimentacao vinculando o veiculo e com data de entrada corrente
		Movimentacao movimentacao = new Movimentacao(veiculo,LocalDateTime.now());
		//registrar na base de dados a informacao
		DAOEstacionamento dao = new DAOEstacionamento();
		dao.criar(movimentacao);
		//atualizar o numero vagas ocupadas
		Vaga.entrou();
		//fim
	}

	/**
	 * A partir de uma placa de veiculo informada, realiza todo o
	 * fluxo de sa�da de ve�culo do estacionamento
	 *
	 * @param placa Placa do veiculo que estiver saindo
	 *
	 * @return Uma inst�ncia de movimenta��o com dados atualizados de valor e data/hora sa�da
	 * @throws VeiculoException Quando a placa estiver incorreta
	 * @throws EstacionamentoException Quando o veiculo com a placa
	 * informada n�o � localizado no estacionamento.
	 */
	public Movimentacao processarSaida(String placa)
			throws VeiculoException, EstacionamentoException{

		//validar a placa
		if(!EstacionamentoUtil.validarPadraoPlaca(placa)){
			throw new VeiculoException("Placa inv�lida!");
		}

		//Buscar a movimentacao aberta baseada na placa
		DAOEstacionamento dao = new DAOEstacionamento();
		Movimentacao movimentacao = dao.buscarMovimentacaoAberta(placa);

		if(movimentacao == null){
			throw new EstacionamentoException("Ve�culo n�o encontrado!");
		}

		//Fazer o calculo do valor a ser pago
		movimentacao.setDataHoraSaida(LocalDateTime.now());
		EstacionamentoUtil.calcularValorPago(movimentacao);

		//Atualizar os dados da movimentacao
		dao.atualizar(movimentacao);

		//Atualizar o status da vaga
		Vaga.saiu();

		return movimentacao;
	}

	/**
	 * Realiza o fluxo de emiss�o de relat�rio de faturamento
	 * baseado num m�s e ano informados.
	 *
	 * @param data Data (m�s e ano) de emiss�o desejado
	 *
	 * @return Lista de movimenta��es que atendem ao filtro
	 */
	public List<Movimentacao> emitirRelatorio(LocalDateTime data){
		DAOEstacionamento dao = new DAOEstacionamento();
		return dao.consultarMovimentacoes(data);
	}

	public int inicializarOcupadas() {
		DAOEstacionamento dao = new DAOEstacionamento();
		return dao.getOcupadas();
	}
}
