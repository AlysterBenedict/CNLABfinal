import java.util.Scanner;

public class LeakyBucket {
    private int bucketSize, outputRate;
    private int currentSize = 0;

    public LeakyBucket(int bucketSize, int outputRate) {
        this.bucketSize = bucketSize;
        this.outputRate = outputRate;
    }

    public void addPacket(int packetSize) {
        if (currentSize + packetSize <= bucketSize) {
            currentSize += packetSize;
            System.out.println("Packet of size " + packetSize + " added to the bucket. Current size: " + currentSize);
        } else {
            System.out.println("Packet of size " + packetSize + " discarded. Bucket overflow!");
        }
    }

    public void leak() {
        if (currentSize >= outputRate) {
            currentSize -= outputRate;
            System.out.println("Leaked " + outputRate + " units. Current size: " + currentSize);
        } else {
            System.out.println("Leaked " + currentSize + " units. Bucket is now empty.");
            currentSize = 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter bucket size: ");
        int bucketSize = scanner.nextInt();

        System.out.print("Enter output rate: ");
        int outputRate = scanner.nextInt();

        LeakyBucket bucket = new LeakyBucket(bucketSize, outputRate);

        while (true) {
            System.out.print("Enter packet size to add (or enter 0 to leak): ");
            int packetSize = scanner.nextInt();
            if (packetSize > 0) {
                bucket.addPacket(packetSize);
            } else {
                bucket.leak();
            }

            System.out.print("Continue? (yes/no): ");
            String continueInput = scanner.next();
            if (continueInput.equalsIgnoreCase("no")) {
                break;
            }
        }

        scanner.close();
    }
}
