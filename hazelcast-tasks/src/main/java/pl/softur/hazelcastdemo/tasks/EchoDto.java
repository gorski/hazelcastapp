package pl.softur.hazelcastdemo.tasks;

import java.io.Serializable;

/**
 * Echo transport object.
 */
public class EchoDto implements Serializable {

    private Long maxMemory;
    private String username;
    private String os;
    private int processors;
    private String message;
    private String address;

    public Long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(Long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getProcessors() {
        return processors;
    }

    public void setProcessors(int processors) {
        this.processors = processors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EchoDto{" +
                "maxMemory=" + maxMemory +
                ", username='" + username + '\'' +
                ", os='" + os + '\'' +
                ", processors=" + processors +
                ", message='" + message + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
