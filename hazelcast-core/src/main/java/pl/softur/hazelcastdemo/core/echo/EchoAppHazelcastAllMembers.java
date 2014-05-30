package pl.softur.hazelcastdemo.core.echo;

import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.Member;
import com.hazelcast.core.MultiExecutionCallback;
import pl.softur.hazelcastdemo.core.bin.ClusterApp;
import pl.softur.hazelcastdemo.tasks.EchoTask;

import java.util.Arrays;
import java.util.Map;

public class EchoAppHazelcastAllMembers {

    public static void main(String[] args) {


        IExecutorService ex = ClusterApp.getHzInstance().getExecutorService("default");
        for (EchoTask m : EchoUtils.generateEchoTasks(1)) {
            ex.submitToAllMembers(m, new MultiExecutionCallback() {
                @Override
                public void onResponse(Member member, Object value) {
                    System.out.println("Member : " + member + " completed");
                }

                @Override
                public void onComplete(Map<Member, Object> values) {
                    System.out.println("Complete " + Arrays.toString(values.entrySet().toArray()));
                }
            });
        }
    }
}
