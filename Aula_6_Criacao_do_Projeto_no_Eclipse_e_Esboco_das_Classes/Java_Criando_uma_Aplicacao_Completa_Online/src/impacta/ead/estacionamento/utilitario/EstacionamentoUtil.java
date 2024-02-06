package impacta.ead.estacionamento.utilitario;

import impacta.ead.estacionamento.negocio.Movimentacao;

/*
* Representa uma classe de apoio às demais do sistema.
*
* @Author: Romulo Vieira
 */
public class EstacionamentoUtil {
    /*
    * Valida a placa com o padrão LLL-NNNN
    * L = Letra
    * N = Número
    *
    * @param placa Placa do veículo
    * @return true se atender o padrão e false se não
     */
    public boolean validarPadraoPlaca(String placa) {
        //TODO implementar
        return false;
    }

    /*
    * o calculo do valor da estada do veiculo baseado no tarifario e na hora de entrada e saída do veiculo
    *
    * Altera a propria instancia do parametro
    *
    *   @param movimentacao Instancia da movimentacao
     */
    public void calcularPagamento(Movimentacao movimentacao) {
        //TODO implementar
    }
}
