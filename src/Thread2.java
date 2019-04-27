import data.FileUtils;

import java.io.IOException;

public class Thread2 extends Thread {

    private BlockingQueue storedBQ;
    private  BlockingQueue resultBQ ;

    static int counter = 1;

    public Thread2(BlockingQueue storedBQ, BlockingQueue resultBQ) {
        this.storedBQ = storedBQ;
        this.resultBQ = resultBQ;

    }

    public BlockingQueue getResultBQ() {
        return this.resultBQ; }
    public BlockingQueue getStoredBQ(){
        return this.resultBQ; }

    @Override
    public void  run(){

        int L = 0;
        int N = 0;
        int R = 0;

        String filename = "";
        String text = "";

        while (true) {

            if(counter >= 100)
            {
                System.out.println(counter);
                System.out.println(this.getClass().getName());
                break;
            }

            try {
                if(storedBQ.isEmpty())
                    continue;
                else {
                    Object o = storedBQ.pop();
                    filename = o.toString();
                    counter++;
                }
            } catch (InterruptedException e) {}

            try {
                text = FileUtils.readFileAsString(filename);
            }catch(IOException ioe){}

            for (int i = 0; i < text.length(); i++) {
                Character ch = text.charAt(i);

                if ((Integer.valueOf(ch) > 64 && Integer.valueOf(ch) < 91) || ((Integer.valueOf(ch) > 96 && Integer.valueOf(ch) < 123))) {
                    L++;
                } else if (Integer.valueOf(ch) > 47 && Integer.valueOf(ch) < 58) {
                    N++;
                } else {
                    R++;
                }
            }
            String fileinfo = filename + "    " + String.valueOf(L) + "    " + String.valueOf(N) + "    " + String.valueOf(R);

            try{
                resultBQ.add(fileinfo);
            }catch(InterruptedException ie){}

            L = 0;
            N = 0;
            R = 0;

            }
    }




}
