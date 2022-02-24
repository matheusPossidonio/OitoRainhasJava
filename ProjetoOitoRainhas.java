/*	 PROGRAMA QUE TENTA SOLUCIONAR O PROBLEMA DAS OITO RAINHAS NO TABULEIRO 8X8
 * ALUNO(A) : MATHEUS POSSIDONIO GONÇALVES VIEIRA COSTA 
 * DISCIPLICA : LABORATORIO DE PROGRAMAÇÃO 2
*/


/*		EXPLICAÇÃO DO PROGRAMA
 * MEU METODO PARA RESOLVER ESSE PROBLEMA FOI ATRAVES DE CONSECUTIVAS REPETIÇÕES, OU SEJA FORÇA BRUTA
 * 
 * INVES DE TRABALHAR COM VETORES BIDIMENSIONAIS COMO MOSTRANO NO EXEMPLO EU USEI APENAS UM VETOR NORMAL
 * ASSIM CADA INDICE DELE REPRESENTA UMA COLUNA E O NUMERO QUE ELE POSSUI REPRESENTA A LINHA A QUAL A RAINHA ESTA
 * DESSE MODO EU NAO PRECISSO ME PREOCUPAR COM DUAS RAINHAS NA MESMA COLUNA, E INVES DE GERAR NUMEROS ALEATORIOS PARA
 * OS ESPAÇOS DO VETOR EU GERO ELE COM OS NUMEROS DE 1 A 8, ASSIM NAO PRECISO ME PREOCUPAR COM RAINHAS EM MESMA LINHA
 * CONTUDO PARA SER CAPAZ DE GERAR TESTES DIFERENTES EU USO O METODO GERADORTABULEIRO PARA MISTURAR OS ELEMENTOS DO VETOR.
 * 
 * APOS RECEBER ESSE VETOR "ALEATORIO" EU APENAS PRECISO TESTAR SE AS RAINHAS SE ATACAM NAS DIAGONAIS E CASO ISSO ACONTECA
 * EU RETIRO ELA E ASSIM FICAM APENAS AS RAINHAS QUE NAO SE ATACAM, ESSA VERIFICAÇÃO OCORRE NO METODO VERIFICATABUOLEIRO
 * PARA CONSEGUIR UMA SP-OLUÇÃO VALIDA BASTA O VETOR ALEATORIO GERADO PASSAR PELO VERIFICATABULEIRO E PERMANECER COM AS 8 RAINHAS
 * UM DESSES  CASOS ONDE A SOLUÇÃO É APRESENTA É NO METODO SOLUCAORAINHA, QUE APRENSENTA UMA POSSIVEL SOLUCAO PARA O PROBLEMA
 * 
 * OUTROS METODOS COMO PRINTTABULEIRO,CONTADORRAINHA, CICLODASRAINHAS TBM FORAM CRIADOS PARA SUPRIR ALGUMAS NECESSIDADES
 * AO DECORRER DO CODIGO.
 * 
 * POR FIM GOSTARIA DE AGRADECE POR ESTAR VENDO ESSE CODIGO, ESSE É UM PROJETO DA PRIMEIRA UNIDADE DE LABORATORIO DE PROGRAMÇÃO
 * E AINDA ESTOU ME SITUANDO EM JAVA POR ISSO ESSE PROGRAMA NAO ESTA MELHOR OTIMIZADO POSSIVEL, MAS ESPERO FUTURAMENTE SER CAPAZ 
 * DE TORNALO O MELHOR POSSIVEL. 
 * 
 * */
 

import java.util.Random;


public class ProjetoOitoRainhas {
	
	//METODO QUE RECEBE O VETOR E EMBARALHA OS SEUS TERMOS 
//		METODO PRONTO 
	public static int[] geradorTabuleiro(int tab[]){
		Random numero = new Random();
		
		int tabuleiro[]=tab;
		int temp;
		int aux= numero.nextInt(8);
		
		for (int i = 0; i <tabuleiro.length; i++) {
			temp=tabuleiro[i];
			tabuleiro[i]=tabuleiro[aux];
			tabuleiro[aux]=temp;
		}//fim do for que transforma o vetor em um vetor aleatorio
		
		return tabuleiro;
		
	}//fim do metodo geradorTabuleiro
	

