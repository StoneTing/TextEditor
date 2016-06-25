package com.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
//import javax.swing.text.DefaultEditorKit.CopyAction;

import com.about.Author;
import com.about.ToBeContinued;

public class WordEdit extends JFrame implements ActionListener {
	// ����˵��
	// �ı��༭����
	private JTextArea jTextArea;
	// �˵���
	private JMenuBar jMenuBar;
	// �˵�
	private JMenu fileJMenu, editJMenu, formatJMenu, aboutJMenu;// modeJMenu,
	// �˵���-------
	// �ļ��˵�
	private JMenuItem newFile, openFile, saveFile, quit; // saveAsFile,
	// �༭�˵�
	private JMenuItem cut, copy, paste, delete, selectAll, timeAndDate;// cancel,
																		// find,
																		// findNext,
																		// replace,
																		// goTo,
	// ��ʽ�˵�
	private JMenu autoLinefeed, font, color;
	private JMenuItem linefeedOpen, linefeedClose, songTi, yaHei, blackColor, redColor, blueColor;
	// ģʽ�˵�

	// ���ڲ˵�
	private JMenuItem findHelp, author;
	
	// ������
	private JScrollPane jsp;
	// �ļ�����
	private JFileChooser fileDialog;
	private FileReader fileReader;
	private FileWriter fileWriter;
	private BufferedReader in;
	private BufferedWriter out;

	// private boolean ysSave = false; //�����ж��ļ��Ƿ񱣴�
	// ����Ҽ��˵�
	private JPopupMenu rightPopup;
	// private JMenu rightJMenu;
	private JMenuItem cutRight, copyRight, pasteRight, selectAllRight;

	// ���췽��----------------------------------
	public WordEdit() {
		setTitle("�ı��༭��");
		setSize(800, 600);
		init();
	}

