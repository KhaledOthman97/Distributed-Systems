import com.sun.jdi.Value;

public class Thread1 extends Thread{
    private BlockingQueue Directories;

    public Thread1(BlockingQueue BQ){
        this.Directories = BQ;
    }

    public BlockingQueue getbq(){
        return this.Directories;
    }

    @Override
    public void run () {

        int i = 0;
        while(true){
            String filename = "src/data/f" + String.valueOf(i) + ".txt";
            try {
                Directories.add(filename);
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
            if(i == 99){
                System.out.println(this.getClass().getName());
                this.yield();
                break;
            }
            i++;
        }
    }
}
