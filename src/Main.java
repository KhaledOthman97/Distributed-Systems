import java.io.IOException;

public class Main {

    public static void main(String args[]) throws InterruptedException, IOException {
        BlockingQueue Directories = new BlockingQueue(8);
        BlockingQueue fileInfo = new BlockingQueue(1);

        Thread1 th1 = new Thread1(Directories);

        Thread2[] th2 = new Thread2[8];
        for (int i = 0; i < 8; i++){
            th2[i] = new Thread2(Directories, fileInfo);
        }

        Thread3 th3 = new Thread3(fileInfo);

        th1.start();
        for (int i = 0; i < 8; i++){
            th2[i].start();
        }
        th3.start();



    }
}
