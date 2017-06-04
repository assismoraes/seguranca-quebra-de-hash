package br.assis.ufpi.seguranca.quebrahash.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.assis.ufpi.seguranca.quebrahash.QuebraSenha;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldNomeDoArquivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 88);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAbrirArquivo = new JButton("Abrir arquivo");
		btnAbrirArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				txtFieldNomeDoArquivo.setText(chooser.getSelectedFile().getAbsolutePath());
				String diretorio = chooser.getSelectedFile().getParentFile().toString();
				System.out.println("diretorio : " + diretorio);
				try {
					BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()));
					File fout = new File(diretorio + "\\" + "resultados.txt");
					FileOutputStream fos = new FileOutputStream(fout);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
										
					for(String line; (line = br.readLine()) != null;){
						if(!line.contains("#")){
							String[] linha = line.split(";");
							String matriculaComNome = linha[0];
							String hash = linha[1];
							QuebraSenha quebraSenha = new QuebraSenha();
							String senha = quebraSenha.quebrarSenha(matriculaComNome, hash);
							String nome = matriculaComNome.replaceAll("\\d", "");
							String l = nome + " ----> " + senha;
							System.out.println(l);
															
							bw.write(l);
							bw.newLine();
						}					 
					}
					
					bw.close();
					br.close();
					
					JOptionPane.showMessageDialog(null, "Quebra de hashs finalizada. Arquivo " + fout.getAbsolutePath() + " gerado!");
					dispose();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAbrirArquivo.setBounds(10, 11, 107, 23);
		contentPane.add(btnAbrirArquivo);
		
		txtFieldNomeDoArquivo = new JTextField();
		txtFieldNomeDoArquivo.setBounds(127, 12, 297, 20);
		contentPane.add(txtFieldNomeDoArquivo);
		txtFieldNomeDoArquivo.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 414, 7);
		contentPane.add(separator);
	}
}
