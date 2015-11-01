package com.lesterprojects.messageserver;

import java.net.ServerSocket;
import java.net.Socket;

import com.lesterprojects.messagehandler.MessageHandler;

import java.io.*;

public class ClientListener implements Runnable {
    
	private String m_host;
	private int m_port;
	private Thread m_thread;
	private ServerSocket m_serverSocket;
	private MessageHandler m_messageHandler;
	
	private static final int max_connections = 1;

	
	ClientListener(MessageHandler messageHandler, String host, int port){
		m_messageHandler = messageHandler;
		m_host = host;
		m_port = port;
		
	}
	
	public void run(){
		try {
			m_serverSocket = new ServerSocket (m_port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {

			System.out.println("Waiting for client..");
			Socket connection;
			
			try {
				connection = m_serverSocket.accept();
				System.out.println("Client accepted.");
				
				if (ClientCounter.clientCounter > max_connections-1) {
					System.out.println("Too many users");
					connection.close();
					continue;
				} else {
					new MessageServer(m_host, connection, m_messageHandler).start();
					ClientCounter.clientCounter++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void start(){
		if(m_thread == null){
			m_thread = new Thread(this, "ClientListener");
			m_thread.start();
		}
	}
	
}