	// ��ʼ��
	public void init() {
		jTextArea = new JTextArea();
		add(jTextArea); // �������ı�����ӵ����ڵ�����������

		jMenuBar = new JMenuBar();
		fileJMenu = new JMenu("�ļ�");
		editJMenu = new JMenu("�༭");
		formatJMenu = new JMenu("��ʽ");
		// modeJMenu = new JMenu("ģʽ");
		aboutJMenu = new JMenu("����");
		// ����Ҽ��˵�
		rightPopup = new JPopupMenu();
		// rightJMenu = new JMenu("�Ҽ��˵�");
		// copyMenuItem = new JMenuItem("����");
		// rightPopup = new JPopupMenu();
		// rightJMenu.add(copyMenuItem);
		// rightPopup = rightJMenu.getPopupMenu();

		// ��������ʼ��
		jsp = new JScrollPane(jTextArea);
		add(jsp, BorderLayout.CENTER);

		// �ļ��˵���
		newFile = new JMenuItem("�½�");
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newFile.addActionListener(this); // ���½��˵���Ӽ������¼�

		openFile = new JMenuItem("��");
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		openFile.addActionListener(this);
		fileDialog = new JFileChooser();

		saveFile = new JMenuItem("����");
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		saveFile.addActionListener(this);

		// saveAsFile = new JMenuItem("���Ϊ");
		// saveAsFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
		// InputEvent.CTRL_MASK));
		// saveAsFile.addActionListener(this);

		quit = new JMenuItem("�˳�");
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));
		quit.addActionListener(this);

		// �༭�˵���
		// cancel = new JMenuItem("����");
		// cancel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
		// InputEvent.CTRL_MASK));
		// cancel.addActionListener(this);

		cut = new JMenuItem("����");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		cut.addActionListener(this);

		copy = new JMenuItem("����");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		copy.addActionListener(this);

		paste = new JMenuItem("ճ��");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		paste.addActionListener(this);

		delete = new JMenuItem("ɾ��");
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		delete.addActionListener(this);

		// find = new JMenuItem("����");
		// find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
		// InputEvent.CTRL_MASK));
		// find.addActionListener(this);
		//
		// findNext = new JMenuItem("������һ��");
		// findNext.addActionListener(this);
		//
		// replace = new JMenuItem("�滻");
		// replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
		// InputEvent.CTRL_MASK));
		// replace.addActionListener(this);
		//
		// goTo = new JMenuItem("ת��");
		// goTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
		// InputEvent.CTRL_MASK));
		// goTo.addActionListener(this);

		selectAll = new JMenuItem("ȫѡ");
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		selectAll.addActionListener(this);

		timeAndDate = new JMenuItem("ʱ���");
		timeAndDate.addActionListener(this);

		// ��ʽ�˵���
		autoLinefeed = new JMenu("�Զ�����");
		linefeedOpen = new JMenuItem("����");
		linefeedClose = new JMenuItem("�ر�");
		linefeedOpen.addActionListener(this);
		linefeedClose.addActionListener(this);

		font = new JMenu("����");
		songTi = new JMenuItem("����");
		yaHei = new JMenuItem("΢���ź�");
		songTi.addActionListener(this);
		yaHei.addActionListener(this);

		color = new JMenu("��ɫ");
		blackColor = new JMenuItem("��ɫ");
		redColor = new JMenuItem("��ɫ");
		blueColor = new JMenuItem("��ɫ");
		blackColor.addActionListener(this);
		redColor.addActionListener(this);
		blueColor.addActionListener(this);

		// ���ڲ˵���
		findHelp = new JMenuItem("Ѱ�Ұ���");
		findHelp.addActionListener(this);

		author = new JMenuItem("����");
		author.addActionListener(this);


		// ��ʼ�� �Ҽ��˵�����Ϊ�Ҽ��˵��Ĳ˵�����Ӽ�����-------------------
		copyRight = new JMenuItem("����");
		copyRight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		copyRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "����") {
					jTextArea.copy();
				}
			}
		});
		cutRight = new JMenuItem("����");
		cutRight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		cutRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "����") {
					jTextArea.cut();
				}
			}
		});
		pasteRight = new JMenuItem("ճ��");
		pasteRight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		pasteRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "ճ��") {
					jTextArea.paste();
				}
			}
		});
		
		selectAllRight = new  JMenuItem("ȫѡ");
		selectAllRight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		selectAllRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getActionCommand() == "ȫѡ");
					jTextArea.selectAll();
			}
		});
		
		// �ı��������������
		jTextArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent g) // ����������ʵ��
			{
				if (jTextArea.getSelectedText() == null) {
					cutRight.setEnabled(false);
					copyRight.setEnabled(false);
					pasteRight.setEnabled(true);
					selectAllRight.setEnabled(true);
				} else if (jTextArea.getSelectedText() != null) {
					cutRight.setEnabled(true);
					copyRight.setEnabled(true);
					pasteRight.setEnabled(true);
					selectAllRight.setEnabled(true);
				}
				if (g.getModifiers() == MouseEvent.BUTTON3_MASK)
					rightPopup.show(jTextArea, g.getX(), g.getY());// ����϶��¼��� x
																	// y����
			}
		});

		// ---------------------------------------------------

		// ���˵�����ӵ� �ļ��˵� ��
		fileJMenu.add(newFile);
		fileJMenu.add(openFile);
		fileJMenu.add(saveFile);
		// fileJMenu.add(saveAsFile);
		fileJMenu.addSeparator();
		fileJMenu.add(quit);

		// ���˵�����ӵ� �༭�˵� ��
		// editJMenu.add(cancel);
		// editJMenu.addSeparator();
		editJMenu.add(cut);
		editJMenu.add(copy);
		editJMenu.add(paste);
		editJMenu.add(delete);
		// editJMenu.addSeparator();
		// editJMenu.add(find);
		// editJMenu.add(findNext);
		// editJMenu.add(replace);
		// editJMenu.add(goTo);
		editJMenu.addSeparator();
		editJMenu.add(selectAll);
		editJMenu.addSeparator();
		editJMenu.add(timeAndDate);

		// ���˵�����ӵ� ��ʽ�˵� ��
		formatJMenu.add(autoLinefeed);
		autoLinefeed.add(linefeedOpen);
		autoLinefeed.add(linefeedClose);
		formatJMenu.add(font);
		font.add(songTi);
		font.add(yaHei);
		formatJMenu.add(color);
		color.add(blackColor);
		color.add(redColor);
		color.add(blueColor);

		// ���˵�����ӵ� ģʽ�˵� ��

		// ���˵�����ӵ� ���ڲ˵� ��
		aboutJMenu.add(findHelp);
		aboutJMenu.add(author);

		// ���˵���ӵ��˵�����
		jMenuBar.add(fileJMenu);
		jMenuBar.add(editJMenu);
		jMenuBar.add(formatJMenu);
		// jMenuBar.add(modeJMenu);
		jMenuBar.add(aboutJMenu);
		// ���˵�����ӵ�������
		setJMenuBar(jMenuBar);

		// �Ҽ��˵�
		rightPopup.add(copyRight);
		rightPopup.add(cutRight);
		rightPopup.add(pasteRight);
		rightPopup.addSeparator();
		rightPopup.add(selectAllRight);

	}

	// �����˵���----------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem jmi = (JMenuItem) e.getSource();
		String menuName = jmi.getText();

		switch (menuName) {
		// �ļ� �˵���
		case "�½�":
			jTextArea.setText(null);
			break;
		case "��":
			openFileFunction();
			break;
		case "����":
			saveFileFunction();
			break;
		// case "���Ϊ":
		// break;
		case "�˳�":
			System.exit(0);
			break;

		// �༭ �˵���
		// case "����":
		// break;
		case "����":
			jTextArea.cut();
			break;
		case "����":
			jTextArea.copy();
			break;
		case "ճ��":
			jTextArea.paste();
			break;
		case "ɾ��":
			// String str = "";
			// jTextArea.setText(str);
			jTextArea.cut();
			break;
		// case "����":
		// break;
		// case "������һ��":
		// break;
		// case "ת��":
		// break;
		case "ȫѡ":
			jTextArea.selectAll();
			break;
		case "ʱ���":
			jTextArea.setText(dateAndTime());
			break;

		// ��ʽ �˵���
		// �Զ�����
		case "����":
			jTextArea.setLineWrap(true);
			break;
		case "�ر�":
			jTextArea.setLineWrap(false);
			break;
		case "����":
			jTextArea.setFont(new Font("����", Font.PLAIN, 14));
			break;
		case "΢���ź�":
			jTextArea.setFont(new Font("΢���ź�", Font.PLAIN, 14));
			break;
		case "��ɫ":
			jTextArea.setForeground(Color.BLACK);
			break;
		case "��ɫ":
			jTextArea.setForeground(Color.RED);
			break;
		case "��ɫ":
			jTextArea.setForeground(Color.BLUE);
			break;
		// ģʽ �˵���

		// ���� �˵���
		case "Ѱ�Ұ���":
			help();
			break;
		case "����":
			new Author().show();
			break;
		}
	}

	// ���ļ� �ķ���
	void openFileFunction() {
		int state = fileDialog.showOpenDialog(this);
		if (state == JFileChooser.APPROVE_OPTION) {
			try {
				jTextArea.setText(null);
				File dir = fileDialog.getCurrentDirectory();
				String name = fileDialog.getSelectedFile().getName();
				File file = new File(dir, name);
				fileReader = new FileReader(file);
				in = new BufferedReader(fileReader);
				String s = null;
				while ((s = in.readLine()) != null) {
					jTextArea.append(s + "\n");
				}
				in.close();
				fileReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// �����ļ� �ķ���
	void saveFileFunction() {
		int state = fileDialog.showSaveDialog(this);
		if (state == JFileChooser.APPROVE_OPTION) {
			try {
				File dir = fileDialog.getCurrentDirectory();
				String name = fileDialog.getSelectedFile().getName();
				File file = new File(dir, name);
				fileWriter = new FileWriter(file);
				out = new BufferedWriter(fileWriter);
				out.write(jTextArea.getText());
				out.flush();
				fileWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// ʱ���
	String dateAndTime() {
		String temp = jTextArea.getText();

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);

		return (temp + "\n" + time);
	}

	// ����
	public void help() {
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI("https://www.baidu.com/"));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
