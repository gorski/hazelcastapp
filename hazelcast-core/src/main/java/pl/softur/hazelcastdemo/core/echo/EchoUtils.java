package pl.softur.hazelcastdemo.core.echo;

import pl.softur.hazelcastdemo.tasks.EchoTask;


public class EchoUtils {

    private EchoUtils() {
    }

    public static EchoTask[] generateEchoTasks(int amount) {
        EchoTask[] messages = new EchoTask[amount];
        for (int i = 0; i < amount; i++) {
            messages[i] = new EchoTask("Message #" + i);
        }
        return messages;
    }
}
