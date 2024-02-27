package impacta.ead.estacionamento.apresentacao;

import impacta.ead.estacionamento.controle.EstacionamentoController;
import impacta.ead.estacionamento.controle.EstacionamentoException;
import impacta.ead.estacionamento.controle.VeiculoException;
import impacta.ead.estacionamento.negocio.Movimentacao;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class TelaSaidaVeiculo extends JFrame implements ActionListener {
    private JFrame parent;
    private JFormattedTextField txtPlaca;
    public TelaSaidaVeiculo(JFrame parent) {
        setResizable(false);
        setTitle("Saída de Veículo");
        setSize(new Dimension(526, 178));
        this.parent = parent;

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(this);
        btnOk.setActionCommand("Ok");
        panel.add(btnOk);

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(this);
        btnCancel.setActionCommand("Cancelar");
        panel.add(btnCancel);

        JPanel panel_1 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setVgap(30);
        getContentPane().add(panel_1, BorderLayout.CENTER);

        JLabel lblPlaca = new JLabel("Placa: ");
        lblPlaca.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel_1.add(lblPlaca);

        try {
            txtPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
        } catch (ParseException e) {
            assert false: "Formato do padrão inválido!";
        }

        txtPlaca.setForeground(Color.BLUE);
        txtPlaca.setFont(new Font("Tahoma", Font.BOLD, 16));
        txtPlaca.setColumns(10);
        panel_1.add(txtPlaca);

        setLocationRelativeTo(null);
    }

    @Override
	public void actionPerformed(ActionEvent evento) {
		String cmd = evento.getActionCommand();

		if(cmd.equals("ok")){
			EstacionamentoController controle = new EstacionamentoController();
			Movimentacao movimentacao = null;
			try {
				movimentacao = controle.processarSaida(txtPlaca.getText());
			} catch (VeiculoException | EstacionamentoException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Falha na Sa�da",JOptionPane.ERROR_MESSAGE);
			}
			TelaResumoPagamento telaResumo =
					new TelaResumoPagamento(movimentacao,parent);
			telaResumo.setVisible(true);
		}else{
			parent.setVisible(true);
		}

		dispose();
	}
}
