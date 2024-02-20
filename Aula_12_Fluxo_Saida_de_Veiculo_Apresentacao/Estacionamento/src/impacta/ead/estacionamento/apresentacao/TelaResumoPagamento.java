package impacta.ead.estacionamento.apresentacao;

import javax.swing.*;

import impacta.ead.estacionamento.negocio.Movimentacao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaResumoPagamento extends JFrame implements ActionListener {
    private JFrame parent;
    public TelaResumoPagamento(Movimentacao movimentacao, JFrame parent) {
        this.parent = parent;
        getContentPane().setFont(new Font("Dialog", Font.BOLD, 12));
        setResizable(false);
        setTitle("Resumo de Pagamento");
        getContentPane().setLayout(null);

        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPlaca.setBounds(110, 47, 46, 14);
        getContentPane().add(lblPlaca);

        JLabel lblDataEntrada = new JLabel("Data de Entrada:");
        lblDataEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDataEntrada.setBounds(96, 87, 60, 14);
        getContentPane().add(lblDataEntrada);

        JLabel lblDataSaida = new JLabel("Data de Saída:");
        lblDataSaida.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDataSaida.setBounds(110, 129, 46, 14);
        getContentPane().add(lblDataSaida);

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblValor.setBounds(110, 172, 46, 14);
        getContentPane().add(lblValor);

        JLabel lblPlaca = new JLabel("[placa]");
        lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPlaca.setBounds(110, 47, 46, 14);
        getContentPane().add(lblPlaca);

        JLabel lblDataEntrada = new JLabel("[data_entrada]");
        lblDataEntrada.setFont(new Font("Dialog", Font.BOLD, 12));
        lblDataEntrada.setBounds(229, 87, 181, 14);
        getContentPane().add(lblDataEntrada);

        JLabel lblValDataSaida = new JLabel("[data_saida] ");
        lblValDataSaida.setFont(new Font("Dialog", Font.BOLD, 12));
        lblValDataSaida.setBounds(229, 129, 181, 14);
        getContentPane().add(lblValDataSaida);

        JLabel lblValValor = new JLabel("[valor]");
        lblValValor.setFont(new Font("Dialog", Font.BOLD, 12));
        lblValValor.setBounds(229, 172, 82, 14);
        getContentPane().add(lblValValor);

        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(this);
        btnOk.setBounds(152, 235, 89, 23);
        getContentPane().add(btnOk);

        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parent.setVisible(true);
        dispose();
    }
}
