# 이한민 202130126
9,10,12,13장 


## 6월 14일  

### 스트림 연결  
- 텍스트 파일을 읽기 위해 문자 스트림  FileReader 클래스 이용  
- 파일 입출력동안 예외가 발생할수 있다 --> FileNotFoundException 발생  
ex )  
```java
import java.io.*;

    public class FileReaderEx {
        public static void main(String[] args) {
            FileReader in = null;
    try {
        in = new FileReader("c:\\windows\\system.ini");
               int c;
        while ((c = in.read()) != -1) { 
                System.out.print((char)c);
              }
              in.close();
             }
              catch (IOException e) {
               System.out.println("입출력 오류");
  }
 }
}
```  
- 텍스트 파일에 쓰기 위해 문자 스트림 FileWriter 클래스 이용  
- 바이너리 값을 파일에 저장하기  
- 프로그램 내의 변수, 배열, 버퍼에 든 바이너리 값을 파일에 그대로 기록 : FileOutputStream 사용  
- 바이너리 파일 읽기 : FileInputStream  


## 6월 7일  

### 스윙 컴포넌트  
- 스윙의 페인팅 기본  
1) 모든 컴포넌트는 자신의 모양을 스스로 그린다.  
2) 컨테이너는 자신을 그린 후 그 위에 자식 컴포넌트들에게 그리기 지시  
3) paintComponent() 보유  

- JPanel 

- Graphics  
1) 색 : Color  
2) 폰트 : Font  
- 도형 그리기  
1) 선의 굵기 조절 X 

- drawimage() 메소드
ex)  
```java
import javax.swing.*;
import java.awt.*;
    public class paintJPanelEx extends JFrame {
        public paintJPanelEx() {
            setTitle("JPanel의 paintComponent() 예제");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setContentPane(new MyPanel());
            setSize(250,200);
            setVisible(true);
}

    class MyPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE); 
            g.drawRect(10,10,50,50);
            g.drawString("굿잡이에요",50,50);
            g.setColor(new Color(255, 0, 0)); 
            g.setFont(new Font("Arial", Font.ITALIC, 30));
            g.drawString("akakakak", 30, 120);
            g.drawRect(90,90,50,50);
            g.drawLine(20, 20, 100, 100);
            g.drawRoundRect(20,20,120,80,40,60);
            g.drawArc(20,100,80,80,90,270);
        }
}
    public static void main(String [] args) {
        new paintJPanelEx();
}
}
```  
- repaint()  
1) 모든 컴포넌트가 가지고있는 메소드  
2) 개발자가 컴포넌트를 다시 그릴때 필요  
ex)  
```java 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicsDrawOvalMouseEx extends JFrame {
    public GraphicsDrawOvalMouseEx() {
        setTitle("마우스 드래깅으로 타원 그리기 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(300, 300);
         setVisible(true);
}
    public static void main(String [] args) {
        new GraphicsDrawOvalMouseEx();
}

    class MyPanel extends JPanel {
        java.awt.Point start=null;
        private java.awt.Point end=null; 
        public MyPanel() {
        MyMouseListener listener = new MyMouseListener();

         addMouseListener(listener);
            addMouseMotionListener(listener);
    }
class MyMouseListener extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
            start = e.getPoint();
    }
    public void mouseDragged(MouseEvent e) {
            end = e.getPoint();
             repaint(); 
    }
    }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
         if(start == null) 
            return;
            g.setColor(Color.BLUE); 
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            g.drawOval(x, y, width, height); 
    }
    }
    }
```  
### 자바 스레드  

- 멀티태스킹

- 스레드 : 운영체제에 의해 관리되는 하나의 작업 혹은 태스크  
- 스레드와 태스크는 바꾸어 사용해도 무관  
- 멀티스레딩 : 여러 스레드를 동시에 실행시키는 응용프로그램을 작성하는 기법  

