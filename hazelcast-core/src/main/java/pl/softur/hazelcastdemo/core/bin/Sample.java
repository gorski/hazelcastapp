package pl.softur.hazelcastdemo.core.bin;

import com.hazelcast.config.Config;
import com.hazelcast.config.InterfacesConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;

/**
 * Created with IntelliJ IDEA.
 * User: Marcin
 * Date: 27.05.14
 * Time: 23:28
 * To change this template use File | Settings | File Templates.
 */
public class Sample {

    public static void main(String[] args) {


        InterfacesConfig interfacesConfig = new InterfacesConfig();
        interfacesConfig.addInterface("129.168.0.*");

        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setInterfaces(interfacesConfig);
        networkConfig.setPort(9293);

        Config config = new Config("dev");
        config.setNetworkConfig(networkConfig);

        Hazelcast.getOrCreateHazelcastInstance(config);


        IMap

    }
}
