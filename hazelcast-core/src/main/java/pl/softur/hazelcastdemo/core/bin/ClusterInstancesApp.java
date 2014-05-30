package pl.softur.hazelcastdemo.core.bin;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Set;

public class ClusterInstancesApp {

    public static void main(String[] args) {
        Set<HazelcastInstance> allHazelcastInstances = Hazelcast.getAllHazelcastInstances();
        System.out.println("Instances:");
        for (HazelcastInstance instance : allHazelcastInstances){
            System.out.println(instance);
        }
    }
}
