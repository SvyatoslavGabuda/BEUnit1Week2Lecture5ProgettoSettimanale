package Biblioteca;

import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Catalogo {
	private ArrayList<Leggibile> cat;

	public Catalogo(ArrayList<Leggibile> c) {
		this.cat = c;
	}

	public String salvaCatalogo() {
		String testo ="";
		for(int i = 0;i<this.cat.size();i++) {
			
			
			if(cat.get(i) instanceof Libro ) {
				
				testo+= cat.get(i).getTitolo()+"@"+ 
						cat.get(i).getISBN() +"@"+
						cat.get(i).getAnnoPublicazione()+"@"+
						cat.get(i).getNPagine() +"@"+
						((Libro)cat.get(i)).getAutore()+"@"+
						((Libro)cat.get(i)).getGenere()+"#"
						;
			}
			if(cat.get(i) instanceof Rivista) {
				
				testo+= cat.get(i).getTitolo()+"@"+ 
						cat.get(i).getISBN() +"@"+
						cat.get(i).getAnnoPublicazione()+"@"+
						cat.get(i).getNPagine() +"@"+
						((Rivista)cat.get(i)).getPeriodicita()+"#"
						;
				
			}
			
			
		};
		return testo;
	}
	public void addToCatalogo(Leggibile l) {
		this.cat.add(l);
		Biblioteca.l.info(l + " aggiunto al catalogo");
	}

	public static Leggibile ricercaPerIsbn(int isbn, Catalogo g) {

		Leggibile sg = g.getCat().stream().filter(el -> el.getISBN() == isbn).findAny().get();
		Biblioteca.l.debug("Trovato: " + sg);
		return sg;
	}
	public static ArrayList<Leggibile> ricercaPerAnno(int anno, Catalogo g) {
		//in questo metodo come in altri simili potevo farre il return diretto(quello commentato poco sotto)
		//però ho lascito diviso per lanciare un messaggio di debug.

		ArrayList<Leggibile> sg = (ArrayList<Leggibile>) g.getCat().stream().filter(el -> el.getAnnoPublicazione() == anno).collect(Collectors.toList());
		Biblioteca.l.debug("Trovato: " + sg);
		return sg;
		
		// return (ArrayList<Leggibile>) g.getCat().stream().filter(el -> el.getAnnoPublicazione() == anno).collect(Collectors.toList());
	}
	public static ArrayList<Leggibile> ricercaPerAutore(String Autore, Catalogo g) {
		
		ArrayList<Leggibile> sg = (ArrayList<Leggibile>) g.getCat().stream()
				.filter(el -> 
				(el instanceof Libro ) ? 
						((Libro) el).getAutore().toLowerCase().equals(Autore.toLowerCase()) : false

// Usato il ternario al posto del codice sottostante che ho lasciato per riferimento :						
						//				{
//					if(el instanceof Libro ) {
//						 return((Libro) el).getAutore().toLowerCase().equals(Autore.toLowerCase());
//						} else {return false;}
//					}
				)
				.collect(Collectors.toList());
		Biblioteca.l.debug("Trovato: " + sg);
		return sg;
	}

	public static Catalogo rimuoviElemento(int isbn, Catalogo g) {
//         refactor per riga unica dirattamente nel return
		return new Catalogo((ArrayList<Leggibile>) g.getCat().stream().filter(el -> !(el.getISBN() == isbn)).collect(Collectors.toList()));
		
//		ArrayList<Leggibile> sg = (ArrayList<Leggibile>) g.getCat().stream().filter(el -> !(el.getISBN() == isbn))
//				.collect(Collectors.toList());
//		Catalogo nuovo = new Catalogo(sg);
//
//		return nuovo;
		
	}

	public void rimuoviElemento(int isbn) {
		
		this.cat = (ArrayList<Leggibile>) this.cat.stream().filter(el -> !(el.getISBN() == isbn))
				.collect(Collectors.toList());

		
	}
}
