package br.assis.ufpi.seguranca.quebrahash;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class QuebraSenha {
	
	public String quebrarSenha(String stringMatriculaNome, String hash){
		String[] matriculaComNome = stringMatriculaNome.split("");
		String senhaQuebrada = "";
		boolean encontrada = false;
		for (int i = 0; i < matriculaComNome.length; i++) {
			for (int j = 0; j < matriculaComNome.length; j++) {
				for (int j2 = 0; j2 < matriculaComNome.length; j2++) {
					for (int k = 0; k < matriculaComNome.length; k++) {
						senhaQuebrada = "";
						senhaQuebrada += matriculaComNome[i] + matriculaComNome[j] + matriculaComNome[j2] + matriculaComNome[k];
						if(Hashing.sha1().hashString(senhaQuebrada, Charsets.UTF_8).toString().equals(hash)){
							i=100;j=100;k=100;j2=100;
							encontrada = true;
						}
					}
				}
			}
		}
		
		if(encontrada)
			return senhaQuebrada;
		else
			return "--------";
	}

}
