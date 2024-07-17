import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

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

	public void inicializaRandomico(){
		int contC,contL,entrada;
		Random gerador = new Random();
		System.out.println("inicializando matriz quadrada");
		System.out.println("ordem: " + this.getOrdem());
		System.out.println("gerando valores entre 0 e " + (this.getOrdem() * this.getOrdem()));
		for(contL = 0; contL < this.getNumLinhas(); contL++){
			for(contC = 0; contC < this.getNumColunas(); contC++){
				entrada = gerador.nextInt((this.getNumLinhas() * this.getNumColunas()));
				this.setElemento(contL,contC,entrada);
			}
		}
	}

	private int contarZerosEmLinha(int linha) {
    int zeros = 0;
    for (int i = 0; i < this.getOrdem(); i++) {
        if (this.getElemento(linha, i) == 0) {
            zeros++;
        }
    }
    return zeros;
}

private int contarZerosEmColuna(int coluna) {
    int zeros = 0;
    for (int i = 0; i < this.getOrdem(); i++) {
        if (this.getElemento(i, coluna) == 0) {
            zeros++;
        }
    }
    return zeros;
}


  public void copiaMatrizGrandeParaMatrizPequena(MatrizQuadrada matGrande, MatrizQuadrada matPequena, int linhaProibida, int colunaProibida){
		int contLG,contCG,contLP,contCP;
		contLP = 0;	
		for(contLG =0; contLG < matGrande.getOrdem(); contLG++){
			if(contLG != linhaProibida){
				contCP = 0;
				for(contCG =0; contCG < matGrande.getOrdem(); contCG++){
					if(contCG != colunaProibida){	
								matPequena.setElemento(contLP,contCP,matGrande.getElemento(contLG, contCG));
						contCP++;
					}
				}
				contLP++;
			}
		}
	}
	
	//implementação recursiva sem determinar a linha ou coluna com a maior
	//quantidade de zeros
	public float detLaPlaceRecursivo(MatrizQuadrada mat){
		MatrizQuadrada matLinha = new MatrizQuadrada(mat.getOrdem()-1);	
		float acum = 0.0f;
		int linha = 0;
		int contColuna;
		for(contColuna = 0; contColuna < mat.getOrdem(); contColuna++){	
			matLinha.copiaMatrizGrandeParaMatrizPequena(mat,matLinha,linha,contColuna);
			acum = acum +
			      (float)( mat.getElemento(linha, contColuna) *
			        Math.pow(-1, linha + contColuna) *
			        detLaPlace(matLinha));
		}	
		return acum;	
	}

	//implementação recursiva agora usando a linha ou coluna com a maior
	//quantidade de zeros
	public float detLaPlaceRecursivoV2(MatrizQuadrada mat) {
		MatrizQuadrada matLinha = new MatrizQuadrada(mat.getOrdem()-1);	
		float acum = 0.0f;
		int contColuna, contLinha;
		int linhaComMaisZeros = this.linhaComMaisZeros();
    int colunaComMaisZeros = this.colunaComMaisZeros();
    int zerosLinha = this.contarZerosEmLinha(linhaComMaisZeros);
		int zerosColuna = this.contarZerosEmColuna(colunaComMaisZeros);
    if(zerosColuna > zerosLinha){
      for(contLinha = 0; contLinha < mat.getOrdem(); contLinha++){	
				matLinha.copiaMatrizGrandeParaMatrizPequena(mat,matLinha,contLinha,colunaComMaisZeros);
				acum = acum +
							(float)( mat.getElemento(contLinha, colunaComMaisZeros) *
								Math.pow(-1, contLinha + colunaComMaisZeros) *
								detLaPlace(matLinha));
			}	
    }
    else {
      for(contColuna = 0; contColuna < mat.getOrdem(); contColuna++){	
				matLinha.copiaMatrizGrandeParaMatrizPequena(mat,matLinha,linhaComMaisZeros,contColuna);
				acum = acum +
							(float)( mat.getElemento(linhaComMaisZeros, contColuna) *
								Math.pow(-1, linhaComMaisZeros + contColuna) *
								detLaPlace(matLinha));
			}	
    }
		
		return acum;	
	}
	
	public float detLaPlace(MatrizQuadrada mat){
		float resultado = 0;
	
		if(mat.getOrdem() == 1){
			resultado = mat.getElemento(0,0);
		}
		else{
			resultado = mat.detLaPlaceRecursivo(mat);
		}
		
		return resultado;
	}

	public float detLaPlaceV2(MatrizQuadrada mat){
		float resultado = 0;
	
		if(mat.getOrdem() == 1){
			resultado = mat.getElemento(0,0);
		}
		else{
			resultado = mat.detLaPlaceRecursivoV2(mat);
		}
		
		return resultado;
	}

	public void imprimirEstatisticasDeterminanteV1(BufferedWriter writer) throws IOException {
        long startTime = System.nanoTime();
        double determinante = this.detLaPlace();
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        String resultado = "O determinante da matriz é: " + determinante + "\n" +
                           "Tempo total em nanosegundos para a versão 1: " + totalTime + " ns\n";

        writer.write(resultado);
    }

    public void imprimirEstatisticasDeterminanteV2(BufferedWriter writer) throws IOException {
        long startTime2 = System.nanoTime();
        double determinante = this.detLaPlaceV2();
        long endTime2 = System.nanoTime();
        long totalTime2 = endTime2 - startTime2;
        String resultado = "O determinante da matriz é: " + determinante + "\n" +
                           "Tempo total em nanosegundos para a versão 2: " + totalTime2 + " ns\n";

        writer.write(resultado);
    }
	
	public float detLaPlace(){
		return this.detLaPlace(this);
	}

	public float detLaPlaceV2(){
		return this.detLaPlaceV2(this);
	}
}
