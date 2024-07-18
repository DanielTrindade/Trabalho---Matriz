import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class MatrizQuadrada extends Matriz {
	//estatisticas sobre a matriz
	private int linhaComMaisZeros;
	private int colunaComMaisZeros;
	private int zerosLinha;
	private int zerosColuna;
  MatrizQuadrada() {
    super(4, 4);
  }
  MatrizQuadrada(int ordem) {
    super(ordem, ordem);
  }
  int getOrdem() {
    return this.getNumLinhas();
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

	public void inicializaRandomico(){
		int contC,contL,entrada;
		Random gerador = new Random();
		for(contL = 0; contL < this.getNumLinhas(); contL++){
			for(contC = 0; contC < this.getNumColunas(); contC++){
				entrada = gerador.nextInt((this.getNumLinhas() * this.getNumColunas()));
				this.setElemento(contL,contC,entrada);
			}
		}
	}

	private void linhaEColunaComMaisZeros() {
		int linhaZeros = 0;
		int colunaZeros = 0;
		Vetor zerosPorLinha = new Vetor(this.getOrdem());
		Vetor zerosPorColuna = new Vetor(this.getOrdem());
		for(int i = 0; i < this.getOrdem(); i++) {
			for(int j = 0; j < this.getOrdem(); j++) {
				if(this.getElemento(i, j) == 0) {
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
		this.linhaEColunaComMaisZeros();
		int linhaComMaisZeros = this.getLinhaComMaisZeros();
    int colunaComMaisZeros = this.getColunaComMaisZeros();
    int zerosLinha = this.getZerosLinha();
		int zerosColuna = this.getZerosColuna();
    if(zerosColuna > zerosLinha) {
			System.out.println("A coluna escolhida foi " + colunaComMaisZeros + " com " + zerosColuna + " zeros");
			for(contLinha = 0; contLinha < mat.getOrdem(); contLinha++) {	
					if (mat.getElemento(contLinha, colunaComMaisZeros) != 0) { 
							matLinha.copiaMatrizGrandeParaMatrizPequena(mat, matLinha, contLinha, colunaComMaisZeros);
							acum = acum + (float)(mat.getElemento(contLinha, colunaComMaisZeros) * 
																		Math.pow(-1, contLinha + colunaComMaisZeros) * 
																		detLaPlace(matLinha));
					}
			}	
		} 
		else {
			System.out.println("A linha escolhida foi " + linhaComMaisZeros + " com " + zerosLinha + " zeros");
			for(contColuna = 0; contColuna < mat.getOrdem(); contColuna++) {	
					if (mat.getElemento(linhaComMaisZeros, contColuna) != 0) { 
							matLinha.copiaMatrizGrandeParaMatrizPequena(mat, matLinha, linhaComMaisZeros, contColuna);
							acum = acum + (float)(mat.getElemento(linhaComMaisZeros, contColuna) * 
																		Math.pow(-1, linhaComMaisZeros + contColuna) * 
																		detLaPlace(matLinha));
					}
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
                           "Tempo total em nanosegundos para a versão 2: " + totalTime2 + " ns\n\n";

        writer.write(resultado);
    }
	
	public float detLaPlace(){
		return this.detLaPlace(this);
	}

	public float detLaPlaceV2(){
		return this.detLaPlaceV2(this);
	}
}
