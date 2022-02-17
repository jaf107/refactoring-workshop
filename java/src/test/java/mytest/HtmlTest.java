package java.src.test.java.mytest;

import java.src.main.java.workshop.plaintext2html.PlaintextToHtmlConverter;

public class HtmlTest {
    public static void main(String[] args) throws Exception {
        PlaintextToHtmlConverter plaintextToHtmlConverter = new PlaintextToHtmlConverter();
        System.out.println(plaintextToHtmlConverter.toHtml());
    }
}
