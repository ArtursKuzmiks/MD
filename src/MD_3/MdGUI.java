package MD_3;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class MdGUI extends JFrame {

    private JTextField jTextField = new JTextField(40);
    private DefaultListModel<String> list1Model = new DefaultListModel<>();
    private DefaultListModel<String> list2Model = new DefaultListModel<>();


    MdGUI() {

        final Border border = BorderFactory.createLineBorder(Color.BLUE,1);
        final String title = "Md_3, 111REB779";
        final int location = 300;
        final int width = 500;
        final int height = 500;

        JPanel jPanel = new JPanel();

        JScrollPane jScrollPane1;
        JScrollPane jScrollPane2;

        JButton add = new JButton("Add");
        JButton run = new JButton("Run");
        JButton delete = new JButton("Delete");
        JButton clear = new JButton("Clear");

        JList<String> list1 = new JList<>();
        JList<String> list2 = new JList<>();

        setTitle(title);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(location, location, width, height);

        setVisible(true);

        jPanel.setLayout(null);

        list1.setBorder(border);
        list2.setBorder(border);
        list1.setModel(list1Model);
        list2.setModel(list2Model);

        jScrollPane1 = new JScrollPane(list1);
        jScrollPane2 = new JScrollPane(list2);

        jTextField.setBounds(50, 25, 400, 25);
        add.setBounds(160, 60, 70, 30);
        run.setBounds(270, 60, 70, 30);
        jScrollPane1.setBounds(50, 100, 180, 300);
        jScrollPane2.setBounds(270, 100, 180, 300);
        delete.setBounds(160, 410, 70, 30);
        clear.setBounds(270, 410, 70, 30);


        jPanel.add(jTextField);
        jPanel.add(add);
        jPanel.add(run);
        jPanel.add(jScrollPane1);
        jPanel.add(jScrollPane2);
        jPanel.add(delete);
        jPanel.add(clear);

        add.addActionListener(new ActionAdd());
        run.addActionListener(new ActionRun());
        jTextField.addActionListener(new ActionAdd());
        delete.addActionListener(new ActionDelete());
        clear.addActionListener(new ActionClear());

        add(jPanel);

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
            for (int i = 0; i < list1Model.getSize(); i++) {
                String[] split = list1Model.get(i).split("\\W");
                String temp = split[0];
                boolean found=false;
                for (int n = 1; n < split.length; n++) {
                    if (split[n].length() < split[0].length() && !found){
                        temp = split[n];
                        found=true;
                    }
                }
                list2Model.addElement(temp);
            }
        }
    }

    private class ActionDelete implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            list1Model.clear();
        }
    }

    private class ActionClear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            list1Model.clear();
            list2Model.clear();
        }
    }
}
