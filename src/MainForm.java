import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.TreeMap;

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

    private Properties prop;
    private TreeMap<String, Abonent> phoneBook;

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


        prop = new Properties();
        String propFileName = "Resources.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null)
            prop.load(inputStream);

        comboBoxCriterion.addItem(prop.getProperty("phoneNumber"));

//        comboBoxCriterion.addItem(prop.getProperty("firstName"));
        comboBoxCriterion.addItem(prop.getProperty("lastName"));
//        comboBoxCriterion.addItem(prop.getProperty("fathersName"));
        comboBoxCriterion.addItem(prop.getProperty("address"));

        phoneBook = new TreeMap<>();
        Abonent abonent1 = new Abonent("George", "Washington", "Unknown", "White House");
        Abonent abonent2 = new Abonent("John", "Adams", "George", "White House");
        Abonent abonent3 = new Abonent("Thomas", "Jefferson", "John", "White House");
        Abonent abonent4 = new Abonent("James", "Madison", "Thomas", "White House");
        Abonent abonent5 = new Abonent("James", "Monroe", "Unknown", "White House");
        phoneBook.put("+1 111 1111", abonent1);
        phoneBook.put("+1 222 2222", abonent2);
        phoneBook.put("+1 333 3333", abonent3);
        phoneBook.put("+1 444 4444", abonent4);
        phoneBook.put("+1 555 5555", abonent5);
    }
    //---------------------------------------------------

    private void onSearch() {
        String selectedValueToSearch = (String) comboBoxCriterion.getSelectedItem();

        String text = textFieldValue.getText();

        for (Map.Entry<String, Abonent> entry : phoneBook.entrySet()) {
            String key = entry.getKey();
            Abonent value = entry.getValue();

            boolean found = false;
            if (Objects.equals(selectedValueToSearch, prop.getProperty("phoneNumber"))) if (Objects.equals(key, text))
                found = true;
            if (Objects.equals(selectedValueToSearch, prop.getProperty("lastName")))
                if (Objects.equals(value.lastName, text))
                    found = true;
            if (Objects.equals(selectedValueToSearch, prop.getProperty("address")))
                if (Objects.equals(value.address, text))
                    found = true;

            if (found) {
                labelPhoneNumber.setText(key);
                labelFirstName.setText(value.firstName);
                labelLastName.setText(value.lastName);
                labelFathersName.setText(value.fathersName);
                labelAddress.setText(value.address);
                break;
            }

        }
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
