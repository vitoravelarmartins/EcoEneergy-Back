
//Importando as bibliotecas necessárias

import java.awt.AWTException;

import java.awt.CheckboxMenuItem;

import java.awt.Image;

import java.awt.MenuItem;

import java.awt.PopupMenu;

import java.awt.SystemTray;

import java.awt.Toolkit;

import java.awt.TrayIcon;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ClassTeste {

	private static MouseListener mouseListener = new MouseListener() {

		public void mouseClicked(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {

			// Toda vez que for clicado imprime uma mensagem na tela

			System.out.println("Tray Icon - O Mouse foi pressionado!");

		}

		public void mouseReleased(MouseEvent e) {

		}

	};

	private static ActionListener exitListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			// Imprime uma mensagem de despedida na tela

			System.out.println("Saindo...");

			JOptionPane.showMessageDialog(null, "Saindo...");

			System.exit(0);

		}

	};

	public static void main(String[] args) throws SQLException {

		final TrayIcon trayIcon;
		Image image = createImage("/ray.png", "EcoEneergy");
		SystemTray tray = SystemTray.getSystemTray();
		
		PopupMenu popup = new PopupMenu("Menu de Opções");
		MenuItem defaultItem = new MenuItem("Sair");
		defaultItem.addActionListener(exitListener);
		popup.add(defaultItem);
		trayIcon = new TrayIcon(image, "EcoEneergy", popup);
		trayIcon.setImageAutoSize(true);

		ActionListener actionListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				trayIcon.displayMessage("Action Event",

						"Um Evento foi disparado",

						TrayIcon.MessageType.INFO);

			}

		};

		trayIcon.addActionListener(actionListener);
		trayIcon.addMouseListener(mouseListener);
		try {

			tray.add(trayIcon);

		} catch (AWTException e) {

			System.err.println("Erro, TrayIcon não sera adicionado.");

		}

		Conexao conexao = new Conexao();

		String sql = "INSERT INTO potencia  (valor_potencia)" + " VALUES (?);";
		PreparedStatement pst = conexao.conectar().prepareStatement(sql);

		System.out.println("Pressione CRTL-C para finalizar o programa");

		while (true) {

			ConexaoUbi conexaoUbi = new ConexaoUbi();
			double potencia = conexaoUbi.getPotencia();
			System.out.println(potencia);

			pst.clearParameters();
			pst.setDouble(1, potencia);
			pst.execute();

			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	//Obtain the image URL
    private static Image createImage(String path, String description) {
        URL imageURL = ClassTeste.class.getResource(path);
         
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}