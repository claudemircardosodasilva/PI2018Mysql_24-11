import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { PessoaDTO } from '../../models/pessoa.dto';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AppModule } from '../../app/app.module';


/**
 * Generated class for the DadosCadastraisPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-dados-cadastrais',
  templateUrl: 'dados-cadastrais.html',
})
export class DadosCadastraisPage {
  foto: string;

  constructor(public navCtrl: NavController, public navParams: NavParams,private camera:Camera,public http : HttpClient) {
  
  }
  private ip: string = AppModule.getIp();
  
  pessoa : PessoaDTO={
    fotoTemp:this.navParams.get('fotoTemp'),
    confidence:this.navParams.get('confidence'),
    nome : this.navParams.get('nome'), 
    rg:this.navParams.get('rg'),
    cpf : this.navParams.get('cpf'),
    email : this.navParams.get('email'),
    telefone : this.navParams.get('telefone'),
    endereco : {
      cep : this.navParams.get('cep'), 
      rua : this.navParams.get('rua'),
      numeroCasa:this.navParams.get('numeroCasa'),
      bairro : this.navParams.get('bairro'),
      cidade : this.navParams.get('cidade'),
      estado : this.navParams.get('estado'),
      pais : this.navParams.get('pais')
    }
  }

  ionViewDidLoad() {
    let base64image = 'data:image/jpeg;base64,'+this.pessoa.fotoTemp;
    this.foto = base64image;
  }
 


  tirarFoto(){
    
    const options: CameraOptions={
      quality:100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      targetWidth:100,
      targetHeight:100

    }
    this.camera.getPicture(options).then((ImageData) =>{
          this.pessoa.fotoTemp = ImageData;
          let data: Observable<any>;
          data = this.http.post(`http://${this.ip}:8080/pessoa/detectFace`,this.pessoa);
          data.subscribe(result =>{
           if(result == true){
              alert("Foto cadastrada com sucesso!!!");
           }
           else{
             alert("Erro, Tire outra foto");
           }
          });
      
    },(error)=>{
      alert("Ops!!! ocorreu um erro");
    })
  }
}


