package impacta.ead.estacionamento.utilitario;

import impacta.ead.estacionamento.negocio.Movimentacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static boolean validarPadraoPlaca(String placa) {
        String padrao = "[A_Z][A_Z][A_Z]-\\d\\d\\d\\d";
        Pattern p = Pattern.compile(padrao);
        Matcher m = p.matcher(placa);

        return m.matches();
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
