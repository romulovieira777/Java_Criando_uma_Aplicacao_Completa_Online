package impacta.ead.estacionamento.persistencia;

import impacta.ead.estacionamento.controle.EstacionamentoException;
import impacta.ead.estacionamento.negocio.Movimentacao;
import impacta.ead.estacionamento.negocio.Vaga;
import impacta.ead.estacionamento.utilitario.EstacionamentoUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

/*
 * Representa a camada de persistencia da aplicacao.
 * Realiza o mapeamento objeto-relacional.
 *
 * @autor Romulo Vieira
 *
 */

public class DAOEstacionamento {

    /*
     * Armazena os dados da movimentacao.
     *
     * @param movimentacao Instancia de movimentacao
     *
     * @throws EstacionamentoException Se houver erro de registro
     */
    public void criar(Movimentacao movimentacao) throws EstacionamentoException {
        String cmd1 = EstacionamentoUtil.get("insertMov");
        String cmd2 = EstacionamentoUtil.get("atualizaVaga");

        Connection conexao = null;
        try {
            conexao = getConnection();
            conexao.setAutoCommit(false);

            PreparedStatement stmt = conexao.prepareStatement(cmd1);
            stmt.setString(1, movimentacao.getVeiculo().getPlaca());
            stmt.setString(2, movimentacao.getVeiculo().getMarca());
            stmt.setString(3, movimentacao.getVeiculo().getModelo());
            stmt.setString(4, movimentacao.getVeiculo().getCor());
            stmt.setString(5, EstacionamentoUtil.getDataAsString(movimentacao.getDataHoraEntrada()));
            stmt.execute();

            stmt = conexao.prepareStatement(cmd2);
            stmt.setInt(1, Vaga.ocupadas() + 1);

            stmt.execute();

            conexao.commit();
        } catch (SQLException e) {
            try {
                conexao.rollback();
                throw new EstacionamentoException("Erro ao registrar veiculo");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    /*
     * Atualiza os dados de data de saida e valor da movimentacao.
     *
     * @param movimentacao Instancia de movimentacao
     */

    public void atualizar(Movimentacao movimentacao) {
        //TODO implementar
    }

    /*
     * Busca a movimentacao cujo veiculo tem a placa informada que ainda
     * esta estacionado (data de saida nula).
     *
     * @param placa A placa do veiculo
     *
     * @return A movimentacao encontrada ou null se não houver
     */

    public Movimentacao buscarMovimentacaoAberta(String placa) {
        //TODO implementar
        return null;
    }

    /*
    * Consulta todas as movimentacoes fechadas
    * (pagas e com data de saida preenchida) no mes e ano da
    * data informada.
    *
    * @param data Data de consulta
    *
    * @return Lista de movimentacoes do ano e mes informados
     */

    public List<Movimentacao> consultarMovimentacoes(LocalDateTime data) {
        //TODO implementar
        return null;
    }

    public static Connection getConnection() throws SQLException {

        String url = EstacionamentoUtil.get("url");
        String usuario = EstacionamentoUtil.get("usuario");
        String senha = EstacionamentoUtil.get("senha");

        Connection conexao = DriverManager.getConnection(url, usuario, senha);

        return conexao;
    }

    public static void closeConnection(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getOcupadas () {
        int ocupdas = 0;
        Connection conexao = null;
        String cmd = EstacionamentoUtil.get("consultaOcupadas");
        try {
            conexao = getConnection();
            PreparedStatement ps = conexao.prepareStatement(cmd);

            ResultSet resultado = ps.executeQuery();
            if (resultado.next()) {
                ocupdas = resultado.getInt("ocupadas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conexao);
        }

        return ocupdas;
    }
}
