package autotestfortetralip;

import javax.swing.*;

public class AutoTestForTetraLip {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                MainWindow guiForLip = new MainWindow();

                guiForLip.setVisible(true);

            }
        });

    }
}
