import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class NetworkScanner {

    public static void main(String[] args) {
        final String subnet = "192.168.0"; // your subnet
        int timeout = 1000;
        final int[] portList = new int[]{21, 22, 23, 80, 443}; // common ports to check

        ExecutorService executor = Executors.newFixedThreadPool(20); 

        for (int i = 1; i < 255; i++) {
            final String host = subnet + "." + i;
            executor.execute(() -> {
                try {
                    InetAddress inetAddress = InetAddress.getByName(host);
                    if (inetAddress.isReachable(timeout)) {
                        System.out.println(host + " is reachable");
                        // Check for open ports and service banners
                        for (int port : portList) {
                            try (Socket socket = new Socket()) {
                                // Connect to the host on the specified port
                                socket.connect(new InetSocketAddress(host, port), timeout);
                                System.out.println("Port " + port + " is open on host " + host);
                                
                                // Try to read the service banner
                                try (BufferedReader in = new BufferedReader(
                                        new InputStreamReader(socket.getInputStream()))) {
                                    String banner = in.readLine();
                                    if (banner != null) {
                                        System.out.println("Service banner for host " + host + " on port " + port + ": " + banner);
                                    }
                                }
                            } catch (IOException ex) {
                                // Failed to connect; the port is closed or blocked
                            }
                        }
                    }
                } catch (IOException e) {
                    // Handle exceptions
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}