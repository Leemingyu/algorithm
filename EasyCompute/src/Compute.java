import java.util.ArrayList;
import java.util.Scanner;

/**
 * ��׺���ʽ
 */
public class Compute {

    public static void main(String[] args) {
        Operators op = new Operators();
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        ArrayList<String> res = op.getHouZuiBDS(expression);
        System.out.print("��׺���ʽ��");
        for (String string : res) {
            System.out.print(string+" ");
        }
        int result = op.getResult(res);
        System.out.println();
        System.out.println("��������"+result);
    }
}
