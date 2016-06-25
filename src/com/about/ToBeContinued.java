package com.about;

import javax.swing.*;

public class ToBeContinued extends JFrame{
	private JFrame frame;
	private JLabel label;
	
	public ToBeContinued(){
		frame = new JFrame("未完待续");
		label = new JLabel("此功能尚未完成，敬请期待。");
		
		frame.add(label);
		frame.setSize(250, 150);
		frame.setVisible(true);
//		frame.pack();
		
	}
//	public static void main(String[] args) {
//		new ToBeContinued();
//	}
}
