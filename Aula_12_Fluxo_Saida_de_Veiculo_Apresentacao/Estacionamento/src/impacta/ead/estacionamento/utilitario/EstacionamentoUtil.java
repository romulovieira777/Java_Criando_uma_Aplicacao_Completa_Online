package impacta.ead.estacionamento.utilitario;

import impacta.ead.estacionamento.negocio.Movimentacao;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
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
        String padrao = "[A-Z][A-Z][A-Z]-\\d\\d\\d\\d";
        Pattern p = Pattern.compile(padrao);
        Matcher m = p.matcher(placa);

        return m.matches();
    }

    public void calcularPagamento(Movimentacao movimentacao) {
        //TODO implementar
    }

    /*
     * Recupera uma propriedade do arquivo de configuração da aplicação
     * configuration.txt
     *
     * @param propriedade
     *
     * @return valor associado a propriedade
     */

    public static String get(String propriedade) {
        Properties prop = new Properties();
        String valor = null;
        try {
            prop.load(EstacionamentoUtil.class.getResourceAsStream("/recursos/configuration.txt"));
            valor = prop.getProperty(propriedade);
        } catch (IOException e) {
            assert false : "Configuração não carregada";
        }

        return valor;
    }

    public static String getDataAsString(LocalDateTime dataHoraEntrada) {
        return dataHoraEntrada.toString();
    }
}
