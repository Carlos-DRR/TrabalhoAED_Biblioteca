package models;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Scanner;

public class FileHandler {
	private String path;
	private File file;
	public FileHandler(String path) {
		this.path = path;
		try {
			File arquivo = new File(this.path);
			Scanner sc = new Scanner(arquivo);
			sc.close();
			this.file = arquivo;
		}catch(FileNotFoundException e) {
			System.out.println("Um erro ocorreu ao abrir o arquivo");
			e.printStackTrace();
		}
	}
	public void mudarPath(String newPath) {
		this.path = newPath;
	}
	public File getFile() {
		return this.file;
	}
	public String getPath() {
		return this.path;
	}
	public Lista<Lista<Lista<Livro>>> carregarArquivoTxtMemoria() {

		Lista<Lista<Lista<Livro>>> estantes = new Lista<Lista<Lista<Livro>>>(-1);
		try {
			Scanner sc = new Scanner(this.file);
			String str;
			//list<Integer> list1 = new list<>();
			Lista<Lista<Livro>> estante = null;
			Lista <Livro> prateleiras = null;
			int idEstante = 0;
			int idPrateleira = 0;
			
			while(sc.hasNextLine()) {
				str = sc.nextLine();
				
				if(str.charAt(0) == 'E') {
					str = Util.removeCharDaString(str, 0);
					idEstante = Util.leNumeroEmString(str);
					if(estantes.getObjeto(idEstante) == null) {
						estante = new Lista<Lista<Livro>>(idEstante);
					} 
					else estante = estantes.getObjeto(idEstante);
					estantes.insereOrdenado(estante);
					

				}else if(str.charAt(0) == 'P') {
					str = Util.removeCharDaString(str, 0);
					idPrateleira = Util.leNumeroEmString(str);
					if(estante.getObjeto(idPrateleira) == null) prateleiras = new Lista<Livro>(idPrateleira);
					else prateleiras = estante.getObjeto(idPrateleira);
					estante.insereOrdenado(prateleiras);
					
				} else {
					Livro livro = Util.converterStringEmLivro(str);
					livro.setEstante(idEstante);
					livro.setPrateleira(idPrateleira);
					prateleiras.insereOrdenado(livro);
				}
				
			}
			
			sc.close();
			return estantes;
		}catch(FileNotFoundException e) {
			System.out.println("Um erro ocorreu ao abrir o arquivo e carregar o txt para memoria");
			e.printStackTrace();	
		}

		return null;
	}
	//passar o objeto por parâmetro para salvar aqui
	public boolean salvarArquivoBinario(String output_name, Object dado) {

		File file = new File(output_name);
		
		try(FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);){
			oos.writeObject(dado);
			oos.close();
			return true;
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existeArquivo(String input_name) {
		File file = new File(input_name);
		if (file.exists() && !file.isDirectory()) return true;
		return false;
	}

	public Object lerArquivoBinario(String input_name) {
		File file = new File(input_name);		
		try(FileInputStream fis = new FileInputStream(file);
				ObjectInputStream oos = new ObjectInputStream(fis);){
			try {
				return oos.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			oos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
