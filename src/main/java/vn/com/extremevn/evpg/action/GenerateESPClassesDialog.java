package vn.com.extremevn.evpg.action;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Input name dialog
 */
public class GenerateESPClassesDialog
        extends DialogWrapper {

    private final Listener listener;
    private JPanel contentProcessorPanel;
    private JTextField processorNameTextField;
    private JCheckBox isCreateFolder;
    private JTextField exceptionNameTextField;
    private JLabel errorLabel;

    public GenerateESPClassesDialog(final Listener listener) {
        super(null);
        this.listener = listener;
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentProcessorPanel;
    }

    @Override
    protected void doOKAction() {
        String processorName = processorNameTextField.getText().trim();
        String exceptionName = exceptionNameTextField.getText().trim();
        if (processorName.isEmpty()) {
            processorNameTextField.setText("");
            errorLabel.setText("Please fill out \"Name\" field");
            return;
        }

        if (exceptionName.isEmpty()) {
            exceptionNameTextField.setText("");
            errorLabel.setText("Please fill out \"Exception Name\" field");
            return;
        }
        super.doOKAction();
        this.listener.onGenerateBlocClicked(processorName, isCreateFolder.isSelected(), exceptionName);
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return processorNameTextField;
    }

    private void createUIComponents() {
    }

    /**
     * Interface definition for a callback to be invoked when a button `ok` is clicked.
     */
    public interface Listener {
        void onGenerateBlocClicked(String blocName, Boolean isCreateFolder, String exceptionName);
    }
}

