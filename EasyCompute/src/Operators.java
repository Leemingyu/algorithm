import java.util.ArrayList;
import java.util.Stack;

/**
 * ���׺���ʽ��������
 */
public class Operators {
	// 9 + ( 3 - 1 ) * 3
	public ArrayList<String> getHouZuiBDS(String expression) {
		Stack<String> expStack = new Stack<String>();
		ArrayList<String> newExp = new ArrayList<String>();
		String[] split = expression.split(" ");
		for (int i = 0; i < split.length; i++) {
			String s = split[i];
			if ("(".equals(s)) {
				expStack.push(s);
			} else if (")".equals(s)) {
				String pop;
				while (!expStack.isEmpty()) {
					pop = expStack.pop();
					if (pop.equals("(")) {
						break;
					} else {
						newExp.add(pop);
					}
				}
			} else if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)) {
				if (expStack.isEmpty()) {
					expStack.push(s);
				} else {
					String top = expStack.peek();
					if (comparePro(s, top)) {
						// ��ǰ����������s���ȼ�����ջ�������top�����ȼ���s��ջ
						expStack.push(s);
					} else {
						// ��ǰ����������s���ȼ�������ջ�������top�����ȼ�������s���ȼ��ͻ���ڵĳ�ջ
						while (!expStack.isEmpty()) {
							String pop = expStack.peek();
							if (!comparePro(s, pop)) {
								newExp.add(expStack.pop());
							} else {
								break;
							}
						}
						expStack.push(s);
					}
				}
			} else {
				// ������
				newExp.add(s);
			}
		}
		while (!expStack.isEmpty()) {
			newExp.add(expStack.pop());
		}
		return newExp;
	}

	/**
	 * �Ƚ����ȼ�����inputOper���ȼ�С�ڵ���sTopOperator����false�����ڷ���true
	 * 
	 * @param inputOper    ���������
	 * @param sTopOperator ջ�������
	 */
	public boolean comparePro(String inputOper, String sTopOperator) {
		// ���ջ�������Ϊ�����ţ�ֱ�ӽ����������ѹջ
		if ("(".equals(sTopOperator)) {
			return true;
		}
		// �����ǰ����Ĳ��������Ϊ��+����-��������ջ�������Ϊ��*����/��ʱ���������������ȼ���
		if (("*".equals(inputOper) || "/".equals(inputOper))
				&& ("+".equals(sTopOperator) || "-".equals(sTopOperator))) {
			return true;
		}
		return false;
	}

	/**
	 * ������׺���ʽ�������ս��
	 */
	public int getResult(ArrayList<String> expression) {
		Stack<Integer> resStack = new Stack<Integer>();
		for (String str : expression) {
			if (str.matches("\\d+")) {
				resStack.push(Integer.valueOf(str));
			} else {
				if (resStack.isEmpty()) {
					return -1;
				}
				// ջ���գ�ȡ��ջ��ǰ����Ԫ��
				int tem1 = resStack.pop();
				int tem2 = resStack.pop();
				int temRes = 0;
				switch (str) {
				case "+":
					temRes = tem2 + tem1;
					break;

				case "-":
					temRes = tem2 - tem1;
					break;
				case "*":
					temRes = tem2 * tem1;
					break;
				case "/":
					temRes = tem2 / tem1;
					break;
				}
				resStack.push(temRes);
			}
		}
		if (resStack.size() == 1) {
			return resStack.pop();
		} else {
			return -1;
		}
	}
}
