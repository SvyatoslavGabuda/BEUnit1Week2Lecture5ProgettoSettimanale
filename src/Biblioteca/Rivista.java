package Biblioteca;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Rivista extends Leggibile {
	private Periodicita periodicita;

	public Rivista(String t, int anno, int p, Periodicita per) {
		super(t, anno, p);
		this.periodicita = per;

	}
	public Rivista(String t, int Isbn, int anno, int p, Periodicita per) {
		super(t, Isbn, anno, p);
		this.periodicita = per;
		
	}

}
