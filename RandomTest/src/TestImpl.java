import java.util.ArrayList;
import java.util.List;

class Window4 implements Runnable {
	// 随机人数
	int rampro = (int) (Math.random() * 100 + 1);
	// 随机办理时间
	int randomTime = (int) (Math.random() * 30 + 2);
	// 生成随机时间
	int randomOpenTime = Common.randomOpenTime;

	public void run() {
		while (true) {
			try {
				show();
				if (  ++randomTime > (12 - randomOpenTime) * 60) {
					System.out.println("银行到12点了 已经关门了");
					System.exit(0);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("static-access")
	public synchronized void show() throws InterruptedException {

		// 随机生成银行人数,放入数组
//	    List<Integer> list = new ArrayList<>();
//		
//		for (int i = 0; i <=rampro ; i++) {
//			list.add(i);
//		}
		if (rampro != 0) {
			try {
				Thread.currentThread().sleep(randomTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			for (int num = 0; num <= list.size(); ++num) {
//				list.remove(num);
			System.out.println(Thread.currentThread().getName() + "办理，号为：" + rampro--);
			System.out.println(Thread.currentThread().getName() + "剩余人数" + rampro);
		}
	}
}

public class TestImpl {
	public static void main(String[] args) {
		int randomOpenTime = Common.randomOpenTime;
		System.out.println("当前生成的时间是" + randomOpenTime);

		Window4 w = new Window4();
		Thread t1 = new Thread(w);
		Thread t2 = new Thread(w);
		Thread t3 = new Thread(w);

		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");
		if (randomOpenTime >= 8 && randomOpenTime <= 12) {
			t1.start();
			t2.start();
			t3.start();
		} else {
			System.out.println("银行已经关门了");
		}
	}
}
