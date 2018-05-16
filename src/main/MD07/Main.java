package MD07;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class MdGUI extends JFrame {

    private JTextField jTextField = new JTextField();
    private JList<String> list1 = new JList<>();
    private DefaultListModel<String> list1Model = new DefaultListModel<>();
    private DefaultListModel<String> list2Model = new DefaultListModel<>();

    MdGUI() {

        final Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        final String title = "Md_3";
        final int location = 300;
        final int width = 500;
        final int height = 500;

        JScrollPane jScrollPane1;
        JScrollPane jScrollPane2;

        JButton add = new JButton("Add");
        JButton run = new JButton("Run");
        JButton delete = new JButton("Delete");
        JButton clear = new JButton("Clear");

        add.setFont(new Font("Tahoma", Font.BOLD, 20));
        run.setFont(new Font("Tahoma", Font.BOLD, 20));
        delete.setFont(new Font("Tahoma", Font.BOLD, 20));
        clear.setFont(new Font("Tahoma", Font.BOLD, 20));

        JList<String> list2 = new JList<>();

        list1.setBorder(border);
        list2.setBorder(border);
        list1.setModel(list1Model);
        list2.setModel(list2Model);

        jScrollPane1 = new JScrollPane(list1);
        jScrollPane2 = new JScrollPane(list2);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(add)
                                        .addComponent(jScrollPane1)
                                        .addComponent(delete))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(run)
                                        .addComponent(jScrollPane2)
                                        .addComponent(clear))))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, add, run, delete, clear);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(add)
                        .addComponent(run))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jScrollPane1)
                        .addComponent(jScrollPane2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(delete)
                        .addComponent(clear))
        );

        pack();

        setBounds(location, location, width, height);
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        add.addActionListener(new ActionAdd());
        run.addActionListener(new ActionRun());
        jTextField.addActionListener(new ActionAdd());
        delete.addActionListener(new ActionDelete());
        clear.addActionListener(new ActionClear());

    }

    private class ActionAdd implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (jTextField.getText().trim().length() > 0)
                list1Model.addElement(jTextField.getText());
            jTextField.setText(null);
        }
    }

    private class ActionRun implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            list2Model.clear();
            for (int i = 0; i < list1Model.size(); i++) {
                StringBuilder temp = new StringBuilder();
                if (list1Model.get(i).length() % 2 == 0)
                    for (int j = 0; j < list1Model.get(i).length() / 2; j++) {
                        temp.append(list1Model.get(i).charAt(list1Model.get(i).length() - (j + 1)));
                        temp.append(list1Model.get(i).charAt(j));
                    }
                else {
                    for (int j = 0; j < (list1Model.get(i).length() - 1) / 2; j++) {
                        temp.append(list1Model.get(i).charAt(list1Model.get(i).length() - (j + 1)));
                        temp.append(list1Model.get(i).charAt(j));
                    }
                    temp.append(list1Model.get(i).charAt(list1Model.get(i).length() / 2));
                }
                list2Model.addElement(temp.toString());

            }
        }
    }

    private class ActionDelete implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            while (true) {
                int[] index = list1.getSelectedIndices();
                if (index.length == 0)
                    break;
                else {
                    list1Model.remove(index[0]);
                }
            }
        }
    }

    private class ActionClear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            list2Model.clear();
        }
    }

}

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(MdGUI::new);
    }
}
