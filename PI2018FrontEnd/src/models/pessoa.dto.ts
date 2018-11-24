import { EnderecoDTO } from "./endereco.dto";

export interface PessoaDTO{
    fotoTemp :String
    confidence:String
    nome : string; 
    rg:String
    cpf : string;
    email : string;
    telefone : string;
    endereco : EnderecoDTO;
}