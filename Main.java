import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
  public static void main(String[] args) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.txt", true))) {
          for (int i = 3; i <= 11; i += 2) {
              for (int j = 0; j < 3; j++) {
                  MatrizQuadrada mat = new MatrizQuadrada(i);
                  MatrizQuadrada mat2 = new MatrizQuadrada(i);
                  MatrizQuadrada mat3 = new MatrizQuadrada(i);
                  mat.inicializaRandomico();
                  writer.write("Medida para a Matriz Quadrada de Ordem " + i + ": " + (j + 1) + "\n");
                  mat.imprimeMatriz(writer);
                  mat2.copiarValores(mat);
                  mat3.copiarValores(mat);
                  mat2.imprimirEstatisticasDeterminanteV1(writer);
                  mat3.imprimirEstatisticasDeterminanteV2(writer);
              }
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}