import java.util.LinkedList;
import java.util.Random;

public class Produser extends Thread {
	LinkedList<Integer> q;

	public Produser(LinkedList<Integer> q) {
		this.q = q;
	}

	@Override
	public void run() {
		synchronized (q) {
//			for (int i = 0; i < 5; i++) {
				Random ran = new Random();
				q.add(ran.nextInt(100));
				System.out.println(" + " + q.get(q.size() - 1) + "\tq.size= " + q.size());
				q.notify();
//			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		LinkedList<Integer> q = new LinkedList<>();
		Thread t[] = new Thread[100] ;

		for (int i = 0; i < t.length; i+=2) {
			t[i] =new Produser(q);
			t[i+1] =new Consumer(q);
			
		}
		for (Thread tt : t) {
			tt.start();
		}

		try {
			for (Thread tt : t) {
				tt.join();
			}
			// Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("q.size in end =" + q.size());
	}

}
