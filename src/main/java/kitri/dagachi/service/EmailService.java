package kitri.dagachi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message.RecipientType;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    JavaMailSender emailSender;

    public String ePw;

    private MimeMessage createMessage(String to) throws Exception{
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + ePw);

        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to); // 보내는 대상
        message.setSubject("dagachi 회원가입 이메일 인증");

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요 dagachi입니다. </h1>";
        msgg += "<br>";
        msgg += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg += "<br>";
        msgg += "<p>감사합니다!<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += ePw+"</strong><div><br/> ";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html"); // 내용
        message.setFrom(new InternetAddress("dagachi1108@gmail.com", "dagachi_admin")); // 보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rand = new Random();

        for(int i=0; i<6; i++) { // 인증코드 6자리
//            int index = rand.nextInt(); // 0~2까지 랜덤

//            switch (index) {
//                case 0:
//                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
//                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
//                    break;
//                case 1:
//                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
//                    //  A~Z
//                    break;
//                case 2:
             // 숫자로만 구성
                    key.append((rand.nextInt(10))); // 0 ~ 9
//                    break;
        }
        System.out.println(key.toString());
        return key.toString();
    }




//    메일 발송
//    sendSimpleMessage의 매개변수로 들어온 to는 곧 이메일 주소가 되고
//    MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
//    그리고 bean으로 등록해둔 javaMail 객체를 사용해서 이메일 send!
//    @Override
    public String sendSimpleMessage(String to) throws Exception {

        ePw = createKey();

        MimeMessage message = createMessage(to); // 메일 발송
        try { // 예외처리
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }

        return ePw; // 메일로 보냈던 인증 코드를 서버로 반환
    }
}
