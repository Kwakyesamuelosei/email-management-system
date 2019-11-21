package io.turntabl.email;

public class Client {
    public String client_name;
    private String client_address;
    private String client_telephone;
    private String client_email;

    public Client(String client_name, String client_address, String client_telephone, String client_email) {
        this.client_name = client_name;
        this.client_address = client_address;
        this.client_telephone = client_telephone;
        this.client_email = client_email;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getClient_address() {
        return client_address;
    }

    public String getClient_telephone() {
        return client_telephone;
    }

    public String getClient_email() {
        return client_email;
    }

    @Override
    public String toString() {
        return "{" +
                "client_name='" + client_name + '\'' +
                ", client_address='" + client_address + '\'' +
                ", client_telephone='" + client_telephone + '\'' +
                ", client_email='" + client_email + '\'' +
                '}';
    }
}
