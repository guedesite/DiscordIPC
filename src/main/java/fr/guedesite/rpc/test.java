package fr.guedesite.rpc;

import java.time.OffsetDateTime;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;

public class test {

	public static void main(String[] args) {
		IPCClient client = new IPCClient(788137279163203594L);
		Thread t = new Thread() {
			@Override
			public void run() {
				client.setListener(new IPCListener(){
				    @Override
				    public void onReady(IPCClient client)
				    {
				        RichPresence.Builder builder = new RichPresence.Builder();
				        builder.setState("Explore les Terres désolé")
				            .setDetails("Joue sur Feu-Ardent.fr")
				            .setStartTimestamp(OffsetDateTime.now())
				            .setLargeImage("logo", "Feu-Ardent.fr");
				        client.sendRichPresence(builder.build());
				    }
				});
				try {
					client.connect();
					System.out.println("connect");
					
				} catch (NoDiscordClientException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		try {
			System.out.println("wait");
			Thread.sleep(500000);
			client.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
