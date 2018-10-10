import java.util.ArrayList;
import java.util.List;

class Window4 implements Runnable {
	// �������
	int rampro = (int) (Math.random() * 100 + 1);
	// �������ʱ��
	int randomTime = (int) (Math.random() * 30 + 2);
	// �������ʱ��
	int randomOpenTime = Common.randomOpenTime;

	public void run() {
		while (true) {
			try {
				show();
				if (  ++randomTime > (12 - randomOpenTime) * 60) {
					System.out.println("���е�12���� �Ѿ�������");
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

		// ���������������,��������
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
			System.out.println(Thread.currentThread().getName() + "������Ϊ��" + rampro--);
			System.out.println(Thread.currentThread().getName() + "ʣ������" + rampro);
		}
	}
}

public class TestImpl {
	public static void main(String[] args) {
		int randomOpenTime = Common.randomOpenTime;
		System.out.println("��ǰ���ɵ�ʱ����" + randomOpenTime);

		Window4 w = new Window4();
		Thread t1 = new Thread(w);
		Thread t2 = new Thread(w);
		Thread t3 = new Thread(w);

		t1.setName("����1");
		t2.setName("����2");
		t3.setName("����3");
		if (randomOpenTime >= 8 && randomOpenTime <= 12) {
			t1.start();
			t2.start();
			t3.start();
		} else {
			System.out.println("�����Ѿ�������");
		}
	}
}
