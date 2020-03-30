package domain.string.bytes;

import java.nio.charset.Charset;

public class ByteSize {

    public static void main(String... strings) {
        utf8();
        sjis();
        
        utf8("あ");
        sjis("あ");
    }

    private static void utf8() {
        System.out.println("UTF-8");
        byte[] bytes = { 48 };
        String str = new String(bytes, Charset.forName("UTF-8"));
        System.out.println(str);

        int length = str.getBytes(Charset.forName("UTF-8")).length;
        System.out.println(length);
    }
    
    

    private static void sjis() {
        System.out.println("SJIS");
        byte[] bytes = { 48 };
        String str = new String(bytes, Charset.forName("SJIS"));
        System.out.println(str);

        int length = str.getBytes(Charset.forName("SJIS")).length;
        System.out.println(length);
    }
    
    
    private static void utf8(String str) {
        System.out.println("UTF-8");
        byte[] bytes =str.getBytes(Charset.forName("UTF8"));
        String str2 = new String(bytes, Charset.forName("UTF-8"));
        System.out.println(str2);

        int length = str.getBytes(Charset.forName("UTF-8")).length;
        System.out.println(length);
    }
    
    

    private static void sjis(String str) {
        System.out.println("SJIS");
        byte[] bytes = str.getBytes(Charset.forName("SJIS"));
        String str2 = new String(bytes, Charset.forName("SJIS"));
        System.out.println(str2);

        int length = str.getBytes(Charset.forName("SJIS")).length;
        System.out.println(length);
    }
}
