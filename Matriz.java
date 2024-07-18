import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class Matriz {
  private int[][] mat;
  private int numLinhas;
  private int numColunas;
  Matriz(int novoNumLinhas, int novoNumColunas) {
    this.setNumLinhas(novoNumLinhas);
    this.setNumColunas(novoNumColunas);
    mat = new int[this.getNumLinhas()][this.getNumColunas()];
  }

  public void setNumLinhas (int novoNumLinhas) {
    this.numLinhas = novoNumLinhas;
  }

  public int getNumLinhas() {
    return numLinhas;
  }

  public void setNumColunas (int novoNumColunas) {
    this.numColunas = novoNumColunas;
  }

  public int getNumColunas() {
    return numColunas;
  }

  public void setElemento(int linha, int coluna, int novoValor) {
    this.mat[linha][coluna] = novoValor;
  }

  public int getElemento(int linha, int coluna) {
    return this.mat[linha][coluna];
  }

  public void inicializaRandomico(){
		int contC,contL,entrada;
		Random gerador = new Random();
		for(contL = 0; contL < this.getNumLinhas(); contL++){
			for(contC = 0; contC < this.getNumColunas(); contC++){
				entrada = gerador.nextInt((this.getNumLinhas() * this.getNumColunas()) * 2);
				this.setElemento(contL,contC,entrada);
			}
		}
	}

  public int linhaComMaisZeros() {
    int linhaComMaisZeros = 0;
    Vetor vet = new Vetor(this.getNumLinhas());
    for(int i = 0; i < this.getNumLinhas(); i++) {
      for(int j = 0; j < this.getNumColunas(); j++) {
        if(this.getElemento(i, j) == 0) {
          vet.setElemento(i, vet.getElemento(i) + 1);
        }
      }
    }
    linhaComMaisZeros = vet.getPosMax();
    return linhaComMaisZeros;
  }

  public int colunaComMaisZeros() {
    int colunaComMaisZeros = 0;
    Vetor vet = new Vetor(this.getNumColunas());
    for(int i = 0; i < this.getNumLinhas(); i++) {
      for(int j = 0; j < this.getNumColunas(); j++) {
        if(this.getElemento(i, j) == 0) {
          vet.setElemento(j, vet.getElemento(j) + 1);
        }
      }
    }
    colunaComMaisZeros = vet.getPosMax();
    return colunaComMaisZeros;
  }
  
  //fazer uma função que verifica qual coluna ou linha tem mais zeros
  //dizer se é linha ou coluna
  public void linhaOuColunaComMaisZeros(){
    int linhaComMaisZeros = this.linhaComMaisZeros();
    int colunaComMaisZeros = this.colunaComMaisZeros();
    int zerosLinha = 0;
    int zerosColuna = 0;
    for(int i = 0; i < this.getNumColunas(); i++) {
      if(this.getElemento(linhaComMaisZeros, i) == 0) {
        zerosLinha++;
      }
    }
    for(int i = 0; i < this.getNumLinhas(); i++) {
      if(this.getElemento(i, colunaComMaisZeros) == 0) {
        zerosColuna++;
      }
    }
    if(zerosColuna >= zerosLinha){
      System.out.println("A coluna com mais zeros é: " + colunaComMaisZeros + ", com " + zerosColuna + " zero(s)");
    }
    else {
      System.out.println("A linha com mais zeros é: " + linhaComMaisZeros + ", com " + zerosLinha + " zero(s)");
    }
  }


  public void imprimeMatriz(){
    System.out.println("Matriz:");
    for(int i = 0; i < this.getNumLinhas(); i++){
      for(int j = 0; j < this.getNumColunas(); j++){
        System.out.print(this.getElemento(i, j) + " ");
      }
      System.out.println();
    }
  }

  public void imprimeMatriz(BufferedWriter writer) throws IOException {
    writer.write("Matriz usada para a determinante:\n");
    for(int i = 0; i < this.getNumLinhas(); i++){
      for(int j = 0; j < this.getNumColunas(); j++){
        writer.write(this.getElemento(i, j) + " ");
      }
      writer.write("\n");
    }
  }

  public void copiarValores(Matriz mat) {
    for(int i = 0; i < this.getNumLinhas(); i++) {
      for(int j = 0; j < this.getNumColunas(); j++) {
        this.setElemento(i, j, mat.getElemento(i, j));
      }
    }
  }
}
