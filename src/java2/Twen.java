package java2;
import java.util.Scanner;
public class Twen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
    System.out.print("나이를 입력하시오:");
    int age = scanner.nextInt();
    if((age>=20) && (age<30)) {
        System.out.print("20대입니다.");
        System.out.print("20대라 행복합니다!");
    }
    else {
     System.out.print("20대가 아닙니다ㅠ.");
    }
    scanner.close();
    }
    
}