- 멀티테스킹 구현 기술  
1) 멀티프로세싱 : 하나의 응용프로그램이 여러개의 프로세스 생성, 각 프로세스가 하나의 작업을 처리하는 기법  
2) 멀티스레딩 : 하나의 응용프로그램이 여러개의 스레드를 생성, 각 스레드가 하나의 작업을 처리  
- 자바 스레드 : 자바 가상 기계에 의해 스케쥴되는 실행 단위의 코드 블럭  
- 스레드 만드는 방법 : java.lang.Thread , java.lang.Runnable  
ex) 
```java 
import java.awt.*;
import javax.swing.*;

class TimerThread extends Thread {
    private JLabel timerLabel; 
        public TimerThread(JLabel timerLabel) {
            this.timerLabel = timerLabel;
}

@Override
    public void run() {
        int n=0; 
        while(true) { 
        timerLabel.setText(Integer.toString(n));
        n++; 
        try {
        Thread.sleep(1000); 
        }
        catch(InterruptedException e) { return;}
        }
    }
}
public class ThreadTimerEx extends JFrame {
    public ThreadTimerEx() {
    setTitle("Thread를 상속받은 타이머 스레드 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    
    JLabel timerLabel = new JLabel();
    timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
    c.add(timerLabel);
    TimerThread th = new TimerThread(timerLabel);
    setSize(250,150);
    setVisible(true);
    th.start(); 
    }
    public static void main(String[] args) {
    new ThreadTimerEx();
    }
    }
```  
- 진동하는 스레드  
ex) 
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class  VibratingFrame extends JFrame implements Runnable{

    private Thread th;
    public VibratingFrame() {
        setTitle("진동하는 프레임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200,200);
        setLocation(300,300);
        setVisible(true);

        getContentPane().addMouseListener(new MouseAdapter() {
         public void mousePressed(MouseEvent e) {
            if(!th.isAlive()) return;
            th.interrupt();
         }   
        });
        th =new Thread(this);
        th.start();
    }

    public void run() {
        Random r=new Random();
        while (true) {
            try{
                Thread.sleep(20);
            }
            catch(InterruptedException e) {
                return;
            }
            int x=getX() + r.nextInt()%5;
            int y=getY() + r.nextInt()%5;
            setLocation(x, y);
        }
    } 
    public static void main(String[] args) {
        new VibratingFrame();
    }

}
```
- 스레드 동기화 : 스레드 사이의 실행순서 제어, 공유데이터에 대한 접근을 원할하게 하는 기법  
- 자바의 스레드 동기화 방법 : synchronized 키워드, wait() - notify()메소드  
- 동기화의 필요성 : 두 스레드가 프린터에 동시에 쓰기로 충돌하는 경우  
- synchronized 키워드 : 스레드가 독점적으로 실행해야하는 부분을 표시하는 키워드  
ex)  
```java 
public class SynchronizedEx {
    public static void main(String[] args) {
    SharedPrinter p = new SharedPrinter(); 
    String [] engText = { "Wise men say, ",
                        "only fools rush in",
                        "But I can't help, ",
                        "falling in love with you",
                        "Shall I stay? ",
                        "Would it be a sin?",
                        "If I can't help, ",
                        "falling in love with you" };
    String [] korText = { "동해물과 백두산이 마르고 닳도록, ",
                        "하느님이 보우하사 우리 나라 만세",
                        "무궁화 삼천리 화려강산, ",
                        "대한 사람 대한으로 길이 보전하세",
                        "남산 위에 저 소나무, 철갑을 두른 듯",
                        "바람서리 불변함은 우리 기상일세.",
                        "무궁화 삼천리 화려강산, ",
                        "대한 사람 대한으로 길이 보전하세" };
    Thread th1 = new WorkerThread(p, engText);
    Thread th2 = new WorkerThread(p, korText);
 
    th1.start();
    th2.start();
    }
    }
    class SharedPrinter {
        
        synchronized void print(String text) { //<-- 사용
     
        for(int i=0; i<text.length(); i++)
        System.out.print(text.charAt(i));
        System.out.println();
        }
        }
    
        class WorkerThread extends Thread {
        private SharedPrinter p; 
        private String [] text;
        public WorkerThread(SharedPrinter p, String[] text) {
        this.p = p; this.text = text;
        }
       
        @Override
        public void run() {
        for (int i=0; i<text.length; i++) 
        p.print(text[i]); 
        }
        }
```  
- wait()-notify() : 공유 데이터로 두 개 이상의 스레드가 데이터를 주고 받을 때


## 5월 31일

### 자바 GUI 프로그래밍 방법
- 컴포넌트 기반 GUI 프로그래밍
- 그래픽 기반 GUI 프로그래밍  
### JComponent 
- : 스윙 컴포넌트는 모두 상속받는 슈퍼 클래스, 추상 클래스 
ex) 
```java 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JcomponentEx extends JFrame{
    public JcomponentEx() {
        super("j컴포넌트의 공동 메소드 예제");
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton b1 = new JButton("magenta/tellow button");
        JButton b2 = new JButton("Disabled Button");
        JButton b3 = new JButton("getx(), gety()");
        b1.setBackground(Color.YELLOW);
        b1.setBackground(Color.MAGENTA);
        b1.setFont(new Font("Arial", Font.ITALIC, 20));
        b2.setEnabled(false);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               JButton b =(JButton)e.getSource();
               setTitle(b.getX() + "," + b.getY());
            }
        });
        c.add(b1); c.add(b2); c.add(b3);
        setSize(260, 200); setVisible(true);
    }
    public static void main(String[] args) {
        new JcomponentEx();
    }
}
```
### JLabel
- JLabel의 용도 : 문자열이나 이미지를 화면에 출력하기 위한 목적
ex)  
```java 
import javax.swing.*;
import java.awt.*;

