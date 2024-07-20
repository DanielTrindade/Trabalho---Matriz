import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

class Matriz{
	private int[][] mat;
	private int tamLinha;
	private int tamColuna;
	private int linhaComMaisZeros;
	private int colunaComMaisZeros;
	private int zerosLinha;
	private int zerosColuna;

	Matriz(){
		mat = new int[6][6];
		this.setTamanhoLinha(6);	
		this.setTamanhoColuna(6);
	}
	Matriz(int ordem) {
		this.setTamanhoLinha(ordem);
		this.setTamanhoColuna(ordem);
		mat = new int[this.getTamanhoLinha()][this.getTamanhoColuna()];
	}

	Matriz(int numLinhas, int numColunas){
		this.setTamanhoLinha(numLinhas);	
		this.setTamanhoColuna(numColunas);
		mat = new int[this.getTamanhoLinha()][this.getTamanhoColuna()];
	}


	public int getValor(int indiceI,int indiceJ){
		return mat[indiceI][indiceJ];
	}	
	
	public void setValor(int indiceI,int indiceJ, int novoValor){
		mat[indiceI][indiceJ] = novoValor;
	}

	public int getTamanhoLinha(){
		return tamLinha;
	}	
	
	public int getTamanhoColuna(){
		return tamColuna;
	}	

	private void setTamanhoLinha(int novoValor){
		tamLinha = novoValor;
	}

	private void setTamanhoColuna(int novoValor){
		tamColuna = novoValor;
	}

	public void setLinhaComMaisZeros(int novaLinhaComMaisZeros) {
    this.linhaComMaisZeros = novaLinhaComMaisZeros;
  }

  public int getLinhaComMaisZeros() {
    return linhaComMaisZeros;
  }

  public void setColunaComMaisZeros(int novaColunaComMaisZeros) {
    this.colunaComMaisZeros = novaColunaComMaisZeros;
  }

  public int getColunaComMaisZeros() {
    return colunaComMaisZeros;
  }

  public void setZerosLinha(int novosZerosLinha) {
    this.zerosLinha = novosZerosLinha;
  }

  public int getZerosLinha() {
    return zerosLinha;
  }

  public void setZerosColuna(int novosZerosColuna) {
    this.zerosColuna = novosZerosColuna;
  }

  public int getZerosColuna() {
    return zerosColuna;
  }


	public void imprime(){
		int conti,contj;	
		for(conti = 0; conti < this.getTamanhoLinha(); conti++){
			System.out.println();
			for(contj = 0; contj < this.getTamanhoColuna(); contj++){
				System.out.print(this.getValor(conti,contj) + " ");
			}
		}
		System.out.println();	
	}

	public void imprimeMatriz(BufferedWriter writer) throws IOException {
		int conti,contj;
    writer.write("Matriz usada para a determinante:\n");
    for(conti = 0; conti < this.getTamanhoLinha(); conti++){
      for(contj = 0; contj < this.getTamanhoColuna(); contj++){
        writer.write(this.getValor(conti, contj) + " ");
      }
      writer.write("\n");
    }
  }

	public void inicializaRandomico(){
		int conti,contj, novoValor;
		int ordem = this.getTamanhoLinha();
		Random gerador = new Random();
		for(conti = 0; conti < this.getTamanhoLinha(); conti++){
			for(contj = 0; contj < this.getTamanhoColuna(); contj++){
				novoValor = gerador.nextInt(ordem * ordem);
				this.setValor(conti,contj,novoValor);
			}
		}
	}

	public void copiarValores(Matriz mat){
		this.setTamanhoLinha(mat.getTamanhoLinha());
		this.setTamanhoColuna(mat.getTamanhoColuna());
		for(int i = 0; i < this.getTamanhoLinha(); i++){
			for(int j = 0; j < this.getTamanhoColuna(); j++){
				this.setValor(i,j,mat.getValor(i,j));
			}
		}
	}

	private void linhaEColunaComMaisZeros() {
		int linhaZeros = 0;
		int colunaZeros = 0;
		Vetor zerosPorLinha = new Vetor(this.getTamanhoLinha());
		Vetor zerosPorColuna = new Vetor(this.getTamanhoColuna());
		for(int i = 0; i < this.getTamanhoLinha(); i++) {
			for(int j = 0; j < this.getTamanhoColuna(); j++) {
				if(this.getValor(i, j) == 0) {
					zerosPorLinha.setElemento(i, zerosPorLinha.getElemento(i) + 1);
					zerosPorColuna.setElemento(j, zerosPorColuna.getElemento(j) + 1);
				}
			}
		}
		linhaZeros = zerosPorLinha.getPosMax();
		colunaZeros = zerosPorColuna.getPosMax();
		this.setLinhaComMaisZeros(linhaZeros);
		this.setColunaComMaisZeros(colunaZeros);
		this.setZerosLinha(zerosPorLinha.getElemento(linhaZeros));
		this.setZerosColuna(zerosPorColuna.getElemento(colunaZeros));
	}

	// caso matriz nao quadrada, retorna -1
	public int retorneOrdem(){
		int numL, numC, ordem;
		numL = this.getTamanhoLinha();
		numC = this.getTamanhoColuna();
		ordem = -1;
		if(numL == numC){
			ordem = numL;
		}
		return ordem;
	}	

	private int detOrdem1(Matriz mat){
		return mat.getValor(0,0);
	}
	
