package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by liu on 2018/4/10.
 */
public class XmlWorkerHelperUtil {
    public static void htmlToPDF(String htmlString,String pdfPath) {
        try {
            Document document = new Document(PageSize.A5);
            PdfWriter pdfWriter = PdfWriter.getInstance(document,
                    new FileOutputStream(pdfPath));
            document.open();
            document.addAuthor("pdf作者");
            document.addCreator("pdf创建者");
            document.addSubject("pdf主题");
            document.addCreationDate();
            document.addTitle("pdf标题,可在html中指定title");
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            worker.parseXHtml(pdfWriter, document, new ByteArrayInputStream(htmlString.getBytes("UTF-8")), null, Charset.forName("UTF-8"),new AsianFontProvider());
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
