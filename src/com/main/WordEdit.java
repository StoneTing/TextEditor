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
	// 属性说明
	// 文本编辑区域
	private JTextArea jTextArea;
	// 菜单栏
	private JMenuBar jMenuBar;
	// 菜单
	private JMenu fileJMenu, editJMenu, formatJMenu, aboutJMenu;// modeJMenu,
	// 菜单栏-------
	// 文件菜单
	private JMenuItem newFile, openFile, saveFile, quit; // saveAsFile,
	// 编辑菜单
	private JMenuItem cut, copy, paste, delete, selectAll, timeAndDate;// cancel,
																		// find,
																		// findNext,
																		// replace,
																		// goTo,
	// 格式菜单
	private JMenu autoLinefeed, font, color;
	private JMenuItem linefeedOpen, linefeedClose, songTi, yaHei, blackColor, redColor, blueColor;
	// 模式菜单

	// 关于菜单
	private JMenuItem findHelp, author;
	
	// 滚动条
	private JScrollPane jsp;
	// 文件操作
	private JFileChooser fileDialog;
	private FileReader fileReader;
	private FileWriter fileWriter;
	private BufferedReader in;
	private BufferedWriter out;

	// private boolean ysSave = false; //用于判断文件是否保存
	// 鼠标右键菜单
	private JPopupMenu rightPopup;
	// private JMenu rightJMenu;
	private JMenuItem cutRight, copyRight, pasteRight, selectAllRight;

	// 构造方法----------------------------------
	public WordEdit() {
		setTitle("文本编辑器");
		setSize(800, 600);
		init();
	}

	// 初始化
	public void init() {
		jTextArea = new JTextArea();
		add(jTextArea); // 将多行文本框添加到窗口的内容容器中

		jMenuBar = new JMenuBar();
		fileJMenu = new JMenu("文件");
		editJMenu = new JMenu("编辑");
		formatJMenu = new JMenu("格式");
		// modeJMenu = new JMenu("模式");
		aboutJMenu = new JMenu("关于");
		// 鼠标右键菜单
		rightPopup = new JPopupMenu();
		// rightJMenu = new JMenu("右键菜单");
		// copyMenuItem = new JMenuItem("复制");
		// rightPopup = new JPopupMenu();
		// rightJMenu.add(copyMenuItem);
		// rightPopup = rightJMenu.getPopupMenu();

		// 滚动条初始化
		jsp = new JScrollPane(jTextArea);
		add(jsp, BorderLayout.CENTER);

		// 文件菜单项
		newFile = new JMenuItem("新建");
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newFile.addActionListener(this); // 对新建菜单添加监听器事件

		openFile = new JMenuItem("打开");
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		openFile.addActionListener(this);
		fileDialog = new JFileChooser();

		saveFile = new JMenuItem("保存");
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		saveFile.addActionListener(this);

		// saveAsFile = new JMenuItem("另存为");
		// saveAsFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
		// InputEvent.CTRL_MASK));
		// saveAsFile.addActionListener(this);

		quit = new JMenuItem("退出");
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));
		quit.addActionListener(this);

		// 编辑菜单项
		// cancel = new JMenuItem("撤销");
		// cancel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
		// InputEvent.CTRL_MASK));
		// cancel.addActionListener(this);

		cut = new JMenuItem("剪切");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		cut.addActionListener(this);

		copy = new JMenuItem("复制");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		copy.addActionListener(this);

		paste = new JMenuItem("粘贴");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		paste.addActionListener(this);

		delete = new JMenuItem("删除");
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		delete.addActionListener(this);

		// find = new JMenuItem("查找");
		// find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
		// InputEvent.CTRL_MASK));
		// find.addActionListener(this);
		//
		// findNext = new JMenuItem("查找下一个");
		// findNext.addActionListener(this);
		//
		// replace = new JMenuItem("替换");
		// replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
		// InputEvent.CTRL_MASK));
		// replace.addActionListener(this);
		//
		// goTo = new JMenuItem("转到");
		// goTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
		// InputEvent.CTRL_MASK));
		// goTo.addActionListener(this);

		selectAll = new JMenuItem("全选");
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		selectAll.addActionListener(this);

		timeAndDate = new JMenuItem("时间戳");
		timeAndDate.addActionListener(this);

		// 格式菜单项
		autoLinefeed = new JMenu("自动换行");
		linefeedOpen = new JMenuItem("开启");
		linefeedClose = new JMenuItem("关闭");
		linefeedOpen.addActionListener(this);
		linefeedClose.addActionListener(this);

		font = new JMenu("字体");
		songTi = new JMenuItem("宋体");
		yaHei = new JMenuItem("微软雅黑");
		songTi.addActionListener(this);
		yaHei.addActionListener(this);

		color = new JMenu("颜色");
		blackColor = new JMenuItem("黑色");
		redColor = new JMenuItem("红色");
		blueColor = new JMenuItem("蓝色");
		blackColor.addActionListener(this);
		redColor.addActionListener(this);
		blueColor.addActionListener(this);

		// 关于菜单项
		findHelp = new JMenuItem("寻找帮助");
		findHelp.addActionListener(this);

		author = new JMenuItem("作者");
		author.addActionListener(this);


		// 初始化 右键菜单，并为右键菜单的菜单项添加监视器-------------------
		copyRight = new JMenuItem("复制");
		copyRight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		copyRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "复制") {
					jTextArea.copy();
				}
			}
		});
		cutRight = new JMenuItem("剪切");
		cutRight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		cutRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "剪切") {
					jTextArea.cut();
				}
			}
		});
		pasteRight = new JMenuItem("粘贴");
		pasteRight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		pasteRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "粘贴") {
					jTextArea.paste();
				}
			}
		});
		
		selectAllRight = new  JMenuItem("全选");
		selectAllRight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		selectAllRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getActionCommand() == "全选");
					jTextArea.selectAll();
			}
		});
		
		// 文本区添加鼠标监听器
		jTextArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent g) // 鼠标监听器的实现
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
					rightPopup.show(jTextArea, g.getX(), g.getY());// 鼠标拖动事件的 x
																	// y坐标
			}
		});

		// ---------------------------------------------------

		// 将菜单项添加到 文件菜单 中
		fileJMenu.add(newFile);
		fileJMenu.add(openFile);
		fileJMenu.add(saveFile);
		// fileJMenu.add(saveAsFile);
		fileJMenu.addSeparator();
		fileJMenu.add(quit);

		// 将菜单项添加到 编辑菜单 中
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

		// 将菜单项添加到 格式菜单 中
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

		// 将菜单项添加到 模式菜单 中

		// 将菜单项添加到 关于菜单 中
		aboutJMenu.add(findHelp);
		aboutJMenu.add(author);

		// 将菜单添加到菜单栏中
		jMenuBar.add(fileJMenu);
		jMenuBar.add(editJMenu);
		jMenuBar.add(formatJMenu);
		// jMenuBar.add(modeJMenu);
		jMenuBar.add(aboutJMenu);
		// 将菜单栏添加到窗口中
		setJMenuBar(jMenuBar);

		// 右键菜单
		rightPopup.add(copyRight);
		rightPopup.add(cutRight);
		rightPopup.add(pasteRight);
		rightPopup.addSeparator();
		rightPopup.add(selectAllRight);

	}

	// 单击菜单项----------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem jmi = (JMenuItem) e.getSource();
		String menuName = jmi.getText();

		switch (menuName) {
		// 文件 菜单项
		case "新建":
			jTextArea.setText(null);
			break;
		case "打开":
			openFileFunction();
			break;
		case "保存":
			saveFileFunction();
			break;
		// case "另存为":
		// break;
		case "退出":
			System.exit(0);
			break;

		// 编辑 菜单项
		// case "撤销":
		// break;
		case "剪切":
			jTextArea.cut();
			break;
		case "复制":
			jTextArea.copy();
			break;
		case "粘贴":
			jTextArea.paste();
			break;
		case "删除":
			// String str = "";
			// jTextArea.setText(str);
			jTextArea.cut();
			break;
		// case "查找":
		// break;
		// case "查找下一个":
		// break;
		// case "转到":
		// break;
		case "全选":
			jTextArea.selectAll();
			break;
		case "时间戳":
			jTextArea.setText(dateAndTime());
			break;

		// 格式 菜单项
		// 自动换行
		case "开启":
			jTextArea.setLineWrap(true);
			break;
		case "关闭":
			jTextArea.setLineWrap(false);
			break;
		case "宋体":
			jTextArea.setFont(new Font("宋体", Font.PLAIN, 14));
			break;
		case "微软雅黑":
			jTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			break;
		case "黑色":
			jTextArea.setForeground(Color.BLACK);
			break;
		case "红色":
			jTextArea.setForeground(Color.RED);
			break;
		case "蓝色":
			jTextArea.setForeground(Color.BLUE);
			break;
		// 模式 菜单项

		// 关于 菜单项
		case "寻找帮助":
			help();
			break;
		case "作者":
			new Author().show();
			break;
		}
	}

	// 打开文件 的方法
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

	// 保存文件 的方法
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

	// 时间戳
	String dateAndTime() {
		String temp = jTextArea.getText();

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);

		return (temp + "\n" + time);
	}

	// 帮助
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
