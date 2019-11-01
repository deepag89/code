

class ThreadEx implements Runnable{
    String ar[];
    boolean even;

    ThreadEx(String ar[], boolean even) {
        this.ar = ar;
        this.even = even;
    }

    @Override
    public void run() {
        for(String str: ar) {
            if(even && str.length() %2 == 0)
                System.out.println(str);
            if(!even && str.length() %2 != 0)
                System.out.println(str);
        }
    }
}

public class Threading {

    public static void main(String[] args) {
        String ar[] = {"hello", "world", "good", "morning"};

        ThreadEx run1 = new ThreadEx(ar, true);
        Thread thread1 = new Thread(run1);

        ThreadEx run2 = new ThreadEx(ar, false);
        Thread thread2 = new Thread(run2);

        thread1.start();
        thread2.start();
    }

}
