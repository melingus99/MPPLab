package service;


import common.CommunicationService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


public class CommunicationServiceImpl implements CommunicationService {
    private ExecutorService executorService;

    public CommunicationServiceImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Future<String> sayHello(String name) {
        return executorService.submit(() -> "Hello " + name);
    }

//    @Override
//    public Future<String> sayBye(String name) {
//        return executorService.submit(() -> "Bye " + name);
//    }
    @Override
    public Future<String> sendMessage(String header,String body){
        //TBA
        return executorService.submit(() -> "Hello " + header);
    }
}
