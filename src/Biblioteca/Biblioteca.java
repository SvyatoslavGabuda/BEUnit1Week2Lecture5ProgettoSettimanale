package Biblioteca;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Biblioteca {

	public static Logger l = LoggerFactory.getLogger(Biblioteca.class);

	public static void main(String[] args) {
		Leggibile l1 = new Libro("Titolo1", 1900, 100, "Autore1", "Genere1");
		Leggibile l2 = new Libro("Titolo2", 1901, 200, "Autore2", "Genere2");
		Leggibile l3 = new Libro("Titolo3", 1902, 120, "Autore2", "Genere3");
		Leggibile l4 = new Libro("Titolo4", 1903, 1300, "Autore4", "Genere4");
		Leggibile l5 = new Libro("Titolo5", 1900, 5600, "Autore5", "Genere5");

		Leggibile r1 = new Rivista("rivista1", 2000, 20, Periodicita.MENSILE);
		Leggibile r2 = new Rivista("rivista2", 2001, 21, Periodicita.SEMESTRALE);
		Leggibile r3 = new Rivista("rivista3", 2002, 25, Periodicita.SEMESTRALE);
		Leggibile r4 = new Rivista("rivista4", 2003, 10, Periodicita.MENSILE);
		Leggibile r5 = new Rivista("rivista5", 2004, 60, Periodicita.MENSILE);

		List<Leggibile> primaLista = new ArrayList<Leggibile>();

		Collections.addAll(primaLista, l1, l2, l3, l4, l5, r1, r2, r3, r4, r5);

		Catalogo primoCatalogo = new Catalogo((ArrayList<Leggibile>) primaLista);
		l.debug("" + primoCatalogo);

		Leggibile l6 = new Libro("Nuovo", 2000, 1111, "Autore6", "Genere6");

		primoCatalogo.addToCatalogo(l6);
		System.out.println(l1.getISBN());
		System.out.println(l5.getISBN());
		System.out.println(l6.getISBN());

		Catalogo.ricercaPerIsbn(l1.getISBN(), primoCatalogo);
		System.out.println("prima " + primoCatalogo);
		Catalogo prova = Catalogo.rimuoviElemento(l1.getISBN(), primoCatalogo);
		System.out.println("dopo " + prova);

		Catalogo.ricercaPerAnno(2000, primoCatalogo);

		Catalogo.ricercaPerAutore("Autore2", primoCatalogo);

		File catagolotesto = new File("cat.txt");

		try {
			FileUtils.writeStringToFile(catagolotesto, primoCatalogo.salvaCatalogo(), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

		try {
			String s = FileUtils.readFileToString(catagolotesto, "UTF-8");
			System.out.println(s);

			Catalogo prov = leggiDaFile(catagolotesto);
			System.out.println(prov);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static Catalogo leggiDaFile(File f) throws IOException {
		// System.out.println(f.exists() + "ESISTE");
		if (f.exists()) {
			String s = FileUtils.readFileToString(f, "UTF-8");
			String[] elementi = s.split("#");

//			for (String asd : elementi) {
//				System.out.println(asd);
//			}

			ArrayList<Leggibile> temp = new ArrayList<Leggibile>();
			for (int i = 0; i < elementi.length; i++) {
				String[] singoli = elementi[i].split("@");
//				for (String abc : singoli) {

//					System.out.println(abc);
//				}
				if (singoli.length == 6) {
					Libro n = new Libro(singoli[0], Integer.parseInt(singoli[1]), Integer.parseInt(singoli[2]),
							Integer.parseInt(singoli[3]), singoli[4], singoli[5]);

				} else if (singoli.length == 5) {
					Periodicita p;
					if (singoli[4].equals("MENSILE")) {
						p = Periodicita.MENSILE;

					} else if (singoli[4].equals("SEMESTRALE")) {
						p = Periodicita.SEMESTRALE;
					} else {
						p = Periodicita.SETTIMANALE;
					}

					Rivista r = new Rivista(singoli[0], Integer.parseInt(singoli[1]), Integer.parseInt(singoli[2]),
							Integer.parseInt(singoli[3]), p);
					temp.add(r);

				}

			}
			Catalogo dafile = new Catalogo(temp);

			return dafile;
		}
		return null;

	}
}