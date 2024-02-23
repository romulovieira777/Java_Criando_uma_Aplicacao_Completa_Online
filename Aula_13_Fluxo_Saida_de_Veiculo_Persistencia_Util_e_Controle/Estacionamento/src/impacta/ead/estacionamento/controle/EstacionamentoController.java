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

    /*
     * A partir dos dados do veiculo informados pelo operador realiza
     * o fluxo de entrada do veículo no estacionamento registrando
     * a movimentação gerada.
     *
     * @param placa Placa do veiculo
     * @param marca Marca do veiculo
     * @param modelo Modelo do veiculo
     * @param cor Cor do veiculo
     * @throws EstacionamentoException Quando o estacionamento estiver lotado
     * @throws VeiculoException Quando a placa estiver incorreta
     */
    public void processarEntrada(String placa, String marca, String modelo, String cor)
            throws EstacionamentoException, VeiculoException {
        //verificar se o estacionamento está lotado
        if (!Vaga.temVagaLivre()){
            throw new EstacionamentoException("Estacionamento lotado!");
        }

        //verificar o padrão de string da placa
        if (!EstacionamentoUtil.validarPadraoPlaca(placa)){
            throw new VeiculoException("Placa informada inválida!");
        }

        //criar uma instancia do veiculo
        Veiculo veiculo = new Veiculo(placa, marca, modelo, cor);

        //criar a movimentação vinculando o veículo e com data de entrada corrente
        Movimentacao movimentacao = new Movimentacao(veiculo, LocalDateTime.now());

        //registrar na base de dados a informação
        DAOEstacionamento dao = new DAOEstacionamento();
        dao.criar(movimentacao);

        //atualizae o número de vagas ocupadas
        Vaga.entrou();

        //fim
    }

    /*
     * A partir de uma placa de veiculo informada, realiza todo o.
     * fluxo de saída de veículo do estacionamento.
     *
     * @param placa Placa do veiculo que estiver saindo.
     * @throws VeiculoException Quando o padrao da placa for invalido.
     * @throws EstacionamentoException Quando o veiculo com a placa informada não é localizado no estacionamento.
     */
    public Movimentacao processarSaida(String placa) throws VeiculoException, EstacionamentoException{
        //validar a placa
        if (!EstacionamentoUtil.validarPadraoPlaca(placa)){
            throw new VeiculoException("Placa inválida!");
        }

        //buscar a movimentação aberta baseada na placa
        DAOEstacionamento dao = new DAOEstacionamento();
        Movimentacao movimentacao = dao.buscarMovimentacaoAberta(placa);

        if (movimentacao == null) {
            throw new EstacionamentoException("Veículo não encontrado!");
        }

        //fazer o calculo do valor a ser pago
        movimentacao.setDataHoraSaida(LocalDateTime.now());
        EstacionamentoUtil.calcularValorPago(movimentacao);

        //atualizar os dados da movimentacao
        dao.atualizar(movimentacao);

        //atualizar status da vaga
        Vaga.saiu();

        return movimentacao;
    }

    public List<Movimentacao> emitirRelatorio(LocalDateTime data){
        //TODO implementar
        return null;
    }

    public int inicializarOcupadas() {
		DAOEstacionamento dao = new DAOEstacionamento();
		return dao.getOcupadas();
	}
}
