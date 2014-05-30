package pl.softur.hazelcastdemo.tasks;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Echo task.
 */
public class EchoTask implements Callable<EchoDto>, Serializable, HazelcastInstanceAware {

    private String message;
    private transient HazelcastInstance hazelcast;

    public EchoTask() {
    }

    public EchoTask(String message) {
        this.message = message;
    }

    @Override
    public EchoDto call() {

        EchoDto dto = new EchoDto();
        dto.setMessage(message);
        dto.setAddress(hazelcast == null ?
                "N/A" :
                hazelcast.getCluster().getLocalMember().getSocketAddress().toString());

        Runtime runtime = Runtime.getRuntime();
        dto.setMaxMemory(runtime.maxMemory());
        dto.setOs(System.getProperty("os.name"));
        dto.setUsername(System.getProperty("user.name"));
        dto.setProcessors(runtime.availableProcessors());

        return dto;
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcast = hazelcastInstance;
    }
}
