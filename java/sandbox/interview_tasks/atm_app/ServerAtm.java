package sandbox.interview_tasks.atm_app;

import java.util.Map;

public class ServerAtm {
    Map<String,String> clients;

    public ServerAtm() {
        this.clients = Map.of("1","1111",
                "2","4242",
                "3","9800");
    }

    public void authClient(DebitCard card, String pincode) throws IllegalAccessException {
       if(! clients.get(card.getClientId()).equals(pincode)){
           throw new IllegalAccessException("pin code not valid");
       }
    }
}
