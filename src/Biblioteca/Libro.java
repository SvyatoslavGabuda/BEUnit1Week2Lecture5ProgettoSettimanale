package Biblioteca;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Libro extends Leggibile {
	private String autore;
	private String genere;

	public Libro(String t, int anno, int p, String a, String g) {
		super(t, anno, p);
		this.autore = a;
		this.genere = g;
	}
	public Libro(String t, int Isbn ,int anno, int p, String a, String g) {
		super(t, Isbn, anno, p);
		this.autore = a;
		this.genere = g;
	
	}
}
