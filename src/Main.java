import java.awt.Point;
import java.io.File;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

	private static void zadanie58() throws Exception {
		
		Scanner plik_dane1 = new Scanner(new File("Files/58/dane_systemy1.txt"));
		Scanner plik_dane2 = new Scanner(new File("Files/58/dane_systemy2.txt"));
		Scanner plik_dane3 = new Scanner(new File("Files/58/dane_systemy3.txt"));
		
		PrintWriter printWriter = new PrintWriter(new File("rozwiazania/58.txt"));
		
		class Pomiar{
			public int temperatura;
			public int czas;
		}
		
		ArrayList<Pomiar> pomiary1 = new ArrayList<Pomiar>();
		ArrayList<Pomiar> pomiary2 = new ArrayList<Pomiar>();
		ArrayList<Pomiar> pomiary3 = new ArrayList<Pomiar>();
		
		while(plik_dane1.hasNext()) {
			String czas = plik_dane1.next();
			String temperatura = plik_dane1.next();
			
			Integer czasInt = 0;
			Integer temperaturaInt = 0;
			// zamiana czasu na int:
			for(int i=0; i<czas.length(); i++) {
				if(czas.charAt(czas.length()-1-i) == '1') {
					czasInt += (int)Math.pow(2, i);
				}
			}
			
			
			// zamiana temperatury na int:
			for(int i=0; i<temperatura.length()-1; i++) {
				if(temperatura.charAt(temperatura.length()-1-i) == '1') {
					temperaturaInt += (int)Math.pow(2, i);
				}
			}
			if(temperatura.charAt(0) == '1'){
				temperaturaInt += (int)Math.pow(2, temperatura.length()-1);
			} else if(temperatura.charAt(0) == '-'){
				temperaturaInt *= -1;
			}
			
			
			
			Pomiar pomiar = new Pomiar();
			pomiar.temperatura = temperaturaInt;
			pomiar.czas = czasInt;
			
			pomiary1.add(pomiar);
		}
		
		while(plik_dane2.hasNext()) {
			String czas = plik_dane2.next();
			String temperatura = plik_dane2.next();
			
			Integer czasInt = 0;
			Integer temperaturaInt = 0;
			// zamiana czasu na int:
			for(int i=0; i<czas.length(); i++) {
				czasInt += (int)Math.pow(4, i) * Character.getNumericValue(czas.charAt(czas.length()-1-i));
			}
			// zamiana temperatury na int:
			for(int i=0; i<temperatura.length()-1; i++) {
				temperaturaInt += (int)Math.pow(4, i) * Character.getNumericValue(temperatura.length()-1-i);
				if((int)Math.pow(4, i) * Character.getNumericValue(temperatura.length()-1-i) < 0) {
					System.out.print("Tu jest b��d");
				}
			}
			if(temperatura.charAt(0) != '-'){
				//System.out.print("Nie ma minusa; ");
				temperaturaInt += (int)Math.pow(4, temperatura.length()-1) * Character.getNumericValue(temperatura.charAt(0));
				if((int)Math.pow(4, temperatura.length()-1) * Character.getNumericValue(temperatura.charAt(0)) < 0) {
					System.out.print("Tu jest b��d");
				}
			} else {
				//System.out.print("Jest minus; ");
				//System.out.print(temperaturaInt + ";;;");
				//temperaturaInt *= -1;
				//System.out.print(temperaturaInt);
			}
			
			//System.out.println("temperaturaInt: " + temperaturaInt  + ", temperaturaStr: " + temperatura  + ", char at 0: " + temperatura.charAt(0));
			
			Pomiar pomiar = new Pomiar();
			pomiar.temperatura = temperaturaInt;
			pomiar.czas = czasInt;
			
			pomiary2.add(pomiar);
		}
		
		while(plik_dane3.hasNext()) {
			String czas = plik_dane3.next();
			String temperatura = plik_dane3.next();
			
			Integer czasInt = 0;
			Integer temperaturaInt = 0;
			// zamiana czasu na int:
			for(int i=0; i<czas.length(); i++) {
				czasInt += (int)Math.pow(8, i) * Character.getNumericValue(czas.charAt(czas.length()-1-i));
			}
			// zamiana temperatury na int:
			for(int i=0; i<temperatura.length()-1; i++) {
				temperaturaInt += (int)Math.pow(8, i) * Character.getNumericValue(temperatura.length()-1-i);
			}
			if(temperatura.charAt(0) != '-'){
				temperaturaInt += (int)Math.pow(4, temperatura.length()-1) * Character.getNumericValue(temperatura.charAt(0));
			} else {
				temperaturaInt *= -1;
			}
			
			Pomiar pomiar = new Pomiar();
			pomiar.temperatura = temperaturaInt;
			pomiar.czas = czasInt;
			
			pomiary3.add(pomiar);
		}
		
		Runnable pp1 = () ->{
			int min1 = 999;
			int min2 = 999;
			int min3= 999;
			
			for(Pomiar pomiar : pomiary1) {
				//System.out.println(pomiar.temperatura );
				if(pomiar.temperatura < min1) {
					min1 = pomiar.temperatura;
				}
			}
			for(Pomiar pomiar2 : pomiary2) {
				if(pomiar2.temperatura < min2) {
					min2 = pomiar2.temperatura;
				}			
			}
			for(Pomiar pomiar3 : pomiary3) {
				if(pomiar3.temperatura < min3) {
					min3 = pomiar3.temperatura;
				}
			}
			
			//System.out.println(pomiary1);
			//System.out.println(pomiary2);
			//System.out.println(pomiary3);
			
			String min1Binarnie = Integer.toBinaryString(Math.abs(min1));
			String min2Binarnie = Integer.toBinaryString(Math.abs(min2));
			String min3Binarnie = Integer.toBinaryString(Math.abs(min3));
			
			if(min1<0) {
				min1Binarnie = "-" + min1Binarnie;
			}
			if(min2<0) {
				min2Binarnie = "-" + min2Binarnie;	
			}
			if(min3<0) {
				min3Binarnie = "-" + min3Binarnie;
			}
			
			printWriter.println("Zadanie 58.1:");
			printWriter.println();
			printWriter.println("Najni�sza odnotowana temperatura w stacji 1: " + min1);
			printWriter.println("Najni�sza odnotowana temperatura w stacji 2: " + min2);
			printWriter.println("Najni�sza odnotowana temperatura w stacji 3: " + min3);
			printWriter.flush();
		};
		Runnable pp2 = () ->{
			int ileBlednychPomiarow = 0;
			for(int i=0; i<pomiary1.size(); i++) {
				if(pomiary1.get(i).czas != pomiary2.get(i).czas || pomiary1.get(i).czas != pomiary3.get(i).czas) {
					ileBlednychPomiarow++;
				}
			}
			printWriter.println();
			printWriter.println("Zadanie 58.2:");
			printWriter.println();
			printWriter.println("Liczba b��dnych pomiar�w: " + ileBlednychPomiarow);
			printWriter.flush();
		};
		Runnable pp3 = () ->{
		};
		Runnable pp4 = () ->{
		};
		
		pp1.run();
		pp2.run();
		pp3.run();
		pp4.run();
	}
	private static void zadanie59() throws Exception {
		
		Scanner plikLiczby = new Scanner(new File("Files/59/liczby.txt"));
		
		PrintWriter printWriter = new PrintWriter(new File("rozwiazania/59.txt"));
		
		ArrayList<Integer> liczby = new ArrayList<Integer>();
		
		while(plikLiczby.hasNext()) {
			liczby.add(plikLiczby.nextInt());
		}
		
		Runnable pp1 = () ->{
			int ileLiczbSpelniajacychWarunki = 0;
			
			for(Integer liczba : liczby) {
				int liczbaCp = liczba;
				ArrayList<Integer> czynnikiPierwsze = new ArrayList<Integer>();
				
				for(int i=2; i<=Math.round(Math.sqrt(liczba)); i++) {
					if(liczbaCp % i == 0) {
						czynnikiPierwsze.add(i);
						liczbaCp /= i;
						i=1; // will be 2 after i++
					}
				}
				if(liczbaCp>1) {
					czynnikiPierwsze.add(liczbaCp);
				}	
				
				HashSet<Integer> rozneCzynnikiPierwsze = new HashSet<Integer>(czynnikiPierwsze);
				
				boolean czyMaParzystyDzielnik = false;
				for (Iterator<Integer> i = rozneCzynnikiPierwsze.iterator(); i.hasNext();) {
				    Integer element = i.next();
				    if (element % 2 == 0) {
				        // liczba nie spelnia warunku
				    	czyMaParzystyDzielnik = true;
				    	break;
				    }
				}
				
				if(rozneCzynnikiPierwsze.size() == 3 && !czyMaParzystyDzielnik) {
					ileLiczbSpelniajacychWarunki++;
				}
			}
			printWriter.println("podpunkt 1:");
			printWriter.println("Ilo�� liczb maj�cych dok�adnie 3 r�ne nieparzyste czynniki pierwsze: " + ileLiczbSpelniajacychWarunki);
			printWriter.flush();
		};
		Runnable pp2 = () -> {
			int ilePalindromow = 0;
			for(int i=0; i<liczby.size(); i++) {
				int liczba = liczby.get(i);
				int odwrocona = Integer.parseInt(new StringBuilder(liczby.get(i).toString()).reverse().toString());
				String suma = String.valueOf(odwrocona + liczba);
				if(suma.equals(new StringBuilder(suma).reverse().toString())) {
					ilePalindromow++;
				}
			}
			printWriter.println();
			printWriter.println("podpunkt 2:");
			printWriter.println("Ilo�� palindrom�w: " + ilePalindromow);
			printWriter.flush();
		};
		Runnable pp3 = () ->{
			HashMap<Integer, ArrayList<Integer>> moc_liczby = new HashMap<Integer, ArrayList<Integer>>();
			
			for(int i=1; i<=8; i++) {
				moc_liczby.put(i, new ArrayList<Integer>());
			}
		
			for(int i=0; i<liczby.size(); i++) {
				String liczbaString = liczby.get(i).toString();
				int iloczyn = Character.getNumericValue(liczbaString.charAt(0));
				for(int j=1; j<liczbaString.length(); j++) {;
					iloczyn *= Character.getNumericValue(liczbaString.charAt(j));
				}
				int moc = 1;
				while(iloczyn > 9) {
					String iloczynString = String.valueOf(iloczyn);
					iloczyn = Character.getNumericValue(iloczynString.charAt(0));
					for(int j=1; j<iloczynString.length(); j++) {
						iloczyn *= Character.getNumericValue(iloczynString.charAt(j));
					}
					moc++;
				}
				moc_liczby.get(moc).add(liczby.get(i));
			}
			
			printWriter.println();
			printWriter.println("podpunkt 3:");
			for(int i=1; i<=8; i++) {
				printWriter.println("Ilo�� liczb o mocy: " + i +  " wynosi " + moc_liczby.get(i).size());
			}
			printWriter.println("Minimalna liczba o mocy 1: " + Collections.min(moc_liczby.get(1)));
			printWriter.print("Maksymalna liczba o mocy 1: " + Collections.max(moc_liczby.get(1)));
			printWriter.flush();
			
		};
			
		pp1.run();
		pp2.run();
		pp3.run();
	}
	private static void zadanie60() throws Exception{
		Scanner plikLiczby = new Scanner(new File("Files/60/liczby.txt"));
		PrintWriter printWriter = new PrintWriter(new File("rozwiazania/60.txt"));
		
		ArrayList<Integer> liczby = new ArrayList<Integer>();
		
		while(plikLiczby.hasNext()) {
			liczby.add(plikLiczby.nextInt());
		}
		
		Runnable pp1 = () ->{
			int ileMniejszychOd1000 = 0;
			int przedostatnia = 0;
			int ostatnia = 0;
			for(int i=0; i<liczby.size(); i++) {
				if(liczby.get(i) < 1000) {
					ileMniejszychOd1000++;
					przedostatnia = ostatnia;
					ostatnia = liczby.get(i);
				}
			}
			printWriter.println("Podpunkt 1: ");
			printWriter.println("Ilo�� liczb mniejszych od 1000: " + ileMniejszychOd1000);
			printWriter.println("Dwie ostatnie liczby: " + przedostatnia + ", " + ostatnia);
			printWriter.flush();
			
		};
		Runnable pp2 = () ->{
			printWriter.println();
			printWriter.println("Podpunkt 2: ");
			for(int i=0; i<liczby.size(); i++) {
				int liczba = liczby.get(i);
				ArrayList<Integer> dzielniki = new ArrayList<Integer>();
				for(int j=1; j<=liczba; j++) {
					if(liczba % j == 0) {
						dzielniki.add(j);
					}
				}
				
				if(dzielniki.size() == 18) {
					printWriter.println(liczba);
					printWriter.println(dzielniki);
				}
			}
			printWriter.flush();
		};
		Runnable pp3 = () ->{
			printWriter.println();
			Collections.sort(liczby);
			Collections.reverse(liczby);
			
			for(int i=0; i<liczby.size(); i++) {
				int liczba = liczby.get(i);
				ArrayList<Integer> dzielnikiPoza1 = new ArrayList<Integer>();
				for(int j=2; j<=liczba; j++) {
					if(liczba % j == 0) {
						dzielnikiPoza1.add(j);
					}
				}
				
				boolean czyJestWzgledniePierwsza = true;
				for(int j=i+1; j<liczby.size()-i-1; j++) {
					czyJestWzgledniePierwsza = true;
					for(int k=0; k<dzielnikiPoza1.size(); k++) {
						if(liczby.get(j) % dzielnikiPoza1.get(k) == 0) {
							// liczba nie jest wzglednie pierwsza;
							czyJestWzgledniePierwsza = false;
							j = 100000;
							break;
						}
					}	
				}
				if(czyJestWzgledniePierwsza) {
					printWriter.println();
					printWriter.println("Podpunkt 3: ");
					printWriter.print("Najwi�ksza wzgl�dnie pierwsza liczba to: " + liczba);
					printWriter.flush();
					break;
				}	
			}
		};
		
		pp1.run();
		pp2.run();
		pp3.run();
	}
	private static void zadanie61() throws Exception{
		PrintWriter printWriter = new PrintWriter(new File("rozwiazania/61.txt"));
		
		Scanner plikCiagi = new Scanner(new File("Files/61/ciagi.txt"));
		Scanner plikBledne = new Scanner(new File("Files/61/bledne.txt"));
		
		ArrayList<ArrayList<Integer>> ciagi = new ArrayList<ArrayList<Integer>>();
		
		while(plikCiagi.hasNext()) {
			int dlugoscCiagu = plikCiagi.nextInt();
			ArrayList<Integer> aktualnyCiag = new ArrayList<Integer>();
			
			for(int i=0; i<dlugoscCiagu; i++) {
				aktualnyCiag.add(plikCiagi.nextInt());
			}
			ciagi.add(aktualnyCiag);
		}
		Runnable pp1 = () ->{
			ArrayList<ArrayList<Integer>> ciagiArytmetyczne = new ArrayList<ArrayList<Integer>>();
			
			for(int i=0; i<ciagi.size(); i++) {
				ArrayList<Integer> ciag = ciagi.get(i);
				int roznica = ciag.get(1) - ciag.get(0);
				boolean czyArytmetyczny = true;
				for(int j=2; j<ciag.size(); j++) {
					if(ciag.get(j) - ciag.get(j-1) != roznica) {
						czyArytmetyczny = false;
						break;
					}
				}
				if(czyArytmetyczny) {
					ciagiArytmetyczne.add(ciag);
				}
			}
			
			int maxRoznica = -1;
			
			for(int i=0; i<ciagiArytmetyczne.size(); i++) {
				int roznica = ciagiArytmetyczne.get(i).get(1) - ciagiArytmetyczne.get(i).get(0);
				if(roznica > maxRoznica) {
					maxRoznica = roznica;
				}
			}
			
			printWriter.println("Podpunkt 1:");
			printWriter.println("Ilo�� ci�g�w arytmetycznych: " + ciagiArytmetyczne.size());
			printWriter.println("Maksymalna r�nica ci�gu arytmetycznego: " + maxRoznica);
			printWriter.flush();
		};
		Runnable pp2 = () ->{
			printWriter.println();
			printWriter.println("Podpunkt 2:");
			for(int i=0; i<ciagi.size(); i++) {
			}
			printWriter.flush();
		};
		Runnable pp3 = () ->{
			
		};
		
		pp1.run();
		pp2.run();
		pp3.run();
	}
	private static void zadanie62() throws Exception {
		Scanner plik1 = new Scanner(new File("Files/62/liczby1.txt"));
		Scanner plik2 = new Scanner(new File("Files/62/liczby2.txt"));
		
		ArrayList<String> osemkowe = new ArrayList<String>();
		ArrayList<Integer> osemkoweInt = new ArrayList<Integer>();
		ArrayList<String> dziesietne = new ArrayList<String>();
		ArrayList<Integer> dziesietneInt = new ArrayList<Integer>();
		
		while(plik1.hasNext()) {
			String next = plik1.next();
			Integer osemkowaInt = 0;
			for(int i=next.length()-1; i>0; i--) {
				osemkowaInt += (int)(Math.pow(8, i)) * Integer.parseInt(next.charAt(i) + "");
			}
			osemkoweInt.add(osemkowaInt);
			
			osemkowe.add(plik1.next());
		}
		while(plik2.hasNext()) {
			String next = plik2.next();
			dziesietne.add(next);
			dziesietneInt.add(Integer.parseInt(next));
		}
		
		Runnable pp1 = () -> {
			Collections.sort(osemkowe);
			int max = osemkoweInt.get(0);
			int min = osemkoweInt.get(osemkoweInt.size() - 1);
			
			System.out.println("Min: " + min);
			System.out.println("Max: " + max);
		};
		Runnable pp2 = () -> {
//			int maxDlugoscCiagu = 0;
//			ArrayList<Integer> najdluzszyCiagMalejacy = new ArrayList<Integer>();
//			ArrayList<Integer> ciagMalejacy = new ArrayList<Integer>();
//			for(int i=0; i<dziesietneInt.size(); i++) {
//				if(ciagMalejacy.size() == 0) {
//					ciagMalejacy.add(dziesietneInt.get(i));
//				} else if(dziesietneInt.get(i) < ciagMalejacy.get(ciagMalejacy.size() - 1)) {
//					ciagMalejacy.add(dziesietneInt.get(i));
//				} else {
//					ciagMalejacy = new ArrayList<Integer>();
//					ciagMalejacy.add(dziesietneInt.get(i));
//				}
//				if(ciagMalejacy.size() > maxDlugoscCiagu) {
//					najdluzszyCiagMalejacy = (ArrayList<Integer>) ciagMalejacy.clone();
//					maxDlugoscCiagu = ciagMalejacy.size();
//				}
//			}
			
//			System.out.println(najdluzszyCiagMalejacy);
//			
//			System.out.println("Max d�ugo�� ci�gu: " + maxDlugoscCiagu);
		};
		Runnable pp3 = () -> {
			
		};
		Runnable pp4 = () -> {
			int ile6 = 0;
			int ile6w8 = 0;
			for(int liczba: dziesietneInt) {
				int liczba8 = 0;
				
			}
		};
		
		pp1.run();
		pp2.run();
		pp3.run();
		pp4.run();
		consumer.accept("d");
		runnable.run();
		Swimmable<String> swimService = (String a) -> {
			// do something
		};
		
		swimService.swim("Hi");
		
		BiFunction<String,String,String> addStrings = (String a, String b) -> {
			return a + b;
		};
		
		String result = addStrings.apply("Hi ", "Mark");
	}
	static Consumer<String> consumer = (String b)->{
		
	};
	static Runnable runnable = () ->{
		
	};
	
	@FunctionalInterface
	interface Swimmable<T> {
		void swim(T t);
	}
	
	
	public static void zadanie69() throws Exception {
		Scanner plik_geny = new Scanner(new File("Files/69/dane_geny.txt"));
		
		ArrayList<String> genotypy = new ArrayList<String>();
		
		while(plik_geny.hasNext()) {
			genotypy.add(plik_geny.next());
		}
		
		Runnable pp1 = () ->{
//			TreeMap<String, Integer> genotyp_ilosc = new TreeMap<String, Integer>();
//			for(int i=0; i<genotypy.size(); i++) {
//				String genotyp = genotypy.get(i);
//				Integer iloscZGatunku = genotyp_ilosc.get(genotyp);
//				if(iloscZGatunku == null){
//					genotyp_ilosc.put(genotyp, 0);
//				} else {
//					genotyp_ilosc.put(genotyp, iloscZGatunku+1);
//				}
//			}
//			int max = 0;
//			for(Map.Entry<String, Integer> entry : genotyp_ilosc.entrySet()) {
//				if(entry.getValue() > max) {
//					max = entry.getValue();
//				}
//			}
//			System.out.println("Ilo�� gatunk�w: " + genotyp_ilosc.size());
//			System.out.println("Max ilo��  osobnik�w w jednym gatunku: " + max);
			
			for(int i=0; i<genotypy.size(); i++) {
				
				
				String genotyp = genotypy.get(i);
				Stack<String> obecnyGen = new Stack<String>();
				for(int j=0; j<genotyp.length(); j++) {
					
				}
			}
			
			
		};
		Runnable pp2 = () ->{
			
		};
		Runnable pp3 = () ->{
			
		};
		Runnable pp4 = () ->{
			
		};
		
		pp1.run();
		pp2.run();
		pp3.run();
		pp3.run();
		pp4.run();
	}
	public static void zadanie70() throws Exception {
		
	}
	public static void zadanie81() throws Exception { // Czworok�ty
		
		Scanner plik_dane = new Scanner(new File("Files/81/wspolrzedne.txt"));
		Scanner plik_daneTR = new Scanner(new File("Files/81/wspolrzedneTR.txt"));
		
		ArrayList<ArrayList<Point>> dane_wiersze = new ArrayList<ArrayList<Point>>();		
		ArrayList<ArrayList<Point>> dane_wierszeTR = new ArrayList<ArrayList<Point>>();		
		
		
		while(plik_dane.hasNext()) {	
			ArrayList<Point> linia = new ArrayList<Point>();
			for(int i=0; i<3; i++) {
				linia.add(new Point(plik_dane.nextInt(), plik_dane.nextInt()));
			}
			dane_wiersze.add(linia);
		}
		
		while(plik_daneTR.hasNext()) {
			ArrayList<Point> linia = new ArrayList<Point>();
			for(int i=0; i<3; i++) {
				linia.add(new Point(plik_daneTR.nextInt(), plik_daneTR.nextInt()));
			}
			dane_wierszeTR.add(linia);
		}
		
		Runnable pp1 = () -> {
			int ileWierszy = 0;
			
			for(int i=0; i<dane_wiersze.size(); i++) {
				boolean czyWierszWCwiartce = true;		
				for(int j=0; j<3; j++) {
					Point punkt = dane_wiersze.get(i).get(j);
					if(!(punkt.getX() > 0 && punkt.getY() > 0)) {
						czyWierszWCwiartce = false;
					}
				}
				if(czyWierszWCwiartce) {
					ileWierszy++;
				}
			}
			
			System.out.println("Zadanie 81.1: " + ileWierszy);
		};
		Runnable pp2 = () -> {
			
			int ileProstych = 0;
			
			for(int i=0; i<dane_wiersze.size(); i++) {
				Point a = dane_wiersze.get(i).get(0);
				Point b = dane_wiersze.get(i).get(1);
				Point c = dane_wiersze.get(i).get(2);
				
				double tgAB = Math.abs((a.getY() - b.getY()) / (a.getX() - b.getX()));
				double tgAC = Math.abs((a.getY() - c.getY()) / (a.getX() - c.getX()));
				
				if(tgAB == tgAC) {
					ileProstych++;
				}
			}
			
			System.out.println("Zadanie 81.2: " + ileProstych);
		};
		Runnable pp3 = () -> {
			double maxObw = 0d;
			Point maxA = new Point();
			Point maxB = new Point();
			Point maxC = new Point();
			for(int i=0; i<dane_wierszeTR.size(); i++) {
				
				Point a = dane_wierszeTR.get(i).get(0);
				Point b = dane_wierszeTR.get(i).get(1);
				Point c = dane_wierszeTR.get(i).get(2);
				
				double obw = a.distance(b) + a.distance(c) + b.distance(c);
				if(obw > maxObw) {
					maxObw = obw;
					maxA = a;
					maxB = b;
					maxC = c;
				}
				//int obwod = Math.hypot(a.get, y) 
			}
			
			Function<Point, String> formatPoint = (Point a) ->{
				return "(" + (int)a.getX() + "," + (int)a.getY() + ")";
			};
			
			new DecimalFormat("#.");
			Pattern a;
			System.out.println("Zadanie 81.3: ");
			System.out.println("Max obw�d: " + String.format("%.2f", maxObw));
			System.out.println("Max obw�d: " + new DecimalFormat("#.##").format(maxObw));
			
			System.out.println("Tr�jk�t: " + formatPoint.apply(maxA) + ", " + formatPoint.apply(maxB) + ", " + formatPoint.apply(maxC));
		};
		Runnable pp4 = () -> {
			int ileProst = 0;
			
			for(int i=0; i<dane_wierszeTR.size(); i++) {
				ArrayList<Point> wiersz = dane_wierszeTR.get(i);
				//if(wiersz.get(0))
				double bokA = wiersz.get(0).distance(wiersz.get(1));
				double bokB = wiersz.get(0).distance(wiersz.get(2));
				double bokC = wiersz.get(1).distance(wiersz.get(2));
				
				
				boolean czyProst = false;
				if(bokA * bokA + bokB * bokB == bokC * bokC) {
					czyProst = true;
				} else if(bokA * bokA + bokB * bokB == bokC * bokC) {
					czyProst = true;
				} else if(bokA * bokA + bokB * bokB == bokC * bokC) {
					czyProst = true;
				}
				if(czyProst) {
					ileProst++;
				}
				
			}
			
			System.out.println("Zadanie 81.4: " + ileProst);
		};
		Runnable pp5 = () -> {
			
		};
		
		pp1.run();
		pp2.run();
		pp3.run();
		pp4.run();
		pp5.run();
	}
	public static void test() throws Exception {
		List<String> list = new ArrayList<String>();
		Collections.addAll(list,"1", "2", "3");
		
		List<Point> points = new ArrayList<Point>();
		
		points.add(new Point(3,3));
		points.add(new Point(3,3));
		points.add(new Point(4,5));

		Point doSkopiowania = new Point(0,0);
		
		points.add(doSkopiowania);
		points.add(doSkopiowania);
		points.add(doSkopiowania);
		
		points = points.stream().distinct().collect(Collectors.toList());
		
		System.out.println(points);
		
		list = list.stream().map((String el)->{
			return el + " hello";
		}).collect(Collectors.toList());
		
		
		// distinct 2: listy z unikatow� zawarto�ci�
		
		ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> lista1 = new ArrayList<Integer>();
		ArrayList<Integer> lista2 = new ArrayList<Integer>();
		ArrayList<Integer> lista3 = new ArrayList<Integer>();
		
		Collections.addAll(lista1, 1,2,3);
		Collections.addAll(lista2, 1,2,3);
		Collections.addAll(lista3, 2,3,4);
		
		Collections.addAll(listOfLists, lista1, lista2, lista3);
		
		List<ArrayList<Integer>> newListOfLists = listOfLists.stream().distinct().collect(Collectors.toList());
		
		System.out.println(listOfLists);
		System.out.println(newListOfLists);
		
		Set<Integer> a = new HashSet<Integer>();
		
		a.add(1);
		a.add(1);
		a.add(2);
		
		System.out.println(a);
		
//		a.add(new ArrayList<Integer>()){
//			
//		}
		
	}
	
	//a.accept("ddd");
	
	public static void main(String[] args) {
		try {
//			zadanie58();
//			zadanie59();
//			zadanie60();
//			zadanie61();
//			zadanie62();
//			zadanie69();
//			zadanie70();
			zadanie81();
			//test();
						
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
