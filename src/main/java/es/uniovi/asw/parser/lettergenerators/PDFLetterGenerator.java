package es.uniovi.asw.parser.lettergenerators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import es.uniovi.asw.parser.Agent;

public class PDFLetterGenerator implements LetterGenerator {

	@Override
	public void generatePersonalLetter(Agent c) {
		Document document = new Document();

		try {

			PdfWriter.getInstance(document, new FileOutputStream(new File(c.getID() + ".pdf")));

			// open
			document.open();

			Font f = new Font();
			f.setStyle(Font.BOLD);

			Paragraph p = new Paragraph();
			p.setFont(f);
			p.add("To: " + c.getEmail());

			document.add(p);

			Paragraph p2 = new Paragraph();
			p2.setFont(f);
			p2.add("Subject: Login data\n");

			document.add(p2);

			Paragraph main = new Paragraph();
			main.add("Mr/Mrs " + c.getName() + ",\n" + "Your login data has been generated:\n" + "\tUsername: "
					+ c.getID() + "\n" + "\tPassword: " + c.getPassword() + "\n");

			document.add(main);

			// close
			document.close();

			System.out.println(c.getID() + " letter sent.");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}

	}

}
