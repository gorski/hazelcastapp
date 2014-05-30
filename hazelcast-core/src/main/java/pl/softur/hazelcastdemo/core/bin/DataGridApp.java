package pl.softur.hazelcastdemo.core.bin;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Queue;

public class DataGridApp {

    public static void main(String[] args) {

        String currentHour = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

        Map<String, String> demoMap = ClusterApp.getDemoMap();

        System.out.println("Initial Map Size:" + demoMap.size());
        demoMap.put(currentHour, "Sample text inside my map");
//        demoMap.put("demo", "Hazelcast !");

        System.out.println("Map size: " + demoMap.size());

        Queue<String> demoQueue = ClusterApp.getDemoQueue();

        System.out.println("Initial Queue size: " + demoQueue.size());
        demoQueue.offer("Task scheduled at " + currentHour);

        System.out.println("Queue size: " + demoQueue.size());

    }
}
