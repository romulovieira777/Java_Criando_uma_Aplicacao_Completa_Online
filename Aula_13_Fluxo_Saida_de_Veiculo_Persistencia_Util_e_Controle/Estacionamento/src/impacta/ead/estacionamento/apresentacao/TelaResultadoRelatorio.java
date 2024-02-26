package impacta.ead.estacionamento.apresentacao;

import impacta.ead.estacionamento.negocio.Movimentacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaResultadoRelatorio extends Frame implements ActionListener {

    private JTable tblFaturamento;
    private JFrame parent;

    public TelaResultadoRelatorio(TelaInicialRelatorio telaInicialRelatorio, List <Movimentacao>) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(600, 300));
        setResizable(false);
        setTitle("Relatório de Faturamento");

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        JButton btnOk = new JButton("Ok");
        panel.add(btnOk);

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));

        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2, BorderLayout.SOUTH);

        JLabel lblTextoFaturamento = new JLabel("[Faturamento]");
        lblTextoFaturamento.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_2.add(lblTextoFaturamento);

        JScrollPane scrollPane = new JScrollPane();
        panel_1.add(scrollPane, BorderLayout.CENTER);

        tblFaturamento = new JTable();
        tblFaturamento.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "Placa", "Entrada", "Saída", "Valor"
            }
        ));
        scrollPane.setViewportView(tblFaturamento);

        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        parent.setVisible(true);
        dispose();
    }
}
