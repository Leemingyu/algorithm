import java.util.ArrayList;
import java.util.Stack;

/**
 * 求后缀表达式及运算结果
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
						// 当前输入的运算符s优先级高于栈顶运算符top的优先级，s入栈
						expStack.push(s);
					} else {
						// 当前输入的运算符s优先级不高于栈顶运算符top的优先级，将比s优先级低或等于的出栈
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
				// 纯数字
				newExp.add(s);
			}
		}
		while (!expStack.isEmpty()) {
			newExp.add(expStack.pop());
		}
		return newExp;
	}

	/**
	 * 比较优先级，若inputOper优先级小于等与sTopOperator返回false，大于返回true
	 * 
	 * @param inputOper    输入运算符
	 * @param sTopOperator 栈顶运算符
	 */
	public boolean comparePro(String inputOper, String sTopOperator) {
		// 如果栈顶运算符为最括号，直接将输入运算符压栈
		if ("(".equals(sTopOperator)) {
			return true;
		}
		// 如果当前输入的操作运算符为“+”或“-”，并且栈顶运算符为“*”或“/”时，输入的运算符优先级大
		if (("*".equals(inputOper) || "/".equals(inputOper))
				&& ("+".equals(sTopOperator) || "-".equals(sTopOperator))) {
			return true;
		}
		return false;
	}

	/**
	 * 根据中缀表达式计算最终结果
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
				// 栈不空，取出栈中前两个元素
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
