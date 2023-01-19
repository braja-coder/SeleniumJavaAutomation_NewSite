/*
 * Creation : 1 août 2017
 */
/*
 * Creation : 27 juil. 2017
 */
package com.main.coreframework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.WebDriver;

import com.main.properties.Commonconfig;

public class CommonUtil {

    public WebDriver driver;

    public static void sendMail() throws Exception {
        String path = "/target/";
        String ZipfileName = "OpvAppsAutomationReport.zip";

        String fileName = System.getProperty("user.dir") + "/target/customReports/emailable-report.html";
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String emailMsgTxt = FileUtils.readFully(br);

        zipFolder(System.getProperty("user.dir") + path + "customReports", System.getProperty("user.dir") + path + "OpvAppsAutomationReport.zip");
        // attachments
        String[] attachFiles = new String[1];
        attachFiles[0] = System.getProperty("user.dir") + path + ZipfileName;
        try {

            /*
             * sendEmailWithAttachments(Commonconfig.SMTPHOST, Commonconfig.SMTPPORT, Commonconfig.FROMEMAIL, Commonconfig.FROMPASSWORD,
             * Commonconfig.SENDMAILTO, Commonconfig.SUBJECT + getDateTimeStamp(), Commonconfig.MESSAGECONTENT + "<br/>" + "<br/>" + emailMsgTxt,
             * attachFiles);
             */

            /* String htmlContent = readHTMLFile(path); */
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }

    public static void sendEmailWithAttachments(String host, String port, final String userName, final String password, List<String> toAddress,
            String subject, String message, String[] attachFiles) throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        // creates a new session with an authenticator
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));

        List<InternetAddress> mailTo = new ArrayList<InternetAddress>();

        for (String Address : toAddress) {

            mailTo.add(new InternetAddress(Address));
        }

        InternetAddress[] sendMailTo = new InternetAddress[mailTo.size()];

        msg.setRecipients(Message.RecipientType.TO, mailTo.toArray(sendMailTo));
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();

                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(attachPart);
            }
        }

        // sets the multi-part as e-mail's content
        msg.setContent(multipart);

        // sends the e-mail
        Transport.send(msg);
    }

    private static void zipFolder(String srcFolder, String destZipFile) throws Exception {
        ZipOutputStream zip = null;
        FileOutputStream fileWriter = null;

        fileWriter = new FileOutputStream(destZipFile);
        zip = new ZipOutputStream(fileWriter);
        System.out.println(zip);
        addFolderToZip("", srcFolder, zip);
        zip.flush();
        zip.close();
    }

    private static void addFileToZip(String path, String srcFile, ZipOutputStream zip) throws Exception {

        File folder = new File(srcFile);
        if (folder.isDirectory()) {
            addFolderToZip(path, srcFile, zip);
        } else {
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(srcFile);
            zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
            while ((len = in.read(buf)) > 0) {
                zip.write(buf, 0, len);
            }
        }
    }

    static private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws Exception {
        File folder = new File(srcFolder);

        for (String fileName : folder.list()) {
            if (path.equals("")) {
                addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
            } else {
                addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
            }
        }
    }

    private static String getDateTimeStamp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("  dd MMM yyyy");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static boolean extractDataFromPdfFile() {
        // String clientID = null;
        boolean b = false;
        try {
            PDDocument document = null;
            document = PDDocument.load(new File(System.getProperty("user.dir") + "/previsualizar.pdf"));
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();
                String st = Tstripper.getText(document);
                String[] str = st.split("\n");
                String cmp = "";//Commonconfig.CLIENT_NAME;
                /* "M. CAPGEMINI CAPGEMINI" */;
                for (int i = 0; i < str.length; i++) {
                    if (str[i].contains(cmp) == true) {
                        System.out.println(str[i]);
                        // clientID = str[i];
                        b = true;
                        break;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return b;
        }
        return b;
    }
}
