import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    String[] studentArray = {
            "Pali",
            "Dani",
            "Bali",
            "Piri",
            "Eri",
            "Dóri",
            "Imi",
            "Árpi",
            "Csabi",
            "Jani"
    };

    final int MAX_STUDENT = studentArray.length;
    final int NUM_OF_STUDENT = 4;

    JButton autoButton = new JButton("Automata");
    JPanel panel = new JPanel();
    JButton markButton = new JButton("Jelöl");

    JCheckBox[] studentsBox = new JCheckBox[MAX_STUDENT];

    ArrayList<Integer> indexList = new ArrayList<>();
    ArrayList<Integer> selectedIndexes = new ArrayList<>();

    public MainFrame() {
        initComponent();
        initFrame();
    }

    private void initFrame() {
        setLayout(new BorderLayout());
        add(autoButton, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(markButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    private void initComponent() {
        markButton.setEnabled(false);
        panel.setLayout(new GridLayout(0, 4));
        // studentsBox
        for (int i = 0; i < MAX_STUDENT; i++) {
            studentsBox[i] = new JCheckBox(
                    i + 1 + ") " +
                            studentArray[i]);
            panel.add(studentsBox[i]);
            studentsBox[i].addItemListener(e -> startCalc());
        }
        autoButton.addActionListener(e -> startAutoCalc());
    }

    private void startAutoCalc() {
        System.out.println("műkszik");
        selectedIndexes.clear();
        for (int i = 0; i < MAX_STUDENT; i++) {
            indexList.add(i);
        }
        Random random = new Random();

        for (int i = MAX_STUDENT; i > MAX_STUDENT - NUM_OF_STUDENT; i--) {
            int selected = random.nextInt(i);
            selectedIndexes.add(indexList.remove(selected));
            System.out.println("lefut");
        }
        System.out.println(selectedIndexes);
        // törlés és jelölés
        for (int i = 0; i < MAX_STUDENT; i++) {
            studentsBox[i].setSelected(false);
            if (selectedIndexes.contains(i)) {
                studentsBox[i].setSelected(true);
            }
        }
    }

    private void startCalc() {
        System.out.println("jelölve");
        int count = 0;
        for (int i = 0; i < MAX_STUDENT; i++) {
            if (studentsBox[i].isSelected()) {
                count++;
            }
        }
        markButton.setEnabled(count == 4);
    }
}
