import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
  public static void main(String[] args) {
    int ordem, repeticoes;
    float totalTime1 = 0;
    float totalTime2 = 0;
      try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.txt", true))) {
          for (ordem = 3; ordem <= 11; ordem += 2) {
            for (repeticoes = 0; repeticoes < 3; repeticoes++) {
                Matriz mat = new Matriz(ordem);
                Matriz mat2 = new Matriz(ordem);
                Matriz mat3 = new Matriz(ordem);
                mat.inicializaRandomico();
                mat.imprime();
                writer.write("Medida para a Matriz Quadrada de Ordem " + ordem + ": " + (repeticoes + 1) + "\n");
                mat.imprimeMatriz(writer);
                mat2.copiarValores(mat);
                mat3.copiarValores(mat);
                totalTime1 += mat2.imprimirEstatisticasDeterminanteV1(writer);
                totalTime2 += mat3.imprimirEstatisticasDeterminanteV2(writer);
                writer.write("\n");
            }
            //colocar a media no arquivo de cada versao
            writer.write("Tempo medio versão 1: " + totalTime1 / 3 + " ns\n");
            writer.write("Tempo medio versão 2: " + totalTime2 / 3 + " ns\n\n");
            totalTime1 = 0;
            totalTime2 = 0;
          }
      } catch (IOException e) {
          e.printStackTrace();
      }

  }
}