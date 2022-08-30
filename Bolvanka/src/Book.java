public class Book {
    String word;
    Book(){
    	word="computersa";
    }
    public static void main(String[] args) {
        Thread wr[] = new Thread[10];
        Thread rd[] = new Thread[10];
        for (int i = 0; i < wr.length; i++) {
            wr[i] = new Thread(new ThreadWrite(), "Thread write" + i);
             wr[i].setPriority(Thread.MIN_PRIORITY + (Thread.MAX_PRIORITY -
             Thread.MIN_PRIORITY) / wr.length * i);
            rd[i] = new Thread(new ThreadWrite(), "Thread read" + i);
             rd[i].setPriority(Thread.MIN_PRIORITY + (Thread.MAX_PRIORITY -
             Thread.MIN_PRIORITY) / wr.length * i);
 
        }
 
        for (int i = 0; i < wr.length; i++) {
            wr[i].start();
            System.out.println(wr[i].getName() + " started");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
 
            rd[i].start();
            System.out.println(rd[i].getName() + " started");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
 
        }
    }
}
 
class ThreadWrite implements Runnable {
    // String wodr;
    Book ms = new Book();
 
    public void run() {
 
        synchronized (ms.word) {
            try {
                ms.word.wait();
 
                for (int i = 0; i < 50000; i++) {
                    if (i % 10000 == 0) {
                        ms.word += i;
 
                        System.out.println(getName() + " counts " + i);
                        ms.word.notifyAll();
                    }
                }
 
            } catch (InterruptedException e) {
            }
        }
    }
 
    public String getName() {
        return Thread.currentThread().getName();
    }
}
 
class ThreadRead {
    // String wodr;
    Book ms = new Book();
 
    public void run() {
        synchronized (ms.word) {
            try {
                ms.word.wait();
 
                // wodr = ms.word;
                // вычисления
 
                System.out.println(getName() + " counts " + ms.word);
            } catch (InterruptedException e) {
 
            }
        }
    }
 
    public String getName() {
        return Thread.currentThread().getName();
    }
}