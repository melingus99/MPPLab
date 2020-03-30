
import common.CommunicationService;
import common.Message;
import tcp.TcpServer;
import service.CommunicationServiceImpl;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ServerApp {

    public static void main(String[] args) {
        try {


            System.out.println("server started");
            ExecutorService executorService = Executors.newFixedThreadPool(
                    Runtime.getRuntime().availableProcessors()
            );
            CommunicationService communicationService =  new CommunicationServiceImpl(executorService);

            TcpServer tcpServer = new TcpServer(executorService);

            tcpServer.addHandler(communicationService.SAY_HELLO, (request) -> {
                String name = request.getBody();
                Future<String> future = communicationService.sayHello(name);
                try {
                    String result = future.get();
                    return new Message("ok", result); //fixme: hardcoded str
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return new Message("error", e.getMessage());//fixme: hardcoded str
                }

            });

//        tcpServer.addHandler(common.HelloService.SAY_BYE, (request) -> {
//            String name = request.getBody();
//            Future<String> future = helloService.sayBye(name);
//            try {
//                String result = future.get();
//                return new common.Message("ok", result);
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//                return new common.Message("error", e.getMessage());
//            }
//        });

            tcpServer.startServer();

            executorService.shutdown();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

    }
}
