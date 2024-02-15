package impacta.ead.estacionamento.negocio;

/*
* Representa as informações relativas à vagas do estacionamento ou status de ocupação.
*
*  @Author: Romulo Vieira
 */
public class Vaga {
    public static int TOTAL_VAGAS = 100;
    private static int vagasOcupadas = 0;

    private Vaga(){}

    /*
    * Verifica a existência de alguma vaga livre no estacionamento.
    *
    * @return true se houver alguma vaga e false se estiver lotado.
     */
    public static boolean temVagaLivre() {
        //TODO: Implementar este metodo.
        return false;
    }

    /*
     * Buscar o status atual das vagas do estacionamento
     */
    public static void inicializarOcupadas(){
        //TODO: Implementar este metodo.
    }

    /*
    * Retorna o número de vagas ocupadas no estacionamento.
    * @return o número total de vagas ocupadas num determinado instante.
     */
    public static int ocupadas(){
        return Vaga.vagasOcupadas;
    }

    /*
    * Retorna o número de vagas livres no estacionamento.
    * @return o número total de vagas livres num determinado instante.
     */
    public static int livres() {
        return TOTAL_VAGAS - Vaga.vagasOcupadas;
    }

    /*
    * Atualiza o número de vagas ocupadas no estacionamento após a entrada do veículo.
     */
    public static void entrou(){
        Vaga.vagasOcupadas ++;
    }
}
