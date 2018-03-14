public class Test {
    public static void main(String[] args) {
        String s = generate4HEX();
        System.out.println(uuid());
    }

    public static String generate4HEX(){
        String str = String.format("%x", (int) (Math.random() * 65535));
        while (str.length() < 4){
            str = "0" + str;
        }
        return str;
    }

    public static String uuid(){
        return generate4HEX() + generate4HEX() + "-" + generate4HEX() + "-" + generate4HEX() + "-" + generate4HEX() + "-" + generate4HEX() + generate4HEX();
    }
}
