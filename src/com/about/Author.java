package com.about;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Author extends JFrame{
	public Author(){

		setTitle("作者");
		setSize(250, 130);
		setVisible(true);
		
		JTextArea author = new JTextArea();
		add(author);	
		String str= "姓名：****\n学号：*******\n班级：*******\nEmail：*******@163.com";
		author.setText(str);
		author.setEditable(false);	// 将文本域设置为不可编辑
	}
}