public class JlableEx extends JFrame{
    public JlableEx() {
        setTitle("레이블 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c= getContentPane();
        c.setLayout(new FlowLayout());

        JLabel textLabel = new JLabel("제임스 뭐시기");

        ImageIcon img = new ImageIcon("images/gosling.jpg");
        JLabel imageLabel = new JLabel(img);

        ImageIcon icon = new ImageIcon("images/icon.gif");
        JLabel label = new JLabel("커피 가자", icon, SwingConstants.CENTER );
        c.add(textLabel);  c.add(imageLabel); c.add(label);
        setSize(300, 500);
        setVisible(true);
    }
    public static void main(String[] args) {
        new JlableEx();
    }
} 
```
### JButton
- 용도 : 버튼 모양의 컴포넌트 
- 이미지 로딩 
### checkBox
- 선택과 비선택 두 상태만 가지는 버튼  

ex)

```java 
import javax.swing.*;
import java.awt.*;
public class CheckBoxEx extends JFrame {
    public CheckBoxEx() {
        setTitle("체크박스 만들기 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        JCheckBox apple = new JCheckBox("사과");
        JCheckBox pear = new JCheckBox("배", true);
        JCheckBox cherry = new JCheckBox("체리");
        c.add(apple);
        c.add(pear);
        c.add(cherry);
        setSize(250,150);
        setVisible(true);
}
    public static void main(String [] args) {
        new CheckBoxEx();
}
}
```
### Itemevent
- item 이벤트 : 체크박스의 선택 상태에 변화가 생길때 발생하는 이벤트  

ex)

```java 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class CheckBoxItemEventEx extends JFrame {
    private JCheckBox [] fruits = new JCheckBox [3];
    private String [] names = {"사과", "배", "체리"};
    private JLabel sumLabel;
    
    public CheckBoxItemEventEx() {
        setTitle("체크박스와 ItemEvent 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(new JLabel("사과 100원, 배 500원, 체리 20000원"));
        MyItemListener listener = new MyItemListener();
         for(int i=0; i<fruits.length; i++) {
          fruits[i] = new JCheckBox(names[i]);
          fruits[i].setBorderPainted(true);
          c.add(fruits[i]);
          fruits[i].addItemListener(listener);
}
        sumLabel = new JLabel("현재 0 원 입니다.");
        c.add(sumLabel);
        setSize(250,200);
        setVisible(true);
}
class MyItemListener implements ItemListener {
    private int sum = 0; // 가격의 합
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
         if(e.getItem() == fruits[0])
             sum += 100;
        else if(e.getItem() == fruits[1])
            sum += 500;
        else
            sum += 20000;
        }
        else {
            if(e.getItem() == fruits[0])
            sum -= 100;
        else if(e.getItem() == fruits[1])
            sum -= 500;
        else
            sum -= 20000;
    }
    sumLabel.setText("현재 " + sum + "원 입니다.");
    }
    }
     public static void main(String [] args) {
         new CheckBoxItemEventEx();
    }
    }
```

### RadioButton
- 버튼 그룹을 생성항ㄴ후, 그룹에 속한 버튼중 하나만 선택되는 버튼  

ex)  
```java 
import javax.swing.*;
import java.awt.*;

public class RadioButtonEx extends JFrame {
    public RadioButtonEx() {
        setTitle("라디오버튼 만들기 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        ButtonGroup g = new ButtonGroup(); 
        ButtonGroup f = new ButtonGroup(); 
        
        JRadioButton apple = new JRadioButton("사과");
        JRadioButton pear = new JRadioButton("배", true);
        JRadioButton cherry = new JRadioButton("체리");
        JRadioButton banana = new JRadioButton("바나나");
    
        g.add(apple);
        g.add(pear);


        f.add(cherry);
        f.add(banana);
      
        c.add(apple); c.add(pear); c.add(cherry); c.add(banana);
        setSize(250,150);
        setVisible(true);
}
    public static void main(String [] args) {
        new RadioButtonEx();
}
}

```
### JtextField
- 한 줄의 문자열을 입력 받는 창 -> enter키 입력하면 action  

### JtextArea
- 여러 줄의 문자열을 입력받을 수 잇는 창  
ex)  

```java 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TextAreaEx extends JFrame {
    private JTextField tf = new JTextField(20);
    private JTextArea ta = new JTextArea(7, 20);
    public TextAreaEx() {
    setTitle("텍스트영역 만들기 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    c.add(new JLabel("입력 후 <Enter> 키를 입력하세요"));
    c.add(tf);
    c.add(new JScrollPane(ta));
    tf.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JTextField t = (JTextField)e.getSource();
            ta.append(t.getText() + "\n");
            t.setText("");
         }
         });
    setSize(300,300);
    setVisible(true);
}
    public static void main(String [] args) {
        new TextAreaEx();
}
}
```

### JList<E>  
- 하나 이상의 아이템을 보여주고 아이템을 선택하도록 하는 리스트  
ex)  

```java
import javax.swing.*;
import java.awt.*;

