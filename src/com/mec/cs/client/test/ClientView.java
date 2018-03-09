package com.mec.cs.client.test;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.List;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.plaf.SliderUI;

import com.mec.cs.client.core.MecClient;
import com.mec.cs.client.interfaces.IClientViewListenner;
import com.mec.cs.common.CommunicationCommand;
import com.mec.cs.common.ConversationCommand;
import com.mec.cs.common.ConversationMessage;
import com.mec.request.model.StudentModel;
import com.mec.request.util.ParameterParser;

public class ClientView implements IClientViewListenner{
	 private JFrame  jframView;
	  private Container container;
	  private JButton jContButton;
	  
	  private JFrame jframClient;
	  private Container containerClient;
	  private JLabel jLabel;
	  private JTextField jtxtEnter;
	  private JButton jbtnEnter;
	  private TextArea area;
	  
	  private MecClient client;
	  private Font fontNormal;
	  
	  public ClientView() {
			fontNormal = new Font("宋体", Font.PLAIN, 16);
			
			jframView = new JFrame("连接窗口");
			container =jframView.getContentPane();      
			container.setLayout(null);     //取消默认位置，自己去设置
	       jframView.setSize(500, 400);	   //设置白板窗口的大小；
	       jframView.setLocation(600, 400);
	       
	       jContButton = new JButton("连接服务器");
	       jContButton.setFont(fontNormal);
	       jContButton.setBounds(150, 180, 200, 32);
	     
	       container.add(jContButton);
	       initClientView();
	       dealConnectionAction();
	       jframView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       jframView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	       jframView.setVisible(true);
	}
	  
	  private void initClientView() {
		   jframClient = new JFrame("客户端窗口");
			containerClient =jframClient.getContentPane();      
			containerClient.setLayout(null);     //取消默认位置，自己去设置
			jframClient.setSize(900, 600);	   //设置白板窗口的大小；
			jframClient.setLocation(200, 100);
			
			area = new TextArea();
			area.setBounds(10, 10, 850, 430);
			area.setBackground(Color.white);
			area.setFont(fontNormal);
			area.setEditable(false);
			containerClient.add(area);
			
			jLabel = new JLabel("输入框");
			jLabel.setBounds(200, 450, 80, 32);
			jLabel.setFont(fontNormal);
			containerClient.add(jLabel);
			
			jtxtEnter = new JTextField();
			jtxtEnter.setBounds(290, 450, 250, 32);
			jtxtEnter.setFont(fontNormal);
			containerClient.add(jtxtEnter);
	       
	       jbtnEnter = new JButton("发送");
	       jbtnEnter.setFont(fontNormal);
	       jbtnEnter.setBounds(600, 450, 70, 32);
	       containerClient.add(jbtnEnter);
	       dealClientAction();
	       
	       jframClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       jframClient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	       jframClient.setVisible(false);
	       client = new MecClient("localhost",54188,this) {
			@Override
			public void netFailure() {
                      jframClient.dispose();				
			}
			
			@Override
			public void dealResponseMessage(String action, String message) {
				 area.append(action +"：请求的响应结果：" +message);
			}
		};
	  }
	  
	  public void dealClientAction() {
		  jbtnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				  String message = jtxtEnter.getText();
				  jtxtEnter.setText("");
				  area.setEditable(true);
				  
				  if(message.equals("byebye")){
					  client.setCommand(ConversationCommand.OFF_LINE);
					  client.setActionAndParameter(null, null);;
					  try {
						Thread.sleep(200);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					  jframClient.dispose();
					  return;
				  }
				    int index = message.indexOf("@");
					if(index == -1) {
						area.append("错误信息\n");
						return;
					}
					String [] str = message.split("@");
					
					if(str[0].trim().equals("all")){
						area.append("你选择的是广播发送.......\n");
						client.setCommand(ConversationCommand.TO_ALL);
						client.setActionAndParameter(null, str[1]);;
					}else if(str[0].trim().equals("other")){
						client.setCommand(ConversationCommand.TO_OTHER);
						client.setActionAndParameter(null, str[1]);
						area.append("你选择的是群发.......\n");
					}else if(str[0].equals("request")){
						client.setCommand(ConversationCommand.REQUEST);
						StudentModel model = new StudentModel();
						model.setId("000001");
						model.setName("李芷源");
						model.setSex("女");
						String id = "007";
						ParameterParser parameterParser = new ParameterParser();
//						client.setActionAndParameter("getStudentById", parameterParser.addOgnl("id", id)
//								.addOgnl("name", name).toString());
						java.util.List<StudentModel>  modelList = new ArrayList<>();
						modelList.add(model);
						modelList.add(model);
						modelList.add(model);
//						client.setActionAndParameter("setStudentListAndId", parameterParser.addOgnl("studentModelList", modelList)
//								.addOgnl("id", id).toString());
						Map<String, StudentModel> studentMap = new HashMap<>();
						studentMap.put("0001", model);
						studentMap.put("0002", model);
						studentMap.put("0003", model);
						client.setActionAndParameter("getClassInfoById", parameterParser
								.addOgnl("id", id).toString());
						area.append("正在请求资源.....\n");
					}
				  area.setEditable(false);
			}
		});
		  jframClient.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				client.offLine();
			}
		});
		  
		 // jtxtEnter.add
	  }
	  
	  public void dealConnectionAction() {

	       jContButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.start();
				if(client.isStart()){
					jframView.dispose();
					jframClient.setVisible(true);
				}
			}
		});
		  
	  }


	@Override
	public void clientMessageGianed(String message) {
		this.area.setEditable(true);
		this.area.append(message+"\n");
		this.area.setEditable(false);
	}
	
	

}
