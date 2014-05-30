package pl.softur.hazelcastdemo.core.bin;

import com.hazelcast.core.*;

import java.util.Set;


public class ClusterManagement {

    public static void main(String[] args) {

//        Config cfg = new Config("dev");
//        HazelcastInstance instance = Hazelcast.getOrCreateHazelcastInstance(cfg);
        HazelcastInstance instance = ClusterApp.getHzInstance();
        Cluster cluster = instance.getCluster();


        cluster.addMembershipListener(new MembershipListener() {
            @Override
            public void memberAdded(MembershipEvent membershipEvent) {
                System.out.println("*HAZELCAST DEMO* New member joined: " + membershipEvent.getMember());
            }

            @Override
            public void memberRemoved(MembershipEvent membershipEvent) {
                System.out.println("*HAZELCAST DEMO* Member left: " + membershipEvent.getMember());
            }

            @Override
            public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent) {
                System.out.println("*HAZELCAST DEMO* Member changed: " + memberAttributeEvent.getMember());
            }
        });
        Member localMember = cluster.getLocalMember();
        System.out.println(localMember.getSocketAddress());
        Set<Member> setMembers = cluster.getMembers();
        System.out.println(setMembers);

    }


}