public class ListEx extends JFrame {
    private String [] fruits= {"apple", "banana", "kiwi", "mango",
        "pear", "peach", "berry", "strawberry", "blackberry"};
        private ImageIcon [] images = { new ImageIcon("images/icon1.png"),
        new ImageIcon("images/icon.gif"),
        new ImageIcon("images/icon3.png"),
       
        new ImageIcon("images/icon4.png") };
        public ListEx() {
            setTitle("리스트 만들기 예제");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container c = getContentPane();
            c.setLayout(new FlowLayout());
           
            JList<String> strList = new JList<String>(fruits);
            c.add(strList);
            JList<ImageIcon> imageList = new JList<ImageIcon>();
            imageList.setListData(images);
            c.add(imageList);
            JList<String> scrollList = new JList<String>(fruits);
            c.add(new JScrollPane(scrollList));
           
            setSize(300,300); setVisible(true);
}
    public static void main(String [] args) {   
        new ListEx();
}
}
```

### JComboBox
- 텍스트 필드와 버튼, 그리고 드롭다운 리스트로 구성되는 콤보박스  

### 메뉴 구성  
- 메뉴 아이템 :JMenuitem  
- 메뉴 : JMenu  
- 메뉴 바 : JMenuBar  
- 분리선 : 메뉴 아이템 사이의 분리선  

ex)  
```java
import javax.swing.*;
public class MenuEx extends JFrame {
    public MenuEx() {
        setTitle("Menu 만들기 예제");
        createMenu(); // 메뉴 생성, 프레임에 삽입
        setSize(250,200);
        setVisible(true);
    }
    public void createMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu screenMenu = new JMenu("Screen");
        screenMenu.add(new JMenuItem("Load"));
        screenMenu.add(new JMenuItem("Hide"));
        screenMenu.add(new JMenuItem("ReShow"));
        screenMenu.addSeparator();
        screenMenu.add(new JMenuItem("Exit"));
        mb.add(screenMenu);
        mb.add(new JMenu("Edit"));
        mb.add(new JMenu("Source"));
        mb.add(new JMenu("Project"));
        mb.add(new JMenu("Run"));
        setJMenuBar(mb);
}
public static void main(String [] args) {
new MenuEx();
}
}
```  



## 5월 24일
### 이벤트 기반 프로그래밍
1. 이벤트의 발생에 의해 프고르램이 결정되는 방식  반대되는 방식 : 배치 실행  
이벤트 종류 : 마우스 드레그, 마우스 클릭, 키보드 누름 등 - 네트워크로부터 데이터 송수신  
2. 이벤트 객체가 포함하는 정보
- 이벤트 종류와 이벤트 소스  
- 이벤트가 발생한 화면 좌표 및 컴포넌트 내 좌표  
- 이벤트가 발생한 버튼이나 메뉴 아이템의 문자열  
- 클릭된 마우스 버튼 번호 및 마우싀 클릭 횟수  
- 키의 코드 값과 문자 값  
3. 리스너 인터페이스  
- 이벤트 리스너 : 이벤트를 처리하는 자바 프로그램 코드, 클래스로 작성  
- 다양한 리스너 인터페이스  
ex) interface MouseListener, ActionListener  
- 인터페이스 구현  
ex) class Name implement ActionListener 상속을 받을떄 extends 말고 implement 사용  
4. 이벤트 리스너 작성 방법  
- 3가지 방법  
1) 독립 클래스로 작성 
2) 내부 클래스(inner class)로 작성
3) 익명 클래스(anonymosu class)로 작성

ex) 독립코드로 만들떄
 ```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IndepClassListener extends JFrame{
    public IndepClassListener() {
        setTitle("Actrion 이벤트 리스너");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton btn = new JButton("Action");
        btn.addActionListener(new MyActionListener());
        c.add(btn);

        setSize(250, 120);
        setVisible(true);
    }
    public static void main(String[] args) {
        new IndepClassListener();
    }
    
}

class MyActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.getText().equals("Action"))
        b.setText("액션");
        else
        b.setText("Action");
    }
}
```
ex) 내부 클래스로 만들떄  
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IndepClassListener extends JFrame{
    public IndepClassListener() {
        setTitle("Actrion 이벤트 리스너");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton btn = new JButton("Action");
        btn.addActionListener(new MyActionListener());
        c.add(btn);

        setSize(250, 120);
        setVisible(true);
    }
    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            if(b.getText().equals("Action"))
            b.setText("액션");
            else
            b.setText("Action");

            IndepClassListener.this.setTitle(b.getText());
        }
    }

    public static void main(String[] args) {
        new IndepClassListener();
    }
    
}
```
5. 익명 클래스  
- 이름 없는 클래스: 클래스 선언+ 인스턴스 생성 한번에 하기
ex) 익명 클래스 예제  
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnonymousClassListener extends JFrame{
    public AnonymousClassListener() {
        setTitle("Actrion 이벤트 리스너");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton btn = new JButton("Action");
        c.add(btn);
        
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b =(JButton)e.getSource();
                if(b.getText().equals("Action"))
                b.setText("액션");
                else
                b.setText("Action");
                setTitle(b.getText());
            }
        });

        setSize(250, 120);
        setVisible(true);
    }
    

    public static void main(String[] args) {
        new AnonymousClassListener();
    }
    
} 
```
6. 마우스 이벤트 리스너  
ex) 예제 
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseListenerEx extends JFrame{
    private JLabel la =new JLabel("Hello");

    public MouseListenerEx() {
        setTitle("Mouse 이벤ㅌ ㅇ제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c=getContentPane();
        c.addMouseListener(new MyMouseListener());

        c.setLayout(null);
        la.setSize(50, 20);
        la.setLocation(30, 30);
        c.add(la);

        setSize(200, 200);
        setVisible(true);
    }

    class MyMouseListener implements MouseListener{
        public void mousePressed(MouseEvent e) {
            int x=e.getX();
            int y=e.getY();
            la.setLocation(x, y);
        }
        public void mouseReleased(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
    public static void main(String[] args) {
        new MouseListenerEx();
    }
}
```
7. 키보드 이벤트와 포커스  
- 키 입력시, 다음 세 경우 각각 Key 이벤트 발생  
1) 키를 누르는 순간  
2) 누른 키를 떼는 순간  
3) 누른 키를 뗴는 순간(unicode 키의 경우만)  
ex)
```java import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyCharEx extends JFrame{
    private JLabel la= new JLabel("enter키로 배경색이 바뀝니다");
    public KeyCharEx() {
        super("KeyLitener의 문자 키 입력 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c=getContentPane();
        c.setLayout(new FlowLayout());
        c.add(la);
        c.addKeyListener(new MyKeyListener()); 
        setSize(200, 200);
        setVisible(true);

        c.setFocusable(true);
        c.requestFocus();
       }
       class MyKeyListener extends KeyAdapter {
        public void KeyPressed(KeyEvent e){
            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256);
            switch (e.getKeyChar()) {
                case '\n':
                la.setText("r=" + r +", g=" + g + ", b=" + b);
                getContentPane().setBackground(new Color(r, g, b));
                    
                    break;
            
                case 'q' : System.exit(0);
            }
        }
       }
       public static void main(String[] args) {
        new KeyCharEx();
       }
}
```


## 5월 17일
### 컨테이너 배치  
1. FLowLayout 배치관리자 : 왼쪽에서 오른쪽으로 배치
2. BorderLayout : 컨테이너의 공간을 동,서남,북,중앙의 5개영역으로 나눔
3. GridLayout : 삽입 순서대로 좌에서 우로, 위에서 아래로 배치, 크기가 일정함
4. CardLayout : 컨테이너의 공간에 컴포넌트를 포개어 배치  
setLayout : 새로운 배치관리자 설정 --> 디폴트 배치관리자를 변경
 ex) container.setLayout(new FlowLayout)  

