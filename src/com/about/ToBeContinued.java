package com.about;

import javax.swing.*;

public class ToBeContinued extends JFrame{
	private JFrame frame;
	private JLabel label;
	
	public ToBeContinued(){
		frame = new JFrame("δ�����");
		label = new JLabel("�˹�����δ��ɣ������ڴ���");
		
		frame.add(label);
		frame.setSize(250, 150);
		frame.setVisible(true);
//		frame.pack();
		
	}
//	public static void main(String[] args) {
//		new ToBeContinued();
//	}
}
