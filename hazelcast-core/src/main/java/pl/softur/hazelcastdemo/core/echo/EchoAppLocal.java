package pl.softur.hazelcastdemo.core.echo;

import pl.softur.hazelcastdemo.tasks.EchoDto;
import pl.softur.hazelcastdemo.tasks.EchoTask;

public class EchoAppLocal {

    public static void main(String[] args) {

        for (EchoTask task : EchoUtils.generateEchoTasks(100)) {
            EchoDto call = task.call();
            System.out.println("Echo: " + call.toString());
        }
    }
}