### FlowLayout 
FlowLayout() 대문자로 시작 ex) (FlowLayout LEFT) hGap-> 좌우, vGap-> 상하 디폴트값은 5  
ex)  
```java 
import javax.swing.*;
import java.awt.*;

public class FlowLayoutEx extends JFrame{
    public FlowLayoutEx() {
        setTitle("FLowLayout 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 30,40));
    
        contentPane.add(new JButton("add"));
        contentPane.add(new JButton("sub"));
        contentPane.add(new JButton("mul"));
        contentPane.add(new JButton("div"));
        contentPane.add(new JButton("Calculate"));
        
        setSize(300, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new FlowLayoutEx();
    }
}

```
### BorderLayout 
배치방법 : 컨테이너 공간을 5구역으로 분할,배치
생성자 : BorderLayout() -> 디폴트 값은 0  
ex) 
```java 
import javax.swing.*;
import java.awt.*;

public class BorderLayoutEx extends JFrame{
    public BorderLayoutEx() {
        setTitle("BorderLayoutEx 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        contentPane.setLayout(new BorderLayout(30,40));
    
        contentPane.add(new JButton("Calculate"));
        contentPane.add(new JButton("add"));
        contentPane.add(new JButton("sub"));
        contentPane.add(new JButton("mul"));
        contentPane.add(new JButton("div"));
        
        
        setSize(300, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new BorderLayoutEx();
    }
}

```
### GridLayout
배치방법 : 컨테이너 공간을 동일한 사각형 격자로 분활하고 각 셀에 컴포넌트 하나씩 배치
생성자 : GridLayout(행의 수, 열의 수)  
ex)
```java
import javax.swing.*;
import java.awt.*;

public class BorderLayoutEx extends JFrame{
    public BorderLayoutEx() {
        setTitle("BorderLayoutEx 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        contentPane.setLayout(new BorderLayout(30,40));
    
        contentPane.add(new JButton("Calculate"));
        contentPane.add(new JButton("add"));
        contentPane.add(new JButton("sub"));
        contentPane.add(new JButton("mul"));
        contentPane.add(new JButton("div"));
        
        
        setSize(300, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new BorderLayoutEx();
    }
}
```
### 배치 관리자가 없는 컨테이너가 필요한 경우
- 응용프로그램에서 직접 컴포넌트의 크기의 위치를 결정하고자 하는 경우
### 배치 관리자가 없는 컨테이너에 컴포넌트 삽입
- 프로그램에서 컴폰너트의 절대 크기와 위치 설정
- 컴폰너트들이 서로 겹치게 할 수 있음  
ex)
```java
import javax.swing.*;
import java.awt.*;

public class NullContainerEx extends JFrame {
    public NullContainerEx(){
        setTitle("배치관리자 없이 절대 위치에 배치하는 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        JLabel la = new JLabel("Hello, Press Buttons");
        la.setBounds(130, 50, 200, 20);
        contentPane.add(la);

        for(int i=1; i<9; i++){
            JButton b = new JButton(Integer.toString(i));
            b.setLocation(i*15, i*15);
            b.setSize(50, 20);
            contentPane.add(b);
        }

        setSize(300,200);
        setVisible(true);
    }
    public static void main(String[] args) {
        
    new NullContainerEx();
    }
}
```


## 5월 3일
제네릭 : 특정 타입만ㄴ 다루지않고, 여러 종류의 타입으로 변신할 수 있도록 클래스나 메소드를 일반화 시키는 기법 ex) 벡터  
일반화된 틀을 만드는 기법  
백터 : 배열을 가변 크기로 다룰 수 있게 하는 컨테이너, 요소 객체들을 삽입,삭제,검색하는 컨테이너 기본타입 X 타입매개 변수 사용  
ArrayList : 백터와 거의 비슷하지만 동가화 기능이 없다
HashMap : 키와 값을 쌍으로 
GUI 응용 프로그램  
컨테이너 : 다른 컴포넌트를 포함할 수 있는 GUI컴포넌트  
컴포넌트 : 컨테이너에 포함돠어야 화면에 출력될수 있는 GUI 객체  
스윙 프레임 : 모든 스윙 컴포넌트를 담는 최상위 컨테이너


## 4월 19일
메소드 오버라이딩 : 서브 클래스에서 슈퍼클래스의 메소드 중복 작성  
조건 : 슈퍼 클래스 메소드의 원형  
오버로딩 같은 클래스일떄 사용 정적 바인딩 오버라이딩슈퍼클 서브클에서 사용 동적바인딩  
추상 메소드 : abstract로 선언된 메소드, 메소드의 코드는 없고 원형만 선언  
추상 클래스 : 추상 메소드를 가지며, abstract로 선언된 클래스  
추상 클래스의 목적 : 상속을 위한 슈퍼클래스를 활용하는 것  
자바 인터페이스 : 규격화되ㅏㄴ 모듈로 만든다  
패키지 : 서로 관련된 클래스와 인터페이스를 컴파이한 클래스 파일들을 묶어 놓은 디렉터리  
모듈 : 여러개의 패키지를 모아 놓은 것  
자바 모듈화의 가장 큰 목적 : 자바 컴포넌트들을 필요에 따라 조립하여 사용하기 위함  
모든 패키지는 object클래스에 상속 받음
### 시험 
프로젝트 생성 -> git초기화 commit, CLASS 생성 (생성자) -> 객체만든후 실행  
상속,패키지 - 오픈북, 구글 사용가능


