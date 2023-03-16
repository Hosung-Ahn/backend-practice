package book;

public class main {
    public static void main(String[] args) {
        Book book1 = new Book("clean code");
        Book book2 = new Book("the art of programming");
        Book book3 = new Book("effective java");

        System.out.println(book1.name);
        System.out.println(book2.name);
        System.out.println(book3.name);

    }
}
