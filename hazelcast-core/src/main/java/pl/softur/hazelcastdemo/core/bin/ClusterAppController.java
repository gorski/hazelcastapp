package pl.softur.hazelcastdemo.core.bin;

import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.Member;
import com.hazelcast.core.MultiExecutionCallback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.softur.hazelcastdemo.tasks.EchoDto;
import pl.softur.hazelcastdemo.tasks.EchoTask;
import pl.softur.hazelcastdemo.tasks.UUIDGenerationJob;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Controller

public class ClusterAppController {


    @RequestMapping("/")
    @ResponseBody
    public String home() throws InterruptedException {
        final IExecutorService ex = ClusterApp.getHzInstance().getExecutorService("default");
        final EchoTask m = new EchoTask("Broadcast");
        final Map<Member, Object> obj = new HashMap<Member, Object>();
        ex.submitToAllMembers(m, new MultiExecutionCallback() {
            @Override
            public void onResponse(Member member, Object value) {
                System.out.println("----");
                System.out.println(member);
                System.out.println(value);

            }

            @Override
            public void onComplete(Map<Member, Object> values) {
                synchronized (obj) {
                    obj.notify();
                }
                obj.putAll(values);
            }


        });
        synchronized (obj) {
            obj.wait();
        }
        return writeResponse(obj);
    }


    @RequestMapping("/uuid")
    @ResponseBody
    public String uuid() throws InterruptedException {
        final IExecutorService ex = ClusterApp.getHzInstance().getExecutorService("default");
        final UUIDGenerationJob job = new UUIDGenerationJob();
        Future<String> submit = ex.submit(job);
        try {
            String s = submit.get();
            return s;
        } catch (ExecutionException e) {
            return e.toString();
        }
    }

    private String writeResponse(Map<Member, Object> map) {
        StringBuffer sb = new StringBuffer();
        StringBuffer users = new StringBuffer();
        DecimalFormat df = new DecimalFormat("#.##");

        long memory = 0;
        int cores = 0;
        sb.append("<html><body><style>*{ font-family: Tahoma, Geneva, sans-serif; font-size:1.2em;}</style>");
        for (Member member : map.keySet()) {
            Object obj = map.get(member);
            if (obj instanceof EchoDto) {
                EchoDto dto = (EchoDto) obj;
                users.append(dto.getUsername()).append(",");
                cores += dto.getProcessors();
                memory += dto.getMaxMemory();
            }
        }
        sb.append("Echo message summary.<br/><br/>");
        sb.append("Total Memory: <strong>").append(df.format(memory / 1073741824.)).append("</strong> Gb <br/>");
        sb.append("Total Cores: <strong>").append(cores).append("</strong><br/>");
        sb.append("Total Nodes: <strong>").append(map.size()).append("</strong><br/>");
        sb.append("Users: ").append(users).append("<br/>");
        sb.append("</body></html>");
        return sb.toString();
    }


}
