import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { EnderecoPage } from '../pages/endereco/endereco';
import { Camera} from '@ionic-native/camera'
import { HttpClientModule } from '@angular/common/http';
import { DadosCadastraisPage } from '../pages/dados-cadastrais/dados-cadastrais';




@NgModule({
  declarations: [
    MyApp,
    HomePage,
    EnderecoPage,
    DadosCadastraisPage,
    
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpClientModule,

  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    EnderecoPage,
    DadosCadastraisPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    Camera
  ]
})
export class AppModule {
  private static rootPage: any = HomePage;
  static getPage(){
    return this.rootPage;
  }
  private static ip: string ="192.168.0.12";

   static getIp(){
     return this.ip;
   }


}
