package metodo.com.br;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	public List<HashMap<String, String>> leArquivoExcel(String caminhoArquivo){
    	
        File file = new File(caminhoArquivo);
        List<HashMap<String, String>> arrLinhas = new ArrayList<HashMap<String, String>>();
        
        try {
        	
			FileInputStream planilha = new FileInputStream(file);
			
			try {
				// Le workbook (todas as abas da planilha)
				@SuppressWarnings("resource")
				XSSFWorkbook pastaTrabalho = new XSSFWorkbook(planilha);
				XSSFSheet plan1 = pastaTrabalho.getSheetAt(0);
				
				// Retorna todas as linhas da planilha 1
				Iterator<Row> linhas = plan1.iterator();
				int linhaAtual = 0;
				int totalColunas = plan1.getRow(linhaAtual).getPhysicalNumberOfCells();
				int totalLinhas = plan1.getPhysicalNumberOfRows()-1; //Conta linhas preenchidas menos cabeçalho
				String[] nomeColunas = new String[totalColunas];
				
				while (linhas.hasNext()) {
					
					int colunaAtual = 0;
					// Pega cada linha da planilha
					Row linha = linhas.next();
					// Pega todas as celulas da linha
					Iterator<Cell> celulas = linha.iterator();
				
			        HashMap<String, String> arrLinha = new HashMap<String, String>();
			        
					// Percorre todas as celulas da linha atual
					while (celulas.hasNext()) {
						
						// Pega cada celula da linha
						Cell celula = celulas.next();
						
						if(linhaAtual == 0) {
							nomeColunas[colunaAtual] = celula.getStringCellValue();
						} else {
							arrLinha.put(nomeColunas[colunaAtual], celula.getStringCellValue());
						}
						
						colunaAtual++;
					}
					
					if(linhaAtual > 0) {
						arrLinhas.add(arrLinha);
					}
					
					linhaAtual++;
					
					if(linhaAtual > totalLinhas) {
						break;
					}
				}
				
			} catch (IOException e) {
				System.out.println("Não foi possível abrir o workbook");
			}
			
		} catch (FileNotFoundException e) {
			file =  null;
			System.out.println("Não foi possível abrir a planilha " + caminhoArquivo);
		}
        
        return arrLinhas;
	}
}