## 4월 12일
자바의 접근 지정자 : private, protected, public, 디폴트(생략)
private < 디폴트 < protected < public    
외부로부터 차단, 동일 패키지 적용, 동일패키지와 자식 클래스에 허용, 모든 클래스 사용가능  
static 멤버 선언   
클래스.변수 =static 객체.변수 = non static  
static 메소드는 static 멤버만 사용가능  
static 메소드는 this 사용불가  
final 클래스 : 더 이상 클래스 상속 불가능  
final 메소드 : 더 이상 오버라이딩 불가능  
final은 실행 중에 값을 변경할 수 없다, 선언 시에 반드시 초기값을 지정해야함  
extends : 상속 선언   
슈퍼 클래스 > 서브클래스 O, 슈퍼클래스 < 서브클래스 X  
java.lang.Object클래스는 모든 클래스의 슈퍼 클래스  
슈퍼 클래스와 서브 클래스 : 각각 여러개의 생성자 작성 가능  
업캐스팅 : 서버 클래스의 레퍼런스를 슈퍼 클래스 레퍼런스에 대입  
다운캐스팅 : 슈퍼클래스 레퍼런스를 서버 클래스 레퍼런스에 대입, 업캐스팅된 것을 다시 원래대로 되돌리는것  
instanceof : 레퍼런스가 가리키는 객체의 타입 식별


## 4월 5일
2차원 배열 int intArray[][]; intArray = new int[2][5]  
배열리턴 : 배열의 레퍼런스만 리턴  
예외 : 실행중 오동작이나 결과에 악영향을 미치는 예상치 못한 상황 발생 try-catch-finally구문 사용 오류를 탐지할수있는 예외 클래스가 있음  
### 4장
 캡슐화 : 객체를 캡슐로 싸서 내부를 볼 수 없게 하는 것  
객체의 가장 본질적인 특징  
상속 : 상위 개체의 속성이 하위 개체에 물려짐  
상위 클래스의 멤버를 하위 클래스가 물려받음  
다형성 : 같은 이름의 메소드가 클래스 혹은 객체에 따라 다르게 구현되는 것  

#### 객체 지향 언어의 목적

