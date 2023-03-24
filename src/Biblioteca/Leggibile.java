package Biblioteca;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Leggibile {
	private static int numeroElementi = 1;
	private int ISBN;
	private String titolo;
	private int annoPublicazione;
	private int nPagine;

	public Leggibile(String t, int anno, int p) {
		this.titolo = t;
		this.annoPublicazione = anno;
		this.nPagine = p;
		this.ISBN = generaISBN();
		numeroElementi ++;
	}
	public Leggibile(String t, int Isnb, int anno, int p) {
		this(t,anno,p);
		this.ISBN= Isnb;
	}

	private static int generaISBN() {
		int nRandom = (int) (Math.random() * 1001) + 1000;
		String s = "" + numeroElementi +""+ nRandom;
		return Integer.parseInt(s);
	}
}
