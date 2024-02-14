package impacta.ead.estacionamento.apresentacao;

import impacta.ead.estacionamento.controle.EstacionamentoController;
import impacta.ead.estacionamento.controle.EstacionamentoException;
import impacta.ead.estacionamento.controle.VeiculoException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEntradaVeiculo extends JFrame implements ActionListener {
    private JFrame parent;
    private JTextField txfPlaca;
    private JButton btnOk;
    private JButton btnCancel;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtCor;

    // TODO temporario
    public static void main(String [] args) {
        TelaEntradaVeiculo tela = new TelaEntradaVeiculo(null);
        tela.setVisible(true);
    }
    public TelaEntradaVeiculo(JFrame parent) {
        setResizable(false);
        setSize(400, 300);
        setTitle("Entrada de Veículo");
        // TODO Auto-generated constructor stub
        this.parent = parent;
        getContentPane().setLayout(null);

        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setBounds(94, 46, 46, 14);
        getContentPane().add(lblPlaca);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(94, 85, 46, 14);
        getContentPane().add(lblMarca);

        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setBounds(94, 124, 46, 14);
        getContentPane().add(lblModelo);

        JLabel lblCor = new JLabel("Cor:");
        lblCor.setBounds(94, 163, 46, 14);
        getContentPane().add(lblCor);

        txtMarca = new JTextField();
        txtMarca.setBounds(174, 82, 86, 20);
        getContentPane().add(txtMarca);
        txtMarca.setColumns(10);

        txtModelo = new JTextField();
        txtModelo.setBounds(174, 121, 97, 20);
        getContentPane().add(txtModelo);
        txtModelo.setColumns(10);

        txtCor = new JTextField();
        txtCor.setBounds(174, 160, 97, 20);
        getContentPane().add(txtCor);
        txtCor.setColumns(10);

        btnOk = new JButton("Ok");
        btnOk.setBounds(104, 241, 91, 23);
        btnOk.addActionListener(this);
        btnOk.setActionCommand("Ok");
        getContentPane().add(btnOk);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(210, 241, 91, 23);
        btnCancel.addActionListener(this);
        btnCancel.setActionCommand("Cancelar");
        getContentPane().add(btnCancel);

        try {
            txfPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
        } catch (Exception e) {
            assert false: "Padrao de placa errado!";
        }
        txfPlaca.setBounds(174, 43, 97, 20);
        getContentPane().add(txfPlaca);

        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getActionCommand().equals("Ok")) {
            EstacionamentoController controle = new EstacionamentoController();
            try {
                controle.processarEntrada(txfPlaca.getText(), txtMarca.getText(), txtModelo.getText(), txtCor.getText());
                JOptionPane.showMessageDialog(null, "Veículo registrado com sucesso!",
                        "Entrada de Veículo", JOptionPane.INFORMATION_MESSAGE);
            } catch (EstacionamentoException | VeiculoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Falha na Entrada",
                        JOptionPane.ERROR_MESSAGE);
            }
            this.parent.setVisible(true);
            this.dispose();
        }

        this.parent.setVisible(true);
        this.dispose();
    }
}
