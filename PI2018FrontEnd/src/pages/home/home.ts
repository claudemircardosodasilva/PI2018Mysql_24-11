import { Component } from '@angular/core';
import { IonicPage, NavController} from 'ionic-angular';
import { EnderecoPage } from '../endereco/endereco';
import { PessoaDTO } from '../../models/pessoa.dto';
import { HttpClient } from '@angular/common/http';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { Observable } from 'rxjs/Observable';
import { DadosCadastraisPage } from '../dados-cadastrais/dados-cadastrais';
import { AppModule } from '../../app/app.module';

/**
 * Generated class for the HomePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
})
export class HomePage {

  constructor(public navCtrl: NavController,private camera:Camera,public http : HttpClient) {
    
  }
  private ip: string = AppModule.getIp();

  identify(){
    const options: CameraOptions={
      quality:100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      targetWidth:100,
      targetHeight:100

    }
    this.camera.getPicture(options).then((ImageData) =>{
          let data: Observable<any>;
          data = this.http.post(`http://${this.ip}:8080/pessoa/identify`,ImageData);
          data.subscribe(result =>{
            if(result!=null){
              console.log(result.endereco)
              this.navCtrl.push(DadosCadastraisPage,{
                fotoTemp: result.fotoTemp,
                confidence:result.confidence,
                nome : result.nome, 
                rg:result.rg,
                cpf : result.cpf,
                email : result.email,
                telefone : result.telefone,
                cep : result.endereco.cep, 
                numeroCasa: result.endereco.numeroCasa,
                rua : result.endereco.rua,
                bairro : result.endereco.bairro,
                cidade : result.endereco.cidade,
                estado : result.endereco.estado,
                pais : result.endereco.pais
              });
            }
            else{
              alert("Erro, Tente Novamente");
            }
          });
      
    },(error)=>{
      console.error(error);
      alert("Erro, Tente Novamente");
    })
    .catch((error) =>{
      console.error(error);
    })
  }
  
  
  irCadastro(){
   document.getElementById("login").style.display="none";
   document.getElementById("cadastro").style.display="block";
  }
  irLogin(){
    document.getElementById("cadastro").style.display="none";
    document.getElementById("login").style.display="block";
   }
   pessoa : PessoaDTO={
    fotoTemp:"",
    confidence:"",
    nome : "", 
    rg:"",
    cpf : "",
    email : "",
    telefone : "",
    endereco : null
  }
   avancar(){
     this.navCtrl.push(EnderecoPage,{
      nome :this.pessoa.nome, 
      rg:this.pessoa.rg,
      cpf :this.pessoa.cpf,
      email :this.pessoa.email,
      telefone :this.pessoa.telefone
     });
   }
}
