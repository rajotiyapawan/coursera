public class HelloGoodbye {
    public static void main(String[] args) {
        int n = args.length;
        if (n > 0) {
            String hello = "Hello " + args[0] + " and " + args[1] + ".";
            String gbye = "Goodbye " + args[1] + " and " + args[0] + ".";
            System.out.println(hello);
            System.out.println(gbye);
        }
        else {
            System.out.println("Enter Valid Arguments");
        }
    }
}
