package com.example.java_course;

//import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.fluent.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class HttpSuggestion extends JFrame {

	JTextField txtInput = new JTextField();
	JList<String> lstSuggestion = new JList<>();

	public HttpSuggestion() {
		super("自动推荐 v1");
		getContentPane().add(BorderLayout.NORTH, txtInput);
		getContentPane().add(BorderLayout.CENTER,
				new JScrollPane(lstSuggestion));
		setSize(1000, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		txtInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//new Thread(() -> {
					try {
						Thread.sleep(10000);
						String word = txtInput.getText();
						String[] suggestion = fetchSuggestion(word);
						SwingUtilities.invokeLater(() -> {
							lstSuggestion.removeAll();
							lstSuggestion.setListData(suggestion);
							lstSuggestion.updateUI();
						});
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				//}).start();
			}
		});

		lstSuggestion.addListSelectionListener(e -> {
			txtInput.setText(lstSuggestion.getSelectedValue());
		});
	}

	public static void main(String... args) throws Exception {
		// String word = "��";
		// fetchSuggestion(word);

		SwingUtilities.invokeLater(() -> {
			new HttpSuggestion();
		});

	}

	public static String[] fetchSuggestion(String word)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException {
		if (word == null || word.length() == 0)
			return new String[0];

		String url = "http://suggestion.baidu.com/su?wd="
				+ URLEncoder.encode(word, "utf-8");
		url += "&rnd=" + Math.random();

		String content = Request.Get(url)
				.addHeader("cookie", "BDUSS=Aadasdfsfee").execute()
				.returnContent().asString();

		System.out.println(content);
		// window.baidu.sug({q:"��",p:false,s:["��������","��������ͼƬ","������","����������Ӱ","����Ҷ���Ԫ����","����","���˴�","����Ӱ��"]});

		String[] sug = content.replaceAll(".*,s:\\[([^\\]]*)\\].*", "$1")
				.replaceAll("\"", "").split(",");
		return sug;
	}

}
