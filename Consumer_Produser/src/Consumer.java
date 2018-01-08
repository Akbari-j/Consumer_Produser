import java.util.LinkedList;

public class Consumer extends Thread {
	LinkedList<Integer> q;

	public Consumer(LinkedList<Integer> q) {
		this.q = q;
	}

	@Override
	public void run() {
		synchronized (q) {
//			for (int i = 0; i < 5; i++) {
			// رشته مصرف کننده را  در  زمانی که مخزن خالی است نگه می دارد تا رشته تولید کننده مخزن را پر کند
				while (q.isEmpty()) {
					try {
						q.wait();

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(" - " + q.poll() + "\tq.size= " + q.size());
				
				//برای اینکه ترتیب خواندن و نوشتن منظم نباشد
				try {
					Thread.sleep((long) (Math.random()*10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//			}
		}
	}
}
