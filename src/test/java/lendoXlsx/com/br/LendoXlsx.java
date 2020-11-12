package lendoXlsx.com.br;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import metodo.com.br.Excel;

public class LendoXlsx {

	public static void main(String[] args) {
		Excel excel = new Excel();

		List<HashMap<String, String>> dados = new ArrayList<HashMap<String, String>>();
		dados = excel.leArquivoExcel("c:\\planilhas\\planilhaDaAula.xlsx");

		for (HashMap<String, String> dado : dados) {
			System.out.println(dado);
		}

	}

}
