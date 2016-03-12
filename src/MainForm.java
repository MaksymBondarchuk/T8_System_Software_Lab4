import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonSearch;
    private JButton buttonCancel;
    private JPanel Center;
    private JPanel South;
    private JLabel labelPhoneNumber;
    private JLabel labelFirstName;
    private JLabel labelLastName;
    private JLabel labelFathersName;
    private JLabel labelAddress;
    private JComboBox<String> comboBoxCriterion;
    private JTextField textFieldValue;

    public MainForm() throws IOException {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSearch);

        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSearch();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        Properties prop = new Properties();
        String propFileName = "Resources.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null)
            prop.load(inputStream);

        comboBoxCriterion.addItem(prop.getProperty("address"));
        comboBoxCriterion.addItem(prop.getProperty("firstName"));
        comboBoxCriterion.addItem(prop.getProperty("lastName"));
        comboBoxCriterion.addItem(prop.getProperty("fathersName"));
        comboBoxCriterion.addItem(prop.getProperty("phoneNumber"));
    }
    //---------------------------------------------------

    private void onSearch() {
// add your code here
        labelPhoneNumber.setText("ABCDEFGHIJK");
//        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) throws IOException {
        MainForm dialog = new MainForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
