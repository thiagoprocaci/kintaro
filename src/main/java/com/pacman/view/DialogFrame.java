package com.pacman.view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pacman.view.event.EventHandler;

/**
 *
 * @author Thiago Baesso Procaci
 *
 */
@SuppressWarnings("serial")
public abstract class DialogFrame extends EventHandler {
	private JDialog saveDialog;
	private JTextField textField;
	private JLabel messageLabel;

	public DialogFrame() {
		textField = new JTextField();
		messageLabel = new JLabel("Nome do mundo");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(messageLabel);
		panel.add(textField);
		JButton button = new JButton("Salvar");
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getButton() == 1)
					onSaveClicked();
			}
		});
		panel.add(button);
		saveDialog = new JDialog();
		saveDialog.add(panel);
		saveDialog.setResizable(false);
		saveDialog.setTitle("Salvar cenario");
		saveDialog.setPreferredSize(new Dimension(200, 100));
		saveDialog.setVisible(false);
	}

	public String value(){
		return textField.getText().trim();
	}

//	@Override
	protected void onSavePressed() {
		messageLabel.setText("Nome do mundo");
		textField.setText("");
		saveDialog.setLocationRelativeTo(this);
		saveDialog.setModal(true);
		saveDialog.pack();
		saveDialog.setVisible(true);
	}

	protected void onSaveClicked() {
		if ("".equals(textField.getText().trim()))
			messageLabel.setText("Nome do mundo - obrigatorio");
		else {
			saveDialog.setModal(false);
			saveDialog.setVisible(false);
			saveWorld();
		}
	}

	/**
	 * Salva as configuracoes do mundo
	 */
	protected abstract void saveWorld();
}
