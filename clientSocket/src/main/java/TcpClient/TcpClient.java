package TcpClient;


import common.CommunicationServiceException;
import common.Message;

import java.io.IOException;
import java.net.Socket;


public class TcpClient {
    public Message sendAndReceive(Message request) {
        try (var socket = new Socket(Message.HOST, Message.PORT);
             var is = socket.getInputStream();
             var os = socket.getOutputStream()
        ) {
            System.out.println("sendAndReceive - sending request: " + request);
            request.writeTo(os);

            System.out.println("sendAndReceive - received response: ");
            Message response = new Message();
            response.readFrom(is);
            System.out.println(response);

            return response;
        } catch (IOException e) {
            throw new CommunicationServiceException("error connection to server " + e.getMessage(), e);
        }
    }
}
