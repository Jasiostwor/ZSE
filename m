import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditorApp extends JFrame {
    private JTextArea textArea;
    private String currentFilePath;

    public TextEditorApp() {
        setTitle("Text Editor");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton openButton = new JButton("Open");
        JButton saveButton = new JButton("Save");

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            currentFilePath = file.getAbsolutePath();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                textArea.setText(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveFile() {
        if (currentFilePath == null) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                currentFilePath = file.getAbsolutePath();
            } else {
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFilePath))) {
            writer.write(textArea.getText());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextEditorApp().setVisible(true);
            }
        });
    }
}
