public class Vetor {
  private int[] vet;
  private int tamanho;
  Vetor(int novoTamanho) {
    this.setTamanho(novoTamanho);
    vet = new int[tamanho];
  }

  public void setTamanho(int novoTamanho) {
    this.tamanho = novoTamanho;
  }

  public int getTamanho() {
    return tamanho;
  }

  public void setElemento(int index, int novoValor) {
    this.vet[index] = novoValor;
  }

  public int getElemento(int index) {
    return this.vet[index];
  }

  //método para contar a quantidade de zeros
  public int countZeros() {
    int count = 0;
    for (int i = 0; i < this.getTamanho(); i++) {
      if(this.getElemento(i) == 0) {
        count++;
      }
    }
    return count;
  }
  //método que retorna a posição do maior elemento
  public int getPosMax() {
    int posMax = 0;
    for(int i = 1; i < this.getTamanho(); i++) {
      if(this.getElemento(i) > this.getElemento(posMax)) {
        posMax = i;
      }
    }
    return posMax;
  }
}