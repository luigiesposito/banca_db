package it.luigi;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner= new Scanner(System.in);
		
		
		int scelta;
		scelta=1;
		
		while (scelta!=0) {
			
			System.out.println("Digita 1 per inserire un nuovo correntista");
			System.out.println("Digita 2 per generare un nuovo conto corrente");
			System.out.println("Digita 3 per effettuare un versamento");
			System.out.println("Digita 4 per effettuare un prelievo");
			System.out.println("Digita 5 per visualizzare la lista movimenti");
			System.out.println("Digita 0 per uscire dalla banca");
			scelta=scanner.nextInt();
			
			switch(scelta) {
			case 1:
				
				String nome_da_inserire;
				String cognome_da_inserire;
				String cf_da_inserire;
				System.out.println("Inserisci il nome del correntista");
				nome_da_inserire=scanner.next();
				System.out.println("Inserisci il cognome del correntista");
				cognome_da_inserire=scanner.next();
				System.out.println("Inserisci il codice fiscale del correntista");
				cf_da_inserire=scanner.next();
			
				
				Correntista intestatario = new Correntista(nome_da_inserire,cognome_da_inserire,cf_da_inserire);		
				
				Correntista.inserisci(intestatario);
			break;
			case 2:
				int num_conto;
				String cf;
				System.out.println("Inserisci il codice fiscale del correntista");
				cf=scanner.next();
				System.out.println("Genero il numero di conto.");
				num_conto=Contocorrente.getRandomInteger(0000001, 9999999);
				System.out.println("Il numero di conto assegnato al tuo CF è "+num_conto);
				Contocorrente nuovo_conto= new Contocorrente(num_conto,0,cf);
				Contocorrente.nuovo_conto(cf, num_conto, nuovo_conto);
				break;
			case 3:
				int num_conto_versamento;
				double operazione;
				System.out.println("Inserisci numero di conto corrente");
				num_conto_versamento=scanner.nextInt();
				System.out.println("Inserisci l'importo da versare");
				operazione=scanner.nextDouble();
				Contocorrente.versamento(num_conto_versamento, operazione);
				break;
			case 4:
				int num_conto_prelievo;
				double operazione_prelievo;
				System.out.println("Inserisci numero di conto corrente");
				num_conto_prelievo=scanner.nextInt();
				System.out.println("Inserisci l'importo da prelevare");
				operazione_prelievo=scanner.nextDouble();
				Contocorrente.prelievo(num_conto_prelievo, operazione_prelievo);
				break;
			case 5:
				int num_conto_movimento;
				System.out.println("Inserisci numero di conto corrente per visualizzare gli ultimi 5 movimenti");
				num_conto_movimento=scanner.nextInt();
				Movimenti.lista_mov(num_conto_movimento);
				break;
			case 0:
				break;
			default:
				System.out.println("Scelta non possibile. Riprova");
			}
			
			
		}
		
		
		
	
	

	
	
	}

}
