public class MatrizQuadrada extends Matriz {
  MatrizQuadrada() {
    super(4, 4);
  }
  MatrizQuadrada(int ordem) {
    super(ordem, ordem);
  }
  int getOrdem() {
    return this.getNumLinhas();
  }
}
