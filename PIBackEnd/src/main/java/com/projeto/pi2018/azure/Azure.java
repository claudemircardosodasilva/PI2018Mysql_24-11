package com.projeto.pi2018.azure;

import java.util.List;

import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.projeto.pi2018.azure.model.Identify;
import com.projeto.pi2018.http.ChamadaHttp;
import com.projeto.pi2018.http.ChamadaHttpBinaryData;

public class Azure {
	
	private String endPoint = "https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/persongroups/";
	private String personGroupId = "001";
	
	/*O método reconhecerFace() faz um post na azure para identificar uma face na imagem passada no body
	 *  ele retorna o faceId ou uma string erro
	*/

	public  String  reconhecerFace(String foto) throws Exception, Exception {
		String endPoint2 = "https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceId=true";
		ChamadaHttpBinaryData http = new ChamadaHttpBinaryData();
		String faceId = http.chamada(endPoint2,foto);
		
		if(faceId.length()>2 && faceId.length()>36) {
			faceId = faceId.substring(12,48);
			System.out.println(faceId);
			return faceId;   
		}
		
		faceId = "Erro";
		return faceId;     
         
	}

	/*O método criarPersonGruop() faz um post na azure para criar um personGruop
	 *  esse método é funcional, porém não vimos a necessidade de usar no nosso projeto
	 *  usamos um personGroupId fixo em uma string no inicio dessa classe
	*/
	public  String  criarPersonGruop() throws Exception, Exception {
		String body ="{\"name\":\"GrupoPI\"}";
		endPoint = endPoint+personGroupId;
		ChamadaHttp http = new ChamadaHttp();
        String jsonString = EntityUtils.toString(http.chamada(endPoint,body)).trim();

       return jsonString;      
       
	}
	/*O método criarPerson() faz um post na azure para criar uma person
	 *  esse método é chamado todas as vezes que é cadastrado uma nova pessoa 
	 *  ele retorna um personId que é setado na pessoa e armazenado no banco
	 *  para um futuro reconhecimento da pessoa
	*/
	public String criarPerson(String nome) throws Exception, Exception, Exception{
		String body ="{\"name\":\""+nome+"\"}";
		endPoint = endPoint+personGroupId+"/persons";
		ChamadaHttp http = new ChamadaHttp();
		String personId = EntityUtils.toString(http.chamada(endPoint,body)).substring(13, 49);
		return personId;   
	}
	/*O método addFace() faz um post na azure para adcionar uma foto em uma person utilizando o personId como chave
	 *  esse método é chamado todas as vezes que é cadastrado uma nova pessoa e depois que a pessoa já está identificada 
	*/
	public void addFace(String pernsonId,String faceId)throws Exception, Exception, Exception {
		endPoint = endPoint+personGroupId+"/persons/"+pernsonId+"/persistedFaces";
		ChamadaHttpBinaryData http = new ChamadaHttpBinaryData();
		String jsonString = http.chamada(endPoint,faceId);
		System.out.println(jsonString);  
	}
	/*O método train() faz um post na azure para reconhecer os pontos da face de uma person para azure identificar
	 *  esse método é chamado todas as vezes que é cadastrado uma nova pessoa e depois que a pessoa já está identificada 
	*/
	public void train()throws Exception, Exception, Exception {
		endPoint = endPoint+personGroupId+"/train";
		ChamadaHttp http = new ChamadaHttp();		
		http.chamada(endPoint,"");
	}
	/*O método identify() faz um post na azure para identificar a face que foi enviada por parametro
	 *  esse método retorna o atributo da classe pessoa chamado confidence com a % entre 0 e 1 de chance de ser a pessoa. 
	*/
	public String identify(String faceId) throws Exception, Exception, Exception {
		String endpoint3="https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/identify";
		String body ="{\"personGroupId\": \"001\",\"faceIds\":[\""+faceId+"\"]}";
		ChamadaHttp http = new ChamadaHttp();
		System.out.println(faceId);
		String identify = EntityUtils.toString(http.chamada(endpoint3,body));
			
		return identify;   
	}

}
