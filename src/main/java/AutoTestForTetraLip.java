import javax.swing.*;

public class AutoTestForTetraLip {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUIForm guiForTest = new GUIForm();
                guiForTest.setVisible(true);
            }
        });


    }
}