소프트웨어의 생산성 향상  
컴퓨터 산업 잘전에 따라 소프트웨어의 생명 주기 단축  
실세계에 대한 쉬운 모델링  
절차 지향 프로그래밍, 함수 지향 프로그래밍  
클래스 : 객체의 속성과 행위 선언  
객체 : 클래스의 틀로 찍어낸 실체  
클래스 구성 요소 : 필드(멤버 변수), 메소드(멤버 함수)  
생성자 : 객체가 생성될 때 초기화 목적으로 실행되는 메소드  
기본 생성자 : 변수가 없고,
this : 객체 자신에 대한 래퍼런스, 메소드에서 사용되며 현재 객체를 가르킴, static에서는 사용 불가  
자바의 객체 배열 : 객체에 대한 레퍼런스 배열
객체 배열 만들기 : 1.배열 레퍼런스 변수 선언 2. 레퍼런스 배열 생성 3. 배열의 각 원소 객체 생성  
메소드는 반드시 클래스 안에 들어있어야함  
메소드 오버로딩 : 한 클래스 내에서 두 개 이상의 이름이 같은 메소드 작성  
객체 치환은 객체를 복사하는게 아닌 레퍼런스를 복사하는 것이다.  
객체 소멸 : new에 의해 할당받은 객체와 배열 메모리를 자바 가상 기계로 되돌려 주는 행위  
가비지 : 가리키는 레퍼런스가 하나도 없는 객체  
패키지 : 상호 관련있는 클래스 파일(컴파일된.class)을 저장하여 관리하는 디렉터리




 ## 3월 29일
 리터럴 : 프로그램에서 직접 표현한 값  
 정수 리터럴 : 10진수, 8진수, 16진수, 2진수 리터럴 +int형 컴파일  
 실수 리터럴 : double로 컴파일 소수점 형태  
 단일 부호리터럴은 char로 컴파일, 특수문자 리터럴은 \로 시작 ex) \n  
 논리 값 true or false로 표시, null값은 래퍼런스에 대입 사용  
 문자열 리터럴은 이중 인용부호 사용 String 객체로 자동 처리  
 상수 선언 final 키워드 사용 ex) final + 데이터타입 + 상수 이름 = 초기화값  
 var은 타입을 생략하고 변수 선언 가능 컴파일러가 알아서 결정 하지만 초깃값은 무조건 있어야함 지역 변수 선언에만 사용 = 메소드 안에서만 사용  
 setting에 들어가서 잡아주면 어디서든 자바 사용가능  
 형 변환 : 한 타입의 값을 다른 타입의 값으로 변환 자동으로 해줌  
 강제 타입 변환은 ()안에 타입 변환 ex) (int), (byte) 등  
 System.in : 키보드와 연결된 자바의 표준 입력 스트림 사용할때는 Scanner사용  
 연산 : 주어진 식을 계산하여 결과를 얻어냄  
 비교 연산자, 논리연산자, 삼항연산자  
 조건문 if, if else문, switch문, case문, break문  
 
 ### 3장 배열문
 자바 반복문 for,while,do-while문 , continue문  
 인덱스 레퍼런스 치환 배열 공유 
 for each문   



 ## 3월 22일
 c+s+p=> java project create 생성후 workspace에 파일 만들기   
 자바 디버그 F5 만약에 디버깅이 안돼면 파일 삭제후 다시 설치하거나 vs코드 재부팅  
 파일 탐색기 좌측위에 파일 파워 쉘 누르기 javac 파일명.java 파일 선택 ls는 파일안에 리스트를 보여주는 것 java 파일명 을하면 자바 실행 
 ### 1장 프로그래밍 언어  
 1.기계어: 0,1 이진수  
 2.어셈블리어: 기계어 명령을 표현하기쉬운 니모닉 기호로 일대일 대응시킨 언어   
 3.고급언어: 사람이 이해하기 쉽고,복잡한 작업,자료 구조,알고리즘을 표현하기 위한 언어  
 소스: 프로그래밍 언어로 작성된 텍스트 파일  
 컴파일: 소스 파일을 컴퓨터가 이해할수있는 기계어로 만드는 과정  
 플랫폼 = 하드웨어 플랫폼 + 운영체제 플랫폼   
 기계어는 cpu마다 다름, 운영체제마다 API가 다름,실행파일 형식도 다름   
 WORA: 한번 작성된 코드는 모든 플랫폼에서 실행되는 자바의 특징<-- 이유는 JVM  
 JVM: 자바 바이트 코드를 실행하는 자바 가상 기계(소프트웨어)  
 바이트 코드: 자바 가상 기계에서 실행 가능한 바이너리 코드, 클래스 파일에 저장  
 JDK : 자바 응용 개발 환경. 개발에 필요한 도구 포함  
 JRE : 자바 실행 환경  
 JDK >> JRE >> JVM  
 모듈 : 자바 패키지들과 이미지,XML,파일 등의 자원들을 묶은 단위  
 자바 API : 주요한 기능들을 미리 구현한 클래스 라이브러리의 집합  
 자바 패키지 : 서로 관련된 클래스들을 분류하여 묶어 놓은 것  
 IDE : 통합 개발 환경  
 이클립스 : 자바 응용프로그램 개발을 위한 통합 개발 환경  
 서블릿 : 웹 서버에서 실행되는 자바 프로그램  
 자바의 특징 : 1) 플랫폼 독립성 2) 객체지향 3) 클래스로 캡슐화 4) 소스와 클래스 파일 
 5) 실행 코드 배포 6) 패키지 7) 멀티스레드 8) 가비지 컬렉션 9) 실시간 응용프로그램에 부적합 10) 자바 프로그램은 안전 11) 프로그램 작성 쉬움 12) 실행 속도 개선을 위한 JIT 컴파일러 사용  
### 2장 
주석 명령어 ctrl+/ 없애는것도 똑같음 여러행 주석 /* */   
표준 출력 스트림 - System.out, 메소드 - println()  
식별자 : 변수,상수,메소드 등에 붙이는 이름 , 특수문자는 사용 X, 한글도 사용 X, 자바언어 키워드 X, 첫번째 숫자 X, 리터럴 사용 X, 대문자를 구별한다  
자바 데이터 타입 : boolean, char, byte, short, int, long, float, double  
문자열은 기본 타입이 아니다, String은 클래스 문자열에 +가 있으면 실수나 정수를 숫자가아닌 문자로 인식한다.  
리터럴 : 프로그램에서 직접 표현한 값



 ## 3월 15일 
 오늘은 git에서 새로운repository를 하나 만들었다 거기서 클론으로 내README 파일을 하나 만들었다    
 commit은 왠만해선 영어로 쓰되 한글로 써도 상관은 없음 대신 내용은 간결하고 필요한것만
 repository 지우는 법은 setting에서 제일 아래있는걸 누른다  
 git graph로 git 내가 짠 코드 로그 확인 가능 graph로 간편하게 버전태그 사용가능  
 git명령어는 gifignore.io에서 확인 가능  
 끄기전에 commit과 push는 필수  
 extention에서 java맨위에 다운하면 쉽게 vs에서 자바 사용가능  
