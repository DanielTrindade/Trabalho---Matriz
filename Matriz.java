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
  public void imprimeMatriz(){
    System.out.println("Matriz:");
    for(int i = 0; i < this.getNumLinhas(); i++){
      for(int j = 0; j < this.getNumColunas(); j++){
        System.out.print(this.getElemento(i, j) + " ");
      }
      System.out.println();
    }
  }
}
