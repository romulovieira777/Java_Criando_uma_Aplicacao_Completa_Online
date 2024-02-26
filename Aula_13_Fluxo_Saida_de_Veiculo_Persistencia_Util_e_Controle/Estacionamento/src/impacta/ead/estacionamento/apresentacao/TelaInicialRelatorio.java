package impacta.ead.estacionamento.apresentacao;

import impacta.ead.estacionamento.controle.EstacionamentoController;
import impacta.ead.estacionamento.negocio.Movimentacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class TelaInicialRelatorio extends JFrame implements ActionListener {

    private JComboBox cboAno;
    private JComboBox cboMes;

    public TelaInicialRelatorio() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 140));
        setResizable(false);
        setTitle("Filtro do Relatório");
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 15, 40));

        JLabel lblAno = new JLabel("Ano:");
        lblAno.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lblAno);

        cboAno = new JComboBox();
        cboAno.setModel(new DefaultComboBoxModel(new String[] {"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"}));
        cboAno.setFont(new Font("Tahoma", Font.PLAIN, 14));
        getContentPane().add(cboAno);

        JLabel lblMes = new JLabel("Mês:");
        lblMes.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lblMes);

        cboMes = new JComboBox();
        cboMes.setModel(new DefaultComboBoxModel(new String[] {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
        cboMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
        getContentPane().add(cboMes);

        JButton btnGerar = new JButton("Gerar");
        btnGerar.addActionListener(this);
        btnGerar.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(btnGerar);

        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //recupera o combo o ano e mes escolhido
        int ano = (Integer) cboAno.getSelectedItem();
        int mes = (Integer) cboMes.getSelectedIndex() + 1;

        //buscar as movimentacoes do mes e ano informados
        EstacionamentoController controller = new EstacionamentoController();
        LocalDateTime data = LocalDateTime.of(ano, mes, 1, 0, 0);
        List <Movimentacao> movimentacoes =  controle.emitirRelatorio(data);

        //exibe a tela de conteudo e faturamento
        TelaResultadoRelatorio relatorio = new TelaResultadoRelatorio(this, movimentacoes);
        relatorio.setVisible(true);
        dispose();
    }
}
