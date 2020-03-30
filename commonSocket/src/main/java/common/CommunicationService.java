package common;

import java.util.concurrent.Future;

/**
 * Created by radu.
 */
public interface CommunicationService {
    String SAY_HELLO = "sayHello";
    String SAY_BYE = "sayBye";

    Future<String> sayHello(String name);

    Future<String> sendMessage(String header,String body);
//    Future<String> sayBye(String name);


}
