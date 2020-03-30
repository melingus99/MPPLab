package Service;


import TcpClient.TcpClient;
import common.CommunicationService;
import common.Message;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


public class CommunicationServiceClient implements CommunicationService {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public CommunicationServiceClient(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<String> sayHello(String name) {
        return executorService.submit(() -> {
            //create a request
            //send request to server
            //get response

            Message request = new Message(CommunicationService.SAY_HELLO, name);
            System.out.println("sending request: "+request);
            Message response = tcpClient.sendAndReceive(request);
            System.out.println("received response: "+response);

            return response.getBody();
        });
    }
    @Override
    public Future<String> sendMessage(String command,String parameters){
        return executorService.submit(() -> {
            //create a request
            //send request to server
            //get response

            Message request = new Message(command,parameters);
            System.out.println("sending request: "+request);
            Message response = tcpClient.sendAndReceive(request);
            System.out.println("received response: "+response);

            return response.getBody();
        });
    }
}
