package com.about;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Author extends JFrame{
	public Author(){

		setTitle("����");
		setSize(250, 130);
		setVisible(true);
		
		JTextArea author = new JTextArea();
		add(author);	
		String str= "������****\nѧ�ţ�*******\n�༶��*******\nEmail��*******@163.com";
		author.setText(str);
		author.setEditable(false);	// ���ı�������Ϊ���ɱ༭
	}
}
