import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { DadosCadastraisPage } from './dados-cadastrais';

@NgModule({
  declarations: [
    DadosCadastraisPage,
  ],
  imports: [
    IonicPageModule.forChild(DadosCadastraisPage),
  ],
})
export class DadosCadastraisPageModule {}
