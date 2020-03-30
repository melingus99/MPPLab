
import Service.CommunicationServiceClient;
import TcpClient.TcpClient;
import Ui.Console;
import common.CommunicationService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        TcpClient tcpClient = new TcpClient();
        CommunicationService communicationService = new CommunicationServiceClient(executorService, tcpClient);
        Console console = new Console(communicationService);
        console.runConsole();

        executorService.shutdown();

        System.out.println("bye client");
    }
}
