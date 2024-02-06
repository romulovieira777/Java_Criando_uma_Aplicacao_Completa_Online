package impacta.ead.estacionamento.negocio;

import java.time.LocalDateTime;

/*
* Representa o fluxo do veículo dentro do estacionamento, ou seja, ele contém as informações de entrada e saída do
* veículo e o valor pago na estada.
*
* @Author: Romulo Vieira
 */
public class Movimentacao {
    private Veiculo veiculo;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private double valor;
}
