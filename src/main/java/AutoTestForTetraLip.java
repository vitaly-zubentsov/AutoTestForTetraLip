import javax.swing.*;

public class AutoTestForTetraLip {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UI guiForTest = new UI();
                guiForTest.setVisible(true);
            }
        });


    }
}
