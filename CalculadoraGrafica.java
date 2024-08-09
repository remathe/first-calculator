import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGrafica extends JFrame implements ActionListener {

    private JTextField display;
    private double num1, num2, resultado;
    private char operador;

    public CalculadoraGrafica() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(this);
            panel.add(button);
        }

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) == 'C') {
            display.setText("");
            num1 = num2 = resultado = 0;
        } else if (command.charAt(0) == '=') {
            num2 = Double.parseDouble(display.getText());
            switch (operador) {
                case '+': resultado = num1 + num2; break;
                case '-': resultado = num1 - num2; break;
                case '*': resultado = num1 * num2; break;
                case '/': resultado = num1 / num2; break;
            }
            display.setText(String.valueOf(resultado));
            num1 = resultado;
        } else if (command.charAt(0) == '+' || command.charAt(0) == '-' ||
                   command.charAt(0) == '*' || command.charAt(0) == '/') {
            num1 = Double.parseDouble(display.getText());
            operador = command.charAt(0);
            display.setText("");
        } else {
            display.setText(display.getText() + command);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraGrafica calculadora = new CalculadoraGrafica();
            calculadora.setVisible(true);
        });
    }
}