	private int detOrdem2(Matriz mat){
		int diagonalP, diagonalI;
		diagonalP = mat.getValor(0,0) * mat.getValor(1,1);		
		diagonalI = mat.getValor(1,0) * mat.getValor(0,1);		
		return (diagonalP - diagonalI);
	}

	private int calculaSinal(int indiceL, int indiceC){
		int sinal;
		sinal = -1;
		if( ((indiceL + indiceC)% 2) == 0 ){
			sinal = 1;
		}
		return sinal;		
	}

	public void copiaMatrizMaiorParaMenor(Matriz maior,Matriz menor,int isqn,int jsqn){
		int contAi,contAj,contBi,contBj,temp,numL,numC;
		numL = menor.getTamanhoLinha();
		numC = menor.getTamanhoColuna();
		contAi = 0;
		for(contBi = 0; contBi < numL; contBi++){
			if(contAi == isqn){
				contAi++;
			}
			contAj = 0;
			for(contBj = 0; contBj < numC; contBj++){
				if(contAj == jsqn){
					contAj++;
				}
				temp = maior.getValor(contAi,contAj);
				menor.setValor(contBi,contBj,temp);
				contAj++;
			}
			contAi++;
		}
	}

	private int detOrdemN(Matriz mat){
		int sinal,cofator,contC,numL,numC;
		int resposta = 0;
		int detTemp;
		Matriz matmenor;
		numL = mat.getTamanhoLinha();
		numC = mat.getTamanhoColuna();
		
		for(contC = 0; contC < mat.getTamanhoColuna(); contC++){
			cofator = mat.getValor(0,contC);
			sinal = this.calculaSinal(0,contC);
			matmenor = new Matriz(numL - 1, numC - 1);
			this.copiaMatrizMaiorParaMenor(mat,matmenor,0,contC);
			detTemp = matmenor.determinante();
			resposta = resposta + (cofator * sinal * detTemp);
		}
		return (resposta);
	}

	private int detOrdemNV2(Matriz mat){
		int sinal,cofator,contC,contL,numL,numC;
		int resposta = 0;
		int detTemp;
		Matriz matmenor;
		numL = mat.getTamanhoLinha();
		numC = mat.getTamanhoColuna();
		mat.linhaEColunaComMaisZeros();
		int linhaComMaisZeros = mat.getLinhaComMaisZeros();
    int colunaComMaisZeros = mat.getColunaComMaisZeros();
    int zerosLinha = mat.getZerosLinha();
		int zerosColuna = mat.getZerosColuna();
		if(zerosColuna > zerosLinha) {
			for(contL = 0; contL < mat.getTamanhoColuna(); contL++){
				cofator = mat.getValor(contL, colunaComMaisZeros);
				if(cofator != 0){
					sinal = this.calculaSinal(contL, colunaComMaisZeros);
					matmenor = new Matriz(numL - 1,numC - 1);
					this.copiaMatrizMaiorParaMenor(mat,matmenor,contL,colunaComMaisZeros);
					detTemp = matmenor.determinanteV2();
					resposta = resposta + (cofator * sinal * detTemp);
				}
			}
		}
		else {
			for(contC = 0; contC < mat.getTamanhoColuna(); contC++){
				cofator = mat.getValor(linhaComMaisZeros, contC);
				if(cofator != 0){
					sinal = this.calculaSinal(linhaComMaisZeros, contC);
					matmenor = new Matriz(numL - 1,numC - 1);
					this.copiaMatrizMaiorParaMenor(mat, matmenor, linhaComMaisZeros, contC);
					detTemp = matmenor.determinante();
					resposta = resposta + (cofator * sinal * detTemp);
				}
			}
		}
		return (resposta);
	}

	public int determinante(){
		int ordem;
		int det;

		ordem = this.retorneOrdem();
		det = 0;

		if(ordem > 0){
			switch (ordem) {
			    case 1:  det = this.detOrdem1(this);
				     break;
			    case 2:  det = this.detOrdem2(this);
				     break;
			    default: det = this.detOrdemN(this);
				     break;
			}
			
		}
		else{
			System.out.println("Matriz nao eh quadrada!! retornando 0");
		}

		return det;
	}

	public int determinanteV2(){
		int ordem;
		int det;

		ordem = this.retorneOrdem();
		det = 0;

		if(ordem > 0){
			switch (ordem) {
			    case 1:  det = this.detOrdem1(this);
				     break;
			    case 2:  det = this.detOrdem2(this);
				     break;
			    default: det = this.detOrdemNV2(this);
				     break;
			}
			
		}
		else{
			System.out.println("Matriz nao eh quadrada!! retornando 0");
		}

		return det;
	}
	
	public long imprimirEstatisticasDeterminanteV1(BufferedWriter writer) throws IOException {
		long startTime = System.nanoTime();
		int determinante = this.determinante();
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		String resultado = "O determinante da matriz é: " + determinante + "\n" +
											 "Tempo total em nanosegundos para a versão 1: " + totalTime + " ns\n";

		writer.write(resultado);
		return totalTime;
	}

	public long imprimirEstatisticasDeterminanteV2(BufferedWriter writer) throws IOException {
		long startTime2 = System.nanoTime();
		int determinante = this.determinanteV2();
		long endTime2 = System.nanoTime();
		long totalTime2 = endTime2 - startTime2;
		String resultado = "O determinante da matriz é: " + determinante + "\n" +
												"Tempo total em nanosegundos para a versão 2: " + totalTime2 + " ns\n\n";
		writer.write(resultado);
		return totalTime2;
	}
	
}
