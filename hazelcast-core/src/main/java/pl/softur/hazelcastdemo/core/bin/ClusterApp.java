package pl.softur.hazelcastdemo.core.bin;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;
import java.util.Queue;

@ComponentScan
@EnableAutoConfiguration
public class ClusterApp {

    private static HazelcastInstance hzInstance = Hazelcast.getOrCreateHazelcastInstance(new Config("dev"));

    public static HazelcastInstance getHzInstance() {
        return hzInstance;
    }

    public static Map<String, String> getDemoMap() {
        return hzInstance.getMap("demo");
    }

    public static Queue<String> getDemoQueue() {
        return hzInstance.getQueue("demo");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClusterApp.class, args);
    }
}
