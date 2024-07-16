public class Main {
  public static void main(String[] args) {
    MatrizQuadrada mat = new MatrizQuadrada(4);
    mat.inicializaRandomico();
    mat.imprimeMatriz();
    System.out.println("O determinante da matriz é: " + mat.detLaPlace());
  }
}
