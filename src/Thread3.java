import data.FileUtils;

import java.io.IOException;

public class Thread3 extends Thread{

    private BlockingQueue results;

    int counter = 0;

    public Thread3(BlockingQueue BQ){
        this.results = BQ;
    }

    public void setbq(BlockingQueue BQ){
        this.results = BQ;
    }
    public BlockingQueue getbq(){
        return this.results;
    }


    @Override
    public void run(){


        String fileinfo = "";
        String res = "";

        while (true){

            if(counter >= 99){
                System.out.println(this.getName());
                break;
            }

            try {
                if(results.isEmpty())
                    continue;
                else {
                    Object o = results.pop();
                    fileinfo = o.toString();
                    counter++;
                }
            } catch (InterruptedException e) {}

            res += "\n" + fileinfo + "   " + counter + "\n";

        }
        try {
            FileUtils.appendStringToFile("src/results.txt", "file name "+"    "+"#letters"+"   "+"#numbers"+"   "+"#rest");
            FileUtils.appendStringToFile("src/results.txt", res);
        }catch (IOException ioe){}


    }

}
