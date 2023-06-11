import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame {
    private JTextField txtAmount;
    private JComboBox<String> txtFrom;
    private JComboBox<String> txtTo;
    private JButton convertButton;
    
   
    private static final Map<String, Map<String, Double>> conversionRates = new HashMap<>();
    
    static {
        
        Map<String, Double> usdRates = new HashMap<>();
        usdRates.put("USD", 1.0);
        usdRates.put("EUR", 0.92910862);
        usdRates.put("GBP", 0.79579162);
        usdRates.put("INR", 82.4693);
        usdRates.put("CAD", 1.33607);
        usdRates.put("AUD", 1.4849);
        usdRates.put("JPY", 139.395);
        
        Map<String, Double> eurRates = new HashMap<>();
        eurRates.put("USD", 1.076);
        eurRates.put("EUR", 1.0);
        eurRates.put("GBP", 0.85646278);
        eurRates.put("INR", 88.722494);
        eurRates.put("CAD", 1.4376);
        eurRates.put("AUD", 1.5979);
        eurRates.put("JPY", 149.997);

        Map<String, Double> gbpRates = new HashMap<>();
        gbpRates.put("USD", 1.2566083);
        gbpRates.put("EUR", 1.1678516);
        gbpRates.put("GBP", 1.0);
        gbpRates.put("INR", 103.63388);
        gbpRates.put("CAD", 1.67892);
        gbpRates.put("AUD", 1.86597);
        gbpRates.put("JPY", 175.31168);
        
        Map<String, Double> inrRates = new HashMap<>();
        inrRates.put("USD", 0.0121257);
        inrRates.put("EUR", 0.0112692);
        inrRates.put("GBP", 0.00964956);
        inrRates.put("INR", 1.0);
        inrRates.put("CAD", 0.0162008);
        inrRates.put("AUD", 0.018006);
        inrRates.put("JPY", 1.69096);

        Map<String, Double> cadRates = new HashMap<>();
        cadRates.put("USD", 0.748461);
        cadRates.put("EUR", 0.695596);
        cadRates.put("GBP", 0.595620);
        cadRates.put("INR", 61.7251);
        cadRates.put("CAD", 1.0);
        cadRates.put("AUD", 1.11159);
        cadRates.put("JPY", 104.45057);

        Map<String, Double> audRates = new HashMap<>();
        audRates.put("USD", 0.673402);
        audRates.put("EUR", 0.625808);
        audRates.put("GBP", 0.535914);
        audRates.put("INR", 55.5356);
        audRates.put("CAD", 0.899605);
        audRates.put("AUD", 1.0);
        audRates.put("JPY", 93.70086);

        Map<String, Double> jpyRates = new HashMap<>();
        jpyRates.put("USD", 0.0071738);
        jpyRates.put("EUR", 0.0066668);
        jpyRates.put("GBP", 0.0057091);
        jpyRates.put("INR", 0.591627);
        jpyRates.put("CAD", 0.0095835);
        jpyRates.put("AUD", 0.01065);
        jpyRates.put("JPY", 1.0);
        
        conversionRates.put("USD", usdRates);
        conversionRates.put("EUR", eurRates);
        conversionRates.put("GBP", gbpRates);
        conversionRates.put("INR", inrRates);
        conversionRates.put("CAD", cadRates);
        conversionRates.put("AUD", audRates);
        conversionRates.put("JPY", jpyRates);
    }

    public CurrencyConverter() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Currency Converter");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(amountLabel, gbc);

        txtAmount = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtAmount, gbc);

        JLabel fromLabel = new JLabel("From:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(fromLabel, gbc);

        txtFrom = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR", "CAD","AUD","JPY"});
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtFrom, gbc);

        JLabel toLabel = new JLabel("To:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(toLabel, gbc);

        txtTo = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR", "CAD","AUD","JPY"});
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtTo, gbc);

        convertButton = new JButton("Convert");
        convertButton.setBackground(new Color(255, 204, 102));
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                convertActionPerformed(evt);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(convertButton, gbc);

        add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    private void convertActionPerformed(ActionEvent evt) {
        double amount = Double.parseDouble(txtAmount.getText());
        String fromCurrency = txtFrom.getSelectedItem().toString();
        String toCurrency = txtTo.getSelectedItem().toString();

        Map<String, Double> fromRates = conversionRates.get(fromCurrency);
        if (fromRates != null) {
            Double conversionRate = fromRates.get(toCurrency);
            if (conversionRate != null) {
                double convert = amount * conversionRate;
                JOptionPane.showMessageDialog(this, "The amount is: " + convert + " " + toCurrency);
            } else {
                JOptionPane.showMessageDialog(this, "Conversion rate not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Conversion rate not found.");
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverter().setVisible(true);
        });
    }
}
