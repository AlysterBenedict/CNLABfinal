import java.util.Random;

class SlidingWindow {
    public static void main(String[] args) {
        int windowSize = 4; // Window size for the sliding window protocol
        int totalFrames = 10; // Total number of frames to be sent
        int[] frames = new int[totalFrames];

        // Initialize frames with dummy data
        for (int i = 0; i < totalFrames; i++) {
            frames[i] = i;
        }

        Sender sender = new Sender(windowSize, frames);
        Receiver receiver = new Receiver(windowSize);

        sender.sendFrames(receiver);
    }
}

class Sender {
    private int windowSize;
    private int[] frames;

    public Sender(int windowSize, int[] frames) {
        this.windowSize = windowSize;
        this.frames = frames;
    }

    public void sendFrames(Receiver receiver) {
        int start = 0;
        int end = Math.min(windowSize, frames.length);

        while (start < frames.length) {
            System.out.println("Sending frames from " + start + " to " + (end - 1));
            for (int i = start; i < end; i++) {
                System.out.println("Sent frame: " + frames[i]);
                receiver.receiveFrame(frames[i]);
            }

            receiver.sendAck();

            start = end;
            end = Math.min(end + windowSize, frames.length);
        }
    }
}

class Receiver {
    private int windowSize;
    private Random random = new Random();

    public Receiver(int windowSize) {
        this.windowSize = windowSize;
    }

    public void receiveFrame(int frame) {
        System.out.println("Received frame: " + frame);
    }

    public void sendAck() {
        int ack = random.nextInt(windowSize);
        System.out.println("Acknowledgement sent for frame: " + ack);
    }
}
