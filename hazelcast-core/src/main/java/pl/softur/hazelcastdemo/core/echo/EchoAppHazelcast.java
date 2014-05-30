package pl.softur.hazelcastdemo.core.echo;

import com.hazelcast.config.Config;
import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import pl.softur.hazelcastdemo.core.bin.ClusterApp;
import pl.softur.hazelcastdemo.tasks.EchoDto;
import pl.softur.hazelcastdemo.tasks.EchoTask;

public class EchoAppHazelcast {

    public static void main(String[] args) {

//        Config config = new Config("dev");
//        HazelcastInstance h = Hazelcast.getOrCreateHazelcastInstance(config);
        HazelcastInstance h = ClusterApp.getHzInstance();
        IExecutorService ex = h.getExecutorService("default");
        for (EchoTask m : EchoUtils.generateEchoTasks(100)) {
            ex.submit(m, new ExecutionCallback<EchoDto>() {
                @Override
                public void onResponse(EchoDto response) {
                    System.out.println("RESPONSE: " + response);
                }

                @Override
                public void onFailure(Throwable t) {
                    System.out.println("ERR: " + t.getMessage());
                }
            });
        }
        h.shutdown();


    }
}
