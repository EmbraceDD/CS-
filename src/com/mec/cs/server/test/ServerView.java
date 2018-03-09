package com.mec.cs.server.test;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mec.cs.server.core.Server;
import com.mec.cs.server.interfaces.IServerViewListenner;

public class ServerView implements IServerViewListenner{
	private JFrame frame;
	private Container container;
	private TextArea area;
	private JLabel jLabel;
	private JButton button;
	private JTextField field;
	
	private Server server;
	
	public ServerView() {
		
		Font fontNormal = new Font("宋体", Font.PLAIN, 16);
		frame = new JFrame("服务器端窗口");
		container =frame.getContentPane();      
		container.setLayout(null);     //取消默认位置，自己去设置
		frame.setSize(900, 600);	   //设置白板窗口的大小；
		frame.setLocation(200, 100);
		
		area = new TextArea();
		area.setBounds(10, 10, 850, 430);
		area.setBackground(Color.white);
		area.setFont(fontNormal);
		area.setEditable(false);
		container.add(area);
		
		jLabel = new JLabel("输入框");
		jLabel.setBounds(200, 450, 80, 32);
		jLabel.setFont(fontNormal);
		container.add(jLabel);
		
		field = new JTextField();
		field.setBounds(290, 450, 250, 32);
		field.setFont(fontNormal);
		container.add(field);
       
       button = new JButton("发送");
       button.setFont(fontNormal);
       button.setBounds(600, 450, 70, 32);
       container.add(button);
       
       server = new Server(this,54188);
       dealClientAction();
       
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame.setVisible(true);
		
	}

	private void dealClientAction() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				area.setEditable(true);
				String command = field.getText();
				field.setText("");
				    if(command.equals("exit")){
				    	frame.setVisible(false);
				    	frame.dispose();
				    	return;
				    }else if(command.equals("startup") || command.equals("st")){
						server.startUp();
						
					}else if(command.equals("shutdown")|| command.equals("sd")){
						if(server.getCommunicationCount() >0){
							area.append("当前存在回话，不能关闭服务器\n");
							return;
						}
						server.shutDown();
					}else if(command.equals("forcedown")||command.equals("fd")){
						server.shutDown();
					}else{
						area.append("无效命令！");
					}
				    area.setEditable(false);
				}
		});
		
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				server.shutDown();
				frame.setVisible(false);
		    	frame.dispose();
			}
		});
	}

	@Override
	public void serverMessageGianed(String message) {
		area.setEditable(true);
		area.append(message+"\n");
		area.setEditable(false);
	}

}