	//METODO QUE RECEBE O TABULEIRO E VERIFICA SE TODAS AS RAINHAS ESTAO CORRETAS
	public static int[] verificaTabuleiro( int  tab[] ){

		int tabuleiro[] = tab;

		for(int i=1;i<8;i++) {
			
			int cont=1;
		
			while(i+cont<8) {
					if(tabuleiro[i]==0) {break;}
				
					if(tabuleiro[i]==(tabuleiro[i+cont]-cont )) {
						tabuleiro[i+cont]=0;
						}
					cont++;
				}//fim do while
		
				cont=1;
				while(i+cont>8) {
			
					if(tabuleiro[i]==0) {break;}
			
					if(tabuleiro[i]==tabuleiro[i+cont]+cont)
						tabuleiro[i+cont]=0;
					cont--;
		}//fim do while
		
		}//fim do for 
		
		return tabuleiro;//retorna o vetor verificado 
		
}//fim do metodo verificaTabuleiro
	

// METODO QUE RECEBE O VETOR UNIDIMENSIONAL QUE TRANSFORMA EM FORMA DE MATRIZ E IMPRIME NA TELA
	public static void printTabuleiro(int tab[]) {
	
	int tabuleiroUnidimensional[]=tab; 
	int tabuleiroMatriz[][]= new int[8][8];
	
	for(int a=0;a<8;a++) {
		int aux = tabuleiroUnidimensional[a];
		
		//tratamento de erro pois o vetor nao pode ter indice menor que zero acessado, senao ele quebra.
		if(aux ==0) {
			tabuleiroMatriz[aux][a] = 0;
		}
		//aqui ele coloca a rainha (o 1 no caso), na matriz que representa o tabuleiro.
		else {
			tabuleiroMatriz[aux-1][a] = 1;
		}
		
		
	}
	
	for(int i=0;i<8;i++) {
		for(int j=0; j<8;j++) {
			System.out.print(" " + tabuleiroMatriz[i][j] + " ");
		}
		System.out.println();
	}
	
}//fim do metodo printTabuleiro

	
// METODO QUE RECEBE O VETOR E VERIFICA QUANTAS RAINHAS EXISTEM NO TABULEIRO
	public static int contadorRainha(int tab[]) {
	int tabuleiro[] = tab;
	int rainha=0;
	for(int i=0;i<8;i++) {
		if(tabuleiro[i]!=0) {
			rainha+=1;
		}
	}
	return rainha;
}//fim do metodo contadorRainha

// METODO QUE RODA A INTERAÇAO DO PROGRAMA DAS RAINHAS 1000 VEZES
	public static void cicloDasRainhas() {

	
	int vitorias=0;
	
	System.out.printf("%10s%20s\n", "Iteração", "Solução Encontrada");
	for(int i=0;i<1000;i++) {
		boolean solucao = false;
		int tabuleiroVazio[]= {1,2,3,4,5,6,7,8};//cria o tabuleiro com as rainhas
		int tabuleiro[] = geradorTabuleiro(tabuleiroVazio);//atraves do metodo gerador de tabuleiro ele geraum tabuleiro aleatorio que ira ser testado
		int tabuleirotestado [] = verificaTabuleiro(tabuleiro);//verifica se todas as rainhas estao em posiçoes validas e caso nao esteja ele retira elas
		int rainha = contadorRainha(tabuleirotestado);//aqui ele testa quantas rainhas conseguiram ficar no tabuleiro
		if(rainha == 8) {solucao = true; vitorias +=1;}
		if(solucao == true) {//caso a solucao seja verdadeiro ele ira dar esse print informando que  conseguiu
			System.out.printf("%10d%10s\n" , i , "Verdadeiro");
		}//fim do if 
		
		else {//caso a solucao seja falso ele ira dar esse print informando que nao conseguiu
			System.out.printf("%10d%10s\n" , i , "Falso");
		}// fim do else
		
	}//fim do for 
	
	System.out.printf("\n%10s%10s\n", "Total", "Soluções");
	System.out.printf("    ---------------------------------\n");
	System.out.printf("%10d%10d\n", 1000, vitorias);
}//fim do metodo cicloDasRainhas

//METODO QUE DEMOSTRA UM CASO ONDE O VETOR POSSUI AS OITO RAINHAS NO TABULEIRO
	public static void solucaoRainha() {
	
	int solucao[] = {2,4,6,8,3,1,7,5}; //esse é uma das sequencias de numeros do vetor que faz asoluçao ser correta
	int tabuleiroTestado [] = verificaTabuleiro(solucao);
	System.out.println("Possivel solução do problema : ");
	printTabuleiro(tabuleiroTestado);
	System.out.println();
	int rainha = contadorRainha(solucao);
	System.out.println( "Numero de rainhas no tabuleiro  : " + rainha);
	System.out.println();
	
}//fim do metodo solucaoRainha

//		METODO MAIN QUE CHAMA OS OUTROS METODOS
	public static void main(String args[]) {
		solucaoRainha();
		System.out.println();
		cicloDasRainhas() ;
		
			}// fim do metodo main 
	
}//fim da classe ProjetoOitoRainhas


















