public class Book {
    String  title;
    String author;
    public Book(String t) {
        title = t;
        author = "작가미상";
    }

    public Book(String t, String a) {
        title = t;
        author = a;
    }

    public static void main(String[] args) {
        Book littlePrince = new Book("어린왕자", "생택쥐페리");
        Book lovestory = new Book("춘향전");
        System.out.println(littlePrince.title + " " + littlePrince.author);
        System.out.println(lovestory.title + " " + lovestory.author);
    }
}
