package models;

public class Util {
	public static String removeCharDaString(String str, int posicao) {
		StringBuilder sb = new StringBuilder(str);
		sb.deleteCharAt(posicao);
		return sb.toString();
	}
	public static int leNumeroEmString(String str) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			sb.append(str.charAt(i));
		}
		return Integer.parseInt(sb.toString());		
	}
	public static Livro converterStringEmLivro(String livro_str) {
		Livro livro = new Livro();
		String[] parts = livro_str.split(",");
		livro.setCodigo(Util.leNumeroEmString(parts[0]));
		livro.setTitulo(parts[1]);
		livro.setAutor(parts[2]);
		return livro;
	}
}
