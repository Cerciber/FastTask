package fasttask.view.components;

import javax.swing.JFileChooser;

public class FileChooser extends JFileChooser {

    @Override
    public void approveSelection() {
        if (getSelectedFile().isFile()) {
            return;
        } else {
            super.approveSelection();
        }
    }
}
