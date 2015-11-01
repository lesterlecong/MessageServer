package com.lesterprojects.messageserver;

import com.lesterprojects.messagehandler.CouchbaseMessageHandler;
import com.lesterprojects.messageparser.JSONDatabaseMessageParser;
import com.lesterprojects.messageparser.MessageParser;
import com.lesterprojects.messageserver.ClientListener;

public class MessageHandlerTest {

	public static void main(String[] args) {
			
		
		
		CouchbaseMessageHandler cbMessageHandler = new CouchbaseMessageHandler();
		MessageParser parser = new JSONDatabaseMessageParser();
		cbMessageHandler.addMessageParser(parser);
		
		ClientListener clientListener = new ClientListener(cbMessageHandler, "localhost", 8089);
		clientListener.start();
			
	}

}
