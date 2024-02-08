package impacta.ead.estacionamento.apresentacao;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    private JPasswordField txtSenha;
    public TelaLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500,150));
        setResizable(false);
        setTitle("Login");

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        JButton btnOk = new JButton("Ok");
        panel.add(btnOk);

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.CENTER);

    }
}
