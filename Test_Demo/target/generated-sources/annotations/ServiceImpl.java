import Test_Demo.Test_Demo;
import java.lang.Exception;
import java.lang.Override;
import java.lang.Runtime;
import java.lang.String;
import java.lang.System;
import java.lang.Thread;
import micronet.network.Context;
import micronet.network.IPeer;
import micronet.network.factory.PeerFactory;

public final class ServiceImpl {
  public static void main(String[] args) {
    try {
      System.out.println("Starting Test_Demo...");

      IPeer peer = PeerFactory.createPeer();
      Context context = new Context(peer, "mn://round");
      Test_Demo service = new Test_Demo();

      System.out.println("Registering message listeners...");

      System.out.println("Test_Demo started...");
      service.onStart(context);

      Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
        public void run() {
          System.out.println("Test_Demo stopped...");
          service.onStop(context);
        }
      });
    }
    catch (Exception e) {
      System.err.println("Could not start Test_Demo...");
      e.printStackTrace();
    }
  }
}
